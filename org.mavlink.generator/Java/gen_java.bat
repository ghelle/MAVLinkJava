echo on

REM set MAVLink Java generator version
set version=1.00

mkdir generated
copy sources\org.mavlink.generator\generator_readme.txt doc\
copy sources\org.mavlink.library\library_readme.txt doc\

REM Build MAVLink Java generator
cd sources/org.mavlink.generator
call ant clean jar
if errorlevel 1 goto :error
cd ../..

REM Generate jar files for each message definitions and all flags
for %%X in (0.9 1.0) do ( 
	if %%X == 1.0 (set extracrc="true") else (set extracrc="false")
	echo %%X %extracrc%
	mkdir lib\%%X
	for %%Z in (embedded gs) do (
		if %%Z == embedded (set isembedded="true") else (set isembedded="false")
		echo %%Z %isembedded%
		for %%E in (little big) do (
			if %%E == little (set islittle="true") else (set islittle="false")
			echo %%E %islittle%
			echo Generate code for each message definitions %islittle% %isembedded% %extracrc% true
			rmdir /S /Q generated\v%%X
			mkdir generated\v%%X
			java -cp lib/org.mavlink.generator-1.00.jar;lib/org.mavlink.util-1.00.jar org.mavlink.generator.MAVLinkGenerator ..\..\..\message_definitions\v%%X\ generated\v%%X %islittle% %isembedded% %extracrc% true
			if errorlevel 1 goto :error
			cd generated\v%%X
			for /d %%Y in (*) do (
				echo %%X  %%Y
				echo Remove old files for %%X and %%Y
				rmdir /S /Q  ..\..\sources\org.mavlink.library\generated\org
				echo Copy new files generated\v%%X\%%Y\
				xcopy /S /Y %%Y\* ..\..\sources\org.mavlink.library\generated\
				if errorlevel 1 goto :error
				echo Go to build
				cd ..\..\sources\org.mavlink.library
				echo BUILD
				call ant clean jar
				if errorlevel 1 goto :error
				echo GENERATE ..\..\lib\%%X\org.mavlink.library-%%E-%%Z-%%X-%version%.jar
				move ..\..\lib\org.mavlink.library-%version%.jar ..\..\lib\%%X\org.mavlink.library-%%Y-%%X-%%E-%%Z-%version%.jar
				if errorlevel 1 goto :error
				cd ..\..\generated\v%%X
			)
			cd ..\..
		)
	)
)
rmdir /S /Q generated
goto :eof

:error
echo ERROR %errorlevel%


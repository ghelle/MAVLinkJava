echo off

REM Create directory for distribution
rmdir /S /Q ..\distrib
mkdir ..\distrib
mkdir ..\distrib\Java

xcopy /E /Y Java\* ..\distrib\Java
if errorlevel 1 goto :error

mkdir ..\distrib\Java\sources\org.mavlink.generator
xcopy /E /Y ..\org.mavlink.generator\* ..\distrib\Java\sources\org.mavlink.generator
if errorlevel 1 goto :error

mkdir ..\distrib\Java\sources\org.mavlink.library
xcopy /E /Y ..\org.mavlink.library\* ..\distrib\Java\sources\org.mavlink.library
if errorlevel 1 goto :error

mkdir ..\distrib\Java\sources\org.mavlink.util
xcopy /E /Y ..\org.mavlink.util\* ..\distrib\Java\sources\org.mavlink.util
if errorlevel 1 goto :error

mkdir ..\distrib\Java\sources\org.mavlink.maven
xcopy /E /Y ..\org.mavlink.maven\* ..\distrib\Java\sources\org.mavlink.maven
if errorlevel 1 goto :error


goto :eof

:error
echo ERROR %errorlevel%


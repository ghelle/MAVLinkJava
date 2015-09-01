#!/bin/sh

# Set MAVLink Java generator version
mavlink_version=1.00

cp sources/org.mavlink.generator/generator_readme.txt doc
cp sources/org.mavlink.library/library_readme.txt doc

# Build MAVLink Java generator
cd sources/org.mavlink.generator
ant clean jar
cd ../..

# Generate jar files for each message definitions and all flags
for protocol in 0.9 1.0; do
    if [[ $protocol = "1.0" ]];
	then
	 extracrc="true" 
    else 
	 extracrc="false"
    fi
    echo "extracrc = " $extracrc
    mkdir -p lib/$protocol

    for mode in embedded gs; do
	if [[ $mode = "embedded" ]];
	    then
	    isEmbedded="true" 
	else 
	    isEmbedded="false"
	fi
	echo "isEmbedded = " $isEmbedded

	for endian in little big; do	
	    if [[ $endian = "little" ]];
		then
		isLittle="true" 
	    else 
		isLittle="false"
	    fi
	    echo "isLittle = " $isLittle

	    echo "Generate code for each message definitions Little="$isLittle "Embedded="$isEmbedded "ExtraCRC="$extracrc true

	    rm -rf generated/v$protocol
	    echo  java -cp lib/org.mavlink.generator-$mavlink_version.jar:lib/org.mavlink.util-$mavlink_version.jar org.mavlink.generator.MAVLinkGenerator ../../../message_definitions/v$protocol/ generated/v$protocol $isLittle  $isEmbedded $extracrc true

	    java -cp lib/org.mavlink.generator-$mavlink_version.jar:lib/org.mavlink.util-$mavlink_version.jar org.mavlink.generator.MAVLinkGenerator ../../../message_definitions/v$protocol/ generated/v$protocol $isLittle $isEmbedded $extracrc true
	    cd generated/v$protocol
	    base=$(basename ../../../message_definitions/v$protocol/*.xml .xml)
	    echo "BASE "  $protocol $mode $endian  $base
	    for xmlfile in $base; do
		echo "Remove old files for "$protocol " and " $xmlfile
		rm -rf ../../sources/org.mavlink.library/generated/org
		echo "Copy new files " generated/v$protocol/$xmlfile
		cp -rf $xmlfile/*  ../../sources/org.mavlink.library/generated/
		echo "Go to build"
		cd ../../sources/org.mavlink.library
		echo "BUILD"
		ant clean jar
		echo "GENERATE ../../lib/"$protocol"/org.mavlink.library-"$endian"-"$mode"-"$protocol"-"$mavlink_version".jar"
		mv ../../lib/org.mavlink.library-$mavlink_version.jar ../../lib/$protocol/org.mavlink.library-$xmlfile-$protocol-$endian-$mode-$mavlink_version.jar
		if [[ $? = 1 ]];
		    then
		    exit
		fi

		cd ../../generated/v$protocol
	    done
	    cd ../..
	done
    done
done
rm -rf generated

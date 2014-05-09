#!/bin/sh

rm -rf ../distrib
mkdir ../distrib
mkdir ../distrib/Java

cp -r Java/* ../distrib/Java


mkdir ../distrib/Java/sources/org.mavlink.generator
cp -r ../org.mavlink.generator/* ../distrib/Java/sources/org.mavlink.generator


mkdir ../distrib/Java/sources/org.mavlink.library
cp -r ../org.mavlink.library/* ../distrib/Java/sources/org.mavlink.library


mkdir ../distrib/Java/sources/org.mavlink.util
cp -r ../org.mavlink.util/* ../distrib/Java/sources/org.mavlink.util

mkdir ../distrib/Java/sources/org.mavlink.maven
cp -r ../org.mavlink.maven/* ../distrib/Java/sources/org.mavlink.maven


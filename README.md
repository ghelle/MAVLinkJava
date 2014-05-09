= MAVLink Java generator and library =

WARNING!!!! THIS PROJECT HAS BEEN MOVED FROM GOOGLECODE : http://code.google.com/p/mavlinkjava
GITHUB IS ITS NEW LOCATION



The goal of this project is to generate a Java Library from [http://www.qgroundcontrol.org/mavlink/ MAVLink] xml files for embedded Java (Android or not) and Java ground stations.
It works with MAVLink 0.9 or 1.0 xml files.

It is architectured with 4 Eclipse Java projects.
org.mavlink.generator : contains the generator and MAVLink xml files. Generated code is put in org.mavlink.library/generated folder.
org.mavlink.library : Helpers for MAVLink and message. Contains generated code from generator.
org.mavlink.util : CRC classes uses by generator and library.
org.mavlink.maven : parent pom for the project

== Generator usage : ==
Put desired mavlink xml files in a directory. Don't forget include files.
By example for ardupilotmega generation put ardupilotmega.xml and common.xml in a directory

Then generate code in directory "generated" in org.mavlink.library Eclipse project.

So build org.mavlink.library and org.mavlink.util Eclipse project and generate jar with each jardesc in projects.

Now you can use the 2 generated jar in your projects!

Command line arguments of MAVLink Java generator are :
  * source : xml file or directory path containing xml files to parse for generation
  * target : directory path for output Java source files
  * isLittleEndian : true if type are exchanged or stored in LittleEndian in buffer, false for BigEndian
  * forEmbeddedJava : true if generated code must use apis for embedded code (CLDC), false else for ground station
  * useExtraByte : if true use extra crc byte to compute CRC : true for MAVLink 1.0, false for 0.9
  * debug : true to generate toString methods in each message class
    
===Example :===
    java org.mavlink.generator.MAVLinkGenerator resources/v1.0 target/ true true true true
    java org.mavlink.generator.MAVLinkGenerator resources/v1.0/ardupilotmega.xml target/ true true true true

Generate MAVLink message Java classes for mavlink xml files contains in resources/v1.0 in target diretory for Little Endian data, embedded code with debug code.

== Integration in MAVLinl distribution : ==
Copy the 4 projects in a directory
Go in org.mavlink.generator and run makedistrib.sh (Linux) or makedistrib.bat (Windows)
A distrib directory is generated at the same level as the 3 projects
Copy the directory distrib/Java in the mavlink distribution : .../mavlink/pymavlink/generator
Go in .../mavlink/pymavlink/generator/Java

So users must run gen_java.bat (Windows) or gen_java.sh (Linux) to generate jar files.
All lib are generated in lib directory.
Users must choose lib/org.mavlink.util-1.00.jar and one of the org.mavlink.library-xxx.jar generated to work.

== Library usage : ==
Use MAVLinkReader to read messages with method MAVLinkMessage getNextMessage(). It's return a message if available else null.
{{{

            MAVLinkReader reader = new MAVLinkReader(dis, IMAVLinkMessage.MAVPROT_PACKET_START_V10);
            MAVLinkMessage msg;
            while (true) {
                msg = reader.getNextMessage();
                if (msg != null) {
                	// Do your stuff
                	...
                }
	    }
}}}


You can use also getNextMessageWithoutBlocking() : If bytes available, try to read it. Don't wait message is completed, it will be retruned nex time


Use encode() method on MAVLink message to generate a byte buffer so you can send it in a Data Output Stream.
{{{
msg_heartbeat hb = new msg_heartbeat(sysId, componentId);
hb.sequence = sequence++;
hb.autopilot = autopilot;
hb.base_mode = base_mode;
hb.custom_mode = custom_mode;
hb.mavlink_version = mavlink_version;
hb.system_status = system_status;
hb.type = type;
byte[] result = hb.encode();
dos.put(result);
}}}


Don't hesitate to send me your issues or requests! :-)

Have fun with that!

= Note : =
Actually 2 projects are using this code for Android :

[http://www.diydrones.com/forum/topics/andropilot-alpha-tester-discussion-for-this-android-application?id=705844 AndroPilot] 
Thank's to Kevin Hester to his remarks and issues!

[http://www.autoquad.org Autoquad] 
Thank's to Peter Hafner for his choice! :-)


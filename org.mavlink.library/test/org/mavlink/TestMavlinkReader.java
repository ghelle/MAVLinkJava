/**
 * $Id: TestMavlinkReader.java 10 2013-04-26 13:04:11Z ghelle31@gmail.com $
 * $Date: 2013-04-26 15:04:11 +0200 (ven., 26 avr. 2013) $
 *
 * ======================================================
 * Copyright (C) 2012 Guillaume Helle.
 * Project : MAVLINK Java
 * Module : org.mavlink.library
 * File : org.mavlink.TestMavlinkReader.java
 * Author : Guillaume Helle
 *
 * ======================================================
 * HISTORY
 * Who       yyyy/mm/dd   Action
 * --------  ----------   ------
 * ghelle	31 aout 2012		Create
 * 
 * ====================================================================
 * Licence: MAVLink LGPL
 * ====================================================================
 */

package org.mavlink;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;

import org.mavlink.messages.MAVLinkMessage;

/**
 * @author ghelle
 * @version $Rev: 10 $
 *
 */
public class TestMavlinkReader {

    /**
     * @param args
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage :");
            System.out.println("java -cp org.mavlink.library-1.00.jar;org.mavlink.util-1.00.jar org.mavlink.TestMavlinkReader logFile");

            System.exit(1);
        }
        String filename = args[0];
        testFile(filename);
        testBuffer(filename);
    }

    static public void testFile(String filename) {
        MAVLinkReader reader;
        String fileOut = filename + "-resultat.out";
        int nb = 0;
        try {
            System.setOut(new PrintStream(fileOut));
            DataInputStream dis = new DataInputStream(new FileInputStream(filename));
            reader = new MAVLinkReader(dis);
            while (dis.available() > 0) {
                MAVLinkMessage msg = reader.getNextMessage();
                //MAVLinkMessage msg = reader.getNextMessageWithoutBlocking();
                if (msg != null) {
                    nb++;
                    System.out.println("SysId=" + msg.sysId + " CompId=" + msg.componentId + " seq=" + msg.sequence + " " + msg.toString());
                }
            }
            dis.close();

            System.out.println("TOTAL BYTES = " + reader.getTotalBytesReceived());
            System.out.println("NBMSG (" + nb + ") : " + reader.getNbMessagesReceived() + " NBCRC=" + reader.getBadCRC() + " NBSEQ="
                               + reader.getBadSequence() + " NBLOST=" + reader.getLostBytes());
        }
        catch (Exception e) {
            System.out.println("ERROR : " + e);
        }
    }

    static public void testBuffer(String filename) {
        MAVLinkReader reader;
        String fileOut = filename + "-resultat-buffer.out";
        int nb = 0;
        int sizeToRead = 4096;
        int available;
        byte[] buffer = new byte[4096];
        MAVLinkMessage msg;
        try {
            System.setOut(new PrintStream(fileOut));
            DataInputStream dis = new DataInputStream(new FileInputStream(filename));
            reader = new MAVLinkReader();
            while (dis.available() > 0) {
                msg = reader.getNextMessage(buffer, 0);
                if (msg != null) {
                    nb++;
                    System.out.println("SysId=" + msg.sysId + " CompId=" + msg.componentId + " seq=" + msg.sequence + " " + msg.toString());
                }
                else {
                    if (dis.available() > 0) {
                        available = dis.available();
                        if (available > sizeToRead) {
                            available = sizeToRead;
                        }
                        dis.read(buffer, 0, available);
                        msg = reader.getNextMessage(buffer, available);
                        if (msg != null) {
                            nb++;
                            System.out.println("SysId=" + msg.sysId + " CompId=" + msg.componentId + " seq=" + msg.sequence + " " + msg.toString());
                        }
                    }
                }
            }
            do {
                msg = reader.getNextMessage(buffer, 0);
                if (msg != null) {
                    nb++;
                    System.out.println("SysId=" + msg.sysId + " CompId=" + msg.componentId + " seq=" + msg.sequence + " " + msg.toString());
                }
            }
            while (msg != null);
            dis.close();

            System.out.println("TOTAL BYTES = " + reader.getTotalBytesReceived());
            System.out.println("NBMSG (" + nb + ") : " + reader.getNbMessagesReceived() + " NBCRC=" + reader.getBadCRC() + " NBSEQ="
                               + reader.getBadSequence() + " NBLOST=" + reader.getLostBytes());
        }
        catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace();
        }

    }
}

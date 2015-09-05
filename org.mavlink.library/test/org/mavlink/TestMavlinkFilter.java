/**
 * 
 */
package org.mavlink;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;

import org.mavlink.messages.IMAVLinkMessageID;
import org.mavlink.messages.MAVLinkMessage;

/**
 * @author ghelle
 *
 */
public class TestMavlinkFilter {
	
	static int[] keys={IMAVLinkMessageID.MAVLINK_MSG_ID_WIND,IMAVLinkMessageID.MAVLINK_MSG_ID_AHRS,IMAVLinkMessageID.MAVLINK_MSG_ID_HWSTATUS,
			IMAVLinkMessageID.MAVLINK_MSG_ID_SCALED_PRESSURE, IMAVLinkMessageID.MAVLINK_MSG_ID_GPS_RAW_INT,IMAVLinkMessageID.MAVLINK_MSG_ID_GLOBAL_POSITION_INT,
			IMAVLinkMessageID.MAVLINK_MSG_ID_SERVO_OUTPUT_RAW, IMAVLinkMessageID.MAVLINK_MSG_ID_RC_CHANNELS_RAW,IMAVLinkMessageID.MAVLINK_MSG_ID_ATTITUDE,
			IMAVLinkMessageID.MAVLINK_MSG_ID_VFR_HUD,IMAVLinkMessageID.MAVLINK_MSG_ID_RAW_IMU,IMAVLinkMessageID.MAVLINK_MSG_ID_SYS_STATUS,
			IMAVLinkMessageID.MAVLINK_MSG_ID_MEMINFO,IMAVLinkMessageID.MAVLINK_MSG_ID_SENSOR_OFFSETS,IMAVLinkMessageID.MAVLINK_MSG_ID_HEARTBEAT,
			IMAVLinkMessageID.MAVLINK_MSG_ID_SYSTEM_TIME,IMAVLinkMessageID.MAVLINK_MSG_ID_MISSION_CURRENT,IMAVLinkMessageID.MAVLINK_MSG_ID_NAV_CONTROLLER_OUTPUT};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage :");
            System.out.println("java -cp org.mavlink.library-1.00.jar;org.mavlink.util-1.00.jar org.mavlink.TestMavlinkFilter logFile");

            System.exit(1);
        }
        String filename = args[0];
        filterFile(filename);
        System.exit(0);
    }

    static public void filterFile(String filename) {
        MAVLinkReader reader;
        String fileOut = filename + "-resultat.filter";
        int nb = 0;
        try {
            System.setOut(new PrintStream(fileOut));
            DataInputStream dis = new DataInputStream(new FileInputStream(filename));
            reader = new MAVLinkReader(dis);
            while (dis.available() > 0) {
                MAVLinkMessage msg = reader.getNextMessage();
                if (msg != null) {
                    nb++;
                    if (filter(msg))
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
    
    static public boolean filter(MAVLinkMessage msg) {
    	for (int key : keys) {
    		if (msg.messageType==key)
    			return false;
    	}
    	return true;
    }

}

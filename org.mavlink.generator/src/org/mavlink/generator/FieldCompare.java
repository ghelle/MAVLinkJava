/**
 * $Id: FieldCompare.java 4 2013-04-11 14:04:50Z ghelle31@gmail.com $
 * $Date: 2013-04-11 16:04:50 +0200 (jeu., 11 avr. 2013) $
 *
 * ======================================================
 * Copyright (C) 2012 Guillaume Helle.
 * Project : MAVLINK Java
 * Module : org.mavlink.generator
 * File : org.mavlink.generator.FieldCompare.java
 * Author : Guillaume Helle
 *
 * ======================================================
 * HISTORY
 * Who       yyyy/mm/dd   Action
 * --------  ----------   ------
 * ghelle	7 sept. 2012		Create
 * 
 * ====================================================================
 * Licence: MAVLink LGPL
 * ====================================================================
 */

package org.mavlink.generator;

import java.util.Comparator;

/**
 * Comparator to sort field in MAVLink messages.
 * Sort only on the size of field type and ignore array size
 * @author ghelle
 * @version $Rev: 4 $
 *
 */
public class FieldCompare implements Comparator<MAVLinkField> {

    public int compare(MAVLinkField field2, MAVLinkField field1) {
        //Sort on type size
        if (field1.getType().getTypeSize() > field2.getType().getTypeSize()) {
            return 1;
        }
        else if (field1.getType().getTypeSize() < field2.getType().getTypeSize()) {
            return -1;
        }
        return 0;
    }

}

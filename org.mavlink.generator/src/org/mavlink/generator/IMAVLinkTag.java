/**
 * $Id: IMAVLinkTag.java 4 2013-04-11 14:04:50Z ghelle31@gmail.com $
 * $Date: 2013-04-11 16:04:50 +0200 (jeu., 11 avr. 2013) $
 *
 * ======================================================
 * Copyright (C) 2012 Guillaume Helle.
 * Project : MAVLink Java Generator
 * Module : org.mavlink.generator
 * File : org.mavlink.generator.IMAVLinkTag.java
 * Author : Guillaume Helle
 *
 * ======================================================
 * HISTORY
 * Who       yyyy/mm/dd   Action
 * --------  ----------   ------
 * ghelle	31 mars 2012		Create
 * 
 * ====================================================================
 * Licence: MAVLink LGPL
 * ====================================================================
 */

package org.mavlink.generator;

/**
 * MAVLink tags in xml files
 * @author ghelle
 * @version $Rev: 4 $
 *
 */
public interface IMAVLinkTag {

    public final static String INCLUDE_TAG = "include";

    public final static String MAVLINK_TAG = "mavlink";

    public final static String VERSION_TAG = "version";

    public final static String DESCRIPTION_TAG = "description";

    public final static String ENUMS_TAG = "enums";

    public final static String ENUM_TAG = "enum";

    public final static String ENTRY_TAG = "entry";

    public final static String PARAM_TAG = "param";

    public final static String MESSAGES_TAG = "messages";

    public final static String MESSAGE_TAG = "message";

    public final static String FIELD_TAG = "field";

    public final static String ID_ATTR = "id";

    public final static String NAME_ATTR = "name";

    public final static String TYPE_ATTR = "type";

    public final static String PRINT_FORMAT_ATTR = "print_format";

    public final static String VALUE_ATTR = "value";

    public final static String INDEX_ATTR = "index";

}

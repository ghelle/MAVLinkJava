/**
 * $Id: MAVLinkField.java 4 2013-04-11 14:04:50Z ghelle31@gmail.com $
 * $Date: 2013-04-11 16:04:50 +0200 (jeu., 11 avr. 2013) $
 *
 * ======================================================
 * Copyright (C) 2012 Guillaume Helle.
 * Project : MAVLink Java Generator
 * Module : org.mavlink.generator
 * File : org.mavlink.generator.MAVLinkField.java
 * Author : Guillaume Helle
 *
 * ======================================================
 * HISTORY
 * Who       yyyy/mm/dd   Action
 * --------  ----------   ------
 * ghelle	30 mars 2012		Create
 * 
 * ====================================================================
 * Licence: MAVLink LGPL
 * ====================================================================
 */

package org.mavlink.generator;

/**
 * MAVLink Field data
 * @author ghelle
 * @version $Rev: 4 $
 *
 */
public class MAVLinkField {

    /**
     * MAVLink Field type
     */
    private MAVLinkDataType type;

    /**
     * MAVLink Field name
     */
    private String name;

    /**
     * MAVLink Field description
     */
    private String description;

    /**
     * MAVLink Field constructor
     * @param type
     * @param name
     */
    public MAVLinkField(MAVLinkDataType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @return The type
     */
    public MAVLinkDataType getType() {
        return type;
    }

    /**
     * @param type The type to set
     */
    public void setType(MAVLinkDataType type) {
        this.type = type;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}

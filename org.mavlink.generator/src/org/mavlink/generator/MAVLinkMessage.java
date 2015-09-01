/**
 * $Id: MAVLinkMessage.java 4 2013-04-11 14:04:50Z ghelle31@gmail.com $
 * $Date: 2013-04-11 16:04:50 +0200 (jeu., 11 avr. 2013) $
 *
 * ======================================================
 * Copyright (C) 2012 Guillaume Helle.
 * Project : MAVLink Java Generator
 * Module : org.mavlink.generator
 * File : org.mavlink.generator.MAVLinkMessage.java
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

import java.util.ArrayList;
import java.util.List;

/**
 * MAVLink message generic type
 * @author ghelle
 * @version $Rev: 4 $
 *
 */
public class MAVLinkMessage {

    private int id;

    private String name;

    private String description;

    private List<MAVLinkField> fields;

    public MAVLinkMessage(int id, String name) {
        this.id = id;
        this.name = name;
        fields = new ArrayList<MAVLinkField>();
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id to set
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * @return The fields
     */
    public List<MAVLinkField> getFields() {
        return fields;
    }

    /**
     * @param fields The fields to set
     */
    public void setFields(List<MAVLinkField> fields) {
        this.fields = fields;
    }

}

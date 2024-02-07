/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.utils;

import java.sql.Timestamp;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.DateTime;

/**
 *
 * @author NDOZENG
 */

public class JodaDateTimeConverter implements Converter {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    /**
     * JodaDateTimeConverter
     * Methode : convertDataValueToObjectValue()
     * @param dataValue
     * @param session
     * @return
     */
    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : new DateTime((Timestamp) dataValue);
    }// fin de convertDataValueToObjectValue()
    
    /**
     * JodaDateTimeConverter
     * Methode : convertObjectValueToDataValue()
     * @param objectValue
     * @param session
     * @return
     */
    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : new Timestamp(((DateTime) objectValue).getMillis());
    }// fin de convertObjectValueToDataValue()
    
    /**
     * JodaDateTimeConverter
     * Methode : initialize()
     * @param mapping
     * @param session
     */
    @Override
    public void initialize(DatabaseMapping mapping, Session session) {}// fin de initialize()
    
    /**
     * JodaDateTimeConverter
     * @return
     */
    @Override
    public boolean isMutable() {
        return false;
    }// fin isMutable()
    
}// fin de la classe JodaDateTimeConverter

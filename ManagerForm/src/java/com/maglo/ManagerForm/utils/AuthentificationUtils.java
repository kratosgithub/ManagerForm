/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de convertir une chaîne de caractère en une autre
 * incompréhensible à l'aide de l'algorithme SHA-256.
 */

public class AuthentificationUtils {
    
    /**
     * AuthentificationUtils : encodeSHA256()
     * Returns SHA-256 encoded String
     * @param password - this string to be encoded
     * @return - SHA-256 encoded String
     * @throws UnsupportedEncodingException if UTF-8 is not supported by the system
     * @throws NoSuchAlgorithmException if SHA-256 is not supported by the system
     */
    public static String encodeSHA256(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        
        byte[] digest = md.digest();
        
        return DatatypeConverter.printBase64Binary(digest).toString();
    }// fin de la méthode encodeSHA256()
    
}// fin de la classe AuthentificationUtils

/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de gerer les interactions entre la BDD, le serveur et les donnees
 * saisies par l'utilisateur sur les differentes vues du systeme.
 */

public class DAOException extends RuntimeException {
    
    /**
     * Constructeurs de la classe : Message - Causes
     */
    
    /**
     * DAOException : DAOException() constructeur qui prend en charge le message d'une erreur renvoyée
     * par la BDD
     * @param message : message d'erreur
     */
    public DAOException(String message) {
        super(message);
    }// DAOException()
    
    /**
     * DAOException : DAOException() constructeur qui prend en charge le message et la cause de l'erreur
     * sur survenue lors d'une interaction avec la BDD
     * @param message 
     * @param cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }// DAOException()
    
    /**
     * DAOException : DAOException() constructeur qui prend en charge la cause de l'erreur survenu
     * @param cause 
     */
    public DAOException(Throwable cause) {
        super(cause);
    }// DAOException()
    
}// fin de la classe DAOException

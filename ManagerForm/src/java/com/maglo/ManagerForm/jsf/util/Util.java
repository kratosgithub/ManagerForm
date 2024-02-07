/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.jsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author NDOZENG
 * Backing bean permettant d'afficher les messages sur les vues, suite aux requêtes
 * de l'utilisateur en fonction des ressources demandées.
 */

public class Util {
    
    /**
     * Avec ClientId
     * Pour retrouver un clientId, sur un exemple : si le <h:inputText>
     * a un id égal à "input" et s'il est dans un formulaire dont le
     * id est "form", clientId est "form:input".
     * On peut aussi le trouver en examinant le source de la page HTML.
     * @param messageDetail : message détaillé
     * @param messageResume : message résumé
     * @param severite : severite du message
     * @param clientId : id du client qui est attaché au message
     */
    public static void message(String messageDetail, String messageResume, FacesMessage.Severity severite, String clientId) {
        FacesMessage message = new FacesMessage(severite, messageResume, messageDetail);
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }// fin de message()
    
    /**
     * Message qui n'est pas attaché à un composant particulier.
     * @param messageDetail
     * @param messageResume
     * @param severite 
     */
    public static void message(String messageDetail, String messageResume, FacesMessage.Severity severite) {
        FacesMessage message = new FacesMessage(severite, messageResume, messageDetail);
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }// fin de la méthode message()
    
    /**
     * Message de sévérité FacesMessage.SEVERITY_ERROR qui n'est pas attaché
     * à un composant particulier. On peut donner un message différente pour
     * le message détaillé et pour le message résumé.
     * @param messageDetail
     * @param messageResume 
     */
    public static void messageErreur(String messageDetail, String messageResume) {
        message(messageDetail, messageResume, FacesMessage.SEVERITY_ERROR);
    }// fin de la méthode messageErreur()
    
    /**
     * Message : message()
     * @param message
     */
    public static void message(String message) {
        message(message, message, FacesMessage.SEVERITY_ERROR); 
    }// fin de la méthode message()
    
    /**
     * Message d'erreur attaché à un composant particulier.
     * @param messageDetail
     * @param messageResume
     * @param clientId 
     */
    public static void messageErreur(String messageDetail, String messageResume, String clientId) {
        message(messageDetail, messageResume, FacesMessage.SEVERITY_ERROR, clientId);
    }// fin de la méthode messageErreur()
    
    /**
     * Message d'information (ou de succès) qui n'est pas attaché à un composant
     * particulier.
     * @param messageDetail
     * @param messageResume 
     */
    public static void messageInfo(String messageDetail, String messageResume) {
        message(messageDetail, messageResume, FacesMessage.SEVERITY_INFO);
    }// fin de la méthode messageInfo()
    
    /**
     * Message : messageInfo()
     * @param message
     */
    public static void messageInfo(String message) {
        message(message, message, FacesMessage.SEVERITY_INFO);
    }// fin de la méthode messageInfo()
    
    /**
     * Message : addFlashMessage()
     * Messages avec redirection, pas attaché à un composant particulier.
     * @param message : à afficher
     */
    public static void addFlashMessage(FacesMessage message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        
        flash.setKeepMessages(true);
        facesContext.addMessage(null, message); 
    }// fin de la méthode addFlashMessage()
    
    /**
     * Message : addFlashErrorMessage()
     * Message d'erreur avec redirection, pas attaché à un composant particulier.
     * @param message 
     */
    public static void addFlashErrorMessage(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
        addFlashMessage(msg); 
    }// fin de la méthode addFlashErrorMessage()
    
    /**
     * Message : addFlashInfoMessage()
     * Message d'information avec redirection, pas attaché à un composant particulier.
     * @param message 
     */
    public static void addFlashInfoMessage(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
        addFlashMessage(msg); 
    }// fin de la méthode addFlashInfoMessage()
    
    /**
     * Message : addFlashWarnMessage()
     * Message de mise en garde à l'attention, pas attaché à un composant particulier
     * @param message
     */
    public static void addFlashWarnMessage(String message) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, message);
        addFlashMessage(msg); 
    }// fin de la méthode addFlashWarnMessage()
    
}// fin de la classe Util

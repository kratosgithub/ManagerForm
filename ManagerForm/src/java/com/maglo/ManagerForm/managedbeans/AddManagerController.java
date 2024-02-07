/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

import com.maglo.ManagerForm.ejb.UserEJB;
import com.maglo.ManagerForm.entities.Users;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de créer un utilisateur avec le profil <!-- Admin -->.
 */

@ManagedBean
@SessionScoped 
public class AddManagerController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    // Logger
    private static final Logger logger = Logger.getLogger(AddManagerController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private UserEJB uejb;
    
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private String confirmPassword;
    private String tel;
    
    // Variables utiles
    private static final String INPUT_PASSWORD = "inputPassword";
    private static final String INPUT_CONFIRM_PASSWORD = "inputConfirmPassword";
    private static final String PASSWORDS_WARN = "Les mots de passe saisis ne sont pas identiques.";
    
    /**
     *  GETTERS & SETTERS ------------------------------------------------------
     */
    
    /**
     * Nom : getNom()
     * @return
     */
    public String getNom() {
        return nom;
    }// fin getNom()
    
    /**
     * Nom : setNom()
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }// fin setNom()
    
    /**
     * Prenom : getPrenom()
     * @return
     */
    public String getPrenom() {
        return prenom;
    }// fin getPrenom()
    
    /**
     * Prenom : setPrenom()
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom.toUpperCase();
    }// fin setPrenom()
    
    /**
     * Login : getLogin()
     * @return
     */
    public String getLogin() {
        return login;
    }// fin getLogin()
    
    /**
     * Login : setLogin()
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }// fin setLogin()
    
    /**
     * Password : getPassword()
     * @return
     */
    public String getPassword() {
        return password;
    }// fin getPassword()
    
    /**
     * Password : setPassword()
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }// fin setPassword()
    
    /**
     * ConfirmPassword : getConfirmPassword()
     * @return
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }// fin getConfirmPassword()
    
    /**
     * ConfirmPassword : setConfirmPassword()
     * @param confirmPassword
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }// fin setConfirmPassword()
    
    /**
     * Tel : getTel()
     * @return
     */
    public String getTel() {
        return tel;
    }// fin getTel()
    
    /**
     * Tel : setTel()
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel; 
    }// fin setTel()
    
    /**
     * Password : validatePassword()
     * Description : Méthode permettant de valider les champs mot de passe.
     * @param event
     */
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        
        // Get Password
        UIInput uiInputPassword = (UIInput) components.findComponent(INPUT_PASSWORD);
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordID = uiInputPassword.getClientId();
        
        // Get Confirm Password 
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent(INPUT_CONFIRM_PASSWORD);
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? "" : uiInputConfirmPassword.getLocalValue().toString();
        
        // let required="true", do its job 
        if(password.isEmpty() || confirmPassword.isEmpty()) return;
        
        // Champs password et confirmPassword différents
        if(!password.equals(confirmPassword)) {
            FacesMessage fm = new FacesMessage("Attention :", PASSWORDS_WARN);
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            
            facesContext.addMessage(passwordID, fm);
            facesContext.renderResponse();
        }// fin de la condition if ...
    }// fin validatePassword()
    
    /**
     * User (Manager) : saveManager()
     * Description : Méthode permettant d'enregistrer un nouveau manager.
     * @return 
     */
    public String saveManager() {
        // Initialisation d'un nouveau manager
        Users users = new Users(nom, prenom, login, password, tel);
        
        // On crée le nouveau manager
        uejb.createAdmin(users);
        
        // Logger Info
        logger.info("SIGN UP : Un nouveau manager a été enregistré avec succès. Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération 
         * Util.addFlashInfoMessage("M/Mme. " + user.getPrenom() + " " + user.getNom() + " a été enregistré avec succès.");
         */
        Util.messageInfo("M/Mme. " + users.getPrenom() + " " + users.getNom() + " a été enregistré avec succès.", "Nouveau manager :");
        
        return "success"; 
    }// fin saveManager()
    
}// fin de la classe AddManagerController

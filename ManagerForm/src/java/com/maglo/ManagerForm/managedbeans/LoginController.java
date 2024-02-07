
/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.maglo.ManagerForm.ejb.UserEJB;
import com.maglo.ManagerForm.entities.Users;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant à un utilisateur de se connecter en s'authentifiant
 * à l'aide des informations qui lui sont demandées pour accèder à ses ressources.
 */

@ManagedBean
@SessionScoped 
public class LoginController implements Serializable {
    
    // Déclaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());
    
    private static final String ERROR_CONNECT = "Identifiant ou mot de passe incorrect(s). Veuillez réessayer.";
    
    @Inject
    private UserEJB uejb;
    
    private Users users;
    
    private String login;
    private String password;
    private String url = "/login-failed.xhtml";
    
    // Récupérer la date 
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Obtenir la date courante 
    Date date = new Date();
    
    /**
     *  GETTERS & SETTERS ------------------------------------------------------
     */
    
    /**
     * LoginController : getAuthenticatedUser()
     * @return : renvoie l'utilisateur correspondant
     */
    public Users getAuthenticatedUser() {
        return users;
    }// fin de getAuthenticatedUser()
    
    /**
     * Login : getLogin()
     * @return : renvoie le logintrouvee
     */
    public String getLogin() {
        return login;
    }// fin de getEmail()
    
    /**
     * Login : setLogin()
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }// fin de setEmail()
    
    /**
     * Password : getPassword()
     * @return : renvoie le mot de passe trouve
     */
    public String getPassword() {
        return password;
    }// fin de getPassword()
    
    /**
     * Password : setPassword()
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }// fin de setPassword()
    
    /**
     * LoginController : getUrl()
     * @return : renvoie l'url 
     */
    public String getUrl() {
        return url;
    }// fin de la getUrl()
    
    /**
     * User : loadUser()
     * Charge l'utilisateur correspondant à l'identifiant passe en parametre
     */
    public void loadUser() {
        users = uejb.getUserByLogin(login);
    }// fin de loadUser()
    
    /**
     * LoginController : logout()
     * Description : Méthode permettant à un utilisateur de se déconnecter
     * @return : renvoie le formulaire d'authentification
     */
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {// essai...
            this.users = null;
            request.logout();
            
            // clear session
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            
            logger.info("SIGN OUT : Déconnexion réussie pour l'utilisateur : " + login + ". Le : " + format.format(date) + ".");
        } catch (ServletException e) {
            logger.log(Level.SEVERE, "Erreur : Nous n'avons pas pu vous déconnecter.", e);
        }// fin du try .. catch fermeture de session
        
        return "/login?faces-redirect=true";
    }// fin de logout()
    
    /**
     * LoginController : login()
     * Description : Méthode permettant à un utilisateur de se connecter
     * @return : renvoie l'espace correspondant aux rôles de l'utilisateur trouvé
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try {// essai ...
            request.login(login, password);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", ERROR_CONNECT));
            
            logger.info("SIGN OUT : Echec de connexion pour l'utilisateur : " + login + ". Le : " + format.format(date) + ".");
            
            // renvoie le formulaire d'authentification
            return "login-failed";
        }// fin du try .. catch 
       
        Principal principal = request.getUserPrincipal();
        
        this.users = uejb.getUserByLogin(principal.getName());  
        
        logger.info("SIGN IN : Authentification réussie pour l'utilisateur : " + principal.getName() + ". Le : " + format.format(date) + ".");
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        
        sessionMap.put("Users", users);
        
        /* Condition sur l'authentification d'un utilisateur en fonction de son rôle */
        if(request.isUserInRole("other_user_role")) {
            // renvoie la page d'accueil d'un utilisateur 
            return "/users/home?faces-redirect=true";
            
        } else if (request.isUserInRole("default_user_role")) {
            // renvoie la page d'accueil d'un technicien 
            return "/agents/home?faces-redirect=true";
            
        } else if (request.isUserInRole("admin_role")) {
            // renvoie la page d'accueil d'un administrateur 
            return "/managers/home?faces-redirect=true";
            
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", ERROR_CONNECT));
            
            return "login-failed"; // renvoie le formulaire de connexion
        }// fin de la condition if .. else
    }// fin de la methode login()
    
    /**
     * Url : init()
     */
    @PostConstruct
    public void init() {
        url = (String) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestMap()
                .get(RequestDispatcher.FORWARD_REQUEST_URI);
    }// fin d'init()
    
    /**
     * LoginController : redirect()
     * Description : renvoie le formulaire d'authentitification
     * @throws IOException
     */
    public void redirect() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        
        response.sendRedirect(url);
        context.responseComplete();
    }// fin de redirect()
    
}// fin de la classe LoginController

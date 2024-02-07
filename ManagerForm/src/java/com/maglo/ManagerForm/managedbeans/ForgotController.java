/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.managedbeans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;

import com.maglo.ManagerForm.ejb.UserEJB;
import com.maglo.ManagerForm.entities.Users;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant aux utilisateurs de réinitialiser leur mot de passe 
 * une fois que login a été reconnu par le système.
 */

@ManagedBean
@SessionScoped 
public class ForgotController implements Serializable {
    
    // Déclaration des variables de la classe 
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ForgotController.class.getName());
    
    @Inject
    private UserEJB uejb;
    
    private Users users;
    
    @Resource(name = "jdbc/managerDS")
    private DataSource ds;
    
    private String login;
    private String password;
    private String confirmPassword; 
    
    // Format de la date 
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Obtenir la date courante
    Date date = new Date();
    
    /**
     *  GETTERS & SETTERS ------------------------------------------------------
     */
    
    /**
     * User : getAuthenticatedUser()
     * @return 
     */
    public Users getAuthenticatedUser() {
        return users; 
    }// fin de getAuthenticatedUser()
    
    /**
     * User : getUser()
     * @return
     */
    public Users getUsers() {
        return users;
    }// fin de getUser()
    
    /**
     * User : setUsers()
     * @param users
     */
    public void setUsers(Users users) {
        this.users = users;
    }// fin de setUser()
    
    /**
     * Login : getLogin()
     * @return : renvoie le login trouvé 
     */
    public String getLogin() {
        return login;
    }// fin de getLogin()
    
    /**
     * Login : setLogin()
     * @param login : nom d'utilisateur 
     */
    public void setLogin(String login) {
        this.login = login;
    }// fin de setLogin()
    
    /**
     * Password : getPassword()
     * @return : renvoie le mot de passe saisi
     */
    public String getPassword() {
        return password;
    }// fin de getPassword()
    
    /**
     * Password : setPassword()
     * @param password : mot de passe saisi 
     */
    public void setPassword(String password) {
        this.password = password;
    }// fin de setPassword()
    
    /**
     * Confirm Password : getConfirmPassword()
     * @return : renvoie le mot de passe saisi
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }// fin de getConfirmPassword()
    
    /**
     * Confirm Password : setConfirmPassword()
     * @param confirmPassword : mot de passe saisi
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }// fin de setConfirmPassword()
    
    /**
     * User : loadUser()
     * Méthode permettant de charger l'utilisateur correspondant à le login saisi
     * et qui a été trouvé dans la BDD.
     */
    public void loadUser() {
        users = uejb.getUserByLogin(login);
    }// fin de loadUser()
    
    /**
     * Login : validateLogin()
     * Description : Méthode permettant de contrôler et de s'assurer de l'existence de le login saisi par 
     * l'utilisateur dans la BDD.
     * @return : renvoie le login trouvé dans la BDD.
     */
    public String validateLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {// essai..
            uejb.getUserByLogin(login);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Nom d'utilisateur incorrect. Veuillez réessayer."));
            
            // logger.info("Echec de l'authentification pour le nom d'utilisateur saisi : " + login + ". Le : " + format.format(date));
            
            return "forgot"; // on reste sur la page en cours.
        }// fin du try .. catch 
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        sessionMap.put("Users", users);
        
        // Condition sous l'existence de l'adresse email saisie.
        if(uejb.getUserByLogin(login) != null) {
            
            return "update?faces-redirect=true";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Nom d'utilisateur incorrect. Veuillez réessayez."));
            
            logger.info("FAILED : Echec de l'authentification du nom d'utilisateur saisi : " + login + ". Le : " + format.format(date) + ".");
            
            return "forgot";
        }// fin de if .. else 
    }// fin de validateLogin()
    
    /**
     * User : validatePassword()
     * Description : Méthode permettant de s'assurer que les champs mots de passe sont identiques.
     * @param event 
     */
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance(); 
        UIComponent components = event.getComponent();
        
        // Get Password 
        UIInput uiInputPassword = (UIInput) components.findComponent("inputPassword");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordID = uiInputPassword.getClientId();
        
        // Get Confirm Password 
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("inputConfirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? "" : uiInputConfirmPassword.getLocalValue().toString();
        
        // let required = "true" do its job.
        if(password.isEmpty() || confirmPassword.isEmpty()) return;
        
        // Champs mot de passe différents.
        if(!password.equals(confirmPassword)) {
            FacesMessage fm = new FacesMessage("Attention :", "Les mots de passe ne sont pas identiques.");
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            
            facesContext.addMessage(passwordID, fm);
            facesContext.renderResponse();
        }// fin de la condition if ... password ... confirmPassword 
    }// fin de validatePassword()
    
    /**
     * Password : getCryptedPassword()
     * Description : Méthode permettant de chiffrer le mot de passe à l'aide de la somme de hachage SHA-256
     * @param notCryptedPassword : chaine non chiffre
     * @throws UnsupportedEncodingException if UTF-8 is not supported by the system
     * @throws NoSuchAlgorithmException if SHA-256 is not supported by the system
     * @return : renvoie le mot de passe en chaine chiffrée.
     */
    public String getCryptedPassword(String notCryptedPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // Déclaration de la variable contenant le mot de passe à chiffrer
        MessageDigest md = null;
        
        try {// essai ...
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ForgotController.class.getName()).log(Level.SEVERE, null, ex);
        }// fin du try .. catch 
        
        if(md == null) return notCryptedPassword;
        
        md.update(notCryptedPassword.getBytes("UTF-8"));
        
        byte[] digest = md.digest();
        
        return DatatypeConverter.printBase64Binary(digest).toString();
    }// fin de getCryptedPassword()
    
    /**
     * Password : updatePassword()
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws SQLException
     */
    public void updatePassword() throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        String url = "jdbc:mysql://localhost:3306/gmaodb?useSSL=false";
        String username = "maglo";
        String pass = "M@gloWamp666++";
        
        try(Connection connection = DriverManager.getConnection(url, username, pass)) {
            String sql = "UPDATE users SET nom=?, prenom=?, password=?, tel=? WHERE login=?";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // statement.setLong(0, user.getIdUsers());
            statement.setString(1, users.getNom());
            statement.setString(2, users.getPrenom());
            // statement.setString(3, user.getLogin());
            statement.setString(3, getCryptedPassword(password));
            statement.setString(4, users.getTel());
            statement.setString(5, users.getLogin());
            
            int rowsUpdate = statement.executeUpdate(); // on exécute la requête
            
            if(rowsUpdate > 0)
                logger.info("UPDATE : Le mot de passe de l'utilisateur de M/Mme. : " + users.getPrenom() + " " + users.getNom() + " a été réinitialisé aves succès. Le : " + format.format(date) + "."); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }// fin du try .. catch 
    }// fin de updatePassword()
    
    /**
     * Password : savePassword()
     * Description : Méthode permettant d'enregistrer le nouveau mot de passe définit par l'utilisateur
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws SQLException
     * @return : renvoie la page information l'utilisateur que son mot de passe a bien été réinitialisé
     */
    public String savePassword() throws NoSuchAlgorithmException, UnsupportedEncodingException, SQLException {
        // Déclaration et initialisation de la variable de connection à la BDD.
        Connection connection = null;
        
        try {
            if(this.ds == null)
                throw new SQLException("Can't get Data Source. Try again.");
            
            connection = this.ds.getConnection();
            
            if(connection == null)
                throw new SQLException("Can't get Database Connection.");
            
            // Mise  à jour du mot de passe si aucune erreur n'est survenue.
            updatePassword();
        } finally {
            if(connection != null)
                connection.close();
        }// fin du try .. finally 
        
        logger.info("UPDATE : Le mot de passe de l'utilisateur a été réinitialisé avec succès. Le : " + format.format(date) + ".");
        
        /**
         * Message informant l'utilisateur sur le succès de l'opération
         * <!-- Util.addFlashInfoMessage("Votre mot de passe a été rénitialisé avec succès M/Mme/Mlle. " + user.getPrenom() + " " + user.getNom().toUpperCase() + "."); -->
         */
        Util.messageInfo("M/Mme. " + users.getPrenom() + " " + users.getNom() + ". Votre mot de passe a été réinitialisé avec succès.", "Information :");
        
        return "update-success"; // on renvoie la page de succès des opérations
    }// fin de savePassword()
    
}// fin de la classe ForgotController

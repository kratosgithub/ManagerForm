/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author NDOZENG
 * Roles : Classe utilitaire permettant de definir les roles attribués à chaque 
 * utilisateur du système.
 * <!-- Admins --> : Managers <!-- Users --> : Users : <!-- Default-group --> :
 * Technicien
 */

@Entity
@Table(name = "users_roles")
public class Roles implements Serializable {
    
    // Declartion des variables de la classe
    private static final long serialVersionUID = 1L;
    
    // Declaration des groupes utilisateurs 
    public static final String MANAGERS_GROUPS = "admins";
    public static final String USERS_GROUPS = "users";
    public static final String AGENTS_GROUPS = "default_group";
    
    // Proprietes de la classe
    @Id
    @Column(name = "login", length = 32, nullable = false)
    private String login;
    
    @Column(name = "roles", length = 32, nullable = false)
    private String roles;
    
    /**
     * GETTERS & SETTERS -------------------------------------------------------
     */
    
    /**
     * Login : identifiant
     * Methode : getLogin()
     * @return
     */
    public String getLogin() {
        return login;
    }// fin de getLogin()
    
    /**
     * Login : identifiant
     * Methode : setLogin()
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }// fin de setLogin()
    
    /**
     * Roles : roles de l'utilisateur
     * Methode : getRoles()
     * @return
     */
    public String getRoles() {
        return roles;
    }// fin de getRoles()
    
    /**
     * Roles : roles de l'utilisateur
     * Methode : setRoles()
     * @param roles
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }// fin de setRoles()
    
    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * Constructeur vide : Roles()
     */
    public Roles() {}// fin Roles()
    
    /**
     * Constructeur intialise : Roles()
     * @param login 
     * @param roles
     */
    public Roles(String login, String roles) {
        this.login = login;
        this.roles = roles;
    }// fin de Roles()
    
}// fin de la classe Roles

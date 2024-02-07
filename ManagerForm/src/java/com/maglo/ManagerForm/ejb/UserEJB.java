/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.maglo.ManagerForm.entities.Roles;
import com.maglo.ManagerForm.entities.Users;
import com.maglo.ManagerForm.utils.AuthentificationUtils;

/**
 *
 * @author NDOZENG
 * Backing bean permettant de gérer les interactions entre les données saisies
 * par l'utilisateur et la BDD.
 */

@Stateless 
public class UserEJB {
    
    // Declaration des propriétés de la classe
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * Methodes utilitaires ----------------------------------------------------
     */
    
    /**
     * Users : compter les utilisateurs existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("Users.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * UserEJB : getUserByLogin() méthode permettant de trouver un utilisateur à l'aide de son login
     * ou nom d'utilisateur
     * @param login : identifiant de l'utilisateur
     * @return
     */
    public Users getUserByLogin(String login) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByLogin", Users.class);
        query.setParameter("login", login);
        Users users = null;
        
        try {// essai
            users = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin try ... catch 
        
        return users;
    }// fin de getUserByLogin()
    
    /**
     * UserEJB : getUserByNom() méthode permettant de trouver un utilisateur à l'aide de son nom
     * @param nom : nom de l'utilisateur
     * @return
     */
    public Users getUserByNom(String nom) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByNom", Users.class);
        query.setParameter("nom", nom);
        Users users = null;
        
        try {// essai
            users = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return users; 
    }// fin de getUserByNom()
    
    /**
     * UserEJB : getUserByPrenom() méthode permettant de trouver un utilisateur à l'aide de son prenom
     * @param prenom : prenom de l'utilisateur
     * @return
     */
    public Users getUserByPrenom(String prenom) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByPrenom", Users.class);
        query.setParameter("prenom", prenom);
        Users users = null;
        
        try {// essai
            users = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return users;
    }// fin de getUserByPrenom()
    
    /**
     * UserEJB : getAllUsers() méthode d'afficher la liste de tous les utilisateurs
     * @return : renvoie les utilisateurs trouvés dans la base de données
     */
    public List<Users> getAllUsers() throws DAOException {
        // TypedQuery<User> query = em.createNamedQuery("getAllUsers", User.class);
        
        try {// essai
            TypedQuery<Users> query = em.createNamedQuery("Users.findAll", Users.class);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ... catch 
    }// fin de getAllUsers()
    
    /**
     * UserEJB : getUserByTel() méthode permettant de recherche un utilisateur à partir de
     * son numéro de téléhone.
     * @param tel : N° de téléphone de l'utilisateur
     * @return
     */
    public Users getUserByTel(String tel) {
        TypedQuery<Users> query = em.createNamedQuery("Users.findByTel", Users.class);
        query.setParameter("tel", tel); 
        Users users = null;
        
        try {// essai 
            users = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin try ... catch 
        
        return users;
    }// fin de getUserByTel()
    
    /**
     * UserEJB : delete() méthode permettant de  supprimer un utilisateur
     * @param users : utilisateur à supprimer
     */
    public void delete(Users users) throws DAOException {
        try {// essai 
            em.remove(em.merge(users));
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin de delete()
    
    /**
     * UserEJB : update() méthode permettant de mettre à jour un utilisateur
     * @param users : utilisateur à mettre à jour
     * @return 
     */
    public Users update(Users users) throws DAOException {
        try {// essai 
            return em.merge(users);
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * UserEJB : createAdmin() méthode permettant de créer un utilisateur avec le profil Administrateur
     * @param users : utilisateur crée en tant qu' <!-- Administrateur / Manager -->
     * @return : renvoie l'administrateur crée
     */
    public Users createAdmin(Users users) throws DAOException {
        try {// essai 
            users.setPassword(AuthentificationUtils.encodeSHA256(users.getPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            
            throw new DAOException(e);
        }// fin try ... catch 
        
        Roles roles = new Roles();
        roles.setLogin(users.getLogin());
        roles.setRoles(Roles.MANAGERS_GROUPS);
        
        em.persist(users);
        em.persist(roles); 
        
        return users;
    }// fin de createAdmin()
    
    /**
     * UserEJB : createAgent() 
     * Description : méthode permettant de créer un utilisateur avec le profil Employé.
     * @param users : utilisateur crée en tant qu' <!-- Agent / Employee / technicien -->
     * @return : renvoie l'employé crée.
     */
    public Users createAgent(Users users) throws DAOException {
        try {// essai...
            users.setPassword(AuthentificationUtils.encodeSHA256(users.getPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            
            throw new DAOException(e);
        }// fin try ... catch 
        
        Roles roles = new Roles();
        roles.setLogin(users.getLogin());
        roles.setRoles(Roles.AGENTS_GROUPS);
        
        em.persist(users);
        em.persist(roles); 
        
        return users;
    }// fin de createAgent()
    
    /**
     * UserEJB : createUser() 
     * Description : méthode permettant de créer un utilisateur avec le profil utilisateur.
     * @param users : utilisateur qui sera crée en tant que <!-- User -->
     * @return : renvoie le <!-- User --> crée
     */
    public Users createUser(Users users) throws DAOException {
        try {// essai 
            users.setPassword(AuthentificationUtils.encodeSHA256(users.getPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
            
            throw new DAOException(e);
        }// fin try ... catch 
        
        Roles roles = new Roles();
        roles.setLogin(users.getLogin());
        roles.setRoles(Roles.USERS_GROUPS); 
        
        em.persist(users);
        em.persist(roles); 
        
        return users;
    }// fin de createUser()
    
}// fin de la classe UserEJB

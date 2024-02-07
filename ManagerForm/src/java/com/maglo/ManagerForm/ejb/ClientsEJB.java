/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.entities.ClientsPK;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requêtes de l'utilisateur à travers
 * les interfaces du systeme et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClientsEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * clients : compter les clients existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("Clients.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les clients
     * @return
     */
    public List<Clients> getAllClients() throws DAOException {
        // TypedQuery<Clients> query = em.createNamedQuery("Clients.findAll", Clients.class);
        
        try {// essai
            TypedQuery<Clients> query = em.createNamedQuery("Clients.findAll", Clients.class);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin getAllClients()
    
    /**
     * Rechercher un client à partir de son identifiant 
     * @param idClient
     * @return
     */
    public Clients getClientsByIdClient(Long idClient) throws DAOException {
        
        try {
            return (Clients) em.find(Clients.class, idClient);
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
        
    }// fin getClientsByIdClient()
    
    /**
     * Rechercher un client à partir de son nom
     * @param nomClient
     * @return 
     */
    public Clients getClientsByNomClient(String nomClient) throws DAOException {
        TypedQuery<Clients> query = em.createNamedQuery("Clients.findByNomClient", Clients.class);
        query.setParameter("nomClient", nomClient);
        Clients clients = null;
        
        try {// essai
            clients = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return clients;
    }// fin getClientsByNomClient()
    
    /**
     * Mise à jour d'un client
     * @param clients
     * @return 
     */
    public Clients update(Clients clients) throws DAOException {
        try {// essai 
            return em.merge(clients); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer un clients
     * @param clients
     */
    public void delete(Clients clients) throws DAOException {
        try {// essai 
            em.remove(em.merge(clients)); 
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin de delete()
    
    /**
     * Creer un client
     * @param idClient
     * @param nomClient
     * @param service
     * @param ville
     * @param telClient
     * @return 
     */
    public Clients create(Long idClient, String nomClient, String service, String ville, String telClient) throws DAOException {
        try {// esssai 
            Clients clients = new Clients(idClient, nomClient, service, ville, telClient);  
            em.persist(clients);
            
            return clients;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de create()
    
}// fin de la classe ClientsEJB

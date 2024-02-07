/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

// import com.maglo.ManagerForm.entities.Agents;
// import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.RemovalForm;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers 
 * les interfaces et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RemovalFormEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * RemovalForm : compter les fiches d'enlevement existantes
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("RemovalForm.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les fiches d'enlevement 
     * @return
     */
    public List<RemovalForm> getAllRemovalForm() throws DAOException {
        // TypedQuery<RemovalForm> query = em.createNamedQuery("RemovalForm.findAll", RemovalForm.class);
        
        try {// essai
            TypedQuery<RemovalForm> query = em.createNamedQuery("RemovalForm.findAll", RemovalForm.class);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin getAllRemovalForm()
    
    /**
     * Rechercher une fiche d'enlevement à partir de son id
     * @param idRmForm 
     * @return 
     */
    public RemovalForm getRemovalById(Long idRmForm) throws DAOException {
        TypedQuery<RemovalForm> query = em.createNamedQuery("RemovalForm.findByIdRmForm", RemovalForm.class);
        query.setParameter("idRmForm", idRmForm);
        RemovalForm removalForm = null;
        
        try {// essai
            removalForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return removalForm;  
    }// fin getRemovalById()
    
    /**
     * Rechercher une fiche d'enlevement à partir du nom client
     * @param nomClnt
     * @return 
     */
    public RemovalForm getRemovalByNomClnt(String nomClnt) throws DAOException {
        TypedQuery<RemovalForm> query = em.createNamedQuery("RemovalForm.findByNomClnt", RemovalForm.class);
        query.setParameter("nomClnt", nomClnt);
        RemovalForm removalForm = null;
        
        try {// essai
            removalForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return removalForm;  
    }// fin getRemovalByNomclnt()
    
    /**
     * Rechercher une fiche d'enlevement à partir du jour
     * @param jour
     * @return 
     */
    public RemovalForm getRemovalByJour(Date jour) throws DAOException {
        TypedQuery<RemovalForm> query = em.createNamedQuery("RemovalForm.findByJour", RemovalForm.class);
        query.setParameter("jour", jour); 
        RemovalForm removalForm = null;
        
        try {// essai
            removalForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return removalForm;    
    }// fin getRemovalByJour()
    
    /**
     * Mise à jour fiche d'enlevement 
     * @param removalForm 
     * @return 
     */
    public RemovalForm update(RemovalForm removalForm) throws DAOException {
        try {// essai 
            return em.merge(removalForm); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer une fiche d'enlevement 
     * @param removalForm 
     */
    public void delete(RemovalForm removalForm) throws DAOException {
        try {// essai 
            em.remove(em.merge(removalForm));   
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin delete()
    
    /**
     * Removal Form
     * Constructeur avec parametres
     * @param jour 
     * @param hrm 
     * @param accessoires 
     * @param nomAgent 
     * @param nomClnt 
     * @param nomHware 
     * @return 
     */
    public RemovalForm create(Date jour, Date hrm, String nomClnt, String nomHware, String nomAgent, String accessoires) throws DAOException {
        try {// essai 
            RemovalForm removalForm = new RemovalForm(jour, hrm, nomClnt, nomHware, nomAgent, accessoires);
            em.persist(removalForm);
            
            return removalForm;  
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin create() 
    
    /**
     * Liste un fiche de retrait à partir du nom du client
     * @param idRmForm
     * @param jour
     * @return
     */
    public Collection<RemovalForm> getRemovalFormByClient(Long idRmForm, Date jour) throws DAOException {
        try {// essai 
            TypedQuery<RemovalForm> query = em.createNamedQuery("RemovalForm.downloadFile", RemovalForm.class);
            query.setParameter("idRmForm", idRmForm);
            query.setParameter("jour", jour);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ..catch 
    }// fin getRemovalFormByClient()
    
}// fin de la classe RemovalFormEJB
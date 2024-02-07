/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

// import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.PreInstallForm;
// import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers 
 * les interfaces et la BDD.
 */

@Stateless 
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PreInstallFormEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * PreInstallForm : compter les fiches de pre-installation existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("PreInstallForm.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les fiches de pre installation 
     * @return
     */
    public List<PreInstallForm> getAllPreInstallForm() throws DAOException {
        // TypedQuery<PreInstallForm> query = em.createNamedQuery("PreInstallForm.findAll", PreInstallForm.class);
        
        try {// essai
            TypedQuery<PreInstallForm> query = em.createNamedQuery("PreInstallForm.findAll", PreInstallForm.class);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin getAllPreInstallForm()
    
    /**
     * Rechercher une fiche de pre installation à partir de son id
     * @param idPreForm
     * @return 
     */
    public PreInstallForm getInstallByIdPreForm(Long idPreForm) throws DAOException {
        TypedQuery<PreInstallForm> query = em.createNamedQuery("PreInstallForm.findByIdPreForm", PreInstallForm.class);
        query.setParameter("idPreForm", idPreForm);
        PreInstallForm installForm = null;
        
        try {// essai
            installForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return installForm;  
    }// fin getInstallByIdPreForm()
    
    /**
     * Rechercher une fiche de pre installation à partir du nom du client
     * @param nomClient
     * @return 
     */
    public PreInstallForm getInstallByNomClient(String nomClient) throws DAOException {
        TypedQuery<PreInstallForm> query = em.createNamedQuery("PreInstallForm.findByNomClient", PreInstallForm.class);
        query.setParameter("nomClient", nomClient);
        PreInstallForm installForm = null;
        
        try {// essai
            installForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return installForm;  
    }// fin getInstallByNomClient()
    
    /**
     * Rechercher une fiche de pre installation à partir du jour
     * @param jour
     * @return 
     */
    public PreInstallForm getPreInstallByJour(Date jour) throws DAOException {
        TypedQuery<PreInstallForm> query = em.createNamedQuery("PreInstallForm.findByJour", PreInstallForm.class);
        query.setParameter("jour", jour); 
        PreInstallForm installForm = null;
        
        try {// essai
            installForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return installForm;   
    }// fin getPreInstallByJour()
    
    /**
     * Mise à jour fiche de pre installation 
     * @param preInstallForm
     * @return 
     */
    public PreInstallForm update(PreInstallForm preInstallForm) throws DAOException {
        try {// essai 
            return em.merge(preInstallForm); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer une fiche de pre installation 
     * @param preInstallForm 
     */
    public void delete(PreInstallForm preInstallForm) throws DAOException {
        try {// essai 
            em.remove(em.merge(preInstallForm));   
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin de delete()
    
    /**
     * Pre Install Form
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomClient
     * @param nomHardware
     * @return
     */
    public PreInstallForm create(Date jour, Date hdebut, Date hfin, String nomClient, String nomHardware) throws DAOException {
        try {// essai 
            PreInstallForm installForm = new PreInstallForm(jour, hdebut, hfin, nomClient, nomHardware);
            em.persist(installForm);
            
            return installForm;  
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de create()
    
    /**
     * Liste un fiche de reception à partir du nom de receveur
     * @param idPreForm
     * @param jour
     * @return
     */
    public Collection<PreInstallForm> getPreInstallFormByNomClient(Long idPreForm, Date jour) throws DAOException {
        try {// essai 
            TypedQuery<PreInstallForm> query = em.createNamedQuery("PreInstallForm.downloadFile", PreInstallForm.class);
            query.setParameter("idPreForm", idPreForm);
            query.setParameter("jour", jour); 
            // PreInstallForm preInstallForm = null;
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ..catch 
    }// fin getPreInstallFormByNomClient()
    
}// fin de la classe PreInstallFormEJB 

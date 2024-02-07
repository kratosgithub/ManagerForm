/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

// import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.InstallFormTypec;
// import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers 
 * les interfaces et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class InstallFormTypecEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * InstallForm WP21A : compter les fiches d'installation WP21A existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("InstallFormTypec.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les fiches d'installation 
     * @return
     */
    public List<InstallFormTypec> getAllInstallTypec() throws DAOException {
        // TypedQuery<InstallFormTypec> query = em.createNamedQuery("InstallFormTypec.findAll", InstallFormTypec.class);
        
        try {// essai
            TypedQuery<InstallFormTypec> query = em.createNamedQuery("InstallFormTypec.findAll", InstallFormTypec.class); 
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de getAllInstallTypec()
    
    /**
     * Rechercher une fiche d'installation à partir de son id
     * @param idFormC 
     * @return 
     */
    public InstallFormTypec getInstallByIdFormC(Long idFormC) throws DAOException {
        TypedQuery<InstallFormTypec> query = em.createNamedQuery("InstallFormTypec.findByIdFormC", InstallFormTypec.class);
        query.setParameter("idFormC", idFormC);
        InstallFormTypec formTypec = null;
        
        try {// essai
            formTypec = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return formTypec;  
    }// fin getInstallByIdFormC()
    
    /**
     * Rechercher une fiche d'installation à partir du jour
     * @param jour
     * @return 
     */
    public InstallFormTypec getInstallByJour(Date jour) throws DAOException {
        TypedQuery<InstallFormTypec> query = em.createNamedQuery("InstallFormTypec.findByJour", InstallFormTypec.class);
        query.setParameter("jour", jour); 
        InstallFormTypec formTypec = null;
        
        try {// essai
            formTypec = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return formTypec;  
    }// fin getInstallByJour()
    
     /**
     * Mise à jour fiche d'installation 
     * @param installFormTypec
     * @return 
     */
    public InstallFormTypec update(InstallFormTypec installFormTypec) throws DAOException {
        try {// essai 
            return em.merge(installFormTypec); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer une fiche d'installation
     * @param installFormTypec
     */
    public void delete(InstallFormTypec installFormTypec) throws DAOException {
        try {// essai 
            em.remove(em.merge(installFormTypec));  
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin delete()
    
    /**
     * Install Form Type C
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomCusto
     * @param nomEqpmt
     * @return 
     */
    public InstallFormTypec create(Date jour, Date hdebut, Date hfin, String nomCusto, String nomEqpmt) throws DAOException {
        try {// essai 
            InstallFormTypec formTypec = new InstallFormTypec(jour, hdebut, hfin, nomCusto, nomEqpmt); 
            em.persist(formTypec);
            
            return formTypec;  
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de create()
    
    /**
     * Liste un fiche d'installation WP21A à partir du nom du client
     * @param idFormC
     * @param jour
     * @return
     */
    public Collection<InstallFormTypec> getInstallFormTypecByNomCusto(Long idFormC, Date jour) throws DAOException {
        try {// essai 
            TypedQuery<InstallFormTypec> query = em.createNamedQuery("InstallFormTypec.downloadFile", InstallFormTypec.class);
            query.setParameter("idFormC", idFormC);
            query.setParameter("jour", jour);
            // ReceiptForm receiptForm = null;
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ..catch 
    }// fin getInstallFormTypecByNomCusto()
    
}//fin de la classe InstallFormTypecEJB 

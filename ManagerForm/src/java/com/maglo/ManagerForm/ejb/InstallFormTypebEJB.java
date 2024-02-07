/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
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
import com.maglo.ManagerForm.entities.InstallFormTypeb;
// import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers 
 * les interfaces et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class InstallFormTypebEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * Lister les fiches d'installation 
     * @return
     */
    public List<InstallFormTypeb> getAllInstallTypeb() throws DAOException {
        // TypedQuery<InstallFormTypeb> query = em.createNamedQuery("InstallFormTypeb.findAll", InstallFormTypeb.class);
        
        try {// essai
            TypedQuery<InstallFormTypeb> query = em.createNamedQuery("InstallFormTypeb.findAll", InstallFormTypeb.class); 
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de getAllInstallTypeb()
    
    /**
     * Rechercher une fiche d'installation à partir de son id
     * @param idFormB 
     * @return 
     */
    public InstallFormTypeb getInstallByIdFormB(Long idFormB) throws DAOException {
        TypedQuery<InstallFormTypeb> query = em.createNamedQuery("InstallFormTypeb.findByIdFormB", InstallFormTypeb.class);
        query.setParameter("idFormB", idFormB);
        InstallFormTypeb formTypeb = null;
        
        try {// essai
            formTypeb = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return formTypeb;  
    }// fin getInstallByIdFormB()
    
    /**
     * Rechercher une fiche d'installation à partir du jour
     * @param jour
     * @return 
     */
    public InstallFormTypeb getInstallByJour(Date jour) throws DAOException {
        TypedQuery<InstallFormTypeb> query = em.createNamedQuery("InstallFormTypeb.findByJour", InstallFormTypeb.class);
        query.setParameter("jour", jour); 
        InstallFormTypeb formTypeb = null;
        
        try {// essai
            formTypeb = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return formTypeb;  
    }// fin getInstallByJour()
    
    /**
     * Mise à jour fiche d'installation 
     * @param installFormTypeb
     * @return 
     */
    public InstallFormTypeb update(InstallFormTypeb installFormTypeb) throws DAOException {
        try {// essai 
            return em.merge(installFormTypeb); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer une fiche d'installation
     * @param installFormTypeb
     */
    public void delete(InstallFormTypeb installFormTypeb) throws DAOException {
        try {// essai 
            em.remove(em.merge(installFormTypeb));  
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin delete()
    
    /**
     * Install Form Type B
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomCtmer
     * @param nomHrdw
     * @return 
     */
    public InstallFormTypeb create(Date jour, Date hdebut, Date hfin, String nomCtmer, String nomHrdw) throws DAOException {
        try {// essai 
            InstallFormTypeb formTypeb = new InstallFormTypeb(jour, hdebut, hfin, nomCtmer, nomHrdw); 
            em.persist(formTypeb);
            
            return formTypeb; 
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de create()
    
}// fin de la classe InstallFormTypebEJB 

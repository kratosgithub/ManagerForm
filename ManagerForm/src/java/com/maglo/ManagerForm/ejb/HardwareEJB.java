/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.maglo.ManagerForm.entities.Hardware;
// import com.maglo.ManagerForm.entities.HardwarePK;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers les interfaces
 * du systeme et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HardwareEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    // Definition des methodes de la classe 
    
    /**
     * Hardware : compter les equipements existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("Hardware.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les equipements
     * @return
     */
    public List<Hardware> getAllHardware() throws DAOException {
        // TypedQuery<Hardware> query = em.createNamedQuery("Hardware.findAll", Hardware.class);
        
        try {// essai
            TypedQuery<Hardware> query = em.createNamedQuery("Hardware.findAll", Hardware.class); 
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de getAllHardware()
    
    /**
     * Rechercher un equipement à partir de son nom
     * @param nomHardware
     * @return 
     */
    public Hardware getHardwareByNomHardware(String nomHardware) throws DAOException {
        TypedQuery<Hardware> query = em.createNamedQuery("Hardware.findByNomHardware", Hardware.class);
        query.setParameter("nomHardware", nomHardware);
        Hardware hardware = null;
        
        try {// essai
            hardware = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return hardware; 
    }// fin de getHardwareByNomHardware()
    
    /**
     * Rechercher un equipement à partir de son nom
     * @param noSerie
     * @return 
     */
    public Hardware getHardwareByNoSerie(String noSerie) throws DAOException {
        TypedQuery<Hardware> query = em.createNamedQuery("Hardware.findByNoSerie", Hardware.class);
        query.setParameter("noSerie", noSerie);
        Hardware hardware = null;
        
        try {// essai
            hardware = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return hardware; 
    }// fin de getHardwareByNomHardware()
    
    /**
     * Rechercher un equipement à partir de son identifiant
     * @param idHardware
     * @return
     */
    public Hardware getHardwareByIdHardware(Long idHardware) throws DAOException {
        
        try {// essai 
            return (Hardware) em.find(Hardware.class, idHardware);
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
        
    }// fin getHardwareByIdHardware()
    
    /**
     * Mise à jour d'un equipement
     * @param hardware
     * @return 
     */
    public Hardware update(Hardware hardware) throws DAOException {
        try {// essai 
            return em.merge(hardware); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer un equipement
     * @param hardware
     */
    public void delete(Hardware hardware) throws DAOException {
        try {// essai 
            em.remove(em.merge(hardware));  
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin delete()
    
    /**
     * Hardware 
     * Methode : constructeur avec parametres
     * @param idHardware 
     * @param nomHardware
     * @param marque 
     * @param model 
     * @param noSerie 
     * @param poids 
     * @param longueur 
     * @param largeur 
     * @param hauteur 
     * @param puissanceMin 
     * @param puissanceMax 
     * @param fqceMin 
     * @param fqceMax 
     * @return
     */
    public Hardware create(Long idHardware, String nomHardware, String marque, String model, String noSerie, int poids, int longueur, int largeur, int hauteur, int puissanceMin, int puissanceMax, int fqceMin, 
            int fqceMax) throws DAOException {
        try {// essai 
            Hardware hardware = new Hardware(idHardware, nomHardware, marque, model, noSerie, poids, longueur, largeur, hauteur, puissanceMin, puissanceMax, fqceMin, fqceMax);
            em.persist(hardware);
            
            return hardware;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de create()
    
    /**
     * Liste un fiche de reception à partir du nom de receveur
     * @param noSerie
     * @param marque
     * @return
     */
    public Collection<Hardware> getHardwareByNoSerie(String noSerie, String marque) throws DAOException {
        try {// essai 
            TypedQuery<Hardware> query = em.createNamedQuery("Hardware.downlaodFile", Hardware.class);
            query.setParameter("noSerie", noSerie);
            query.setParameter("marque", marque);
            // Hardware hardware = null;
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
        
    }// fin getHardwareByNoSerie()
    
}// fin de la classe HardwareEJB 

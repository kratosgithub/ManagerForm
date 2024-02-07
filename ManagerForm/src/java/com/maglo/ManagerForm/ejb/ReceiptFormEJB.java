/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.Collection;
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
// import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.ReceiptForm;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers 
 * les interfaces et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReceiptFormEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * ReceiptForm : compter les fiches de réception existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("ReceiptForm.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les fiches de reception 
     * @return
     */
    public List<ReceiptForm> getAllReceiptForm() throws DAOException {
        // TypedQuery<ReceiptForm> query = em.createNamedQuery("ReceiptForm.findAll", ReceiptForm.class);
        
        try {// essai
            TypedQuery<ReceiptForm> query = em.createNamedQuery("ReceiptForm.findAll", ReceiptForm.class);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin getAllReceiptForm()
    
    /**
     * Liste un fiche de reception à partir du nom de receveur
     * @param nomReceipt 
     * @param destination
     * @return
     */
    public Collection<ReceiptForm> getReceiptFormByNom(String nomReceipt, String destination) throws DAOException {
        try {// essai 
            TypedQuery<ReceiptForm> query = em.createNamedQuery("ReceiptForm.downlaodReceipt", ReceiptForm.class);
            query.setParameter("nomReceipt", nomReceipt);
            query.setParameter("destination", destination);
            // ReceiptForm receiptForm = null;
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
        
        // return (List<ReceiptForm>) receiptForm; 
    }// fin getReceiptFormByNom()
    
    /**
     * Rechercher une fiche d'enlevement à partir de son id
     * @param idReceiptForm
     * @return 
     */
    public ReceiptForm getReceiptById(Long idReceiptForm) throws DAOException {
        TypedQuery<ReceiptForm> query = em.createNamedQuery("ReceiptForm.findByIdReceiptForm", ReceiptForm.class);
        query.setParameter("idReceiptForm", idReceiptForm);
        ReceiptForm receiptForm = null;
        
        try {// essai
            receiptForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return receiptForm; 
    }// fin getReceiptById()
    
    /**
     * Rechercher une fiche de reception à partir du jour
     * @param jour
     * @return 
     */
    public ReceiptForm getReceiptByJour(Date jour) throws DAOException {
        TypedQuery<ReceiptForm> query = em.createNamedQuery("ReceiptForm.findByJour", ReceiptForm.class);
        query.setParameter("jour", jour); 
        ReceiptForm receiptForm = null;
        
        try {// essai
            receiptForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return receiptForm;  
    }// fin getReceiptByJour()
    
    /**
     * Rechercher une fiche de reception à partir du nom de receveur
     * @param nomReceipt 
     * @return 
     */
    public ReceiptForm getReceiptByNom(String nomReceipt) throws DAOException {
        TypedQuery<ReceiptForm> query = em.createNamedQuery("ReceiptForm.findByNomReceipt", ReceiptForm.class);
        query.setParameter("nomReceipt", nomReceipt); 
        ReceiptForm receiptForm = null;
        
        try {// essai
            receiptForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return receiptForm;  
    }// fin getReceiptByNom()
    
    /**
     * Rechercher une reception à partir du nom du materiel et du nom du client
     * @param nomCst
     * @param nomHard
     * @return
     */
    public ReceiptForm getReceiptByHardware(String nomCst, String nomHard) throws DAOException {
        TypedQuery<ReceiptForm> query = em.createNamedQuery("ReceiptForm.findByHardware", ReceiptForm.class);
        query.setParameter("idCst", nomCst);
        query.setParameter("idHard", nomHard);
        
        ReceiptForm receiptForm = null;
        
        try {// essai 
            receiptForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin try ... catch 
        
        return receiptForm;
    }// fin getReceiptByHardware()
    
    /**
     * Mise à jour fiche de receiption 
     * @param receiptForm 
     * @return 
     */
    public ReceiptForm update(ReceiptForm receiptForm) throws DAOException {
        try {// essai 
            return em.merge(receiptForm); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer une fiche d'enlevement 
     * @param receiptForm
     */
    public void delete(ReceiptForm receiptForm) throws DAOException {
        try {// essai 
            em.remove(em.merge(receiptForm));   
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin delete()
    
    /**
     * Receipt Form
     * Methode : constructeur avec parametres 
     * @param jour 
     * @param harrivee 
     * @param destination 
     * @param expediteur 
     * @param pays 
     * @param accessoires 
     * @param jourReceipt 
     * @param hreceipt 
     * @param nomReceipt 
     * @param nomClt
     * @param nomHard 
     * @return 
     */
    public ReceiptForm create(Date jour, Date harrivee, String destination, String expediteur, String pays, String accessoires, Date jourReceipt, Date hreceipt,
            String nomReceipt, String nomClt, String nomHard) throws DAOException {
        try {// essai 
            ReceiptForm receiptForm = new ReceiptForm(jour, harrivee, destination, expediteur, pays, accessoires, jourReceipt, hreceipt, nomReceipt, nomClt, nomHard);
            receiptForm.setNomClt(nomClt);
            receiptForm.setNomHard(nomHard);
            
            em.persist(receiptForm);
            
            return receiptForm;   
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin create()
    
}// fin de la classe ReceiptFormEJB 

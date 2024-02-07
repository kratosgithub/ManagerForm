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

// import com.maglo.ManagerForm.entities.ActionForm;
// import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.entities.HistoryRecord;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers les interfaces 
 * du systeme et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HistoryRecordEJB {
    
    // Declaration des variables de la classe 
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * History record : compter les fiches d'historique des equipements
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("HistoryRecord.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les fiches d'historique 
     * @return
     */
    public List<HistoryRecord> getAllHistoryRecord() throws DAOException {
        // TypedQuery<HistoryRecord> query = em.createNamedQuery("HistoryRecord.findAll", HistoryRecord.class);
        
        try {// essai
            TypedQuery<HistoryRecord> query = em.createNamedQuery("HistoryRecord.findAll", HistoryRecord.class); 
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin try ... catch 
    }// fin de getAllHistoryRecord()
    
    /**
     * Rechercher une fiche d'historique à partir de son id
     * @param idRecord
     * @return 
     */
    public HistoryRecord getHistoryByIdRecord(Long idRecord) throws DAOException {
        TypedQuery<HistoryRecord> query = em.createNamedQuery("HistoryRecord.findByIdRecord", HistoryRecord.class);
        query.setParameter("idRecord", idRecord);
        HistoryRecord hr = null;
        
        try {// essai
            hr = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return hr; 
    }// fin de getHistoryByIdRecord()
    
    /**
     * Rechercher une fiche d'historique à partir de l'id equipement
     * @param idHardware
     * @return 
     */
    public HistoryRecord getHistoryByIdHardware(int idHardware) {
        TypedQuery<HistoryRecord> query = em.createNamedQuery("HistoryRecord.findByIdHardware", HistoryRecord.class);
        query.setParameter("idHardware", idHardware); 
        HistoryRecord hr = null;
        
        try {// essai
            hr = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return hr; 
    }// fin de getHistoryByIdHardware()
    
    /**
     * Rechercher une fiche d'historique à partir du jour
     * @param jour
     * @return 
     */
    public HistoryRecord getHistoryByJour(Date jour) throws DAOException {
        TypedQuery<HistoryRecord> query = em.createNamedQuery("HistoryRecord.findByJour", HistoryRecord.class);
        query.setParameter("jour", jour); 
        HistoryRecord hr = null;
        
        try {// essai
            hr = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return hr; 
    }// fin de getHistoryByJour()
    
    /**
     * Mise à jour fiche historique
     * @param historyRecord
     * @return 
     */
    public HistoryRecord update(HistoryRecord historyRecord) throws DAOException {
        try {// essai 
            return em.merge(historyRecord); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer une fiche d'historique
     * @param historyRecord
     */
    public void delete(HistoryRecord historyRecord) throws DAOException {
        try {// essai 
            em.remove(em.merge(historyRecord));  
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin de delete()
    
    /**
     * History Record
     * Methode : constructeur avec parametres
     * @param idHardware 
     * @param jour
     * @param motif 
     * @param idAction
     * @param nomAgt
     * @return
     */
    public HistoryRecord create(int idHardware, Date jour, String motif, int idAction, String nomAgt) throws DAOException {
        try {// essai ... 
            HistoryRecord record = new HistoryRecord(idHardware, jour, motif, idAction, nomAgt);
            em.persist(record);
            
            return record;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin de try ... catch 
    }// fin de create()
    
    /**
     * Liste un fiche d'intervention à partir du nom du client
     * @param idRecord
     * @param jour
     * @return
     */
    public Collection<HistoryRecord> getRecordByNomHardware(Long idRecord, Date jour) throws DAOException {
        try {// essai 
            TypedQuery<HistoryRecord> query = em.createNamedQuery("HistoryRecord.downloadFile", HistoryRecord.class);
            query.setParameter("idRecord", idRecord);
            query.setParameter("jour", jour);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ..catch 
    }// fin getRecordByNomHardware()
    
}// fin de la classe HistoryRecordEJB

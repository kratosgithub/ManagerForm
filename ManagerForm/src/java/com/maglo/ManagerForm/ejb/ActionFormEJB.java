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

import com.maglo.ManagerForm.entities.ActionForm;
// import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 */

@Stateless 
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ActionFormEJB {
    
    // Declaration des variables de la classe
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    /**
     * Methodes utilitaires ----------------------------------------------------
     */
    
    /**
     * Agents : compter les fiches d'interventions existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("AcctionForm.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les fiches d'intervention
     * @return
     */
    public List<ActionForm> getAllActionForm() throws DAOException {
        // TypedQuery<ActionForm> query = em.createNamedQuery("ActionForm.findAll", ActionForm.class);
        
        try {// essai
            TypedQuery<ActionForm> query = em.createNamedQuery("ActionForm.findAll", ActionForm.class);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ... catch 
    }// fin de la methode getAllActionForm()
    
    /**
     * Lister les fiches d'intervention
     * @param idAction
     * @return 
     */
    public ActionForm getActionFormByIdAction(Long idAction) throws DAOException {
        TypedQuery<ActionForm> query = em.createNamedQuery("ActionForm.findByIdAction", ActionForm.class);
        query.setParameter("idAction", idAction);
        ActionForm actionForm = null;
        
        try {// essai
            actionForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return actionForm;
    }// fin de la getActionFormByIdAction()
    
    /**
     * Lister les fiches d'intervention
     * @param jour
     * @return 
     */
    public ActionForm getActionFormByJour(Date jour) throws DAOException {
        TypedQuery<ActionForm> query = em.createNamedQuery("ActionForm.findByJour", ActionForm.class);
        query.setParameter("jour", jour);
        ActionForm actionForm = null;
        
        try {// essai
            actionForm = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return actionForm;
    }// fin de getActionFormByJour() 
    
    /**
     * Mise à jour des fiches d'intervention
     * @param actionForm 
     * @return 
     */
    public ActionForm update(ActionForm actionForm) throws DAOException {
        try {// essai 
            return em.merge(actionForm);
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer un fiche d'intervention
     * @param actionForm
     */
    public void delete(ActionForm actionForm) throws DAOException {
        try {// essai 
            em.remove(em.merge(actionForm)); 
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin du try ... catch 
    }// fin de delete() 
    
    /**
     * Creer une fiche d'intervention
     * @param jour
     * @param harrivee 
     * @param hfin
     * @param statut
     * @param nomCustomer
     * @param nomHdre
     * @return
     */
    public ActionForm create(Date jour, Date harrivee, Date hfin, String nomCustomer, String nomHdre, String statut) throws DAOException {
        try {// essai 
            ActionForm actionForm = new ActionForm(jour, harrivee, hfin, nomCustomer, nomHdre, statut);
            em.persist(actionForm);
            
            return actionForm;
        } catch (Exception e) {
             Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
             e.printStackTrace();
             
             throw new DAOException(e); 
        }// fin try ... catch
    }// fin de create()
    
    /**
     * Liste un fiche d'intervention à partir du nom du client
     * @param idAction
     * @param jour
     * @return
     */
    public Collection<ActionForm> getActionFormByNomCustomer(Long idAction, Date jour) throws DAOException {
        try {// essai 
            TypedQuery<ActionForm> query = em.createNamedQuery("ActionForm.downloadFile", ActionForm.class);
            query.setParameter("idAction", idAction);
            query.setParameter("jour", jour);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ..catch 
    }// fin getActionFormByNomCustomer()
    
}// fin de la classe ActionFormEJB

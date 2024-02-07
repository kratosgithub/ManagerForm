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

import com.maglo.ManagerForm.entities.Agents;
// import com.maglo.ManagerForm.entities.AgentsPK;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requêtes de l'utilisateur à travers les interfaces
 * du systeme et la BDD.
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AgentsEJB {
    
    // Declaration des variables de la classe
    @PersistenceContext(name = "ManagerFormPU")
    private EntityManager em;
    
    // Methodes de la classe
    
    /**
     * Agents : compter les agents existants
     * @return 
     */
    public long getCountAll() throws DAOException {
        try {// essai .. 
            TypedQuery<Long> query = em.createNamedQuery("Agents.countAll", Long.class);
            
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try .. catch 
    }// fin getCountAll()
    
    /**
     * Lister les agents
     * @return
     */
    public List<Agents> getAllAgents() throws DAOException {
        // TypedQuery<Agents> query = em.createNamedQuery("Agents.findAll", Agents.class);
        
        try {// essai
            TypedQuery<Agents> query = em.createNamedQuery("Agents.findAll", Agents.class);
            
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException(e);
        }// fin try ... catch 
    }// fin de getAllAgents()
    
    /**
     * Rechercher un agent à partir de son nom
     * @param nomAgent
     * @return 
     */
    public Agents getAgentsByNomAgent(String nomAgent) throws DAOException {
        TypedQuery<Agents> query = em.createNamedQuery("Agents.findByNomAgent", Agents.class);
        query.setParameter("nomAgent", nomAgent);
        Agents agents = null;
        
        try {// essai
            agents = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult() throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }// fin  try ... catch 
        
        return agents;
    }// fin de getAgentByNomAgent()
    
    /**
     * Mise à jour d'un agent
     * @param agents
     * @return 
     */
    public Agents update(Agents agents) throws DAOException {
        try {// essai 
            return em.merge(agents);
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin update()
    
    /**
     * Supprimer un agent
     * @param agents
     */
    public void delete(Agents agents) throws DAOException {
        try {// essai 
            em.remove(em.merge(agents)); 
        } catch (Exception e) {
            throw new DAOException(e); 
        }// fin du try ... catch 
    }// fin delete()
   
    /**
     * Creer un agent
     * @param idAgent 
     * @param nomAgent
     * @param telAgent 
     * @return
     */
    public Agents create(Long idAgent, String nomAgent, String telAgent) throws DAOException {
        try {// essai 
            Agents agts = new Agents(idAgent, nomAgent, telAgent); 
            em.persist(agts);
            
            return agts;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
             
            throw new DAOException(e); 
        }// fin try .. catch 
    }// fin de create()
    
}// fin de la classe AgentsEJB

/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;

import com.maglo.ManagerForm.entities.Agents;
// import com.maglo.ManagerForm.entities.AgentsPK;

/**
 *
 * @author NDOZENG
 * Interface Agents permettant de definir les operations effectuees sur les agents.
 */

public interface IAgents {
    
    /**
     * Lister les agents
     * @return
     */
    public List<Agents> getAllAgents();
    
    /**
     * Rechercher un agent à partir de son nom
     * @param nomAgent
     * @return 
     */
    public Agents getAgetnsByNomAgent(String nomAgent); 
    
    /**
     * Mise à jour d'un agent
     * @param agents
     * @return 
     */
    public Agents update(Agents agents);
    
    /**
     * Supprimer un agent
     * @param agents
     */
    public void delete(Agents agents); 
    
    /**
     * Creer un agent
     * @param nomAgent
     * @param telAgent
     * @return
     */
    public Agents create(String nomAgent, String telAgent); 
    
}//fin de l'interface IAgents

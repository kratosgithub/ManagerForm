/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.entities.Agents;
// import com.maglo.ManagerForm.entities.AgentsPK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant d'afficher un technicien en chargeant ses informations
 * depuis la base de données.
 */

@Named(value = "detailAgentsController")
@ViewScoped 
public class DetailAgentsController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailAgentsController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private AgentsEJB aejb;
    
    private Agents agents;
    private String nomAgent;
    
    /**
     * Agents : getAgents()
     * @return
     */
    public Agents getAgents() {
        return agents;
    }// fin de getAgents()
    
    /**
     * Agents : setAgents()
     * @param agents
     */
    public void setAgents(Agents agents) {
        this.agents = agents;
    }// fin de setAgents()
    
    /**
     * Agents : getNomAgent()
     * @return
     */
    public String getNomAgent() {
        return nomAgent;
    }// fin getNomAgent()
    
    /**
     * Agents : setNomAgent()
     * @param nomAgent
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent.toUpperCase();
    }// fin setNomAgent()
    
    /**
     * Agents : loadAgent()
     * Methode permettant de charger un technicien à partir de son nom
     */
    public void loadAgent() {
        agents = aejb.getAgentsByNomAgent(nomAgent); 
    }// fin loadAgent()
    
    /**
     * Agents : updateAgents()
     * @param agents
     * @return
     */
    public String updateAgents(Agents agents) {
        // Mettre à jour l'agent
        aejb.update(agents);
        
        // Logger
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations de M. " + agents.getNomAgent() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/detailAgent";
    }// fin updateAgents()
    
    /**
     * Agents : deleteAgents()
     * @param agents
     * @return
     */
    public String deleteAgents(Agents agents) {
        // Supprime l'agent
        aejb.delete(agents);
        
        // Logger
        logger.info("DELETE : Le technicien a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Le technicien dénommé M. " + agents.getNomAgent() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listAgents";
    }// fin deleteAgents()
    
}// fin de la classe DetailAgentsController

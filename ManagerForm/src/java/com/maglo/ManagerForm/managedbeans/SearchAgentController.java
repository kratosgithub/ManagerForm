/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.entities.Agents;
// import com.maglo.ManagerForm.entities.AgentsPK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * classe utilitaire permettant de trouver un technicien enregistré dans le systeme 
 * à partir de son nom. 
 */

@ManagedBean
@SessionScoped 
public class SearchAgentController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(SearchAgentController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private AgentsEJB aejb;
    
    private Agents agents;
    private String nomAgent;
    
    /**
     * Agents : getAgents
     * @return
     */
    public Agents getAgents() {
        return agents;
    }// fin getAgents()
    
    /**
     * Agents : setAgents()
     * @param agents
     */
    public void setAgents(Agents agents) {
        this.agents = agents;
    }// fin setAgents()
    
    /**
     * Agent : getNomAgent()
     * @return
     */
    public String getNomAgent() {
        return nomAgent;
    }// fin getNomAgent()
    
    /**
     * Agent : setNomAgent()
     * @param nomAgent
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent.toUpperCase(); 
    }// fin setNomAgent()
    
    /**
     * Agent : loadAgent()
     * Methode permettant de charger les informations de l'agent dont le nom est 
     * demande en parametre
     */
    public void loadAgent() {
        agents = aejb.getAgentsByNomAgent(nomAgent); 
    }// fin loadAgent()
    
    /**
     * Agent : validateNomAgent()
     * @return
     */
    public String validateNomAgent() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {// essai ...
            aejb.getAgentsByNomAgent(nomAgent);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Aucun technicien n'a été trouvé."));
            
            return "/managers/searchAgent";
        }// fin try .. catch 
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        sessionMap.put("Agents", agents);
        
        // Condition sur la validité du nom saisi
        if(aejb.getAgentsByNomAgent(nomAgent) != null) {
            return "/managers/showAgent?faces-redirect=true";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Aucun technicien n'a été trouvé."));
            
            // on reste sur la page courante
            return "/managers/searchAgent";
        }// fin if .. else 
    }// fin de validateNomAgent()
    
    /**
     * Agent : deleteAgent()
     * Methode permettant de supprimer un technicien si besoin
     * @param agents
     * @return
     */
    public String deleteAgent(Agents agents) {
        // on appelle la methode de suppression 
        aejb.delete(agents);
        
        // Logger
        logger.info("DELETE : le technicien a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message à afficher
        Util.messageInfo("Le dénommé technicien : " + agents.getNomAgent() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listAgents";
    }// fin deleteAgent()
    
    /**
     * Agent : updateAgent()
     * Methode permettant de mettre à jour un technicien
     * @param agents
     * @return
     */
    public String updateAgent(Agents agents) {
        // on appelle la methode de mise à jour
        aejb.update(agents);
        
        // Logger
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message à afficher
        Util.messageInfo("Les informations de : " + agents.getNomAgent() + " ont été mises à jour aves succès.", "Information :");
        
        return "/managers/showAgent";
    }// fin updateAgent()
    
}// fin de la classe SearchAgentController 

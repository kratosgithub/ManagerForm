/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

// import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
// import javax.faces.component.UIComponent;
// import javax.faces.component.UIInput;
// import javax.faces.context.FacesContext;
// import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.entities.Agents;
// import com.maglo.ManagerForm.entities.AgentsPK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de créer un technicien dans le système.
 */

@ManagedBean
@SessionScoped 
public class AddAgtsController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddAgtsController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private AgentsEJB aejb;
    
    private Long idAgent;
    private String nomAgent;
    private String telAgent;
    
    /**
     * Agents : getIdAgent()
     * @return 
     */
    public Long getIdAgent() {
        return idAgent;
    }// fin getIdAgent()

    /**
     * Agents : setIdAgent()
     * @param idAgent 
     */
    public void setIdAgent(Long idAgent) {
        this.idAgent = idAgent;
    }// fin setIdAgent()
    
    /**
     * NomAgent : getNomAgent()
     * @return
     */
    public String getNomAgent() {
        return nomAgent;
    }// fin getNomAgent()
    
    /**
     * NomAgent : setNomAgent()
     * @param nomAgent
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent.toUpperCase();
    }// fin setNomAgent()
    
    /**
     * TelAgent : getTelAgent()
     * @return
     */
    public String getTelAgent() {
        return telAgent;
    }// fin getTelAgent()
    
    /**
     * TelAgent : setTelAgent()
     * @param telAgent
     */
    public void setTelAgent(String telAgent) {
        this.telAgent = telAgent;
    }// fin setTelAgent()
     
    /**
     * Agent : saveAgent()
     * Description : Méthode permettant d'enregistrer un nouvel agent ou technicien
     * @return 
     */
    public String saveAgent() {
        // Initialisation d'un nouveau agent
        Agents agents = new Agents(idAgent, nomAgent, telAgent);
        
        // On crée le nouveau agent
        aejb.create(idAgent, nomAgent, telAgent);
        
        // Logger Info
        logger.info("CREATE : Un nouvel agent a été enregistré avec succès : " + agents.getNomAgent() + ". Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération 
         * Util.addFlashInfoMessage("M/Mme. " + user.getPrenom() + " " + user.getNom() + " a été créé avec succès.");
         */
        Util.messageInfo("M/Mme. " + agents.getNomAgent() + " a été enregistré avec succès.", "Information :");
        
        return "/managers/newAgents"; 
    }// fin saveAgent()
    
}// fin de la classe AddAgtsController

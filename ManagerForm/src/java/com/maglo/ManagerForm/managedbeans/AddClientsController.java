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

import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.entities.ClientsPK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de créer un client dans le système.
 */

@ManagedBean
@SessionScoped 
public class AddClientsController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddClientsController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private ClientsEJB cejb;
    
    private Long idClient;
    private String nomClient;
    private String service;
    private String ville;
    private String telClient;
    
    /**
     * idClient : getIdClient()
     * @return 
     */
    public Long getIdClient() {
        return idClient;
    }// fin getIdClients()

    /**
     * idClient : setIdClient()
     * @param idClient 
     */
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }// fin setIdClient()
    
    /**
     * NomClient : getNomClient()
     * @return
     */
    public String getNomClient() {
        return nomClient;
    }// fin getNomClient()
    
    /**
     * NomClient : setNomClient()
     * @param nomClient
     */
    public void setNomClient(String nomClient) {
       this.nomClient = nomClient.toUpperCase();
    }// fin setNomClient()
    
    /**
     * Service : getService()
     * @return
     */
    public String getService() {
        return service;
    }// fin getService()
    
    /**
     * Service : setService()
     * @param service
     */
    public void setService(String service) {
        this.service = service.toUpperCase();
    }// fin setService()
    
    /**
     * Ville : getVille()
     * @return
     */
    public String getVille() {
        return ville;
    }// fin getVille()
    
    /**
     * Ville : setVille()
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville.toUpperCase();
    }// fin setVille()
    
    /**
     * Ville : getTelClient()
     * @return
     */
    public String getTelClient() {
        return telClient;
    }// fin getTelClient()
    
    /**
     * Ville : setTelClient()
     * @param telClient
     */
    public void setTelClient(String telClient) {
        this.telClient = telClient; 
    }// fin setTelClient()
    
    /**
     * Client : saveClient()
     * Description : Méthode permettant d'enregistrer un nouveau client
     * @return 
     */
    public String saveClient() {
        // Initialisation d'un nouveau client
        Clients clients = new Clients(idClient, nomClient, service, ville, telClient);
        
        // On crée le nouveau client
        cejb.create(idClient, nomClient, service, ville, telClient); 
        
        // Logger info
        logger.info("CREATE : Un nouveau client a été créé avec succès : " + clients.getNomClient() + ". Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération 
         * Util.addFlashInfoMessage("M/Mme. " + user.getPrenom() + " " + user.getNom() + " a été créé avec succès.");
         */
        Util.messageInfo("Le client " + clients.getNomClient() + " siègeant à " + clients.getVille() + " a été enregistré avec succès.", "Information :");
        
        return "/managers/newClients"; 
    }// fin saveClient()
    
}// fin de la classe AddClientsController

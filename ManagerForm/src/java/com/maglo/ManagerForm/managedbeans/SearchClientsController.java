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

import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.entities.ClientsPK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de trouver un client enregistre dans le systeme à 
 * partir de son nom.
 */

@ManagedBean
@SessionScoped 
public class SearchClientsController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L; 
    
    private static final Logger logger = Logger.getLogger(SearchClientsController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private ClientsEJB cejb;
    
    private Clients clients;
    private String nomClient;
    
    /**
     * Clients : getClients()
     * @return
     */
    public Clients getClients() {
        return clients;
    }// fin getClients()
    
    /**
     * Clients : setClients()
     * @param clients
     */
    public void setClients(Clients clients) {
        this.clients = clients;
    }// fin setClients()
    
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
     * Clients : loadClient()
     * Charge le client à partir du nom passe en parametre
     */
    public void loadClient() {
        clients = cejb.getClientsByNomClient(nomClient);
    }// fin loadClient()
    
    /**
     * Client : validateNomClient()
     * @return
     */
    public String validateNomClient() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {// essai ...
            cejb.getClientsByNomClient(nomClient);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Aucun client n'a été trouvé."));
            
            return "/managers/searchClient";
        }// fin try .. catch 
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        sessionMap.put("Clients", clients);
        
        // Condition sur la validité du nom saisi
        if(cejb.getClientsByNomClient(nomClient) != null) {
            return "/managers/showClient?faces-redirect=true";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Aucun client n'a été trouvé."));
            
            // on reste sur la page courante
            return "/managers/searchClient";
        }// fin if .. else 
    }// fin de validateNomClient()
    
    /**
     * Clients : deleteClient()
     * @param clients
     * @return
     */
    public String deleteClient(Clients clients) {
        // On supprime un client 
        cejb.delete(clients);
        
        // Logger
        logger.info("DELETE : Le client a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message à afficher
        Util.messageInfo("Le client : " + clients.getNomClient() + " de " + clients.getVille() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listClients"; 
    }// fin deleteClient()
    
    /**
     * Clients : updateClient()
     * @param clients
     * @return 
     */
    public String updateClient(Clients clients) {
        // on met à jour un client
        cejb.update(clients);
        
        // Logger
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message à afficher
        Util.messageInfo("Les informations du client : " + clients.getNomClient() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/showClient";
    }// fin updateClient()
    
}// fin de la classe SearchClientsController

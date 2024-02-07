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

import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.entities.ClientsPK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant d'afficher un client en chargeant les informations
 * depuis la base de donnees.
 */

@Named(value = "detailClientsController")
@ViewScoped 
public class DetailClientsController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailClientsController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
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
     * Clients : getNomClient()
     * @return
     */
    public String getNomClient() {
        return nomClient;
    }// fin getNomClient()
    
    /**
     * Clients : setNomClient()
     * @param nomClient
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient.toUpperCase();
    }// fin setNomClient()
    
    /**
     * Client : loadClient()
     * Charge un client à partir de son nom
     */
    public void loadClient() {
        clients = cejb.getClientsByNomClient(nomClient);
    }// fin loadClient()
    
    /**
     * Client : updateClient()
     * @param clients
     * @return
     */
    public String updateClient(Clients clients) {
        // Mettre à jour le client
        cejb.update(clients);
        
        // Logger 
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations du client " + clients.getNomClient() + " de la ville de " + clients.getVille() + " a été mis à jour avec succès.", "Information :");
        
        return "/managers/detailClient";
    }// fin updateClient()
    
    /**
     * Client : deleteClient()
     * @param clients
     * @return
     */
    public String deleteClient(Clients clients) {
        // On supprime le client
        cejb.delete(clients);
        
        // Logger
        logger.info("DELETE : Le client a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Le client " + clients.getNomClient() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listClients";
    }// fin deleteClient()
    
}// fin de la classe DetailClientsController

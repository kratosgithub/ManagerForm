/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;

import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.entities.ClientsPK;

/**
 *
 * @author NDOZENG
 * Interface Clients permettant de definir les operations qui seront effectuees sur les clients
 */

public interface IClients {
    
    /**
     * Lister les clients
     * @return
     */
    public List<Clients> getAllClients();
    
    /**
     * Rechercher un client à partir de son nom
     * @param nomClient
     * @return 
     */
    public Clients getClientsByNomClient(String nomClient); 
    
    /**
     * Mise à jour d'un client
     * @param clients
     * @return 
     */
    public Clients update(Clients clients);
    
    /**
     * Supprimer un clients
     * @param clients
     */
    public void delete(Clients clients); 
    
    /**
     * Creer un client
     * @param nomClient
     * @param service
     * @param ville
     * @param telClient
     * @return 
     */
    public Clients create(String nomClient, String service, String ville, String telClient); 
    
}// fin de l'interface IClients

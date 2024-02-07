/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
// import java.util.Map;
// import java.util.HashMap;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
// import javax.faces.component.UIComponent;
// import javax.faces.component.UIInput;
// import javax.faces.context.FacesContext;
// import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.annotation.PostConstruct;

import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.ejb.ReceiptFormEJB;
import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.ReceiptForm;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant d'enregistrer une fiche de reception dans le système.
 */

@ManagedBean
@SessionScoped 
public class AddReceiptFormController implements Serializable {
    
    // Déclaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddReceiptFormController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private AgentsEJB aejb; 
    
    @EJB 
    private ClientsEJB cejb;
    
    @EJB
    private HardwareEJB hejb;
    
    @Inject
    private ReceiptFormEJB rfejb;
    
    // Variables 
    private String nomClient;
    private String nomHrd;
    private Date jour;
    private Date harrivee; 
    // private int marrivee;
    private String destination;
    private String expediteur;
    private String pays;
    private String accessoires;
    private Date jourReceipt;
    private Date hreceipt;
    // private int mreceipt;
    private String nomReceipt;
    private Clients nomClt;
    private Hardware nomHard;
    
    private LocalDateTime minJour;
    private LocalDateTime minJourReceipt;
    private List<Agents> agents;
    private List<Clients> clients;
    private List<Hardware> hardwares;
    
    @PostConstruct
    public void init() {
        // Recuperation de les listes  existantes
        
        // Limite Jour ou Date
        minJour = LocalDateTime.now().minusWeeks(0);
        minJourReceipt = LocalDateTime.now().minusWeeks(0);
        
        // Liste des agents
        agents = aejb.getAllAgents();
        
        // Liste des clients
        clients = cejb.getAllClients(); 
        
        // Map<Long, Clients> mapClients = new HashMap<Long, Clients>();
            
        /**
         * for(Clients c : clients) {
         *   mapClients.put(c.getIdClient(), c);
         * }// fin de for ...
         */ 
        
        // Liste des equipements
        hardwares = hejb.getAllHardware();
        
        // Map<Long, Hardware> mapHardware = new HashMap<Long, Hardware>();
        
        /**
         * for(Hardware h : hardwares) {
         *   mapHardware.put(h.getIdHardware(), h);
         * }// fin de for ..
         */ 
    }// fin init()
    
    /**
     * Nom client : getNomClient()
     * @return 
     */
    public String getNomClient() { 
        return nomClient; 
    }// fin getNomClient()
    
    /**
     * Nom client : setNomClient()
     * @param nomClient 
     */
    public void setNomClient(String nomClient) { 
        this.nomClient = nomClient; 
    }// fin setNomClient()
    
    /**
     * Nom hardware : getNomHrd()
     * @return 
     */
    public String getNomHrd() { 
        return nomHrd; 
    }// fin getNomHrd()
    
    /**
     * Nom hardware : setNomHrd()
     * @param nomHrd 
     */
    public void setNomHrd(String nomHrd) { 
        this.nomHrd = nomHrd; 
    }// fin setNomHrd() 
    
    /**
     * Jour 
     * Methode : getJour()
     * @return 
     */
    public Date getJour() {
        return jour;
    }// fin de getJour()
    
    /**
     * Jour 
     * Methode : setJour()
     * @param jour 
     */
    public void setJour(Date jour) {
        this.jour = jour;
    }// fin de setJour()
    
    /**
     * Heure arrivee
     * Methode : getHarrivee()
     * @return 
     */
    public Date getHarrivee() {
        return harrivee;
    }// fin de getHarrivee()
    
    /**
     * Heure arrivee
     * Methode : setHarrivee()
     * @param harrivee 
     */
    public void setHarrivee(Date harrivee) {
        this.harrivee = harrivee;
    }// fin de setHarrivee()
    
    /**
     * Destination
     * Methode : getDestination()
     * @return 
     */
    public String getDestination() {
        return destination;
    }// fin de getDestination()
    
    /**
     * Jour 
     * Methode : getDestination()
     * @param destination 
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }// fin de setDestination()
    
    /**
     * Expediteur
     * Methode : getExpediteur()
     * @return 
     */
    public String getExpediteur() {
        return expediteur;
    }// fin de getExpediteur()
    
    /**
     * Expideteur
     * Methode : setExpediteur()
     * @param expediteur 
     */
    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur.toUpperCase();
    }// fin de setExpediteur()
    
    /**
     * Pays
     * Methode : getPays()
     * @return 
     */
    public String getPays() {
        return pays;
    }// fin de getPays()
    
    /**
     * Pays
     * Methode : setPays()
     * @param pays 
     */
    public void setPays(String pays) {
        this.pays = pays.toUpperCase();
    }// fin de setPays()
    
    /**
     * Accessoires
     * Methode : getAccessoires()
     * @return 
     */
    public String getAccessoires() {
        return accessoires;
    }// fin de getAccessoires()
    
    /**
     * Accessoires
     * Methode : setAccessoires()
     * @param accessoires 
     */
    public void setAccessoires(String accessoires) {
        this.accessoires = accessoires;
    }// fin de setAccessoires()
    
    /**
     * Jour Receipt
     * Methode : getJourReceipt()
     * @return 
     */
    public Date getJourReceipt() {
        return jourReceipt;
    }// fin de getJourReceipt()
    
    /**
     * Jour Receipt
     * Methode : setJourReceipt()
     * @param jourReceipt 
     */
    public void setJourReceipt(Date jourReceipt) {
        this.jourReceipt = jourReceipt;
    }// fin de setJourReceipt()
    
    /**
     * Heure de reception
     * Methode : getHreceipt()
     * @return 
     */
    public Date getHreceipt() {
        return hreceipt;
    }// fin de getHreceipt()
    
    /**
     * Heure de reception
     * Methode : setHreceipt()
     * @param hreceipt 
     */
    public void setHreceipt(Date hreceipt) {
        this.hreceipt = hreceipt;
    }// fin de setHreceipt()
    
    /**
     * Nom receveur
     * Methode : getNomReceipt()
     * @return 
     */
    public String getNomReceipt() {
        return nomReceipt;
    }// fin de getNomReceipt()
    
    /**
     * Nom receveur
     * Methode : setNomReceipt()
     * @param nomReceipt 
     */
    public void setNomReceipt(String nomReceipt) {
        this.nomReceipt = nomReceipt.toUpperCase();
    }// fin de setNomReceipt()
    
    /**
     * Client : nomCst
     * Methode : getNomClt()
     * @return
     */
    public Clients getNomClt() {
        return nomClt;
    }// fin getNomClt()
    
    /**
     * Client : nomClt
     * Methode : getNomClt()
     * @param nomClt
     */
    public void setNomClt(Clients nomClt) {
        this.nomClt = nomClt; 
    }// fin setIdCst()
    
    /**
     * Hardware : nomHard
     * Methode : getNomHard()
     * @return 
     */
    public Hardware getNomHard() {
        return nomHard;
    }// fin de getNomHard()
    
    /**
     * Hardware : nomHard
     * Methode : setNomHard()
     * @param nomHard 
     */
    public void setNomHard(Hardware nomHard) {
        this.nomHard = nomHard;
    }// fin de la setNomHard()
    
    /**
     * Jour 
     * Methode : getMinJour()
     * @return 
     */
    public LocalDateTime getMinJour() {
        return minJour;
    }// fin de getMinJour()
    
    /**
     * Jour 
     * Methode : setMinJour()
     * @param minJour 
     */
    public void setMinJour(LocalDateTime minJour) {
        this.minJour = minJour;
    }// fin de setMinJour()
    
    /**
     * Jour Receipt
     * Methode : getMinJourReceipt()
     * @return 
     */
    public LocalDateTime getMinJourReceipt() {
        return minJourReceipt;
    }// fin de getMinJourReceipt()
    
    /**
     * Jour Receipt
     * Methode : setMinJourReceipt()
     * @param minJourReceipt 
     */
    public void setJourReceipt(LocalDateTime minJourReceipt) {
        this.minJourReceipt = minJourReceipt;
    }// fin de setMinJourReceipt()
    
    /**
     * Clients : getClients()
     * @return 
     */
    public List<Clients> getClients() {
        return clients;
    }// fin getClients()
    
    /**
     * Clients : setClients()
     * @param clients 
     */
    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }// fin setClients()
    
    /**
     * Agents : getAgents()
     * @return
     */
    public List<Agents> getAgents() {
        return agents;
    }// fin getAgents()
    
    /**
     * Agents : setAgents()
     * @param agents
     */
    public void setAgents(List<Agents> agents) {
        this.agents = agents;
    }// fin setAgents()
    
    /**
     * Clients : getHardware()
     * @return 
     */
    public List<Hardware> getHardware() {
        return hardwares;
    }// fin getHardware()
    
    /**
     * Clients : setHardware()
     * @param hardwares 
     */
    public void setHardware(List<Hardware> hardwares) {
        this.hardwares = hardwares;
    }// fin setHardware()
    
    /**
     * Receipt Form : saveReceipt()
     * Description : Méthode permettant d'enregistrer une nouvelle fiche de reception
     * @return 
     */
    public String saveReceipt() {
        // Initialisation d'une nouvelle fiche de reception
        ReceiptForm rf = new ReceiptForm(jour, harrivee, destination, expediteur, pays, accessoires, jourReceipt, hreceipt, nomReceipt, nomClient, nomHrd);
        
        // On crée la nouvelle fiche de reception
        rfejb.create(jour, harrivee, destination, expediteur, pays, accessoires, jourReceipt, hreceipt, nomReceipt, nomClient, nomHrd); 
        
        // Logger Info
        logger.info("SIGN UP : Une nouvelle fiche de réception a été enregistré avec succès. Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération
         */
        Util.messageInfo("La fiche de réception a destination du " + rf.getDestination() + " provenant du " + rf.getPays() + " a été enregistré avec succès.", "Information :");
        
        return "/managers/newReceipt"; 
    }// fin saveReceipt()
    
}// fin de la classe AddReceiptFormController

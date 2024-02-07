/*
 * Chanas Assurances S.A.
 * Professional Computer.
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
import com.maglo.ManagerForm.ejb.RemovalFormEJB;
import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.RemovalForm;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'enregistrer une fiche de retrait dans le système.
 */

@ManagedBean
@SessionScoped 
public class AddRemovalController implements Serializable {
    
    // Déclaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddRemovalController.class.getName());
    
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
    private RemovalFormEJB rfejb;
    
    // Variables 
    private String nomClt;
    private String nomHrd;
    private String nomAgnt;
    private Date jour;
    private Date hrm; 
    // private int marrivee;
    // private String destination;
    // private String expediteur;
    // private String pays;
    private String accessoires;
    // private Date jourReceipt;
    // private Date hreceipt;
    // private int mreceipt;
    private String nomAgent;
    private String nomClnt;
    private String nomHware;
    
    private LocalDateTime minJour;
    private List<Agents> agents;
    private List<Clients> clients;
    private List<Hardware> hardwares;
    
    @PostConstruct
    public void init() {
        // Recuperation de les listes  existantes
        
        // Limite Jour ou Date
        minJour = LocalDateTime.now().minusWeeks(0);
        
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
     * Nom client : getNomClnt()
     * @return 
     */
    public String getNomClt() { 
        return nomClt; 
    }// fin getNomClt()
    
    /**
     * Nom client : setNomClt()
     * @param nomClt 
     */
    public void setNomClt(String nomClt) { 
        this.nomClt = nomClt; 
    }// fin setNomClnt()
    
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
     * Heure remove
     * Methode : getHrm()
     * @return 
     */
    public Date getHrm() {
        return hrm;
    }// fin de getHrm()
    
    /**
     * Heure remove
     * Methode : setHrm()
     * @param hrm
     */
    public void setHrm(Date hrm) {
        this.hrm = hrm;
    }// fin de setHrm()
    
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
     * Nom receveur
     * Methode : getNomAgnt()
     * @return 
     */
    public String getNomAgnt() {
        return nomAgnt;
    }// fin de getNomAgnt()
    
    /**
     * Nom receveur
     * Methode : setNomAgnt()
     * @param nomAgnt
     */
    public void setNomAgnt(String nomAgnt) {
        this.nomAgnt = nomAgnt;
    }// fin de setNomAgnt()
    
    /**
     * Client : nomClnt
     * Methode : getNomClnt()
     * @return
     */
    public String getNomClnt() {
        return nomClnt;
    }// fin getNomClnt()
    
    /**
     * Client : nomClt
     * Methode : getNomClt()
     * @param nomClnt
     */
    public void setNomClnt(String nomClnt) {
        this.nomClnt = nomClnt; 
    }// fin setNomClnt()
    
    /**
     * Hardware : nomHware
     * Methode : getNomHware()
     * @return 
     */
    public String getNomHware() {
        return nomHware;
    }// fin de getNomHware()
    
    /**
     * Hardware : nomHware
     * Methode : setNomHware()
     * @param nomHware
     */
    public void setNomHware(String nomHware) {
        this.nomHware = nomHware; 
    }// fin de la setNomHware()
    
    /**
     * Agents : nomAgent
     * Methode : getNomAgent()
     * @return
     */
    public String getNomAgent() {
        return nomAgent;
    }// fin getNomAgent()
    
    /**
     * Agents : nomAgent
     * Methode : setNomAgent()
     * @param nomAgent
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent.toUpperCase(); 
    }// fin setNomAgnt()
    
    /**
     * Receipt Form : saveReceipt()
     * Description : Méthode permettant d'enregistrer une nouvelle fiche de reception
     * @return 
     */
    public String saveRemoval() {
        // Initialisation d'une nouvelle fiche de retrait
        RemovalForm rf = new RemovalForm(jour, hrm, nomClnt, nomHware, nomAgent, accessoires);
        
        // On crée la nouvelle fiche de retrait
        rfejb.create(jour, hrm, nomClnt, nomHware, nomAgent, accessoires); 
        
        // Logger Info
        logger.info("SIGN UP : Une nouvelle fiche de retrait a été enregistré avec succès. Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération
         */
        Util.messageInfo("La fiche de retrait du client " + rf.getNomClnt() + " a été enregistré avec succès.", "Information :");
        
        return "/managers/newRemoval"; 
    }// fin saveRemoval()
    
}// fin classe AddRemovalContoller 

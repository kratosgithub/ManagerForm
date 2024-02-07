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

// import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.ejb.InstallFormTypecEJB;
// import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.InstallFormTypec;
import com.maglo.ManagerForm.jsf.util.Util;

// import org.joda.time.LocalDateTime;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'enregistrer une fiche d'installation WP21A dans le système.
 */

@ManagedBean
@SessionScoped 
public class AddInstallFormTypecController implements Serializable {
    
    // Déclaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddInstallFormTypecController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB 
    private ClientsEJB cejb;
    
    @EJB
    private HardwareEJB hejb;
    
    @Inject
    private InstallFormTypecEJB iftejb;
    
    // variables 
    private String nomClient;
    private String nomHrd;
    
    // variables proprietes
    private Date jour;
    private Date hdebut;
    private Date hfin;
    private String nomCusto;
    private String nomEqpmt;
    
    private LocalDateTime minJour;
    private List<Clients> clients;
    private List<Hardware> hardwares;
    
    @PostConstruct
    public void init() {
        // Recuperation de les listes  existantes
        
        minJour = LocalDateTime.now().minusWeeks(0);
                
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
     * Getters & Setters -------------------------------------------------------
     */
    
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
     * Heure debut : getHdebut()
     * @return 
     */
    public Date getHdebut() {
        return hdebut;
    }// fin getHdebut()

    /**
     * Heure debut : setHdebut()
     * @param hdebut 
     */
    public void setHdebut(Date hdebut) {
        this.hdebut = hdebut;
    }// fin setHdebut()

    /**
     * Heure fin : getHfin()
     * @return 
     */
    public Date getHfin() {
        return hfin;
    }// fin getHfin()

    /**
     * Heure fin : setHfin()
     * @param hfin 
     */
    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }// fin setHfin()
    
    /**
     * MinJour 
     * Methode : getMinJour()
     * @return 
     */
    public LocalDateTime getMinJour() {
        return minJour;
    }// fin de getMinJour()
    
    /**
     * MinJour 
     * Methode : setMinJour()
     * @param minJour 
     */
    public void setMinJour(LocalDateTime minJour) {
        this.minJour = minJour;
    }// fin de setMinJour()
    
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
     * Client : nomCusto
     * Methode : getNomCusto()
     * @return
     */
    public String getNomCusto() {
        return nomCusto;
    }// fin getNomCusto()
    
    /**
     * Client : nomCusto
     * Methode : getNomCusto()
     * @param nomCusto
     */
    public void setNomCusto(String nomCusto) {
        this.nomCusto = nomCusto.toUpperCase(); 
    }// fin setIdCst()
    
    /**
     * Hardware : nomEqpmt
     * Methode : getNomEqpmt()
     * @return 
     */
    public String getNomEqpmt() {
        return nomEqpmt;
    }// fin de getNomEqpmt()
    
    /**
     * Hardware : nomEqpmt
     * Methode : setNomEqpmt()
     * @param nomEqpmt 
     */
    public void setNomEqpmt(String nomEqpmt) {
        this.nomEqpmt = nomEqpmt.toUpperCase();
    }// fin de la setNomEqpmt()
    
    /**
     * Install Form : saveInstallTypec()
     * Description : Méthode permettant d'enregistrer une nouvelle fiche de pre-installation
     * @return 
     */
    public String saveInstallTypec() {
        // Initialisation d'une nouvelle fiche d'installation WP21A
        InstallFormTypec ift = new InstallFormTypec(jour, hdebut, hfin, nomCusto, nomEqpmt);
     
        // On crée la nouvelle fiche d'installation WP12A
        iftejb.create(jour, hdebut, hfin, nomCusto, nomEqpmt); 
        
        // Logger Info
        logger.info("SIGN UP : Une nouvelle fiche d'installation du WP21A a été enregistré avec succès. Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération
         */
        Util.messageInfo("La fiche d'installation du client " + ift.getNomCusto() + " a été enregistré avec succès.", "Information :");
        
        return "/managers/newInstallFormTypec"; 
    }// fin saveInstallTypec()
    
}// fin AddInstallFormTypecController

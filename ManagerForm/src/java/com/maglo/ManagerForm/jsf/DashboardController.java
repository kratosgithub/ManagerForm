/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
// import java.util.Locale;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import com.maglo.ManagerForm.ejb.ActionFormEJB;
import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.ejb.UserEJB;
import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.ejb.HistoryRecordEJB;
import com.maglo.ManagerForm.ejb.InstallFormTypecEJB;
import com.maglo.ManagerForm.ejb.PreInstallFormEJB;
import com.maglo.ManagerForm.ejb.ReceiptFormEJB;
import com.maglo.ManagerForm.ejb.RemovalFormEJB;
// import com.maglo.ManagerForm.entities.Agents;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de representer les donnees enregistrees dans le systeme/
 */

@Named(value = "dashboardController")
@ViewScoped 
public class DashboardController implements Serializable {
    
    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DashboardController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private ActionFormEJB afejb;
    
    @Inject
    private AgentsEJB aejb;
    
    @Inject
    private UserEJB uejb;
    
    @Inject
    private ClientsEJB cejb;
    
    @Inject
    private HardwareEJB hejb;
    
    @Inject
    private HistoryRecordEJB hrejb;
    
    @Inject
    private InstallFormTypecEJB iftejb;
    
    @Inject
    private PreInstallFormEJB pifejb;
    
    @Inject
    private ReceiptFormEJB rfejb;
    
    @Inject
    private RemovalFormEJB rmfejb;
    
    private Long nbActionForms;
    private Long nbAgents;
    private Long nbUsers;
    private Long nbClients;
    private Long nbHardwares;
    private Long nbInstallFormTypecs;
    private Long nbPreInstallForms;
    private Long nbReceiptForms;
    private Long nbRemovalForms;
    private Long nbHistoryRecords;
    
    // private Agents agents;
    
    /**
     * Agents : init()
     * on initialise les donnees
     */
    @PostConstruct
    public void init() {
        // Agents 
        nbAgents = aejb.getCountAll();
        
        // Clients
        nbClients = cejb.getCountAll();
        
        // Users
        nbUsers = uejb.getCountAll();
        
        // Hardwares
        nbHardwares = hejb.getCountAll();
        
        // ReceiptForm 
        nbReceiptForms = rfejb.getCountAll();
        
        // PreInstallForm
        nbPreInstallForms = pifejb.getCountAll();
        
        // Install WP21A
        nbInstallFormTypecs = iftejb.getCountAll();
        
        // ActionForm
        nbActionForms = afejb.getCountAll();
        
        // RemovalForm
        nbRemovalForms = rmfejb.getCountAll();
        
        // History record
        nbHistoryRecords = hrejb.getCountAll();
    }// fin init()
    
    /**
     * AgentsEJB : getAgentsEJB()
     * @return
     */
    public AgentsEJB getAgentEJB() {
        return aejb;
    }// fin getAgentsEJB()
    
    /**
     * AgentsEJB : setAgentsEJB()
     * @param aejb
     */
    public void setAgentsEJB(AgentsEJB aejb) {
        this.aejb = aejb;
    }// fin setAgentsEJB()
    
    /**
     * Agents : getNbAgents()
     * @return
     */
    public Long getNbAgents() {
        // Logger
        logger.info("SHOW : Le nombre de techniciens a été chargé avec succès. Le : " + format.format(date) + ".");
        
        return nbAgents;
    }// fin getNbAgents()
    
    /**
     * Agents : setNbAgents()
     * @param nbAgents
     */
    public void setNbAgents(Long nbAgents) {
        this.nbAgents = nbAgents;
    }// fin setAgents()
    
    /**
     * ClientsEJB : getClientsEJB()
     * @return 
     */
    public ClientsEJB getClientsEJB() {
        return cejb;
    }// fin getClientsEJB()
    
    /**
     * ClientsEJB : setClientsEJB()
     * @param cejb
     */
    public void setClientsEJB(ClientsEJB cejb) {
        this.cejb = cejb;
    }// fin setClientsEJB()
    
    /**
     * Clients : getNbClients()
     * @return
     */
    public Long getNbClients() {
        // Logger
        logger.info("SHOW : Le nombre de clients a été chargé avec succès. Le : " + format.format(date) + ".");
        
        return nbClients;
    }// fin getNbClients()
    
    /**
     * Clients : setClients()
     * @param nbClients
     */
    public void setClients(Long nbClients) {
        this.nbClients = nbClients;
    }// fin setClients()
    
    /**
     * ReceiptForm : getNbReceiptForm()
     * @return
     */
    public Long getNbReceiptForms() {
        // Logger
        logger.info("SHOW : Le nombre de fiche de réception a été chargé avec succès. Le : " + format.format(date) + ".");
        
        return nbReceiptForms;
    }// fin getNbReceiptForm()
    
    /**
     * ReceiptForm : setNbReceiptForm()
     * @param nbReceiptForms
     */
    public void setNbReceiptForms(Long nbReceiptForms) {
        this.nbReceiptForms = nbReceiptForms;
    }// fin setNbReceiptForms()
    
    /**
     * Users : getNbUser()
     * @return 
     */
    public Long getNbUsers() {
        // Logger
        logger.info("SHOW : Le nombre d'utilisateurs a été chargé avec succès. Le : " + format.format(date) + ".");
        
        return nbUsers;
    }// fin getNbUsers()
    
    /**
     * Users : setNbUsers()
     * @param nbUsers
     */
    public void setNbUsers(Long nbUsers) {
        this.nbUsers = nbUsers;
    }// fin setNbUsers()
    
    /**
     * PreInstallForm : getNbPreInstallForms()
     * @return 
     */
    public Long getNbPreInstallForms() {
        // Logger 
        logger.info("SHOW : Le nombre de fiches de pré-installation a été chargé avec succès. Le : " + format.format(date) + ".");
        
        return nbPreInstallForms;
    }// fin getNbPreInstallForms()
    
    /**
     * PreInstallForm : setNbPreInstallForms()
     * @param nbPreInstallForms
     */
    public void setNbPreInstallForms(Long nbPreInstallForms) {
        this.nbPreInstallForms = nbPreInstallForms; 
    }// fin setNbPreInstallForms()
    
    /**
     * InstallForms WP21A : getNbInstallFormTypecs()
     * @return
     */
    public Long getNbInstallFormTypecs() {
        // Logger
        logger.info("SHOW : Le nombre de fiche d'installation d'un WP21A a été chargé avec succès. Le " + format.format(date) + ".");
        
        return nbInstallFormTypecs;
    }// fin getNbInstallFormTypecs()
    
    /**
     * InstallForms WP21A : setNbInstallFormTypecs()
     * @param nbInstallFormTypecs
     */
    public void setNbInstallFormTypecs(Long nbInstallFormTypecs) {
        this.nbInstallFormTypecs = nbInstallFormTypecs;
    }// fin setNbInstallFormTypecs()
    
    /**
     * Hardware : getNbHardwares()
     * @return
     */
    public Long getNbHardwares() {
        // Logger
        logger.info("SHOW : Le nombre d'équipements a été chargé avec succès. Le " + format.format(date) + ".");
        
        return nbHardwares;
    }// fin getNbHardwares()
    
    /**
     * Hardware : setNbHardwares()
     * @param nbHardwares
     */
    public void setNbHardwares(Long nbHardwares) {
        this.nbHardwares = nbHardwares;
    }// fin setNbHardwares()
    
    /**
     * ActionForm : getNbActionForms()
     * @return
     */
    public Long getNbActionForms() {
        // Logger
        logger.info("SHOW : Le nombre de fiches d'interventions a été chargé avec succès. Le " + format.format(date) + ".");
        
        return nbActionForms;
    }// getNbActionForms()
    
    /**
     * ActionForm : setNbActionForms()
     * @param nbActionForms
     */
    public void setNbActionForms(Long nbActionForms) {
        this.nbActionForms = nbActionForms;
    }// fin setNbActionForms()
    
    /**
     * RemovalForm : getRemovalForms()
     * @return
     */
    public Long getNbRemovalForms() {
        // Logger
        logger.info("SHOW : Le nombre de fiches d'enlèvements a été chargé avec succès. Le " + format.format(date) + ".");
        
        return nbRemovalForms;
    }// fin getNbRemovalForms()
    
    /**
     * RemovalForm : setNbRemovalForms()
     * @param nbRemovalForms
     */
    public void setNbRemovalForms(Long nbRemovalForms) {
        this.nbRemovalForms = nbRemovalForms;
    }// fin setNbRemovalForms()
    
    /**
     * History record : getNbHistoryRecords()
     * @return
     */
    public Long getNbHistoryRecords() {
        // Logger
        logger.info("SHOW : Le nombre de fiches d'historique a été chargé avec succès. Le " + format.format(date) + ".");
        
        return nbHistoryRecords;
    }// fin getNbHistoryRecords()
    
    /**
     * History record : setNbHistoryRecords()
     * @param nbHistoryRecords
     */
    public void setNbHistoryRecords(Long nbHistoryRecords) {
        this.nbHistoryRecords = nbHistoryRecords;
    }// fin setNbHistoryRecords()
    
}// fin DashboardController

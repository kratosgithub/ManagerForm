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
import com.maglo.ManagerForm.ejb.HistoryRecordEJB;
import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.entities.HistoryRecord;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'enregistrer une fiche d'historique dans le système.
 */

@ManagedBean
@SessionScoped 
public class AddHistoryController implements Serializable {
    
    // Déclaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddHistoryController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private AgentsEJB aejb; 
    
    @Inject
    private HistoryRecordEJB hrejb;
    
    // Variables 
    private int idHardware;
    private Date jour;
    private String motif;
    private int idAction;
    private String nomAgt;
    
    private LocalDateTime minJour;
    private List<Agents> agents;
    
    @PostConstruct
    public void init() {
        // Recuperation de les listes  existantes
        
        // Limite Jour ou Date
        minJour = LocalDateTime.now().minusWeeks(0);
        
        // Liste des agents
        agents = aejb.getAllAgents();
    }// fin init()
    
    /**
     * GETTES & SETTERS --------------------------------------------------------
     */
    
    /**
     * IdHardware : getIdHardware()
     * @return
     */
    public int getIdHardware() {
        return idHardware;
    }// fin getIdHardware()
    
    /**
     * IdHardware : setIdHardware()
     * @param idHardware
     */
    public void setIdHardware(int idHardware) {
        this.idHardware = idHardware;
    }// fin setIdHardware()
    
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
     * Motif : getMotif()
     * @return
     */
    public String getMotif() {
        return motif;
    }// fin getMotif()
    
    /**
     * Motif : setMotif()
     * @param motif
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }// fin setMotif()
    
    /**
     * IdAction : getIdAction()
     * @return
     */
    public int getIdAction() {
        return idAction;
    }// fin getIdAction()
    
    /**
     * IdAction : setIdAction()
     * @param idAction
     */
    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }// fin setIdAction()
    
    /**
     * Nom agent : getNomAgt()
     * @return 
     */
    public String getNomAgt() { 
        return nomAgt; 
    }// fin getNomAgt()
    
    /**
     * Nom agent : setNomAgt()
     * @param nomAgt
     */
    public void setNomAgt(String nomAgt) { 
        this.nomAgt = nomAgt.toUpperCase(); 
    }// fin setNomAgt()
    
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
     * History record : saveRecord()
     * Description : Méthode permettant d'enregistrer une nouvelle fiche d'historique
     * @return 
     */
    public String saveRecord() {
        // Initialisation d'une nouvelle fiche d'historique
        HistoryRecord hr = new HistoryRecord(idHardware, jour, motif, idAction, nomAgt);
        
        // On crée la nouvelle fiche d'historique
        hrejb.create(idHardware, jour, motif, idAction, nomAgt); 
        
        // Logger Info
        logger.info("SIGN UP : Une nouvelle fiche d'historique a été enregistré avec succès. Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération
         */
        Util.messageInfo("La fiche d'historique de M. " + hr.getNomAgt() + " a été enregistré avec succès.", "Information :");
        
        return "/managers/newHistory"; 
    }// fin saveRecord()
    
}// fin HistoryController

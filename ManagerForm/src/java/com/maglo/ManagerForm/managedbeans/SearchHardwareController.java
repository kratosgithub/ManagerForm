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

import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.entities.Hardware;
// import com.maglo.ManagerForm.entities.HardwarePK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de trouver un equipement enrgistrré à partir de son
 * nom. 
 */

@ManagedBean
@SessionScoped 
public class SearchHardwareController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(SearchHardwareController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private HardwareEJB hejb;
    
    private Hardware hardware;
    private String nomHardware;
    
    /**
     * Hardware : getHardware()
     * @return
     */
    public Hardware getHardware() {
        return hardware;
    }// fin de getHardware()
    
    /**
     * Hardware : setHardware()
     * @param hardware
     */
    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }// fin setHardware()
    
    /**
     * NomHardware : getNomHardware()
     * @return
     */
    public String getNomHardware() {
        return nomHardware;
    }// fin getNomHardware()
    
    /**
     * NomHardware : setNomHardware()
     * @param nomHardware
     */
    public void setNomHardware(String nomHardware) {
        this.nomHardware = nomHardware.toUpperCase();
    }// fin setNomHardware()
    
    /**
     * Hardware : loadHardware()
     * Charge l'equipement
     */
    public void loadHardware() {
        hardware = hejb.getHardwareByNomHardware(nomHardware);
    }// fin loadHardware()
    
    /**
     * Hardware : validateNomHardware()
     * @return
     */
    public String validateNomHardware() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {// essai ...
            hejb.getHardwareByNomHardware(nomHardware);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Aucun équipement n'a été trouvé."));
            
            return "/managers/searchHardware";
        }// fin try .. catch 
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        sessionMap.put("Hardware", hardware);
        
        // Condition sur la validité du nom saisi
        if(hejb.getHardwareByNomHardware(nomHardware) != null) {
            return "/managers/showHardware?faces-redirect=true";
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Aucun équipement n'a été trouvé."));
            
            // on reste sur la page courante
            return "/managers/searchHardware";
        }// fin if .. else 
    }// fin de validateNomHardware()
    
    /**
     * Hardware : deleteHardware()
     * @param hardware
     * @return
     */
    public String deleteHardware(Hardware hardware) {
        // on supprime l'equipement
        hejb.delete(hardware);
        
        // Logger
        logger.info("DELETE : L'équipement a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message à afficher
        Util.messageInfo("L'équipement " + hardware.getNomHardware() + " de N° série " + hardware.getNoSerie() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listHardware";
    }// fin deleteHardware()
    
    /**
     * Hardware : updateHardware()
     * @param hardware
     * @return
     */
    public String updateHardware(Hardware hardware) {
        // on met à jour l'équipement
        hejb.update(hardware);
        
        // Logger
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message à afficher 
        Util.messageInfo("Les informations de l'équipement " + hardware.getNomHardware() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/showHardware";
    }// fin updateHardware()
    
}// fin de la classe SearchHardwareController

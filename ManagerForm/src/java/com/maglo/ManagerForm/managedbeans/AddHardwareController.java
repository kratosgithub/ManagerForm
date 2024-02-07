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

import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.entities.Hardware;
// import com.maglo.ManagerForm.entities.HardwarePK;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de créer un équipmenent dans le système.
 */

@ManagedBean
@SessionScoped
public class AddHardwareController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(AddHardwareController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private HardwareEJB hejb;
    
    private Long idHardware;
    private String nomHardware;
    private String marque;
    private String model;
    private String noSerie;
    private int poids;
    private int longueur;
    private int largeur;
    private int hauteur;
    private int puissanceMax;
    private int puissanceMin;
    private int fqceMax;
    private int fqceMin;
    
    /**
     * Id Hardware : getIdHardware()
     * @return 
     */
    public Long getIdHardware() {
        return idHardware;
    }// fin getIdHardware()

    /**
     * Id Hardware : setIdHardware()
     * @param idHardware 
     */
    public void setIdHardware(Long idHardware) {
        this.idHardware = idHardware;
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
     * Marque 
     * Methode : getMarque()
     * @return 
     */
    public String getMarque() {
        return marque;
    }// fin de getMarque()

    /**
     * Marque 
     * Methode : setMarque()
     * @param marque 
     */
    public void setMarque(String marque) {
        this.marque = marque.toUpperCase();
    }// fin de setMarque()

    /**
     * Model
     * Methode : getModel()
     * @return 
     */
    public String getModel() {
        return model;
    }// fin getModel()

    /**
     * Model
     * Methode : setModel()
     * @param model 
     */
    public void setModel(String model) {
        this.model = model.toUpperCase();
    }// fin setModel()

    /**
     * No Serie
     * Methode : getNoSerie()
     * @return 
     */
    public String getNoSerie() {
        return noSerie;
    }// fin getNoSerie()

    /**
     * No Serie
     * Methode : setNoSerie()
     * @param noSerie 
     */
    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }// fin setNoSerie()

    /**
     * Poids
     * Methode : getPoids()
     * @return 
     */
    public int getPoids() {
        return poids;
    }// fin getPoids()

    /**
     * Poids
     * Methode : setPoids()
     * @param poids 
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }// fin setPoids()

    /**
     * Longueur
     * Methode : getLongueur()
     * @return 
     */
    public int getLongueur() {
        return longueur;
    }// fin getLongueur()

    /**
     * Longueur
     * Methode : setLongueur()
     * @param longueur 
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }// fin setLongueur()

    /**
     * Largeur
     * Methode : getLargeur()
     * @return 
     */
    public int getLargeur() {
        return largeur;
    }// fin getLargeur()

    /**
     * Largeur
     * Methode : setLargeur()
     * @param largeur 
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }// fin setLargeur()

    /**
     * Hauteur
     * Methode : getHauteur()
     * @return 
     */
    public int getHauteur() {
        return hauteur;
    }// fin getHauteur()

    /**
     * Hauteur
     * Methode : setHauteur()
     * @param hauteur 
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }// fin setHauteur()

    /**
     * Puissance Min
     * Methode : getPuissanceMin()
     * @return 
     */
    public int getPuissanceMin() {
        return puissanceMin;
    }// fin getPuissanceMin()

    /**
     * Puissance Min
     * Methode : getPuissanceMin()
     * @param puissanceMin 
     */
    public void setPuissanceMin(int puissanceMin) {
        this.puissanceMin = puissanceMin;
    }// fin de setPuissanceMin()

    /**
     * Puissance Max
     * Methode : getPuissanceMax()
     * @return 
     */
    public int getPuissanceMax() {
        return puissanceMax;
    }// fin getPuissanceMax()

    /**
     * Puissance Max
     * Methode : setPuissanceMax()
     * @param puissanceMax 
     */
    public void setPuissanceMax(int puissanceMax) {
        this.puissanceMax = puissanceMax;
    }// fin setPuissanceMax()

    /**
     * Frequence Min
     * Methode : getFqceMin()
     * @return 
     */
    public int getFqceMin() {
        return fqceMin;
    }// fin getFqceMin()

    /**
     * Frequence Min
     * Methode : setFqceMin()
     * @param fqceMin 
     */
    public void setFqceMin(int fqceMin) {
        this.fqceMin = fqceMin;
    }// fin setFqceMin()

    /**
     * Frequence Max
     * Methode : getFqceMax()
     * @return 
     */
    public int getFqceMax() {
        return fqceMax;
    }// fin getFqceMax()

    /**
     * Frequence Max
     * Methode : setFqceMax()
     * @param fqceMax 
     */
    public void setFqceMax(int fqceMax) {
        this.fqceMax = fqceMax;
    }// fin setFqceMax()
    
    /**
     * Hardware : saveHardware()
     * Description : Méthode permettant d'enregistrer un nouvel equipement
     * @return 
     */
    public String saveHardware() {
        // Initialisation d'un nouvel equipement
        Hardware hardware = new Hardware(idHardware, nomHardware, marque, model, noSerie, poids, longueur, largeur, hauteur, puissanceMin, puissanceMax, fqceMin, fqceMax);
        
        // On crée le nouvel equipement
        hejb.create(idHardware, nomHardware, marque, model, noSerie, poids, longueur, largeur, hauteur, puissanceMin, puissanceMax, fqceMin, fqceMax);
        
        // Logger Info ... 
        logger.info("CREATE : Un nouvel équipement a été enregistré avec succès : " + hardware.getNomHardware() + ". Le : " + format.format(date) + ".");
        
        /**
         * message informant l'utilisateur du succès de l'opération 
         * Util.addFlashInfoMessage("M/Mme. " + user.getPrenom() + " " + user.getNom() + " a été créé avec succès.");
         */
        Util.messageInfo("L'équipement " + hardware.getNomHardware() + " a été enregistré avec succès.", "Information :");
        
        return "/managers/newHardware"; 
    }// fin saveHardware()
    
}// fin de la classe AddHardwareController

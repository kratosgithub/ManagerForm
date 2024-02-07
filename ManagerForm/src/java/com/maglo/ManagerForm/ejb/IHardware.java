/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;

import com.maglo.ManagerForm.entities.Hardware;
// import com.maglo.ManagerForm.entities.HardwarePK;

/**
 *
 * @author NDOZENG
 * Interface Hardware permettant de definir les operations effectues sur les equipements. 
 */

public interface IHardware {
    
    /**
     * Lister les equipements
     * @return
     */
    public List<Hardware> getAllHardware();
    
    /**
     * Rechercher un equipement à partir de son nom
     * @param nomHardware
     * @return 
     */
    public Hardware getHardwareByNomHardware(String nomHardware);
    
    /**
     * Mise à jour d'un equipement
     * @param hardware
     * @return 
     */
    public Hardware update(Hardware hardware);
    
    /**
     * Supprimer un equipement
     * @param hardware
     */
    public void delete(Hardware hardware);
    
    /**
     * Hardware 
     * Methode : constructeur avec parametres
     * @param nomHardware 
     * @param marque 
     * @param model 
     * @param noSerie 
     * @param poids 
     * @param longueur 
     * @param largeur 
     * @param hauteur 
     * @param puissanceMin 
     * @param puissanceMax 
     * @param fqceMin 
     * @param fqceMax 
     * @return
     */
    public Hardware create(String nomHardware, String marque, String model, String noSerie, int poids, int longueur, int largeur, int hauteur, int puissanceMin, int puissanceMax, int fqceMin, int fqceMax);
    
}// fin de l'interface IHardware

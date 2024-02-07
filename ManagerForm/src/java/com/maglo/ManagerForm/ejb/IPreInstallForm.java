/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;

import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.PreInstallForm;
import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 * Interface utilitaire permettant de definir les operations effectuees sur une fiche 
 * de pre installation.
 */

public interface IPreInstallForm {
    
    /**
     * Lister les fiches de pre installation 
     * @return
     */
    public List<PreInstallForm> getAllPreInstallForm();
    
    /**
     * Rechercher une fiche de pre installation à partir de son id
     * @param idPreForm
     * @return 
     */
    public PreInstallForm getInstallByIdPreForm(Long idPreForm); 
    
    /**
     * Rechercher une fiche de pre installation à partir du jour
     * @param jour
     * @return 
     */
    public PreInstallForm getPreInstallByJour(Date jour);  
    
    /**
     * Mise à jour fiche de pre installation 
     * @param preInstallForm
     * @return 
     */
    public PreInstallForm update(PreInstallForm preInstallForm); 
    
    /**
     * Supprimer une fiche de pre installation 
     * @param preInstallForm 
     */
    public void delete(PreInstallForm preInstallForm); 
    
    /**
     * Pre Install Form
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomClient
     * @param nomHardware
     * @return
     */
    public PreInstallForm create(Date jour, Date hdebut, Date hfin, Clients nomClient, Hardware nomHardware);
    
}// fin de l'interface IPreInstallForm

/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;

import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.InstallFormTypec;
import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de definir les operations qui seront effectuees sur 
 * une fiche d'installation. 
 */

public interface IInstallFormTypec {
    
    /**
     * Lister les fiches d'installation 
     * @return
     */
    public List<InstallFormTypec> getAllInstallTypec();
    
    /**
     * Rechercher une fiche d'installation à partir de son id
     * @param idFormC 
     * @return 
     */
    public InstallFormTypec getInstallByIdFormC(Long idFormC); 
    
    /**
     * Rechercher une fiche d'installation à partir du jour
     * @param jour
     * @return 
     */
    public InstallFormTypec getInstallByJour(Date jour);  
    
    /**
     * Mise à jour fiche d'installation 
     * @param installFormTypec
     * @return 
     */
    public InstallFormTypec update(InstallFormTypec installFormTypec);
    
    /**
     * Supprimer une fiche d'installation
     * @param installFormTypec
     */
    public void delete(InstallFormTypec installFormTypec);  
    
    /**
     * Install Form Type C
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomCusto
     * @param nomEqpmt
     * @return 
     */
    public InstallFormTypec create(Date jour, Date hdebut, Date hfin, Clients nomCusto, Hardware nomEqpmt); 
    
}// fin  de l'interface IInstallFormTypec

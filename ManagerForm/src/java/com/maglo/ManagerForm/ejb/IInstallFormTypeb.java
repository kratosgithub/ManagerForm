/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;

import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.InstallFormTypeb;
import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de definir les operations qui seront effectuees sur
 * une fiche d'installation. 
 */

public interface IInstallFormTypeb {
    
    /**
     * Lister les fiches d'installation 
     * @return
     */
    public List<InstallFormTypeb> getAllInstallTypeb();
    
    /**
     * Rechercher une fiche d'installation à partir de son id
     * @param idFormB 
     * @return 
     */
    public InstallFormTypeb getInstallByIdFormB(Long idFormB); 
    
    /**
     * Rechercher une fiche d'installation à partir du jour
     * @param jour
     * @return 
     */
    public InstallFormTypeb getInstallByJour(Date jour);  
    
    /**
     * Mise à jour fiche d'installation 
     * @param installFormTypeb
     * @return 
     */
    public InstallFormTypeb update(InstallFormTypeb installFormTypeb);
    
    /**
     * Supprimer une fiche d'installation
     * @param installFormTypeb
     */
    public void delete(InstallFormTypeb installFormTypeb); 
    
    /**
     * Install Form Type B
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param mdebut 
     * @param hfin 
     * @param mfin 
     * @param idCtmer
     * @param idHrdw
     * @return 
     */
    public InstallFormTypeb create(Date jour, int hdebut, int mdebut, int hfin, int mfin, Clients idCtmer, Hardware idHrdw);
    
}// fin de l'interface IInstallFormTypeb

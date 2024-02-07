/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;

import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.RemovalForm;

/**
 *
 * @author NDOZENG
 * Interface utilitaire permettant de definir les methodes qui seront effectuees sur une
 * fiche d'enlevement. 
 */

public interface IRemovalForm {
    
    /**
     * Lister les fiches d'enlevement 
     * @return
     */
    public List<RemovalForm> getAllRemovalForm();
    
    /**
     * Rechercher une fiche d'enlevement à partir de son id
     * @param idRmForm 
     * @return 
     */
    public RemovalForm getRemovalById(Long idRmForm); 
    
    /**
     * Rechercher une fiche d'enlevement à partir du jour
     * @param jour
     * @return 
     */
    public RemovalForm getRemovalByJour(Date jour);
    
    /**
     * Mise à jour fiche d'enlevement 
     * @param removalForm 
     * @return 
     */
    public RemovalForm update(RemovalForm removalForm); 
    
    /**
     * Supprimer une fiche d'enlevement 
     * @param removalForm 
     */
    public void delete(RemovalForm removalForm);
    
    /**
     * Removal Form
     * Constructeur avec parametres
     * @param jour 
     * @param hrm 
     * @param mrn 
     * @param accessoires 
     * @param idAgent 
     * @param idClt 
     * @param idHware 
     * @return 
     */
    public RemovalForm create(Date jour, int hrm, int mrn, String accessoires, Agents idAgent, Clients idClt, Hardware idHware);
    
}// fin de l'interface IRemovalForm 

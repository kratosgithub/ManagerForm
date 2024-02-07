/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.Date;
import java.util.List;

import com.maglo.ManagerForm.entities.ActionForm;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author NDOZENG
 */

public interface IActionForm {
    
    /**
     * Lister les fiches d'intervention
     * @return
     */
    public List<ActionForm> getAllActionForm();
    
    /**
     * Lister les fiches d'intervention
     * @param idAction
     * @return 
     */
    public ActionForm getActionFormByIdAction(Long idAction);
    
    /**
     * Lister les fiches d'intervention
     * @param jour
     * @return 
     */
    public ActionForm getActionFormByJour(Date jour); 
    
    /**
     * Mise à jour des fiches d'intervention
     * @param actionForm 
     * @return 
     */
    public ActionForm update(ActionForm actionForm);
    
    /**
     * Supprimer un fiche d'intervention
     * @param actionForm
     */
    public void delete(ActionForm actionForm); 
    
    /**
     * Creer une fiche d'intervention
     * @param jour
     * @param harrivee
     * @param marrivee
     * @param hfin
     * @param mfin
     * @param statut
     * @param idCustomer
     * @param idHdre
     * @return
     */
    public ActionForm create(Date jour, int harrivee, int marrivee, int hfin, int mfin, String statut, Clients idCustomer, Hardware idHdre);
    
}// fin de l'interface IActionForm

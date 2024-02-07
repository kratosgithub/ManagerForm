/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;

import com.maglo.ManagerForm.entities.HistoryRecord;
import com.maglo.ManagerForm.entities.ActionForm;
import com.maglo.ManagerForm.entities.Agents;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de definir les operations qui seront effectuees sur une
 * fiche d'historique. 
 */

public interface IHistoryRecord {
    
    /**
     * Lister les fiches d'historique 
     * @return
     */
    public List<HistoryRecord> getAllHistoryRecord();
    
    /**
     * Rechercher une fiche d'historique à partir de son id
     * @param idRecord
     * @return 
     */
    public HistoryRecord getHistoryByIdRecord(Long idRecord); 
    
    /**
     * Rechercher une fiche d'historique à partir de l'id equipement
     * @param idHardware
     * @return 
     */
    public HistoryRecord getHistoryByIdHardware(int idHardware); 
    
    /**
     * Rechercher une fiche d'historique à partir du jour
     * @param jour
     * @return 
     */
    public HistoryRecord getHistoryByJour(Date jour); 
    
    /**
     * Mise à jour fiche historique
     * @param historyRecord
     * @return 
     */
    public HistoryRecord update(HistoryRecord historyRecord);
    
    /**
     * Supprimer une fiche d'historique
     * @param historyRecord
     */
    public void delete(HistoryRecord historyRecord); 
    
    /**
     * History Record
     * Methode : constructeur avec parametres
     * @param idHardware 
     * @param jour
     * @param motif 
     * @param idAction
     * @param idAgt
     * @return
     */
    public HistoryRecord create(int idHardware, Date jour, String motif, ActionForm idAction, Agents idAgt); 
    
}// fin de l'interface IHistoryRecord

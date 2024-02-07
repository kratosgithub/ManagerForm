/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import java.util.List;
import java.util.Date;


import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.ReceiptForm;

/**
 *
 * @author NDOZENG
 * Interface utilitaire permettant de definir les operations qui seront effectuees sur une 
 * fiche de reception.
 */

public interface IReceiptForm {
    
    /**
     * Lister les fiches de reception 
     * @return
     */
    public List<ReceiptForm> getAllReceiptForm();
    
    /**
     * Rechercher une fiche d'enlevement à partir de son id
     * @param idReceiptForm
     * @return 
     */
    public ReceiptForm getReceiptById(Long idReceiptForm); 
    
    /**
     * Rechercher une fiche de receiption à partir du jour
     * @param jour
     * @return 
     */
    public ReceiptForm getReceiptByJour(Date jour);
    
    /**
     * Mise à jour fiche de receiption 
     * @param receiptForm 
     * @return 
     */
    public ReceiptForm update(ReceiptForm receiptForm); 
    
    /**
     * Supprimer une fiche d'enlevement 
     * @param receiptForm
     */
    public void delete(ReceiptForm receiptForm); 
    
    /**
     * Receipt Form
     * Methode : constructeur avec parametres 
     * @param jour 
     * @param harrivee 
     * @param destination 
     * @param expediteur 
     * @param pays 
     * @param accessoires 
     * @param jourReceipt 
     * @param hreceipt 
     * @param nomReceipt 
     * @param idHard 
     * @return 
     */
    public ReceiptForm create(Date jour, Date harrivee, String destination, String expediteur, String pays, String accessoires, Date jourReceipt, Date hreceipt,
            String nomReceipt, Hardware idHard);
    
}// fin de l'interface IReceiptForm 

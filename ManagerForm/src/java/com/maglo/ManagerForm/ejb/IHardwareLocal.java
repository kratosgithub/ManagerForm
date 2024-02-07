/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import javax.ejb.Local;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant la communication entre les requetes de l'utilisateur à travers 
 * les interfaces et la BDD.
 */

@Local 
public interface IHardwareLocal extends IHardware {
    
    /* Effectue les operations definies dans l'interface Hardware */
    
}// fin de l'interface IHardwareLocal

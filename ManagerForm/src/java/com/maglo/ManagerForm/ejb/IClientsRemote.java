/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.ejb;

import javax.ejb.Remote;

/**
 *
 * @author NDOZENG
 */

@Remote 
public interface IClientsRemote extends IClients {
    
    /* Effectue les methodes definies dans l'interface IClients */
    
}// fin IClientsRemote

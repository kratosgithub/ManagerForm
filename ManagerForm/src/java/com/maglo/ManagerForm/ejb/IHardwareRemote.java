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
public interface IHardwareRemote extends IHardware {
    
    /* Effectue les operations definies dans l'interface Hardware */
    
}// fin de l'interface IHardwareRemote

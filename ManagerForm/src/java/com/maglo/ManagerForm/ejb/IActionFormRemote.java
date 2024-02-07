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
public interface IActionFormRemote extends IActionForm {
    
    /**
     * Effectue les methodes definies dans l'interface ActionForm
     */
    
}// fin de l'interface IActionForm

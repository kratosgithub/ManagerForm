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
public interface IHistoryRecordRemote extends IHistoryRecord {
    
    /* Effectue les operations definies dans l'interface HistoryRecord */
    
}// fin de l'interface IHistoryRecordRemote

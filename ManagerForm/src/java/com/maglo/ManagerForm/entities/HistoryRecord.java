/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior.ndozeng
 */
@Entity
@Table(name = "history_record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryRecord.findAll", query = "SELECT h FROM HistoryRecord h"),
    @NamedQuery(name = "HistoryRecord.findByIdRecord", query = "SELECT h FROM HistoryRecord h WHERE h.idRecord = :idRecord"),
    @NamedQuery(name = "HistoryRecord.findByIdHardware", query = "SELECT h FROM HistoryRecord h WHERE h.idHardware = :idHardware"),
    @NamedQuery(name = "HistoryRecord.findByJour", query = "SELECT h FROM HistoryRecord h WHERE h.jour = :jour"),
    @NamedQuery(name = "HistoryRecord.findByMotif", query = "SELECT h FROM HistoryRecord h WHERE h.motif = :motif"),
    @NamedQuery(name = "HistoryRecord.findByIdAction", query = "SELECT h FROM HistoryRecord h WHERE h.idAction = :idAction"),
    @NamedQuery(name = "HistoryRecord.findByNomAgt", query = "SELECT h FROM HistoryRecord h WHERE h.nomAgt = :nomAgt"),
    @NamedQuery(name = "HistoryRecord.countAll", query = "SELECT COUNT(h) FROM HistoryRecord h"),
    @NamedQuery(name = "HistoryRecord.downloadFile", 
            query = "SELECT h.idRecord, h.jour, h.motif, h.idHardware, hr.nomHardware,\n" +
                    "hr.marque, hr.model, hr.noSerie, h.nomAgt, h.idAction\n" +
                    "FROM HistoryRecord h\n" +
                    "LEFT JOIN Hardware hr ON h.idHardware = hr.idHardware\n" +
                    "WHERE h.idRecord = :idRecord AND h.jour = :jour")
})
public class HistoryRecord implements Serializable {

    // Declaration des variables
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRecord")
    private Long idRecord;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "idHardware")
    private int idHardware;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "jour")
    @Temporal(TemporalType.DATE)
    private Date jour;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "motif")
    private String motif;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAction")
    private int idAction;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomAgt")
    private String nomAgt;

    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * History Record
     * Methode : constructeur vide
     */
    public HistoryRecord() {}// fin HistoryRecord()

    /**
     * History Record
     * Methode : constructeur avec parametre
     * @param idRecord 
     */
    public HistoryRecord(Long idRecord) {
        this.idRecord = idRecord;
    }// fin HistoryRecord()

    /**
     * History Record
     * Methode : constructeur avec parametres
     * @param idRecord 
     * @param idHardware 
     * @param jour 
     * @param motif 
     * @param idAction 
     * @param nomAgt 
     */
    public HistoryRecord(Long idRecord, int idHardware, Date jour, String motif, int idAction, String nomAgt) {
        this.idRecord = idRecord;
        this.idHardware = idHardware;
        this.jour = jour;
        this.motif = motif;
        this.idAction = idAction;
        this.nomAgt = nomAgt;
    }// fin HistoryRecord()

    /**
     * History Record
     * Methode : constructeur avec parametres
     * @param idHardware 
     * @param jour
     * @param motif 
     * @param idAction
     * @param nomAgt
     */
    public HistoryRecord(int idHardware, Date jour, String motif, int idAction, String nomAgt) {
        this.idHardware = idHardware;
        this.jour = jour;
        this.motif = motif;
        this.idAction = idAction;
        this.nomAgt = nomAgt;
    }// fin HistoryRecord()
    
    /**
     * Getters & Setters -------------------------------------------------------
     */
    
    /**
     * IdRecord
     * Methode : getIdRecord()
     * @return 
     */
    public Long getIdRecord() {
        return idRecord;
    }// fin getIdRecord()

    /**
     * IdRecord
     * Methode : setIdRecord()
     * @param idRecord 
     */
    public void setIdRecord(Long idRecord) {
        this.idRecord = idRecord;
    }// fin setIdRecord()

    /**
     * IdHardware
     * Methode : getIdHardware()
     * @return 
     */
    public int getIdHardware() {
        return idHardware;
    }// fin getIdHardware()

    /**
     * IdHardware
     * Methode : setIdHardware
     * @param idHardware 
     */
    public void setIdHardware(int idHardware) {
        this.idHardware = idHardware;
    }// fin setIdHardware()

    /**
     * Jour 
     * Methode : getJour()
     * @return 
     */
    public Date getJour() {
        return jour;
    }// fin getJour()

    /**
     * Jour
     * Methode : setJour()
     * @param jour
     */
    public void setJour(Date jour) {
        this.jour = jour;
    }// fin setJour()

    /**
     * Motif : getMotif()
     * @return 
     */
    public String getMotif() {
        return motif;
    }// fin getMotif()

    /**
     * Motif : setMotif()
     * @param motif 
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }// fin setMotif()

    /**
     * ActionForm : idAction
     * Methode :getIdAction()
     * @return 
     */
    public int getIdAction() {
        return idAction;
    }// fin getIdAction()

    /**
     * ActionForm : idAction
     * Methode : setIdActin()
     * @param idAction 
     */
    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }// fin setIdAction()

    /**
     * Agents : nomAgt
     * Methode : getNomAgt()
     * @return 
     */
    public String getNomAgt() {
        return nomAgt;
    }// fin getNomAgt()

    /**
     * Agents : nomAgt
     * Methode : setNomAgt()
     * @param nomAgt 
     */
    public void setNomAgt(String nomAgt) {
        this.nomAgt = nomAgt;
    }// fin setNomAgt()

    /**
     * History Form
     * Methode : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (idRecord != null ? idRecord.hashCode() : 0);
        
        return hash;
    }// fin hashCode()
    
    /**
     * History Form
     * Methode : equals()
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoryRecord)) {
            return false;
        }// fin if condition
        
        HistoryRecord other = (HistoryRecord) object;
        
        if ((this.idRecord == null && other.idRecord != null) || (this.idRecord != null && !this.idRecord.equals(other.idRecord))) {
            return false;
        }// fin if condition
        
        return true;
    }// fin equals()
    
    /**
     * History Form
     * Methode : toString()
     * @return 
     */
    @Override
    public String toString() {
        return "com.maglo.ManagerForm.entities.HistoryRecord[ idRecord=" + idRecord + " ]";
    }// fin toString()
    
}// fin HistoryRecord

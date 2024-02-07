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
@Table(name = "receipt_form")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceiptForm.findAll", query = "SELECT r FROM ReceiptForm r"),
    @NamedQuery(name = "ReceiptForm.findByIdReceiptForm", query = "SELECT r FROM ReceiptForm r WHERE r.idReceiptForm = :idReceiptForm"),
    @NamedQuery(name = "ReceiptForm.findByJour", query = "SELECT r FROM ReceiptForm r WHERE r.jour = :jour"),
    @NamedQuery(name = "ReceiptForm.findByHarrivee", query = "SELECT r FROM ReceiptForm r WHERE r.harrivee = :harrivee"),
    @NamedQuery(name = "ReceiptForm.findByDestination", query = "SELECT r FROM ReceiptForm r WHERE r.destination = :destination"),
    @NamedQuery(name = "ReceiptForm.findByExpediteur", query = "SELECT r FROM ReceiptForm r WHERE r.expediteur = :expediteur"),
    @NamedQuery(name = "ReceiptForm.findByPays", query = "SELECT r FROM ReceiptForm r WHERE r.pays = :pays"),
    @NamedQuery(name = "ReceiptForm.findByAccessoires", query = "SELECT r FROM ReceiptForm r WHERE r.accessoires = :accessoires"),
    @NamedQuery(name = "ReceiptForm.findByJourReceipt", query = "SELECT r FROM ReceiptForm r WHERE r.jourReceipt = :jourReceipt"),
    @NamedQuery(name = "ReceiptForm.findByHreceipt", query = "SELECT r FROM ReceiptForm r WHERE r.hreceipt = :hreceipt"),
    @NamedQuery(name = "ReceiptForm.findByNomReceipt", query = "SELECT r FROM ReceiptForm r WHERE r.nomReceipt = :nomReceipt"),
    @NamedQuery(name = "ReceiptForm.findByNomClt", query = "SELECT r FROM ReceiptForm r WHERE r.nomClt = :nomClt"),
    @NamedQuery(name = "ReceiptForm.findByNomHard", query = "SELECT r FROM ReceiptForm r WHERE r.nomHard = :nomHard"),
    @NamedQuery(name = "ReceiptForm.downlaodReceipt", 
            query = "SELECT r FROM ReceiptForm r WHERE r.nomReceipt = :nomReceipt AND r.destination = :destination"),
    @NamedQuery(name = "ReceiptForm.countAll", query = "SELECT COUNT(r) FROM ReceiptForm r")
})
public class ReceiptForm implements Serializable {

    // Declaractions des variables 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReceiptForm")
    private Long idReceiptForm;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "jour")
    @Temporal(TemporalType.DATE)
    private Date jour;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "harrivee")
    @Temporal(TemporalType.TIME)
    private Date harrivee;
    
    @Size(max = 6)
    @Column(name = "destination")
    private String destination;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "expediteur")
    private String expediteur;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "pays")
    private String pays;
    
    @Size(max = 4)
    @Column(name = "accessoires")
    private String accessoires;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "jourReceipt")
    @Temporal(TemporalType.DATE)
    private Date jourReceipt;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "hreceipt")
    @Temporal(TemporalType.TIME)
    private Date hreceipt;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomReceipt")
    private String nomReceipt;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomClt")
    private String nomClt;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomHard")
    private String nomHard;

    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * Constructeur vide
     */
    public ReceiptForm() {}// fin ReceiptForm

    /**
     * Constructeur avec parametre
     * @param idReceiptForm 
     */
    public ReceiptForm(Long idReceiptForm) {
        this.idReceiptForm = idReceiptForm;
    }// fin ReceiptForm()

    /**
     * Receipt Form
     * Methode : constructeur avec parametres
     * @param idReceiptForm  
     * @param jour 
     * @param harrivee 
     * @param expediteur 
     * @param pays 
     * @param jourReceipt 
     * @param hreceipt 
     * @param nomReceipt 
     * @param nomClt 
     * @param nomHard 
     */
    public ReceiptForm(Long idReceiptForm, Date jour, Date harrivee, String expediteur, String pays, Date jourReceipt, Date hreceipt, String nomReceipt, String nomClt, String nomHard) {
        this.idReceiptForm = idReceiptForm;
        this.jour = jour;
        this.harrivee = harrivee;
        this.expediteur = expediteur;
        this.pays = pays;
        this.jourReceipt = jourReceipt;
        this.hreceipt = hreceipt;
        this.nomReceipt = nomReceipt;
        this.nomClt = nomClt;
        this.nomHard = nomHard;
    }// fin ReceiptForm()
    
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
     * @param nomClt
     * @param nomHard 
     */
    public ReceiptForm(Date jour, Date harrivee, String destination, String expediteur, String pays, String accessoires, Date jourReceipt, Date hreceipt, String nomReceipt, String nomClt,
            String nomHard) {
        this.jour = jour;
        this.harrivee = harrivee;
        this.destination = destination;
        this.expediteur = expediteur;
        this.pays = pays;
        this.accessoires = accessoires;
        this.jourReceipt = jourReceipt;
        this.hreceipt = hreceipt;
        this.nomReceipt = nomReceipt; 
        this.nomClt = nomClt;
        this.nomHard = nomHard; 
    }// fin de ReceiptForm()

    /**
     * Id Receipt Form
     * Methode : getIdReceiptForm()
     * @return 
     */
    public Long getIdReceiptForm() {
        return idReceiptForm;
    }// fin getIdReceiptForm()

    /**
     * Id Receipt Form
     * Methode : setIdReceiptForm()
     * @param idReceiptForm  
     */
    public void setIdReceiptForm(Long idReceiptForm) {
        this.idReceiptForm = idReceiptForm;
    }// fin setIdReceiptForm()

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
     * Heure arrivee
     * Methode : getHarrivee()
     * @return 
     */
    public Date getHarrivee() {
        return harrivee;
    }// fin getHarrivee()

    /**
     * Heure arrivee
     * Methode : setHarrivee()
     * @param harrivee 
     */
    public void setHarrivee(Date harrivee) {
        this.harrivee = harrivee;
    }// fin setHarrivee()

    /**
     * Destination 
     * Methode : getDestination()
     * @return 
     */
    public String getDestination() {
        return destination;
    }// fin getDestination()

    /**
     * Destination
     * Methode : setDestination()
     * @param destination 
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }// fin setDestination()

    /**
     * Expediteur 
     * Methode : getExpediteur()
     * @return 
     */
    public String getExpediteur() {
        return expediteur;
    }// fin getExpediteur()

    /**
     * Expediteur
     * Methode : setExpediteur()
     * @param expediteur 
     */
    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }// fin setExpediteur()

    /**
     * Pays
     * Methode : getPays()
     * @return 
     */
    public String getPays() {
        return pays;
    }// fin getPays()

    /**
     * Pays 
     * Methode : setPays()
     * @param pays 
     */
    public void setPays(String pays) {
        this.pays = pays;
    }// fin setPays()

    /**
     * Accessoires 
     * Methode : getAccessoires()
     * @return 
     */
    public String getAccessoires() {
        return accessoires;
    }// fin getAccessoires()

    /**
     * Accessoires 
     * Methode : setAccessoires()
     * @param accessoires  
     */
    public void setAccessoires(String accessoires) {
        this.accessoires = accessoires;
    }// fin setAccessoires()

    /**
     * Jour Receipt
     * Methode : getJourReceipt()
     * @return 
     */
    public Date getJourReceipt() {
        return jourReceipt;
    }// fin getJourReceipt()

    /**
     * Jour Receipt
     * Methode : setJourReceipt()
     * @param jourReceipt 
     */
    public void setJourReceipt(Date jourReceipt) {
        this.jourReceipt = jourReceipt;
    }// fin de setJourReceipt()

    /**
     * Heure reception
     * Methode : getHreceipt()
     * @return 
     */
    public Date getHreceipt() {
        return hreceipt;
    }// fin getHreceipt()

    /**
     * Heure reception
     * Methode : setHreceipt()
     * @param hreceipt 
     */
    public void setHreceipt(Date hreceipt) {
        this.hreceipt = hreceipt;
    }// fin setHreceipt()

    /**
     * Nom receveur
     * Methode : getNomReceipt()
     * @return 
     */
    public String getNomReceipt() {
        return nomReceipt;
    }// fin de getNomReceipt()
    
    /**
     * Nom receveur
     * Methode : setNomReceipt()
     * @param nomReceipt 
     */
    public void setNomReceipt(String nomReceipt) {
        this.nomReceipt = nomReceipt; 
    }// fin de setNomReceipt()

    /**
     * Clients : getNomClt()
     * @return 
     */
    public String getNomClt() {
        return nomClt;
    }// fin getNomClt()

    /**
     * Clients : setNomClt()
     * @param nomClt 
     */
    public void setNomClt(String nomClt) {
        this.nomClt = nomClt;
    }// fin setNomclt()

    /**
     * Hardware : getNomHard()
     * @return 
     */
    public String getNomHard() {
        return nomHard;
    }// fin getNomHard()

    /**
     * Hardware : setNomHard()
     * @param nomHard 
     */
    public void setNomHard(String nomHard) {
        this.nomHard = nomHard;
    }// fin setNomHard()

    /**
     * Receipt Form
     * Methode : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        Long idReceiptForm = getIdReceiptForm();
        int hash = 0;
        
        hash += (idReceiptForm != null ? idReceiptForm.hashCode() : 0);
        
        return hash;
    }// fin hashCode()

    /**
     * Receipt Form
     * Methode : equals()
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReceiptForm)) {
            return false;
        }// fin if ..
        
        ReceiptForm other = (ReceiptForm) object; 
        
        if ((this.getIdReceiptForm() == null && other.getIdReceiptForm() != null) || (this.getIdReceiptForm() != null && !this.getNomReceipt().equals(other.getIdReceiptForm()))) {
            return false;
        }// fin if ..
        
        return true;
    }// fin equals()

    /**
     * Receipt Form
     * Methode : toString()
     * @return 
     */
    @Override
    public String toString() {
        // return "com.maglo.ManagerForm.entities.ReceiptForm[ idReceiptForm=" + idReceiptForm + " ]";
        return String.format("%s", getNomReceipt()); 
    }// fin toString()
    
}// fin classe ReceiptForm

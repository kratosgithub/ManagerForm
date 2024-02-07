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
 * Classe utilitaire permettant de representer une fiche de pre installation dans le systeme
 * <!-- PreInstallForm -->
 */

@Entity
@Table(name = "pre_install_form")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreInstallForm.findAll", query = "SELECT p FROM PreInstallForm p"),
    @NamedQuery(name = "PreInstallForm.findByIdPreForm", query = "SELECT p FROM PreInstallForm p WHERE p.idPreForm = :idPreForm"),
    @NamedQuery(name = "PreInstallForm.findByJour", query = "SELECT p FROM PreInstallForm p WHERE p.jour = :jour"),
    @NamedQuery(name = "PreInstallForm.findByHdebut", query = "SELECT p FROM PreInstallForm p WHERE p.hdebut = :hdebut"),
    @NamedQuery(name = "PreInstallForm.findByHfin", query = "SELECT p FROM PreInstallForm p WHERE p.hfin = :hfin"),
    @NamedQuery(name = "PreInstallForm.findByNomClient", query = "SELECT p FROM PreInstallForm p WHERE p.nomClient = :nomClient"),
    @NamedQuery(name = "PreInstallForm.findByNomHardware", query = "SELECT p FROM PreInstallForm p WHERE p.nomHardware = :nomHardware"),
    @NamedQuery(name = "PreInstallForm.downloadFile", 
        query = "SELECT p.idPreForm, p.jour, p.hdebut, p.hfin, p.nomClient, c.service, c.ville, c.telClient, p.nomHardware, h.marque, h.model, h.noSerie, h.poids, h.longueur, h.largeur, h.hauteur, h.puissanceMin, "
                + "h.puissanceMax\n" +
                "FROM PreInstallForm p\n" +
                "LEFT JOIN Clients c ON p.nomClient = c.nomClient\n" +
                "LEFT JOIN Hardware h ON p.nomHardware = h.nomHardware\n" +
                "WHERE p.idPreForm = :idPreForm AND p.jour = :jour"),
    @NamedQuery(name = "PreInstallForm.countAll", query = "SELECT COUNT(p) FROM PreInstallForm p")
})
public class PreInstallForm implements Serializable {

    // Declarations des variables 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPreForm")
    private Long idPreForm;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "jour")
    @Temporal(TemporalType.DATE)
    private Date jour;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "hdebut")
    @Temporal(TemporalType.TIME)
    private Date hdebut;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "hfin")
    @Temporal(TemporalType.TIME)
    private Date hfin;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomClient")
    private String nomClient;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomHardware")
    private String nomHardware;

    /**
     * Pre Install Form
     * Methode : constructeur sans parametre
     */
    public PreInstallForm() {}// fin du constructeur vide 
    
    /**
     * Pre Install Form
     * Methode : constructeur avec parametre
     * @param idPreForm 
     */
    public PreInstallForm(Long idPreForm) {
        this.idPreForm = idPreForm; 
    }// fin de PreInstallForm()
    
    /**
     * Pre Install Form
     * Methode : constructeur avec parametres
     * @param idPreForm 
     * @param hdebut 
     * @param hfin 
     */
    public PreInstallForm(Long idPreForm, Date hdebut, Date hfin) {
        this.idPreForm = idPreForm;
        this.hdebut = hdebut;
        this.hfin = hfin;
    }// fin de PreInstallForm()
    
    /**
     * Pre Install Form
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomClient
     * @param nomHardware
     */
    public PreInstallForm(Date jour, Date hdebut, Date hfin, String nomClient, String nomHardware) {
        this.jour = jour;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.nomClient = nomClient;
        this.nomHardware = nomHardware;
    }// fin de PreInstallForm()

    /**
     * IdPreForm
     * Methode : getIdPreForm()
     * @return 
     */
    public Long getIdPreForm() {
        return idPreForm;
    }// fin de getIdPreForm()
    
    /**
     * IdPreForm
     * Methode : setIdPreForm()
     * @param idPreForm 
     */
    public void setIdPreForm(Long idPreForm) {
        this.idPreForm = idPreForm;
    }// fin de setIdPreForm()

    /**
     * Jour
     * Methode : getJour()
     * @return 
     */
    public Date getJour() {
        return jour;
    }// fin de getJour()
    
    /**
     * Jour
     * Methode : setJour()()
     * @param jour 
     */
    public void setJour(Date jour) {
        this.jour = jour;
    }// fin de setJour()

    /**
     * Heure debut : getHdebut()
     * @return 
     */
    public Date getHdebut() {
        return hdebut;
    }// fin getHdebut()

    /**
     * Heure debut : setHdebut()
     * @param hdebut 
     */
    public void setHdebut(Date hdebut) {
        this.hdebut = hdebut;
    }// fin setHdebut()

    /**
     * Heure fin : getHfin()
     * @return 
     */
    public Date getHfin() {
        return hfin;
    }// fin getHfin()

    /**
     * Heure fin : setHfin()
     * @param hfin 
     */
    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }// fin setHfin()
    
    /**
     * Clients : nomClient
     * Methode : getNomClient()
     * @return 
     */
    public String getNomClient() {
        return nomClient;
    }// fin getNomClient()

    /**
     * Clients : nomClient
     * Methode : setNomClient()
     * @param nomClient 
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }// fin setNomClient()

    /**
     * Hardware : nomHardware
     * Methode : getNomHardware()
     * @return 
     */
    public String getNomHardware() {
        return nomHardware;
    }// fin getNomHardware()

    /**
     * Hardware : nomHardware
     * Methode : setNomHardware()
     * @param nomHardware 
     */
    public void setNomHardware(String nomHardware) {
        this.nomHardware = nomHardware;
    }// fin setNomHardware()

    /**
     * Pre Install Form
     * Methode : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (idPreForm != null ? idPreForm.hashCode() : 0);
        
        return hash;
    }// fin de hashCode()
    
    /**
     * Pre Install Form
     * Methode : equals()
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreInstallForm)) {
            return false;
        }// fin de if condition 
        
        PreInstallForm other = (PreInstallForm) object;
        
        if ((this.idPreForm == null && other.idPreForm != null) || (this.idPreForm != null && !this.idPreForm.equals(other.idPreForm))) {
            return false;
        }// fin de if condition
        
        return true;
    }// fin de equals()
    
    /**
     * Pre Install Form
     * Methode : toString()
     * @return 
     */
    @Override
    public String toString() {
        return "com.maglo.ManagerForm.entities.PreInstallForm[ idPreForm=" + idPreForm + " ]";
    }// fin de toString()
    
}// fin classe PreInstallForm

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
 * Classe utilitaire permettant de representer une fiche d'installation du WP21A dans le systeme
 * <!-- Install Form Type C -->
 */

@Entity
@Table(name = "install_form_typec")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstallFormTypec.findAll", query = "SELECT i FROM InstallFormTypec i"),
    @NamedQuery(name = "InstallFormTypec.findByIdFormC", query = "SELECT i FROM InstallFormTypec i WHERE i.idFormC = :idFormC"),
    @NamedQuery(name = "InstallFormTypec.findByJour", query = "SELECT i FROM InstallFormTypec i WHERE i.jour = :jour"),
    @NamedQuery(name = "InstallFormTypec.findByHdebut", query = "SELECT i FROM InstallFormTypec i WHERE i.hdebut = :hdebut"),
    @NamedQuery(name = "InstallFormTypec.findByHfin", query = "SELECT i FROM InstallFormTypec i WHERE i.hfin = :hfin"),
    @NamedQuery(name = "InstallFormTypec.findByNomCusto", query = "SELECT i FROM InstallFormTypec i WHERE i.nomCusto = :nomCusto"),
    @NamedQuery(name = "InstallFormTypec.findByNomEqpmt", query = "SELECT i FROM InstallFormTypec i WHERE i.nomEqpmt = :nomEqpmt"),
    @NamedQuery(name = "InstallFormTypec.countAll", query = "SELECT COUNT(i) FROM InstallFormTypec i"),
    @NamedQuery(name = "InstallFormTypec.downloadFile", 
        query = "SELECT i.idFormC, i.jour, i.hdebut, i.hfin, i.nomCusto, c.service, c.ville, c.telClient, i.nomEqpmt, h.marque, h.model, h.noSerie\n" +
                "FROM InstallFormTypec i\n" +
                "LEFT JOIN Clients c ON i.nomCusto = c.nomClient\n" +
                "LEFT JOIN Hardware h ON i.nomEqpmt = h.nomHardware\n" +
                "WHERE i.idFormC = :idFormC AND i.jour = :jour")
})
public class InstallFormTypec implements Serializable {

    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFormC")
    private Long idFormC;
    
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
    @Column(name = "nomCusto")
    private String nomCusto;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomEqpmt")
    private String nomEqpmt;

    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * Install Form Type C
     * Methode : constructeur vide
     */
    public InstallFormTypec() {}// fin InstallFormTypec

    /**
     * Install Form Type C
     * Methode : constructeur avec parametre
     * @param idFormC 
     */
    public InstallFormTypec(Long idFormC) {
        this.idFormC = idFormC;
    }// fin InstallFormTypec()

    /**
     * Install Form Type C
     * Methode : constructeur avec parametres
     * @param idFormC 
     * @param jour 
     * @param hdebut 
     * @param hfin 
     * @param nomCusto 
     * @param nomEqpmt 
     */
    public InstallFormTypec(Long idFormC, Date jour, Date hdebut, Date hfin, String nomCusto, String nomEqpmt) {
        this.idFormC = idFormC;
        this.jour = jour;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.nomCusto = nomCusto;
        this.nomEqpmt = nomEqpmt;
    }// fin InstallFormTypec()
    
    /**
     * Install Form Type C
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomCusto
     * @param nomEqpmt
     */
    public InstallFormTypec(Date jour, Date hdebut, Date hfin, String nomCusto, String nomEqpmt) {
        this.jour = jour;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.nomCusto = nomCusto;
        this.nomEqpmt = nomEqpmt; 
    }// fin InstallFormTypec()

    /**
     * Getters & Setters -------------------------------------------------------
     */
    
    /**
     * IdFormC
     * Methode : getIdFormC()
     * @return 
     */
    public Long getIdFormC() {
        return idFormC;
    }// fin getIdFormC()

    /**
     * IdFormC
     * Methode : setIdFormC()
     * @param idFormC 
     */
    public void setIdFormC(Long idFormC) {
        this.idFormC = idFormC;
    }// fin setIdFormC()

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
     * Clients : nomCusto
     * Methode : getNomCusto()
     * @return 
     */
    public String getNomCusto() {
        return nomCusto;
    }// fin getNomCusto()

    /**
     * Clients : nomCusto
     * Methode : setNomCusto()
     * @param nomCusto 
     */
    public void setNomCusto(String nomCusto) {
        this.nomCusto = nomCusto;
    }// fin setNomCusto()

    /**
     * Hardware : nomEqpmt
     * Methode : getNomEqpmt()
     * @return 
     */
    public String getNomEqpmt() {
        return nomEqpmt;
    }// fin getNomEqpmt()

    /**
     * Hardware : nomEqpmt
     * Methode : setNomEqpmt()
     * @param nomEqpmt 
     */
    public void setNomEqpmt(String nomEqpmt) {
        this.nomEqpmt = nomEqpmt;
    }// fin setNomEqpmt()

    /**
     * Install Form Type C
     * MEthode : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (idFormC != null ? idFormC.hashCode() : 0);
        
        return hash;
    }// fin de hashCode()
    
    /**
     * Install Form Type C
     * Methode : equals()
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstallFormTypec)) {
            return false;
        }// fin if condition
        
        InstallFormTypec other = (InstallFormTypec) object;
        
        if ((this.idFormC == null && other.idFormC != null) || (this.idFormC != null && !this.idFormC.equals(other.idFormC))) {
            return false;
        }// fin if condition
        
        return true;
    }// fin equals()
    
    /**
     * Install Form Type C
     * MEthode : toString()
     * @return
     */
    @Override
    public String toString() {
        return "com.maglo.ManagerForm.entities.InstallFormTypec[ idFormC=" + idFormC + " ]";
    }// fin toString()
    
}// fin classe InstallFormTypec

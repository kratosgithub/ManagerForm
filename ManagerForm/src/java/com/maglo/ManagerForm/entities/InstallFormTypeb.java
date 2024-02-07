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
 * Classe utilitaire permettant de representer une fiche d'installation du KT60 dans le systeme
 * <!-- Install Form Type B -->
 */

@Entity
@Table(name = "install_form_typeb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstallFormTypeb.findAll", query = "SELECT i FROM InstallFormTypeb i"),
    @NamedQuery(name = "InstallFormTypeb.findByIdFormB", query = "SELECT i FROM InstallFormTypeb i WHERE i.idFormB = :idFormB"),
    @NamedQuery(name = "InstallFormTypeb.findByJour", query = "SELECT i FROM InstallFormTypeb i WHERE i.jour = :jour"),
    @NamedQuery(name = "InstallFormTypeb.findByHdebut", query = "SELECT i FROM InstallFormTypeb i WHERE i.hdebut = :hdebut"),
    @NamedQuery(name = "InstallFormTypeb.findByHfin", query = "SELECT i FROM InstallFormTypeb i WHERE i.hfin = :hfin"),
    @NamedQuery(name = "InstallFormTypeb.findByNomCtmer", query = "SELECT i FROM InstallFormTypeb i WHERE i.nomCtmer = :nomCtmer"),
    @NamedQuery(name = "InstallFormTypeb.findByNomHrdw", query = "SELECT i FROM InstallFormTypeb i WHERE i.nomHrdw = :nomHrdw")})
public class InstallFormTypeb implements Serializable {

    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFormB")
    private Long idFormB;
    
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
    @Column(name = "nomCtmer")
    private String nomCtmer;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomHrdw")
    private String nomHrdw;

    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * Install Form Type B
     * Methode : constructeur vide
     */
    public InstallFormTypeb() {}// fin InstallFormTypeb()

    /**
     * Install Form Type B
     * Methode : constructeur avec parametre
     * @param idFormB 
     */
    public InstallFormTypeb(Long idFormB) {
        this.idFormB = idFormB;
    }// fin InstallFormTypeb()

    /**
     * Install Form Type B
     * Methode : constructeur avec parametres
     * @param idFormB 
     * @param jour 
     * @param hdebut 
     * @param hfin 
     * @param nomCtmer 
     * @param nomHrdw 
     */
    public InstallFormTypeb(Long idFormB, Date jour, Date hdebut, Date hfin, String nomCtmer, String nomHrdw) {
        this.idFormB = idFormB;
        this.jour = jour;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.nomCtmer = nomCtmer;
        this.nomHrdw = nomHrdw;
    }// fin InstallFormTypeb()
    
    /**
     * Install Form Type B
     * Methode : constructeur avec parametres
     * @param jour
     * @param hdebut 
     * @param hfin 
     * @param nomCtmer
     * @param nomHrdw
     */
    public InstallFormTypeb(Date jour, Date hdebut, Date hfin, String nomCtmer, String nomHrdw) {
        this.jour = jour;
        this.hdebut = hdebut;
        this.hfin = hfin;
        this.nomCtmer = nomCtmer;
        this.nomHrdw = nomHrdw;
    }// fin InstallFormTypeb()

    /**
     * Getters & Setters -------------------------------------------------------
     */
    
    /**
     * Id Form B
     * Methode : getIdFormB()
     * @return 
     */
    public Long getIdFormB() {
        return idFormB;
    }// fin getIdFormB()

    /**
     * Id Form B
     * Methode : setIdFormB()
     * @param idFormB 
     */
    public void setIdFormB(Long idFormB) {
        this.idFormB = idFormB;
    }// fin setIdFormB()

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
     * Clients : nomCtmer
     * Methode : getNomCtmer()
     * @return 
     */
    public String getNomCtmer() {
        return nomCtmer;
    }// fin getNomCtmer()

    /**
     * Clients : nomCtmer
     * Methode : setnomCtmer()
     * @param nomCtmer 
     */
    public void setNomCtmer(String nomCtmer) {
        this.nomCtmer = nomCtmer;
    }// fin setNomCtmer()

    /**
     * Hardware : nomHrdw
     * Methode getNomHrdw()
     * @return 
     */
    public String getNomHrdw() {
        return nomHrdw;
    }// fin setNomHrdw()

    /**
     * Hardware : nomHrdw
     * Methode : setNomHrdw()
     * @param nomHrdw 
     */
    public void setNomHrdw(String nomHrdw) {
        this.nomHrdw = nomHrdw;
    }// fin setNomHrdw()

    /**
     * Install Form type B : KT60
     * Methode : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (idFormB != null ? idFormB.hashCode() : 0);
        
        return hash;
    }// fin hashCode()
    
    /**
     * Install Form Type C
     * Methode : equals()
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstallFormTypeb)) {
            return false;
        }// fin if condition
        
        InstallFormTypeb other = (InstallFormTypeb) object;
        
        if ((this.idFormB == null && other.idFormB != null) || (this.idFormB != null && !this.idFormB.equals(other.idFormB))) {
            return false;
        }// fin if condition
        
        return true;
    }// fin equals()
    
    /**
     * Install Form Type B : KT60
     * Methode : toString()
     * @return 
     */
    @Override
    public String toString() {
        return "com.maglo.ManagerForm.entities.InstallFormTypeb[ idFormB=" + idFormB + " ]";
    }// fin toString()
    
}// fin classe InstallFormTypeb

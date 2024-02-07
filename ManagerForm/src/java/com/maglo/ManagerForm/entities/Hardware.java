/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de representer un equipement dans le systeme.
 */

@Entity
@Table(name = "hardware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardware.findAll", query = "SELECT h FROM Hardware h"),
    @NamedQuery(name = "Hardware.findByIdHardware", query = "SELECT h FROM Hardware h WHERE h.idHardware = :idHardware"),
    @NamedQuery(name = "Hardware.findByNomHardware", query = "SELECT h FROM Hardware h WHERE h.nomHardware = :nomHardware"),
    @NamedQuery(name = "Hardware.findByMarque", query = "SELECT h FROM Hardware h WHERE h.marque = :marque"),
    @NamedQuery(name = "Hardware.findByModel", query = "SELECT h FROM Hardware h WHERE h.model = :model"),
    @NamedQuery(name = "Hardware.findByNoSerie", query = "SELECT h FROM Hardware h WHERE h.noSerie = :noSerie"),
    @NamedQuery(name = "Hardware.findByPoids", query = "SELECT h FROM Hardware h WHERE h.poids = :poids"),
    @NamedQuery(name = "Hardware.findByLongueur", query = "SELECT h FROM Hardware h WHERE h.longueur = :longueur"),
    @NamedQuery(name = "Hardware.findByLargeur", query = "SELECT h FROM Hardware h WHERE h.largeur = :largeur"),
    @NamedQuery(name = "Hardware.findByHauteur", query = "SELECT h FROM Hardware h WHERE h.hauteur = :hauteur"),
    @NamedQuery(name = "Hardware.findByPuissanceMin", query = "SELECT h FROM Hardware h WHERE h.puissanceMin = :puissanceMin"),
    @NamedQuery(name = "Hardware.findByPuissanceMax", query = "SELECT h FROM Hardware h WHERE h.puissanceMax = :puissanceMax"),
    @NamedQuery(name = "Hardware.findByFqceMin", query = "SELECT h FROM Hardware h WHERE h.fqceMin = :fqceMin"),
    @NamedQuery(name = "Hardware.findByFqceMax", query = "SELECT h FROM Hardware h WHERE h.fqceMax = :fqceMax"),
    @NamedQuery(name = "Hardware.downlaodFile", query = "SELECT h FROM Hardware h WHERE h.noSerie = :noSerie AND h.marque = :marque"),
    @NamedQuery(name = "Hardware.countAll", query = "SELECT COUNT(h) FROM Hardware h")
})
public class Hardware implements Serializable {

    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHardware")
    private Long idHardware;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomHardware")
    private String nomHardware;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "marque")
    private String marque;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "model")
    private String model;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "noSerie")
    private String noSerie;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "poids")
    private int poids;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "longueur")
    private int longueur;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "largeur")
    private int largeur;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "hauteur")
    private int hauteur;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "puissanceMin")
    private int puissanceMin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "puissanceMax")
    private int puissanceMax;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fqceMin")
    private int fqceMin;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fqceMax")
    private int fqceMax;

    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * Constructeur vide 
     */
    public Hardware() {}// fin hardware()

    /**
     * Constructeur avec parametre
     * @param idHardware 
     */
    public Hardware(Long idHardware) {
        this.idHardware = idHardware; 
    }// fin Hardware()

    /**
     * Constructeur avec parametres
     * @param idHardware 
     * @param nomHardware 
     * @param marque 
     * @param model 
     * @param noSerie 
     * @param poids 
     * @param largeur 
     * @param longueur 
     * @param hauteur 
     * @param puissanceMin 
     * @param puissanceMax 
     * @param fqceMin 
     * @param fqceMax 
     */
    public Hardware(Long idHardware, String nomHardware, String marque, String model, String noSerie, int poids, int longueur, int largeur, int hauteur, int puissanceMin, int puissanceMax, int fqceMin, int fqceMax) {
        this.idHardware = idHardware;
        this.nomHardware = nomHardware;
        this.marque = marque;
        this.model = model;
        this.noSerie = noSerie;
        this.poids = poids;
        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.puissanceMin = puissanceMin;
        this.puissanceMax = puissanceMax;
        this.fqceMin = fqceMin;
        this.fqceMax = fqceMax;
    }// fin Hardware()
    
    /**
     * Constructeur avec parametres
     * @param nomHardware 
     * @param marque 
     * @param model 
     * @param noSerie 
     * @param poids 
     * @param largeur 
     * @param longueur 
     * @param hauteur 
     * @param puissanceMin 
     * @param puissanceMax 
     * @param fqceMin 
     * @param fqceMax 
     */
    public Hardware(String nomHardware, String marque, String model, String noSerie, int poids, int longueur, int largeur, int hauteur, int puissanceMin, int puissanceMax, int fqceMin, int fqceMax) {
        this.nomHardware = nomHardware;
        this.marque = marque;
        this.model = model;
        this.noSerie = noSerie;
        this.poids = poids;
        this.longueur = longueur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.puissanceMin = puissanceMin;
        this.puissanceMax = puissanceMax;
        this.fqceMin = fqceMin;
        this.fqceMax = fqceMax;
    }// fin Hardware()

    /**
     * Getters & Setters -------------------------------------------------------
     */
    
    /**
     * Id Hardware : getIdHardware()
     * @return 
     */
    public Long getIdHardware() {
        return idHardware;
    }// fin getIdHardware()

    /**
     * Id Hardware : setIdHardware()
     * @param idHardware 
     */
    public void setIdHardware(Long idHardware) {
        this.idHardware = idHardware;
    }// fin setIdHardware()

    /**
     * Nom Hardware : getNomHardware()
     * @return 
     */
    public String getNomHardware() {
        return nomHardware;
    }// fin getNomHardware()

    /**
     * Nom Hardware : setNomHardware()
     * @param nomHardware 
     */
    public void setNomHardware(String nomHardware) {
        this.nomHardware = nomHardware;
    }// fin setNomHardware()

    /**
     * Marque : getMarque()
     * @return 
     */
    public String getMarque() {
        return marque;
    }// fin getMarque()

    /**
     * Maarque : setMarque()
     * @param marque 
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }// fin setMarque()

    /**
     * Model : getModel()
     * @return 
     */
    public String getModel() {
        return model;
    }// fin getModel()

    /**
     * Model : setModel()
     * @param model 
     */
    public void setModel(String model) {
        this.model = model;
    }// fin setModel()

    /**
     * No serie : getNoSerie()
     * @return 
     */
    public String getNoSerie() {
        return noSerie;
    }// fin getNoSerie()

    /**
     * No serie : setNoSerie()
     * @param noSerie 
     */
    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }// fin setNoSerie()

    /**
     * Poids : getPoids()
     * @return 
     */
    public int getPoids() {
        return poids;
    }// fin getPoids()

    /**
     * Poids : setPoids()
     * @param poids 
     */
    public void setPoids(int poids) {
        this.poids = poids;
    }// fin setPoids()

    /**
     * Longueur : getLongueur()
     * @return 
     */
    public int getLongueur() {
        return longueur;
    }// fin getLongueur()

    /**
     * Longueur : setLongueur()
     * @param longueur 
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }// fin setLongueur()

    /**
     * Largeur : getLargeur()
     * @return 
     */
    public int getLargeur() {
        return largeur;
    }// fin getLargeur()

    /**
     * Largeur : setLargeur()
     * @param largeur 
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }// fin setLargeur()

    /**
     * Hauteur : getHauteur()
     * @return 
     */
    public int getHauteur() {
        return hauteur;
    }// fin getHauteur()

    /**
     * Hauteur : setHauteur()
     * @param hauteur 
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }// fin setHauteur()

    /**
     * Puissance min : getPuissanceMin()
     * @return 
     */
    public int getPuissanceMin() {
        return puissanceMin;
    }// fin getPuissanceMin()
    
    /**
     * Puissance min : setPuissanceMin()
     * @param puissanceMin 
     */
    public void setPuissanceMin(int puissanceMin) {
        this.puissanceMin = puissanceMin;
    }// fin setPuissanceMin()

    /**
     * Puissance max : getPuissanceMax()
     * @return 
     */
    public int getPuissanceMax() {
        return puissanceMax;
    }// fin getPuissanceMax()

    /**
     * Puissance max : setPuissanceMax()
     * @param puissanceMax 
     */
    public void setPuissanceMax(int puissanceMax) {
        this.puissanceMax = puissanceMax;
    }// fin setPuissanceMax()

    /**
     * Fqce min : getFrqceMin()
     * @return 
     */
    public int getFqceMin() {
        return fqceMin;
    }// fin getFqceMin()

    /**
     * Fqce min : setFqceMin()
     * @param fqceMin 
     */
    public void setFqceMin(int fqceMin) {
        this.fqceMin = fqceMin;
    }// fin setFqceMin()

    /**
     * Fqce max : getFqceMax()
     * @return 
     */
    public int getFqceMax() {
        return fqceMax;
    }// fin getFqceMax()

    /**
     * Fqce max : setFqceMax()
     * @param fqceMax 
     */
    public void setFqceMax(int fqceMax) {
        this.fqceMax = fqceMax;
    }// finsetFqceMax()

    /**
     * Hardware : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        Long idHardware = getIdHardware();
        int hash = 0;
        
        hash += (idHardware != null ? idHardware.hashCode() : 0);
        
        return hash;
    }// fin hashCode()

    /**
     * Hardware : equals()
     * @param object 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hardware)) {
            return false;
        }// fin if ...
        
        Hardware other = (Hardware) object;
        
        /**
         * if ((this.idHardware == null && other.idHardware != null) || (this.idHardware != null && !this.idHardware.equals(other.idHardware))) {
         *   return false;
         * }// fin if ..
         */ 
        
        if ((this.getIdHardware() == null && other.getIdHardware() != null) || (this.getIdHardware() != null && !this.getNomHardware().equals(other.getNomHardware()))) return false;
        
        return true;
    }// fin equals()

    /**
     * Hardware : toString()
     * @return 
     */
    @Override
    public String toString() {
        // return "com.maglo.ManagerForm.entities.Hardware[ idHardware=" + idHardware + " ]";
        return String.format("%s", getNomHardware());   
    }// fin toString()
    
}// fin Hardware

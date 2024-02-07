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
@Table(name = "removal_form")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemovalForm.findAll", query = "SELECT r FROM RemovalForm r"),
    @NamedQuery(name = "RemovalForm.findByIdRmForm", query = "SELECT r FROM RemovalForm r WHERE r.idRmForm = :idRmForm"),
    @NamedQuery(name = "RemovalForm.findByJour", query = "SELECT r FROM RemovalForm r WHERE r.jour = :jour"),
    @NamedQuery(name = "RemovalForm.findByHrm", query = "SELECT r FROM RemovalForm r WHERE r.hrm = :hrm"),
    @NamedQuery(name = "RemovalForm.findByNomClnt", query = "SELECT r FROM RemovalForm r WHERE r.nomClnt = :nomClnt"),
    @NamedQuery(name = "RemovalForm.findByNomHware", query = "SELECT r FROM RemovalForm r WHERE r.nomHware = :nomHware"),
    @NamedQuery(name = "RemovalForm.findByNomAgent", query = "SELECT r FROM RemovalForm r WHERE r.nomAgent = :nomAgent"),
    @NamedQuery(name = "RemovalForm.findByAccessoires", query = "SELECT r FROM RemovalForm r WHERE r.accessoires = :accessoires"),
    @NamedQuery(name = "RemovalForm.countAll", query = "SELECT COUNT(r) FROM RemovalForm r"),
    @NamedQuery(name = "RemovalForm.downloadFile", 
        query = "SELECT r.idRmForm, r.jour, r.hrm, r.nomClnt, c.service, c.ville, c.telClient, r.nomHware, h.marque, h.model, h.noSerie, r.nomAgent, a.telAgent, r.accessoires\n" +
                "FROM RemovalForm r\n" +
                "LEFT JOIN Clients c ON r.nomClnt = c.nomClient \n" +
                "LEFT JOIN Hardware h ON r.nomHware = h.nomHardware\n" +
                "LEFT JOIN Agents a ON r.nomAgent = a.nomAgent\n" +
                "WHERE \n" +
                "r.idRmForm = :idRmForm AND r.jour = :jour")
})
public class RemovalForm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRmForm")
    private Long idRmForm;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "jour")
    @Temporal(TemporalType.DATE)
    private Date jour;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "hrm")
    @Temporal(TemporalType.TIME)
    private Date hrm;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomClnt")
    private String nomClnt;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomHware")
    private String nomHware;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomAgent")
    private String nomAgent;
    
    @Size(max = 4)
    @Column(name = "accessoires")
    private String accessoires;

    /**
     * Constructeurs de la classe 
     */
    
    /**
     * Constructeur 1 : RemovalForm()
     */
    public RemovalForm() {}// fin RemovalForm()

    /**
     * Constructeur 2 : RemovalForm()
     * @param idRmForm 
     */
    public RemovalForm(Long idRmForm) {
        this.idRmForm = idRmForm;
    }// fin RemovalForm()

    /**
     * Constructeur 3
     * @param idRmForm 
     * @param jour 
     * @param hrm 
     * @param nomClnt 
     * @param nomHware 
     * @param nomAgent 
     * @param accessoires
     */
    public RemovalForm(Long idRmForm, Date jour, Date hrm, String nomClnt, String nomHware, String nomAgent, String accessoires) {
        this.idRmForm = idRmForm;
        this.jour = jour;
        this.hrm = hrm;
        this.nomClnt = nomClnt;
        this.nomHware = nomHware;
        this.nomAgent = nomAgent;
        this.accessoires = accessoires;
    }// fin RemovalForm()
    
    /**
     * Constructeur 
     * @param jour 
     * @param hrm 
     * @param nomClnt 
     * @param nomHware 
     * @param nomAgent 
     * @param accessoires
     */
    public RemovalForm(Date jour, Date hrm, String nomClnt, String nomHware, String nomAgent, String accessoires) {
        // this.idRmForm = idRmForm;
        this.jour = jour;
        this.hrm = hrm;
        this.nomClnt = nomClnt;
        this.nomHware = nomHware;
        this.nomAgent = nomAgent;
        this.accessoires = accessoires;
    }// fin RemovalForm()

    /**
     * Id Rm Form
     * Methode : getIdRmForm()
     * @return 
     */
    public Long getIdRmForm() {
        return idRmForm;
    }// fin getIdRmFomr()

    /**
     * Id Rm Form
     * Methode : setIdRmForm()
     * @param idRmForm 
     */
    public void setIdRmForm(Long idRmForm) {
        this.idRmForm = idRmForm;
    }// fin setIdRmForm()

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
     * Methode : setJour()
     * @param jour 
     */
    public void setJour(Date jour) {
        this.jour = jour;
    }// fin de setJour()

    /**
     * Heure suppression : getHrm()
     * @return 
     */
    public Date getHrm() {
        return hrm;
    }// fin getHrm()

    /**
     * Heure suppression : setHrm()
     * @param hrm 
     */
    public void setHrm(Date hrm) {
        this.hrm = hrm;
    }// fin setHrm()

    /**
     * Clients : nomClnt
     * Methode : getNomClnt()
     * @return 
     */
    public String getNomClnt() {
        return nomClnt;
    }// fin de getNomClnt()
    
    /**
     * Clients : nomClnt
     * Methode : setNomClnt()
     * @param nomClnt 
     */
    public void setNomClnt(String nomClnt) {
        this.nomClnt = nomClnt;
    }// fin de setNomClnt()

    /**
     * Hardware : nomHware
     * Methode : getNomHware()
     * @return 
     */
    public String getNomHware() {
        return nomHware;
    }// fin de getNomHware()
    
    /**
     * Hardware : NomHware
     * Methode : getNomHware()
     * @param nomHware 
     */
    public void setNomHware(String nomHware) {
        this.nomHware = nomHware; 
    }// fin de setNomHware()

    /**
     * Agents : nomAgent
     * Methode : getNomAgent()
     * @return 
     */
    public String getNomAgent() {
        return nomAgent;
    }// fin de getNomAgent()
    
    /**
     * Agents : nomAgent
     * Methode : setNomAgent()
     * @param nomAgent 
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }// fin de setNomAgent()

    /**
     * Accessoires : getAccessoires()
     * @return 
     */
    public String getAccessoires() {
        return accessoires;
    }// fin getAccessoires()

    /**
     * Accessoires : setAccessoires()
     * @param accessoires 
     */
    public void setAccessoires(String accessoires) {
        this.accessoires = accessoires;
    }// fin setAccessoires()

    /**
     * Removal Form
     * Methode : HashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        Long idRmForm = getIdRmForm();
        int hash = 0;
        
        hash += (getIdRmForm() != null ? getIdRmForm().hashCode() : 0);
        
        return hash;
    }// fin hashCode()

    /**
     * Removal Form
     * Methode : equals()
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemovalForm)) {
            return false;
        }
        RemovalForm other = (RemovalForm) object;
        if ((this.idRmForm == null && other.idRmForm != null) || (this.idRmForm != null && !this.idRmForm.equals(other.idRmForm))) {
            return false;
        }
        return true;
    }// fin equals()

    /**
     * Removal Form
     * Methode : toString()
     * @return 
     */
    @Override
    public String toString() {
        return "com.maglo.ManagerForm.entities.RemovalForm[ idRmForm=" + idRmForm + " ]";
    }// fin toString()
    
}// fin classe RemovalForm

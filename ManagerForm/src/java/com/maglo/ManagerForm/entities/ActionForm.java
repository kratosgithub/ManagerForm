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

// import org.eclipse.persistence.annotations.Convert;
// import org.eclipse.persistence.annotations.Converter;
// import org.joda.time.DateTime;

// import com.maglo.ManagerForm.utils.JodaDateTimeConverter;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de representer une fiche d'intervention dans le systeme.
 */

@Entity
@Table(name = "action_form")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionForm.findAll", query = "SELECT a FROM ActionForm a ORDER BY a.idAction"),
    @NamedQuery(name = "ActionForm.findByIdAction", query = "SELECT a FROM ActionForm a WHERE a.idAction = :idAction"),
    @NamedQuery(name = "ActionForm.findByJour", query = "SELECT a FROM ActionForm a WHERE a.jour = :jour"),
    @NamedQuery(name = "ActionForm.findByHarrivee", query = "SELECT a FROM ActionForm a WHERE a.harrivee = :harrivee"),
    @NamedQuery(name = "ActionForm.findByHfin", query = "SELECT a FROM ActionForm a WHERE a.hfin = :hfin"),
    @NamedQuery(name = "ActionForm.findByNomCustomer", query = "SELECT a FROM ActionForm a WHERE a.nomCustomer = :nomCustomer"),
    @NamedQuery(name = "ActionForm.findByNomHdre", query = "SELECT a FROM ActionForm a WHERE a.nomHdre = :nomHdre"),
    @NamedQuery(name = "ActionForm.findByStatut", query = "SELECT a FROM ActionForm a WHERE a.statut = :statut"),
    @NamedQuery(name = "AcctionForm.countAll", query = "SELECT COUNT(a) FROM ActionForm a"),
    @NamedQuery(name = "ActionForm.downloadFile", 
        query = "SELECT a.idAction, a.jour, a.harrivee, a.hfin, a.statut, a.nomCustomer, c.service, c.ville, c.telClient, a.nomHdre, h.marque, h.model, h.noSerie, a.statut\n" +
                "FROM ActionForm a\n" +
                "LEFT JOIN Clients c ON a.nomCustomer = c.nomClient\n" +
                "LEFT JOIN Hardware h ON a.nomHdre = h.nomHardware\n" +
                "WHERE a.idAction = :idAction AND a.jour = :jour")
    // @NamedQuery(name = "ActionForm.findByClients", query = "SELECT a FROM ActionForm a JOIN a.idCustomer c JOIN a.idHdre h WHERE c.clientsPK =:nomClient and h.hardwarePK =:nomHardware")
})
public class ActionForm implements Serializable {

    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAction")
    private Long idAction;
    
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
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "hfin")
    @Temporal(TemporalType.TIME)
    private Date hfin;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomCustomer")
    private String nomCustomer;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomHdre")
    private String nomHdre;
    
    @Size(max = 27)
    @Column(name = "statut")
    private String statut;

    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * Constructeur 1 : Action Form 
     * Methode : constructeur vide
     */
    public ActionForm() {
    }// fin ActionForm()

    /**
     * Action Form 
     * Methode : constructeur avec un seul parametre
     * @param idAction 
     */
    public ActionForm(Long idAction) {
        this.idAction = idAction;
    }// fin ActionForm()

    /**
     * Constructeur 2 : Action Form
     * Methode : constructeur avec plusieurs parametres
     * @param idAction 
     * @param jour 
     * @param harrivee 
     * @param hfin 
     * @param nomCustomer 
     * @param nomHdre 
     */
    public ActionForm(Long idAction, Date jour, Date harrivee, Date hfin, String nomCustomer, String nomHdre) {
        this.idAction = idAction;
        this.jour = jour;
        this.harrivee = harrivee;
        this.hfin = hfin;
        this.nomCustomer = nomCustomer;
        this.nomHdre = nomHdre;
    }// fin ActionForm()
    
    /**
     * Constructeur 3 : Action Form
     * Methode : constructeur avec plusieurs parametres
     * @param jour
     * @param harrivee 
     * @param hfin 
     * @param nomCustomer
     * @param nomHdre
     * @param statut 
     */
    public ActionForm(Date jour, Date harrivee, Date hfin, String nomCustomer, String nomHdre, String statut) {
        this.jour = jour;
        this.harrivee = harrivee;
        this.hfin = hfin;
        this.nomCustomer = nomCustomer;
        this.nomHdre = nomHdre;
        this.statut = statut;
    }// fin ActionForm()

    /**
     * Getters & Setters -------------------------------------------------------
     */
    
    /**
     * Id Action
     * Methode : getIdAction()
     * @return 
     */
    public Long getIdAction() {
        return idAction;
    }// fin getIdAction()

    /**
     * Id Action
     * Methode : setIdAction()
     * @param idAction 
     */
    public void setIdAction(Long idAction) {
        this.idAction = idAction;
    }// fin setIdAction()

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
     * Heure arrivee : getHarrivee()
     * @return 
     */
    public Date getHarrivee() {
        return harrivee;
    }// fnn getHarrivee()

    /**
     * Heure arrivee : setHarrivee()
     * @param harrivee 
     */
    public void setHarrivee(Date harrivee) {
        this.harrivee = harrivee;
    }// fin setHarrivee()

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
     * Nom Customer
     * Methode : getNomCustomer()
     * @return 
     */
    public String getNomCustomer() {
        return nomCustomer;
    }// fin getNomCustomer()

    /**
     * Nom Customer
     * Methode : setNomCustomer()
     * @param nomCustomer 
     */
    public void setNomCustomer(String nomCustomer) {
        this.nomCustomer = nomCustomer;
    }// fin setNomCustomer()

    /**
     * Nom Hdre
     * Methode : getNomHdre()
     * @return 
     */
    public String getNomHdre() {
        return nomHdre;
    }// fin getNomHdre()

    /**
     * Nom Hdre
     * Methode : setNomHdre()
     * @param nomHdre 
     */
    public void setNomHdre(String nomHdre) {
        this.nomHdre = nomHdre;
    }// fin setNomHdre()

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Action Form
     * Methode : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (idAction != null ? idAction.hashCode() : 0);
        
        return hash;
    }// fin hashCode()

    /**
     * Action Form
     * Methode : equals()
     * @param object 
     * @return 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionForm)) {
            return false;
        }// fin if condition
        
        ActionForm other = (ActionForm) object;
        
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }// fin if condition
        
        return true;
    }// fin equals()

    /**
     * Action Form
     * Methode : toString()
     * @return 
     */
    @Override
    public String toString() {
        return "com.maglo.ManagerForm.entities.ActionForm[ idAction=" + idAction + " ]";
    }// fin  toString()
    
}// fin classe ActionForm 

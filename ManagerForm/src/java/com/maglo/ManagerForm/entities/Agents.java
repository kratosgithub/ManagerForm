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
 * Classe utilitaire permettant de representer un client dans le systeme. 
 */
@Entity
@Table(name = "agents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agents.findAll", query = "SELECT a FROM Agents a"),
    @NamedQuery(name = "Agents.findByIdAgent", query = "SELECT a FROM Agents a WHERE a.idAgent = :idAgent"),
    @NamedQuery(name = "Agents.findByNomAgent", query = "SELECT a FROM Agents a WHERE a.nomAgent = :nomAgent"),
    @NamedQuery(name = "Agents.findByTelAgent", query = "SELECT a FROM Agents a WHERE a.telAgent = :telAgent"),
    @NamedQuery(name = "Agents.countAll", query = "SELECT COUNT(a) FROM Agents a")
})
public class Agents implements Serializable {

    // Declaration des variables de la classe 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAgent")
    private Long idAgent;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomAgent")
    private String nomAgent;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "telAgent")
    private String telAgent;

    /**
     * Constructeurs de la classe ----------------------------------------------
     */
    
    /**
     * Agents : constructeur vide 
     */
    public Agents() {}

    /**
     * Agents : constructeur avec parametre
     * @param idAgent 
     */
    public Agents(Long idAgent) {
        this.idAgent = idAgent;
    }// fin Agents()

    /**
     * Agents : constructeur avec parametres
     * @param idAgent
     * @param nomAgent 
     * @param telAgent 
     */
    public Agents(Long idAgent, String nomAgent, String telAgent) {
        this.idAgent = idAgent;
        this.nomAgent = nomAgent;
        this.telAgent = telAgent;
    }// fin Agents()
    
    /**
     * Agents : getIdAgent()
     * @return 
     */
    public Long getIdAgent() {
        return idAgent;
    }// fin getIdAgent()

    /**
     * Agents : setIdAgent()
     * @param idAgent 
     */
    public void setIdAgent(Long idAgent) {
        this.idAgent = idAgent;
    }// fin setIdAgent()

    /**
     * Agents : getNomAgent()
     * @return 
     */
    public String getNomAgent() {
        return nomAgent;
    }// fin getNomAgent()

    /**
     * Agents : setNomAgent()
     * @param nomAgent 
     */
    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }// fin setNomAgent()

    /**
     * Agents : getTelAgent()
     * @return 
     */
    public String getTelAgent() {
        return telAgent;
    }// fin getTelAgent()

    /**
     * Agents : setTelAgent()
     * @param telAgent 
     */
    public void setTelAgent(String telAgent) {
        this.telAgent = telAgent; 
    }// fin setTelAgent()

    /**
     * Agents : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        Long idAgent = getIdAgent();
        int hash = 0;
        
        hash += (idAgent != null ? idAgent.hashCode() : 0);
        
        return hash;
    }// fin hashCode()

    /**
     * Agents : equals()
     * @param object 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agents)) {
            return false;
        }// fin if ..
        
        Agents other = (Agents) object;
        
        /**
         * if ((this.idAgent == null && other.idAgent != null) || (this.idAgent != null && !this.idAgent.equals(other.idAgent))) {
         *   return false;
         * }// fin if ..
         */ 
        
        if ((this.getIdAgent() == null && other.getIdAgent() != null) || (this.getIdAgent() != null && !this.getNomAgent().equals(other.getNomAgent()))) return false;
        
        return true;
    }// fin equals()

    /**
     * Agents : toString()
     * @return 
     */
    @Override
    public String toString() {
        // return "com.maglo.ManagerForm.entities.Agents[ idAgent=" + idAgent + " ]";
        return String.format("%s", getNomAgent());  
    }// fin toString()
    
}// fin class Agents

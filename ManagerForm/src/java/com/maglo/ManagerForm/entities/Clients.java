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
@Table(name = "clients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c"),
    @NamedQuery(name = "Clients.findByIdClient", query = "SELECT c FROM Clients c WHERE c.idClient = :idClient"),
    @NamedQuery(name = "Clients.findByNomClient", query = "SELECT c FROM Clients c WHERE c.nomClient = :nomClient"),
    @NamedQuery(name = "Clients.findByService", query = "SELECT c FROM Clients c WHERE c.service = :service"),
    @NamedQuery(name = "Clients.findByVille", query = "SELECT c FROM Clients c WHERE c.ville = :ville"),
    @NamedQuery(name = "Clients.findByTelClient", query = "SELECT c FROM Clients c WHERE c.telClient = :telClient"),
    @NamedQuery(name = "Clients.countAll", query = "SELECT COUNT(c) FROM Clients c")
})
public class Clients implements Serializable {

    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClient")
    private Long idClient;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomClient")
    private String nomClient;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "service")
    private String service;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ville")
    private String ville;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "telClient")
    private String telClient;

    /**
     * Constructeurs de la classe 
     */
    
    /**
     * Constructeur vide
     */
    public Clients() {}// fin Clients()

    /**
     * Clients : Clients()
     * @param idClient 
     */
    public Clients(Long idClient) {
        this.idClient = idClient;
    }// fin Clients()

    /**
     * Clients : Clients()
     * @param idClient 
     * @param nomClient 
     * @param service 
     * @param ville 
     * @param telClient 
     */
    public Clients(Long idClient, String nomClient, String service, String ville, String telClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.service = service;
        this.ville = ville;
        this.telClient = telClient;
    }// fin Clients()

    /**
     * Clients : Clients() 
     * @param nomClient 
     * @param service 
     * @param ville 
     * @param telClient 
     */
    public Clients(String nomClient, String service, String ville, String telClient) {
        this.nomClient = nomClient;
        this.service = service;
        this.ville = ville;
        this.telClient = telClient;
    }// fin Clients()
    
    /**
     * idClient : getIdClient()
     * @return 
     */
    public Long getIdClient() {
        return idClient;
    }// fin getIdClients()

    /**
     * idClient : setIdClient()
     * @param idClient 
     */
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }// fin setIdClient()

    /**
     * Nom client : getNomClient()
     * @return 
     */
    public String getNomClient() {
        return nomClient;
    }// fin getNomClient()

    /**
     * Nom client : setNomClient()
     * @param nomClient 
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }// fin setNomClient()

    /**
     * Service : getService()
     * @return 
     */
    public String getService() {
        return service;
    }// fin getService()

    /**
     * Service : setService()
     * @param service 
     */
    public void setService(String service) {
        this.service = service;
    }// fin setService()

    /**
     * Ville : getVille()
     * @return 
     */
    public String getVille() {
        return ville;
    }// fin getVille()

    /**
     * Ville : setVille()
     * @param ville 
     */
    public void setVille(String ville) {
        this.ville = ville;
    }// fin setVille()

    /**
     * Tel Client : getTelClient()
     * @return 
     */
    public String getTelClient() {
        return telClient;
    }// fin getTelClient()

    /**
     * Tel Client : setTelClient()
     * @param telClient 
     */
    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }// fin setTelClient()

    /**
     * Clients : hashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        Long idClient = getIdClient();
        int hash = 0; 
        
        hash += (idClient != null ? idClient.hashCode() : 0);
        
        return hash;
    }// fin hashCode()

    /**
     * Clients : equals()
     * @param object 
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clients)) {
            return false;
        }// fin if ..
        
        Clients other = (Clients) object;
        
        if ((this.getIdClient() == null && other.getIdClient() != null) || (this.getIdClient() != null && !this.getNomClient().equals(other.getNomClient()))) {
            return false;
        }// fin if ..
       
        return true;
    }// fin equals()

    /**
     * Clients : toString()
     */
    @Override
    public String toString() {
        // return "com.maglo.ManagerForm.entities.Clients[ idClient=" + idClient + " ]";
        return String.format("%s", getNomClient()); 
    }// fin toString()
    
}// fin Clients

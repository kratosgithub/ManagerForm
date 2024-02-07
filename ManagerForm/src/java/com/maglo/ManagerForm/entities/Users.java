/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author junior.ndozeng
 * User : Classe utilitaire permettant de representer un utilisateur dans le système
 * <!-- Admins -->, <!-- User --> et <!-- Technicien -->
 */

@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.countAll", query = "SELECT COUNT(u) FROM Users u"),
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    // @NamedQuery(name = "Users.findByIdUsers", query = "SELECT u FROM Users u WHERE u.usersPK.idUsers = :idUsers"),
    @NamedQuery(name = "Users.findByIdUsers", query = "SELECT u FROM Users u WHERE u.idUsers = :idUsers"),
    @NamedQuery(name = "Users.findByNom", query = "SELECT u FROM Users u WHERE u.nom = :nom"),
    @NamedQuery(name = "Users.findByPrenom", query = "SELECT u FROM Users u WHERE u.prenom = :prenom"),
    // @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.usersPK.login = :login"),
    @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByTel", query = "SELECT u FROM Users u WHERE u.tel = :tel")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*@EmbeddedId
    protected UsersPK usersPK;*/
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_users")
    private Long idUsers;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nom")
    private String nom;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "prenom")
    private String prenom;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "login")
    private String login;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "tel")
    private String tel;

    /**
     * Construteurs de la classe ------------------------------------------------
     */
    
    /**
     * Constructeur vide : User()
     */
    public Users() {}// fin de User()
    
    /**
     * Constructeur initialise : User()
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param tel
     */
    public Users(String nom, String prenom, String login, String password, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.tel = tel;
    }// fin de User()

    /*public Users(UsersPK usersPK) {
    this.usersPK = usersPK;
    }*/

    /*public Users(UsersPK usersPK, String nom, String prenom, String password, String tel) {
    this.usersPK = usersPK;
    this.nom = nom;
    this.prenom = prenom;
    this.password = password;
    this.tel = tel;
    }*/

    /*public Users(int idUsers, String login) {
    this.usersPK = new UsersPK(idUsers, login);
    }*/

    /**
     *  GETTERS & SETTERS ------------------------------------------------------
     */
    
    /**
     * Id_Users : id de l'utilisateur
     * Methode : getIdUsers()
     * @return 
     */
    public Long getIdUsers() {
        return idUsers;
    }// fin de getIdUsers()
    
    /**
     * Id_users : id de l'utilisateur
     * Méthode setIdUsers()
     * @param idUsers
     */
    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }// fin de setIdUsers()

    /**
     * Nom : nom de l'uitlisateur
     * Methode : getNom()
     * @return
     */
    public String getNom() {
        return nom;
    }// fin getNom()

    /**
     * Nom : nom de l'utilisateur
     * Methode : setNom()
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }// fin setNom()

    /**
     * Prenom : prenom de l'utilisateur
     * Methode : getPrenom()
     * @return
     */
    public String getPrenom() {
        return prenom;
    }// fin getPrenom()

    /**
     * Prenom : prenom de l'utilisateur
     * Methode : setPrenom()
     * @param prenom 
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }// fin setPrenom()

    /**
     * Login : identifiant de l'utilisateur
     * Methode : getLogin()
     * @return
     */
    public String getLogin() {
        return login;
    }// fin de getLogin()
    
    /**
     * Login : identifiant de l'utilisateur
     * Methode : setLogin()
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }// fin de setLogin()
    
    /**
     * Password : mot de passe de l'utilisateur
     * Methode : getPassword()
     * @return
     */
    public String getPassword() {
        return password;
    }// fin getPassword()

    /**
     * Password : mot de passe de l'utilisateur
     * Methode : setPassword()
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }// fin setPassword()

    /**
     * Tel : telephone de l'utilisateur
     * Methode : getTel()
     * @return
     */
    public String getTel() {
        return tel;
    }// fin getTel()

    /**
     * Tel : telephone de l'utilisateur
     * Methode : setTel()
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }// fin setTel()

    /**
     * Removal Form
     * Methode : HashCode()
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsers != null ? idUsers.hashCode() : 0);
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.idUsers == null && other.idUsers != null) || (this.idUsers != null && !this.idUsers.equals(other.idUsers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.maglo.ManagerForm.entities.Users[ usersPK=" + idUsers + " ]";
    }
    
}// fin classe Users

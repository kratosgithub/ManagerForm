/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.maglo.ManagerForm.ejb.DAOException;
import com.maglo.ManagerForm.ejb.UserEJB;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de vérifier l'existence d'un nom déjà enregistré
 * dans le système.
 */

@ManagedBean
@RequestScoped 
public class ExistenceNomValidator implements Validator {
    
    // Declaration des variables de la classe
    private static final String MESSAGE_INFO = "Ce nom existe déjà. Veuillez saisir un autre nom.";
    
    @EJB
    private UserEJB uejb;
    
    /**
     * ExistenceNomValidator : validate()
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        /* Récupération de la valeur à traiter depuis le paramètre value */
        String nom = (String) value;
        
        try {// essai 
            if(uejb.getUserByNom(nom) != null) {
                /**
                 * Si un nom est retourné, alors on envoie une exception propre à la JSF, qu'on initialise avec une message de gravité
                 * "Information" et contenant le message d'explication. Le framework va alors lui-même gérer l'exception et s'en servir
                 * pour afficher le message
                 */
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_INFO, "Information :", MESSAGE_INFO)); 
            }// fin de la condition if..
        } catch (DAOException e) {
            /**
             * En cas d'erreur imprévue émanant de la BDD, on récupère un message d'erreur contenant  l'exception
             * retournée pour l'afficher à l'utlisateur.
             */
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Une erreur imprévue est survenue lors de la tentative de connexion à la base de données.");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(component.getClientId(context), message);
        }// fin du try .. catch 
    }// fin de validate()
    
}// fin de la classe ExistenceNomValidator

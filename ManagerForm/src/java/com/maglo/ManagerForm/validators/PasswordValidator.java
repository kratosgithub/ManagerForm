/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de vérifier la validité d'un mot de passe saisi
 * par un utilisateur.
 */

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {
    
    // Declaration des variables de la classe
    private static final String PASSWORD_WARN = "Le mot de passe doit être compris entre 10 et 20 caractères, y compris les lettres majuscules et minuscules.";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{10,20})";
    
    private Matcher matcher;
    private Pattern pattern;
    
    /**
     * PasswordValidator : PasswordValidate()
     */
    public PasswordValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }// fin de  PasswordValidator()
    
    /**
     * PasswordValidate : validate()
     * 
     * @param context
     * @param component
     * @param value
     * @throws ValidatorException
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
        
        if(!matcher.matches())
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention :", PASSWORD_WARN));
    }// fin de validate()
    
    /**
     * validate regex : password 
     * <!-- f:validateRegex pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,12})" -->
     */
    
}// fin de la classe PasswordValidator

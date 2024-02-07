/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.validators;

import javax.ejb.EJB;
// import javax.el.ValueExpression;
import javax.faces.convert.Converter;
// import javax.faces.convert.ConverterException;
// import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
// import javax.faces.validator.Validator;
// import javax.faces.validator.ValidatorException;

// import com.maglo.ManagerForm.ejb.DAOException;
import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.entities.Clients;
// import com.maglo.ManagerForm.managedbeans.AddReceiptFormController;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de representer un client existant en chaine de caractere.
 */

@ManagedBean
@RequestScoped 
public class ClientsConverter implements Converter {
    
    @EJB
    private ClientsEJB cejb;    
   
    /**
     * Client converter
     * @param context
     * @param component
     * @param value
     * @return 
     */
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return cejb.getClientsByNomClient(value);
        // No Change ...
        
    }// fin getAsObject()
   
    /**
     * Client converter
     * @param context
     * @param component
     * @param value
     * @return 
     */
    @Override 
    public String getAsString(FacesContext context, UIComponent component, Object value) {
    
        return value == null ? null : value instanceof Clients ? ((Clients) value).getIdClient().toString() : null;
        // return ((Clients) value).getIdClient().toString();
    }// fin getAsString()
    
}// fin de la classe ClientsConverter

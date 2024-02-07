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

import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.entities.Hardware;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de representer un equipement existant en chaine de caractere.
 */

@ManagedBean
@RequestScoped 
public class HardwareConverter implements Converter {
    
    @EJB
    private HardwareEJB hejb;
    
    /**
     * Client converter
     * @param context
     * @param component
     * @param value
     * @return 
     */
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      
        return hejb.getHardwareByNomHardware(value); 
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
        
        return value == null ? null : value instanceof Hardware ? ((Hardware) value).getIdHardware().toString() : null;
    }// fin getAsString()
    
}// fin de la classe HardwareConverter

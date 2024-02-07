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
import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.entities.Agents;
// import com.maglo.ManagerForm.managedbeans.AddReceiptFormController;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de convertir un technicien existant en chaine de caractere.
 */

@ManagedBean
@RequestScoped 
public class AgentsConverter implements Converter {
    
    @EJB
    private AgentsEJB aejb;
    
    /**
     * Client converter
     * @param context
     * @param component
     * @param value
     * @return 
     */
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      
        return aejb.getAgentsByNomAgent(value); 
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
        
        return value == null ? null : value instanceof Agents ? ((Agents) value).getIdAgent().toString() : null;
    }// fin getAsString()
    
}// fin de la classe AgentsConverter

/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.io.File;
// import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.maglo.ManagerForm.ejb.ActionFormEJB;
import com.maglo.ManagerForm.entities.ActionForm;
// import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de lister les fiches d'intervention enregistrées
 * dans le systeme
 */

@Named(value = "listActionController")
@ViewScoped 
public class ListActionController implements Serializable {
    
    // Declaration des variables
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListActionController.class.getName());
    
    @Inject
    private ActionFormEJB afejb;
    
    private List<ActionForm> actionForms;
    private List<ActionForm> printFile;
    private List<ActionForm> filteredActionForms;
    
    /**
     * Pre Install Form : init()
     * On initialise la liste des fiches de pre installation
     */
    @PostConstruct
    public void init() {
        actionForms = afejb.getAllActionForm();
        filteredActionForms = afejb.getAllActionForm();
    }// init()
    
    /**
     * GETTERS & SETTERS -------------------------------------------------------
     */
    
    /**
     * ActionFormEJB : getActionFormEJB()
     * @return
     */
    public ActionFormEJB getActionFormEJB() {
        return afejb;
    }// fin getActionFormEJB()
    
    /**
     * ActionFormEJB : setActionFormEJB()
     * @param afejb
     */
    public void setActionFormEJB(ActionFormEJB afejb) {
        this.afejb = afejb;
    }// fin setActionFormEJB()
    
    /**
     * ActionForm : getActionForms()
     * @return
     */
    public List<ActionForm> getActionForms() {
        return actionForms;
    }// fin getActionForms()
    
    /**
     * ActionForm : setActionForms()
     * @param actionForms
     */
    public void setActionForms(List<ActionForm> actionForms) {
        this.actionForms = actionForms;
    }// fin setActionForms()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public List<ActionForm> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<ActionForm> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * FilteredActionForms : getFilteredActionForms()
     * @return
     */
    public List<ActionForm> getFilteredActionForms() {
        return filteredActionForms;
    }// fin getFilteredActionForms()
    
    /**
     * FilteredActionForms : setFilteredActionForms()
     * @param filteredActionForms()
     */
    public void setFilteredActionForms(List<ActionForm> filteredActionForms) {
        this.filteredActionForms = filteredActionForms; 
    }// fin setFilteredActionForms()
    
    /**
     * ActionForm : globalFilterActionForm()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterActionForm(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        ActionForm af = (ActionForm) value; 
        
        return af.getNomCustomer().toLowerCase().contains(filterText)
                || af.getNomHdre().toLowerCase().contains(filterText);
    }// fin globlalFilterActionForm()
    
    /**
     * ReceiptForm : downloadFile()
     * Charge de generer la fiche de reception sous format PDF
     * @param ae 
     * @throws JRException
     * @throws IOException 
     * @throws SQLException 
     * @throws Exception 
     */
    public void downloadFile(ActionEvent ae) throws JRException, IOException, SQLException, Exception {
        // Declaration des variables 
        FacesContext context = FacesContext.getCurrentInstance();
        printFile = afejb.getAllActionForm();
        // receiptForm = rfejb.getReceiptByNom(nomReceipt);
        
        // Configuration de la source de données SQL
        String url = "jdbc:mysql://localhost:3306/gmaodb?useSSL=false";
        String username = "maglo";
        String pwd = "M@gloWamp666++";
        // String query = "SELECT * FROM receipt_form WHERE nomReceipt =? AND destination =?";
        
        // Connection connection = null;
        Connection connection = DriverManager.getConnection(url, username, pwd);
        // statement = connection.createStatement();
        // rs = statement.executeQuery(query);
        
        // JRResultSetDataSource jrrsds = new JRResultSetDataSource(rs);
        // JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(printFile);
        
        Map parameters = new HashMap();
        parameters.put("actionform", getActionForms()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FIS.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FIS.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La liste des fiches d'interventions a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioactionforms.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FIS.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin ListActionController

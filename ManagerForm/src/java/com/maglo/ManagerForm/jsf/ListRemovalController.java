/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.List;
import java.util.Locale;
import java.io.File;
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

import com.maglo.ManagerForm.ejb.RemovalFormEJB;
import com.maglo.ManagerForm.entities.RemovalForm;
// import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher la liste des fiches de retraits.
 */

@Named(value = "listRemovalController")
@ViewScoped 
public class ListRemovalController implements Serializable {
    
    // Declaration des variables
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListRemovalController.class.getName());
    
    @Inject
    private RemovalFormEJB rfejb;
    
    private List<RemovalForm> removalForms;
    private List<RemovalForm> printFile;
    private List<RemovalForm> filteredRemovalForms;
    
    /**
     * Receipt Form : init()
     * On initialise la liste des fiches de retraits
     */
    @PostConstruct
    public void init() {
        removalForms = rfejb.getAllRemovalForm();
        filteredRemovalForms = rfejb.getAllRemovalForm();
    }// init()
    
    /**
     * GETTERS & SETTERS -------------------------------------------------------
     */
    
    /**
     * RemovalFormEJB : getRemovalFormEJB()
     * @return
     */
    public RemovalFormEJB getRemovalFormEJB() {
        return rfejb;
    }// fin getRemovalFormEJB()
    
    /**
     * RemovalFormEJB : setRemovalFormEJB()
     * @param rfejb
     */
    public void setRemovalFormEJB(RemovalFormEJB rfejb) {
        this.rfejb = rfejb;
    }// fin setRemovalFormEJB()
    
    /**
     * RemovalForm : getRemovalForms()
     * @return
     */
    public List<RemovalForm> getRemovalForms() {
        return removalForms;
    }// fin getRemovalForms()
    
    /**
     * RemovalFormEJB : setRemovalFormEJB()
     * @param removalForms
     */
    public void setRemovalForms(List<RemovalForm> removalForms) {
        this.removalForms = removalForms; 
    }// fin setRemovalForms()
    
    /**
     * FilteredRemovalForms : getFilteredRemovalForms()
     * @return
     */
    public List<RemovalForm> getFilteredRemovalForms() {
        return filteredRemovalForms;
    }// fin getFilteredRemoavalForms()
    
    /**
     * FilteredRemaovalForms : setFilteredRemovalForms()
     * @param filteredRemovalForms()
     */
    public void setFilteredRemovalForms(List<RemovalForm> filteredRemovalForms) {
        this.filteredRemovalForms = filteredRemovalForms; 
    }// fin setFilteredRemovalForms()
    
    /**
     * RemovalForm : printFile
     * @return 
     */
    public List<RemovalForm> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * RemovalForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<RemovalForm> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * RemovalForm : globalFilterRemovalForm()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterRemovalForm(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        RemovalForm removalForm = (RemovalForm) value; 
        
        return removalForm.getNomAgent().toLowerCase().contains(filterText)
                || removalForm.getNomClnt().toLowerCase().contains(filterText)
                || removalForm.getNomHware().toLowerCase().contains(filterText);
    }// fin globlalFilterRemovalForm()
    
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
        printFile = rfejb.getAllRemovalForm();
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
        parameters.put("removalform", getRemovalForms()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FRM.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FRM.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La liste des fiches des retraits d'équipements a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioremovalforms.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FRM.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin de la classe ListRemovalController

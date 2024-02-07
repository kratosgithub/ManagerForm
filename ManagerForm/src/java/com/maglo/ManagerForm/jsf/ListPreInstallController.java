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

import com.maglo.ManagerForm.ejb.PreInstallFormEJB;
import com.maglo.ManagerForm.entities.PreInstallForm;
// import com.maglo.ManagerForm.jsf.util.Util;

/**
 * @author junior.ndozeng
 * Classe utilitaire permettant de lister les fiches de pre installations enregistrées
 * dans le systeme
 */

@Named(value = "listPreInstallController")
@ViewScoped 
public class ListPreInstallController implements Serializable {
    
    // Declaration des variables
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListPreInstallController.class.getName());
    
    @Inject
    private PreInstallFormEJB pifejb;
    
    private List<PreInstallForm> installForms;
    private List<PreInstallForm> printFile;
    private List<PreInstallForm> filteredInstallForms;
    
    /**
     * Pre Install Form : init()
     * On initialise la liste des fiches de pre installation
     */
    @PostConstruct
    public void init() {
        installForms = pifejb.getAllPreInstallForm();
        filteredInstallForms = pifejb.getAllPreInstallForm();
    }// init()
    
    /**
     * GETTERS & SETTERS -------------------------------------------------------
     */
    
    /**
     * PreInstallFormEJB : getPreInstallFormEJB()
     * @return
     */
    public PreInstallFormEJB getPreInstallFormEJB() {
        return pifejb;
    }// fin getPreInstallFormEJB()
    
    /**
     * PreInstallFormEJB : setPreInstallFormEJB()
     * @param pifejb
     */
    public void setPreInstallFormEJB(PreInstallFormEJB pifejb) {
        this.pifejb = pifejb;
    }// fin setPreInstallFormEJB()
    
    /**
     * PreInstallForm : getInstallForms()
     * @return
     */
    public List<PreInstallForm> getInstallForms() {
        return installForms;
    }// fin getPreInstallForms()
    
    /**
     * PreInstallForm : setInstallForms()
     * @param installForms
     */
    public void setInstallForms(List<PreInstallForm> installForms) {
        this.installForms = installForms;
    }// fin setInstallForms()
    
    /**
     * FilteredInstallForms : getFilteredInstallForms()
     * @return
     */
    public List<PreInstallForm> getFilteredInstallForms() {
        return filteredInstallForms;
    }// fin getFilteredInstallForms()
    
    /**
     * FilteredInstallForms : setFilteredInstallForms()
     * @param filteredInstallForms()
     */
    public void setFilteredInstallForms(List<PreInstallForm> filteredInstallForms) {
        this.filteredInstallForms = filteredInstallForms; 
    }// fin setFilteredInstallForms()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public List<PreInstallForm> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<PreInstallForm> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * PreInstallForm : globalFilterInstallForm()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterInstallForm(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        PreInstallForm pif = (PreInstallForm) value; 
        
        return pif.getNomClient().toLowerCase().contains(filterText)
                || pif.getNomHardware().toLowerCase().contains(filterText);
    }// fin globlalFilterInstallForm()
    
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
        printFile = pifejb.getAllPreInstallForm();
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
        parameters.put("preinstallform", getInstallForms()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FPS.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FPS.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La liste des fiches de pré-installations des équipements a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=biopreinstallforms.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FES.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin ListPreInstallController

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

import com.maglo.ManagerForm.ejb.InstallFormTypecEJB;
import com.maglo.ManagerForm.entities.InstallFormTypec;
// import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant de lister les fiches d'installations WP21A enregistrées
 * dans le systeme
 */

@Named(value = "listInstallTypecController")
@ViewScoped 
public class ListInstallTypecController implements Serializable {
    
   // Declaration des variables
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListInstallTypecController.class.getName());
    
    @Inject
    private InstallFormTypecEJB iftejb;
    
    private List<InstallFormTypec> installFormTypecs;
    private List<InstallFormTypec> printFile;
    private List<InstallFormTypec> filteredInstallFormTypecs;
    
    /**
     * Install Form : init()
     * On initialise la liste des fiches d'installations WP21A
     */
    @PostConstruct
    public void init() {
        installFormTypecs = iftejb.getAllInstallTypec();
        filteredInstallFormTypecs = iftejb.getAllInstallTypec(); 
    }// init() 
    
    /**
     * GETTERS & SETTERS -------------------------------------------------------
     */
    
    /**
     * InstallFormTypecEJB : getInstallFormTypecEJB()
     * @return
     */
    public InstallFormTypecEJB getInstallFormTypecEJB() {
        return iftejb;
    }// fin getInstallFormTypecEJB()
    
    /**
     * InstallFormTypecEJB : setInstallFormTypecEJB()
     * @param iftejb
     */
    public void setInstallFormTypecEJB(InstallFormTypecEJB iftejb) {
        this.iftejb = iftejb;
    }// fin setInstallFormTypecEJB()
    
    /**
     * InstallFormTypec : getInstallFormTypecs()
     * @return
     */
    public List<InstallFormTypec> getInstallFormTypecs() {
        return installFormTypecs;
    }// fin getInstallFormTypecs()
    
    /**
     * InstallFormTypec : setInstallFormTypecs()
     * @param installFormTypecs
     */
    public void setInstallFormTypecs(List<InstallFormTypec> installFormTypecs) {
        this.installFormTypecs = installFormTypecs;
    }// fin setInstallFormTypecs()
    
    /**
     * FilteredInstallFormTypecs : getFilteredInstallFormTypecs()
     * @return
     */
    public List<InstallFormTypec> getFilteredInstallFormTypecs() {
        return filteredInstallFormTypecs;
    }// fin getFilteredInstallFormTypecs()
    
    /**
     * FilteredInstallFormTypecs : setFilteredInstallFormTypecs()
     * @param filteredInstallFormTypecs()
     */
    public void setFilteredInstallFormTypecs(List<InstallFormTypec> filteredInstallFormTypecs) {
        this.filteredInstallFormTypecs = filteredInstallFormTypecs; 
    }// fin setFilteredInstallFormTypecs()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public List<InstallFormTypec> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<InstallFormTypec> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * InstallFormTypec : globalFilterInstallFormTypec()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterInstallFormTypec(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        InstallFormTypec ift = (InstallFormTypec) value; 
        
        return ift.getNomCusto().toLowerCase().contains(filterText)
                || ift.getNomEqpmt().toLowerCase().contains(filterText);
    }// fin globlalFilterInstallFormTypec()
    
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
        printFile = iftejb.getAllInstallTypec();
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
        parameters.put("installformtypec", getInstallFormTypecs()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FWS.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FWS.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La liste des fiches d'installations des équipements WP21A a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioinstallwp21as.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FES.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin ListInstallTypecController

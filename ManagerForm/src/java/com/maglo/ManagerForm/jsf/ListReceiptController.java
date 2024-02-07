/*
 * Copyright 2022 Maglo Solutions Corp.
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

import com.maglo.ManagerForm.ejb.ReceiptFormEJB;
import com.maglo.ManagerForm.entities.ReceiptForm;
// import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant d'afficher la liste des fiches de reception.
 */

@Named(value = "listReceiptController")
@ViewScoped 
public class ListReceiptController implements Serializable {
    
    // Declaration des variables
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListReceiptController.class.getName());
    
    @Inject
    private ReceiptFormEJB rfejb;
    
    private List<ReceiptForm> receiptForms;
    private List<ReceiptForm> printFile;
    private List<ReceiptForm> filteredReceiptForms;
    
    /**
     * Receipt Form : init()
     * On initialise la liste des fiches de reception
     */
    @PostConstruct
    public void init() {
        receiptForms = rfejb.getAllReceiptForm();
        filteredReceiptForms = rfejb.getAllReceiptForm();
    }// init()
    
    /**
     * GETTERS & SETTERS -------------------------------------------------------
     */
    
    /**
     * ReceiptFormEJB : getReceiptFormEJB()
     * @return
     */
    public ReceiptFormEJB getReceiptFormEJB() {
        return rfejb;
    }// fin getReceiptFormEJB()
    
    /**
     * ReceiptFormEJB : setReceiptFormEJB()
     * @param rfejb
     */
    public void setReceiptFormEJB(ReceiptFormEJB rfejb) {
        this.rfejb = rfejb;
    }// fin setReceiptFormEJB()
    
    /**
     * ReceiptForm : getReceiptForms()
     * @return
     */
    public List<ReceiptForm> getReceiptForms() {
        return receiptForms;
    }// fin getReceiptForms()
    
    /**
     * ReceiptFormEJB : setReceiptFormEJB()
     * @param receiptForms
     */
    public void setReceiptForms(List<ReceiptForm> receiptForms) {
        this.receiptForms = receiptForms;
    }// fin setReceiptForms()
    
    /**
     * FilteredReceiptForms : getFilteredReceiptForms()
     * @return
     */
    public List<ReceiptForm> getFilteredReceiptForms() {
        return filteredReceiptForms;
    }// fin getFilteredReceiptForms()
    
    /**
     * FilteredReceiptForms : setFilteredReceiptForms()
     * @param filteredReceiptForms()
     */
    public void setFilteredReceiptForms(List<ReceiptForm> filteredReceiptForms) {
        this.filteredReceiptForms = filteredReceiptForms; 
    }// fin setFilteredReceiptForms()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public List<ReceiptForm> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<ReceiptForm> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * ReceiptForm : globalFilterReceiptForm()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterReceiptForm(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        ReceiptForm receiptForm = (ReceiptForm) value; 
        
        return receiptForm.getDestination().toLowerCase().contains(filterText)
                || receiptForm.getExpediteur().toLowerCase().contains(filterText)
                || receiptForm.getNomReceipt().toLowerCase().contains(filterText)
                || receiptForm.getPays().toLowerCase().contains(filterText);
    }// fin globlalFilterAgents()
    
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
        printFile = rfejb.getAllReceiptForm();
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
        parameters.put("receiptform", getReceiptForms()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FRS.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FRS.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La liste des fiches de réceptionS des équipements a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioreceiptforms.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FRS.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin de la classe ListReceiptController

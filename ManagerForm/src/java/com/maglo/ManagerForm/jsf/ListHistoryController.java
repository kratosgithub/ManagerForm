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

import com.maglo.ManagerForm.ejb.HistoryRecordEJB;
import com.maglo.ManagerForm.entities.HistoryRecord;
// import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher la liste des fiches de retraits.
 */

@Named(value = "listHistoryController")
@ViewScoped 
public class ListHistoryController implements Serializable {
    
    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListHistoryController.class.getName());
    
    @Inject
    private HistoryRecordEJB hrejb;
    
    private List<HistoryRecord> historyRecords;
    private List<HistoryRecord> printFile;
    private List<HistoryRecord> filteredHistoryRecords;
    
    /**
     * Receipt Form : init()
     * On initialise la liste des fiches d'historique
     */
    @PostConstruct
    public void init() {
        historyRecords = hrejb.getAllHistoryRecord();
        filteredHistoryRecords = hrejb.getAllHistoryRecord();
    }// init()
    
    /**
     * GETTERS & SETTERS -------------------------------------------------------
     */
    
    /**
     * HistoryRecordEJB : getHistoryRecordEJB()
     * @return
     */
    public HistoryRecordEJB getHistoryRecordEJB() {
        return hrejb;
    }// fin getHistoryRecordEJB()
    
    /**
     * HistoryRecordEJB : setHistoryRecordEJB()
     * @param hrejb
     */
    public void setHistoryRecordEJB(HistoryRecordEJB hrejb) {
        this.hrejb = hrejb;
    }// fin setHistoryRecordEJB()
    
    /**
     * HistoryRecord : getHistoryRecords()
     * @return
     */
    public List<HistoryRecord> getHistoryRecords() {
        return historyRecords;
    }// fin getHistoryRecords()
    
    /**
     * HistoryRecordEJB : setHistoryRecordEJB()
     * @param historyRecords
     */
    public void setHistoryRecords(List<HistoryRecord> historyRecords) {
        this.historyRecords = historyRecords;
    }// fin setHistoryRecords()
    
    /**
     * FilteredHistoryRecords : getFilteredHistoryRecords()
     * @return
     */
    public List<HistoryRecord> getFilteredHistoryRecords() {
        return filteredHistoryRecords;
    }// fin getFilteredRemoavalForms()
    
    /**
     * FilteredRemaovalForms : setFilteredHistoryRecords()
     * @param filteredHistoryRecords()
     */
    public void setFilteredHistoryRecords(List<HistoryRecord> filteredHistoryRecords) {
        this.filteredHistoryRecords = filteredHistoryRecords; 
    }// fin setFilteredHistoryRecords()
    
    /**
     * HistoryRecord : printFile
     * @return 
     */
    public List<HistoryRecord> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * HistoryRecord : printFile
     * @param printFile 
     */
    public void setPrintFile(List<HistoryRecord> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * HistoryRecord : globalFilterHistoryRecord()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterHistoryRecord(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        HistoryRecord historyRecord = (HistoryRecord) value; 
        
        return historyRecord.getMotif().contains(filterText)
                || historyRecord.getNomAgt().toLowerCase().contains(filterText);
    }// fin globlalFilterHistoryRecord()
    
    /**
     * History record : downloadFile()
     * Charge de generer la fiche d'historique sous format PDF
     * @param ae 
     * @throws JRException
     * @throws IOException 
     * @throws SQLException 
     * @throws Exception 
     */
    public void downloadFile(ActionEvent ae) throws JRException, IOException, SQLException, Exception {
        // Declaration des variables 
        FacesContext context = FacesContext.getCurrentInstance();
        printFile = hrejb.getAllHistoryRecord();
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
        parameters.put("historyrecord", getHistoryRecords()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FHS.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FHS.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La liste des fiches d'historique d'un équipement a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=biohistoryrecords.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FHS.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin ListHistoryController

/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import javax.ejb.EJB;
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
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher une fiche d'historique en chargeant les informations
 * depuis la base de donnees.
 */

@Named(value = "detailHistoryController")
@ViewScoped 
public class DetailHistoryController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailHistoryController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private HistoryRecordEJB hrejb;
    
    private HistoryRecord historyRecord;
    private String nomAgt;
    private Long idRecord;
    private Date jour;
    private Collection<HistoryRecord> printFile;
    
    /**
     * HistoryRecord : getHistoryRecord()
     * @return
     */
    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }// fin getHistoryRecord()
    
    /**
     * HistoryRecord : setHistoryRecord()
     * @param historyRecord
     */
    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }// fin setHistoryRecord()
    
    /**
     * Clients : getNomAgt()
     * @return
     */
    public String getNomAgt() {
        return nomAgt;
    }// fin getNomAgt()
    
    /**
     * Clients : setNomAgt()
     * @param nomAgt
     */
    public void setNomClient(String nomAgt) {
        this.nomAgt = nomAgt.toUpperCase();
    }// fin setNomAgt()
    
    /**
     * Id Rm Form
     * Methode : getIdRecord()
     * @return 
     */
    public Long getIdRecord() {
        return idRecord;
    }// fin getIdRmFomr()

    /**
     * Id Rm Form
     * Methode : setIdRecord()
     * @param idRecord 
     */
    public void setIdRecord(Long idRecord) {
        this.idRecord = idRecord;
    }// fin setIdRecord()
    
    /**
     * HistoryRecord : printFile
     * @return 
     */
    public Collection<HistoryRecord> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * HistoryRecord : printFile
     * @param printFile 
     */
    public void setPrintFile(Collection<HistoryRecord> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * Date : getJour()
     * @return 
     */
    public Date getJour() {
        return jour;
    }// fin getJour()
    
    /**
     * Date : setJour()
     * @param jour
     */
    public void setJour(Date jour) {
        this.jour = jour; 
    }// fin setJour()
    
    /**
     * Client : loadClient()
     * Charge un client à partir de son nom
     */
    public void loadClient() {
        // historyRecord = hrejb.getRemovalByNomAgt(nomAgt);
        historyRecord = hrejb.getHistoryByIdRecord(idRecord);
    }// fin loadClient()
    
    /**
     * HistoryRecord : updateHistory()
     * @param historyRecord
     * @return
     */
    public String updateHistory(HistoryRecord historyRecord) {
        // Mettre à jour la fiche
        hrejb.update(historyRecord);
        
        // Logger 
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations de la fiche d'historique de M. : " + historyRecord.getNomAgt() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/detailHistory";
    }// fin updateHistory()
    
    /**
     * HistoryRecord : deleteHistory()
     * @param historyRecord
     * @return
     */
    public String deleteHistory(HistoryRecord historyRecord) {
        // On supprime la fiche
        hrejb.delete(historyRecord);
        
        // Logger
        logger.info("DELETE : La fiche d'historique a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("La fiche d'historique de M. " + historyRecord.getNomAgt() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listHistories";
    }// fin deleteHistory()
    
    /**
     * ActionForm : downloadFile()
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
        printFile = hrejb.getRecordByNomHardware(idRecord, jour);
        
        // Configuration de la source de données SQL
        String url = "jdbc:mysql://localhost:3306/gmaodb?useSSL=false";
        String username = "maglo";
        String pwd = "M@gloWamp666++";
        
        // Connection connection = null;
        Connection connection = DriverManager.getConnection(url, username, pwd);
        // statement = connection.createStatement();
        // rs = statement.executeQuery(query);
        
        // JRResultSetDataSource jrrsds = new JRResultSetDataSource(rs);
        // JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(printFile);
        
        Map parameters = new HashMap();
        parameters.put("idRecord", getIdRecord());  
        // parameters.put("jour", jour);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FHI.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FHI.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche d'historique a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=biorecord.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\biorecord.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin DetailHistoryController

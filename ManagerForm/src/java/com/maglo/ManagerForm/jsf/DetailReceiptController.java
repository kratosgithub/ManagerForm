/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.jsf;

import java.io.IOException;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.Statement;
import java.sql.SQLException;
// import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;
// import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
// import javax.mail.MessagingException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
// import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRException;

import com.maglo.ManagerForm.ejb.ReceiptFormEJB;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.ReceiptForm;
import com.maglo.ManagerForm.jsf.util.Util;
// import com.maglo.ManagerForm.jsf.util.EmailUtil;

// import com.mysql.jdbc.Driver;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher les détails d'une fiche reception préalablement
 * remplise via le formulaire prévue à cet effet.
 */

@Named(value = "detailReceiptController")
@ViewScoped 
public class DetailReceiptController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailReceiptController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private ReceiptFormEJB rfejb;
    
    // private Long idReceiptForm;
    private ReceiptForm receiptForm;
    private Clients nomCst;
    private Hardware nomHard;
    private String nomReceipt;
    private String destination;
    private Collection<ReceiptForm> printFile;
    
    // private EmailUtil eu;
    // private Statement statement;
    ///private ResultSet rs;
    
    /**
     * ReceiptForm : getReceiptForm()
     * @return
     */
    public ReceiptForm getReceiptForm() {
        return receiptForm;
    }// fin getReceiptForm()
    
    /**
     * ReceiptForm : setReceiptForm()
     * @param receiptForm
     */
    public void setReceiptForm(ReceiptForm receiptForm) {
        this.receiptForm = receiptForm;
    }// fin setReceiptForm()
    
    /**
     * Destination 
     * Methode : getDestination()
     * @return 
     */
    public String getDestination() {
        return destination;
    }// fin getDestination()

    /**
     * Destination
     * Methode : setDestination()
     * @param destination 
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }// fin setDestination()
    
    /**
     * Clients : getNomCst()
     * @return
     */
    public Clients getNomCst() {
        return nomCst;
    }// fin getNomCst()
    
    /**
     * Clients : setNomCst()
     * @param nomCst
     */
    public void setNomCst(Clients nomCst) {
        this.nomCst = nomCst;
    }// fin setNomCst()
    
    /**
     * Hardware : getHardware()
     * @return
     */
    public Hardware getHardware() {
        return nomHard;
    }// fin getHardware()
    
    /**
     * Hardware : setHardware()
     * @param nomHard
     */
    public void setHardware(Hardware nomHard) {
        this.nomHard = nomHard;
    }// fin setHardware()
    
    /**
     * NomReceipt : getNomReceipt()
     * @return
     */
    public String getNomReceipt() {
        return nomReceipt;
    }// fin getNomReceipt()
    
    /**
     * NomReceipt : setNomReceipt()
     * @param nomReceipt
     */
    public void setNomReceipt(String nomReceipt) {
        this.nomReceipt = nomReceipt.toUpperCase();
    }// fin setNomReceipt()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public Collection<ReceiptForm> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(Collection<ReceiptForm> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()

    /**
     * ReceiptForm : loadReceipt()
     * Charge une fiche de reception à partir du nom du receveur
     */
    public void loadReceipt() {
        receiptForm = rfejb.getReceiptByNom(nomReceipt);
    }// fin loadByHardware()
    
    /**
     * ReceiptForm : updateReceipt()
     * @param receiptForm
     * @return
     */
    public String updateReceipt(ReceiptForm receiptForm) {
        // Mettre à jour la fiche de reception
        rfejb.update(receiptForm);
        
        // Logger info
        logger.info("UPDATE : Les informations de la fiche de réception ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations de la fiche de réception reçue par : " + receiptForm.getNomReceipt() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/detailReceipt";
    }// fin de updateReceipt()
    
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
        printFile = rfejb.getReceiptFormByNom(nomReceipt, destination);
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
        parameters.put("nomReceipt", getNomReceipt()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FDR.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FDR.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche de réception a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioreceived.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FDR.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanassurances.com");
    }// fin de downloadFile()
    
}// fin de la classe DetailReceiptController 

/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher un equipement en chargeant les informations
 * stockees dans la BDD.
 */

@Named(value = "detailHardwareController")
@ViewScoped 
public class DetailHardwareController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailHardwareController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private HardwareEJB hejb;
    
    private String noSerie;
    private Hardware hardware;
    private String nomHardware;
    private String marque;
    
    private Collection<Hardware> printFile;
    
    /**
     * No serie : getNoSerie()
     * @return 
     */
    public String getNoSerie() {
        return noSerie;
    }// fin getNoSerie()

    /**
     * No serie : setNoSerie()
     * @param noSerie 
     */
    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie.toUpperCase();
    }// fin setNoSerie()
    
    /**
     * Marque : getMarque()
     * @return
     */
    public String getMarque() {
        return marque;
    }// fin getMarque()
    
    /**
     * Marque : setMarque()
     * @param marque
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }// fin setMarque()
    
    /**
     * Hardware : getHardware()
     * @return
     */
    public Hardware getHardware() {
        return hardware;
    }// fin getHardware()
    
    /**
     * Hardware : setHardware()
     * @param hardware
     */
    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }// fin setHardware()
    
    /**
     * Hardware : getNomHardware()
     * @return
     */
    public String getNomHardware() {
        return nomHardware;
    }// fin getNomHardware()
    
    /**
     * Hardware : setNomHardware()
     * @param nomHardware 
     */
    public void setNomHardware(String nomHardware) {
        this.nomHardware = nomHardware.toUpperCase();
    }// fin setNomHardware()
    
    /**
     * PrintFile : getPrintFile()
     * @return 
     */
    public Collection<Hardware> getPrintFile() {
        return printFile;
    }// fin getPrintFile()

    /**
     * PrintFile : setIdHardware()
     * @param printFile
     */
    public void setPrintFile(Collection<Hardware> printFile) {
        this.printFile = printFile; 
    }// fin setPrintFile()
    
    /**
     * Hardware : loadHardware()
     * Charge un hardware à partir de son nom
     */
    public void loadHardware() {
        hardware = hejb.getHardwareByNoSerie(noSerie);
    }// fin loadHardware()
    
    /**
     * Hardware : updateHardware()
     * @param hardware 
     * @return
     */
    public String updateHardware(Hardware hardware) {
        // Mettre à jour l'équipement
        hejb.update(hardware); 
        
        // Logger 
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations de l'équipement " + hardware.getNomHardware() + " de N° de série " + hardware.getNoSerie() + " a été mis à jour avec succès.", "Information :");
        
        return "/managers/detailHardware";
    }// fin updateHardware()
    
    /**
     * Hardware : deleteHardware()
     * @param hardware
     * @return
     */
    public String deleteHardware(Hardware hardware) {
        // On supprime l'equipement
        hejb.delete(hardware);
        
        // Logger
        logger.info("DELETE : L'équipement a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("L'équipement " + hardware.getNomHardware() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listHardware";
    }// fin deleteHardware()
    
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
        printFile = hejb.getHardwareByNoSerie(noSerie, marque);
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
        parameters.put("noSerie", getNoSerie());  
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FEI.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FDR.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche technique a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioequipement.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FDR.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin de la classe DetailHardwareController

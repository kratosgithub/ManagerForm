/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de lister les equipements enregistres dans les systeme.
 */

@Named(value = "listHardwareController")
@ViewScoped 
public class ListHardwareController implements Serializable {
    
    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListHardwareController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private HardwareEJB hejb;
    
    private List<Hardware> hardwares;
    private List<Hardware> printFile;
    private List<Hardware> filteredHardwares;
    
    /**
     * Hardware : init()
     * On initialise la liste des equipements
     */
    @PostConstruct
    public void init() {
        hardwares = hejb.getAllHardware();
        filteredHardwares = hejb.getAllHardware();
    }// fin init()
    
    /**
     * HardwareEJB : getHardwareEJB()
     * @return
     */
    public HardwareEJB getHardwareEJB() {
        return hejb;
    }// fin getHardwareEJB()
    
    /**
     * HardwareEJB : setHardwareEJB()
     * @param hejb
     */
    public void setHardwareEJB(HardwareEJB hejb) {
        this.hejb = hejb;
    }// fin setHardwareEJB()
    
    /**
     * Hardware : getHardwares()
     * @return
     */
    public List<Hardware> getHardwares() {
        return hardwares;
    }// fin getHardwares()
    
    /**
     * Hardware : setHardwares()
     * @param hardwares
     */
    public void setHardwares(List<Hardware> hardwares) {
        this.hardwares = hardwares;
    }// fin setHardwares()
    
    /**
     * Hardware : getFilteredHardwares()
     * @return
     */
    public List<Hardware> getFilteredHardwares() {
        return filteredHardwares;
    }// fin getFilteredHardwares()
    
    /**
     * Hardware : setFilteredHardwares()
     * @param filteredHardwares
     */
    public void setFilteredHardwares(List<Hardware> filteredHardwares) {
        this.filteredHardwares = filteredHardwares;
    }// fin setFilteredHardwares()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public List<Hardware> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<Hardware> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
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
        Util.messageInfo("L'équipement " + hardware.getNomHardware() + " " + hardware.getNoSerie() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listHardware";
    }// fin deleteHardware()
    
    /**
     * Hardware : globalFilterHardware()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterHardware(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        Hardware hardware = (Hardware) value;
        
        return hardware.getNomHardware().toLowerCase().contains(filterText)
                || hardware.getMarque().toLowerCase().contains(filterText)
                || hardware.getNoSerie().toUpperCase().contains(filterText);
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
        printFile = hejb.getAllHardware();
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
        parameters.put("hardware", getHardwares()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FES.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FES.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche des équipements a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=biohardwares.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FES.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin de la classe ListHardwareController

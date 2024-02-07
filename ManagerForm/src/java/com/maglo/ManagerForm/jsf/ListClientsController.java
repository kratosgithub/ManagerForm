/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.jsf;

import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de liste les clients enregistres dans le système.
 */

@Named(value = "listClientsController")
@ViewScoped 
public class ListClientsController implements Serializable {
    
    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListClientsController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private ClientsEJB cejb;
    
    private List<Clients> clients;
    private List<Clients> printFile;
    private List<Clients> filteredClients;
    
    /**
     * Clients : init()
     * on initialise la liste des clients
     */
    @PostConstruct
    public void init() {
        clients = cejb.getAllClients();
        filteredClients = cejb.getAllClients();
    }// fin init()
    
    /**
     * Clients : getClientsEJB
     * @return
     */
    public ClientsEJB getClientsEJB() {
        return cejb;
    }// fin getClientsEJB()
    
    /**
     * Clients : setClientsEJB()
     * @param cejb
     */
    public void setClientsEJB(ClientsEJB cejb) {
        this.cejb = cejb;
    }// fin setClientsEJB()
    
    /**
     * Clients : getClients()
     * @return
     */
    public List<Clients> getClients() {
        return clients;
    }// fin getClients()
    
    /**
     * Clients : setClients()
     * @param clients
     */
    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }// fin setClients()
    
    /**
     * Clients : getFilteredClients()
     * @return
     */
    public List<Clients> getFilteredClients() {
        return filteredClients;
    }// fin getFilteredClients()
    
    /**
     * Clients : setFilteredClients()
     * @param filteredClients
     */
    public void setFilteredClients(List<Clients> filteredClients) {
        this.filteredClients = filteredClients;
    }// fin setFilteredClients()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public List<Clients> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<Clients> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * Clients : deleteClients()
     * @param clients
     * @return 
     */
    public String deleteClients(Clients clients) {
        // on supprime le client
        cejb.delete(clients);
        
        // Logger 
        logger.info("DELETE : Le client a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message confirmation
        Util.messageInfo("Le client " + clients.getNomClient() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listClients";
    }// fin deleteClients()
    
    /**
     * Clients : globalFilterClients()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterClients(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        // int filterInt = getInteger(filterText);
        
        Clients clients = (Clients) value;
        
        return clients.getNomClient().toLowerCase().contains(filterText)
                || clients.getService().toLowerCase().contains(filterText)
                || clients.getVille().toLowerCase().contains(filterText)
                || clients.getTelClient().contains(filterText);
    }// fin globlalFilterClients()
    
    /**
     * Client : getInteger()
     * @param string
     * @return
     */
    public int getInteger(String string) {
        
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }// fin du try ... catch 
    }// fin getInteger()
    
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
        printFile = cejb.getAllClients();
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
        parameters.put("clients", getClients()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FCS.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FCS.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche des clients a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioclients.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FDR.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin de la classe ListClientsController 

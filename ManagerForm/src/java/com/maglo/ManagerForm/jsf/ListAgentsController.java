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

import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de liste les techniciens enregistres dans le systeme.
 */

@Named(value = "listAgentsController")
@ViewScoped 
public class ListAgentsController implements Serializable {
    
    // Declaration des variables 
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(ListAgentsController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @Inject
    private AgentsEJB aejb;
    
    private List<Agents> agents;
    private List<Agents> printFile;
    private List<Agents> filteredAgents;
    
    /**
     * Agents : init()
     * on initialise la liste des techniciens
     */
    @PostConstruct
    public void init() {
        agents = aejb.getAllAgents();
        filteredAgents = aejb.getAllAgents();
    }// fin init()
    
    /**
     * AgentsEJB : getAgentsEJB()
     * @return
     */
    public AgentsEJB getAgentEJB() {
        return aejb;
    }// fin getAgentsEJB()
    
    /**
     * AgentsEJB : setAgentsEJB()
     * @param aejb
     */
    public void setAgentsEJB(AgentsEJB aejb) {
        this.aejb = aejb;
    }// fin setAgentsEJB()
    
    /**
     * Agents : getAgents()
     * @return
     */
    public List<Agents> getAgents() {
        return agents;
    }// fin getAgents()
    
    /**
     * Agents : setAgents()
     * @param agents
     */
    public void setAgents(List<Agents> agents) {
        this.agents = agents;
    }// fin setAgents()
    
    /**
     * Agents : getFilteredAgents()
     * @return
     */
    public List<Agents> getFilteredAgents() {
        return filteredAgents;
    }// fin getFilteredAgents()
    
    /**
     * ReceiptForm : printFile
     * @return 
     */
    public List<Agents> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(List<Agents> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * Agents : setFilteredAgents()
     * @param filteredAgents
     */
    public void setFilteredAgents(List<Agents> filteredAgents) {
        this.filteredAgents = filteredAgents;
    }// fin setFilteredAgents()
    
    /**
     * Agents : deleteAgents()
     * @param agents 
     * @return
     */
    public String deleteAgents(Agents agents) {
        // On supprime l'agent
        aejb.delete(agents);
        
        // Logger
        logger.info("DELETE : Le technicien " + agents.getNomAgent() + " a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Le technicien " + agents.getNomAgent() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listAgents";
    }// fin de deleteAgents()
    
    /**
     * Agents : globalFilterAgents()
     * @param value
     * @param filter
     * @param locale
     * @return
     */
    public boolean globalFilterAgents(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        
        if(filterText == null || filterText.equals("")) return true;
        
        Agents agents = (Agents) value;
        
        return agents.getNomAgent().toLowerCase().contains(filterText)
                || agents.getTelAgent().contains(filterText);
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
        printFile = aejb.getAllAgents();
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
        parameters.put("agents", getAgents()); 
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FAS.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FAS.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche des techniciens a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioagents.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\FDR.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
   
}// fin de classe ListAgentsController

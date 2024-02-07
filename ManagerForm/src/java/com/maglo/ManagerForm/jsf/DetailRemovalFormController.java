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

import com.maglo.ManagerForm.ejb.RemovalFormEJB;
import com.maglo.ManagerForm.entities.RemovalForm;
import com.maglo.ManagerForm.jsf.util.Util;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher une fiche de retrait en chargeant les informations
 * depuis la base de donnees.
 */

@Named(value = "detailRemovalFormController")
@ViewScoped 
public class DetailRemovalFormController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailRemovalFormController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private RemovalFormEJB rfejb;
    
    private RemovalForm removalForm;
    private String nomClnt;
    private Long idRmForm;
    private Collection<RemovalForm> printFile;
    
    /**
     * RemovalForm : getRemovalForm()
     * @return
     */
    public RemovalForm getRemovalForm() {
        return removalForm;
    }// fin getRemovalForm()
    
    /**
     * RemovalForm : setRemovalForm()
     * @param removalForm
     */
    public void setRemovalForm(RemovalForm removalForm) {
        this.removalForm = removalForm;
    }// fin setRemovalForm()
    
    /**
     * Clients : getNomClnt()
     * @return
     */
    public String getNomClnt() {
        return nomClnt;
    }// fin getNomClnt()
    
    /**
     * Clients : setNomClnt()
     * @param nomClnt
     */
    public void setNomClient(String nomClnt) {
        this.nomClnt = nomClnt.toUpperCase();
    }// fin setNomClnt()
    
    /**
     * Id Rm Form
     * Methode : getIdRmForm()
     * @return 
     */
    public Long getIdRmForm() {
        return idRmForm;
    }// fin getIdRmFomr()

    /**
     * Id Rm Form
     * Methode : setIdRmForm()
     * @param idRmForm 
     */
    public void setIdRmForm(Long idRmForm) {
        this.idRmForm = idRmForm;
    }// fin setIdRmForm()
    
    /**
     * RemovalForm : printFile
     * @return 
     */
    public Collection<RemovalForm> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * RemovalForm : printFile
     * @param printFile 
     */
    public void setPrintFile(Collection<RemovalForm> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * Client : loadClient()
     * Charge un client à partir de son nom
     */
    public void loadClient() {
        // removalForm = rfejb.getRemovalByNomClnt(nomClnt);
        removalForm = rfejb.getRemovalById(idRmForm);
    }// fin loadClient()
    
    /**
     * RemovalForm : updateRemoval()
     * @param removalForm
     * @return
     */
    public String updateRemoval(RemovalForm removalForm) {
        // Mettre à jour la fiche
        rfejb.update(removalForm);
        
        // Logger 
        logger.info("UPDATE : Les informations ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations de la fiche de client : " + removalForm.getNomClnt() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/detailRemoval";
    }// fin updateRemoval()
    
    /**
     * RemovalForm : deleteRemoval()
     * @param removalForm
     * @return
     */
    public String deleteRemoval(RemovalForm removalForm) {
        // On supprime la fiche
        rfejb.delete(removalForm);
        
        // Logger
        logger.info("DELETE : La fiche de retrait a été supprimé avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("La fiche du client " + removalForm.getNomClnt() + " a été supprimé avec succès.", "Information :");
        
        return "/managers/listRemovals";
    }// fin deleteRemoval()
    
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
        printFile = rfejb.getRemovalFormByClient(idRmForm, date);
        
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
        parameters.put("idRmForm", getIdRmForm());  
        // parameters.put("jour", jour);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FRI.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FRI.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche d'enlèvement a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioremoval-client.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\bioremoval-client.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin de la classe DetailRemovalFormController

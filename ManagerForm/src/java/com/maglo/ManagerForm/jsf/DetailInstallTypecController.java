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
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
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

import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.ejb.InstallFormTypecEJB;

import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.InstallFormTypec;
import com.maglo.ManagerForm.jsf.util.Util;
// import java.time.LocalDateTime;

// import com.maglo.ManagerForm.jsf.util.EmailUtil;

// import com.mysql.jdbc.Driver;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher les détails d'une fiche d'installation WP21A préalablement
 * remplise via le formulaire prévue à cet effet.
 */

@Named(value = "detailInstallTypecController")
@ViewScoped 
public class DetailInstallTypecController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailInstallTypecController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private InstallFormTypecEJB iftejb;
    
    @EJB 
    private ClientsEJB cejb;
    
    @EJB
    private HardwareEJB hejb;
    
    private InstallFormTypec ift;
    private String nomCusto;
    private String nomEqpmt;
    
    // Variables de recuperation 
    private String nomCst;
    private String nomHrd;
    private Long idFormC;
    private Date jour;
    
    private Collection<InstallFormTypec> printFile;
    private List<Clients> clients;
    private List<Hardware> hardwares;
    
    @PostConstruct
    public void init() {
        // Recuperation de les listes  existantes
        
        // minJour = LocalDateTime.now().minusWeeks(0);
                
        // Liste des clients
        clients = cejb.getAllClients(); 
        
        // Map<Long, Clients> mapClients = new HashMap<Long, Clients>();
            
        /**
         * for(Clients c : clients) {
         *   mapClients.put(c.getIdClient(), c);
         * }// fin de for ...
         */ 
        
        // Liste des equipements
        hardwares = hejb.getAllHardware();
        
        // Map<Long, Hardware> mapHardware = new HashMap<Long, Hardware>();
        
        /**
         * for(Hardware h : hardwares) {
         *   mapHardware.put(h.getIdHardware(), h);
         * }// fin de for ..
         */ 
    }// fin init()
    
    /**
     * Getters & Setters -------------------------------------------------------
     */
    
    /**
     * InstallFormTypec : getInstallFormTypec()
     * @return
     */
    public InstallFormTypec getInstallFormTypec() {
        return ift;
    }// fin getInstallFormTypec()
    
    /**
     * InstallFormTypec : setInstallFormTypec()
     * @param ift
     */
    public void setInstallFormTypec(InstallFormTypec ift) {
        this.ift = ift;
    }// fin setInstallFormTypec()
    
    /**
     * Clients : getNomCusto()
     * @return
     */
    public String getNomCusto() {
        return nomCusto;
    }// fin getNomCusto()
    
    /**
     * Clients : setNomCusto()
     * @param nomCusto
     */
    public void setNomCusto(String nomCusto) {
        this.nomCusto = nomCusto;
    }// fin setNomCusto()
    
    /**
     * Hardware : getNomEqpmt()
     * @return
     */
    public String getNomEqpmt() {
        return nomEqpmt;
    }// fin getNomEqpmt()
    
    /**
     * Hardware : setNomEqpmt()
     * @param nomEqpmt
     */
    public void setNomEqpmt(String nomEqpmt) {
        this.nomEqpmt = nomEqpmt;
    }// fin setNomEqpmt()
    
    /**
     * NomCst : getNomCst()
     * @return
     */
    public String getNomCst() {
        return nomCst;
    }// fin getNomCst()
    
    /**
     * NomCst : setNomCst()
     * @param nomCst
     */
    public void setNomCst(String nomCst) {
        this.nomCst = nomCst.toUpperCase();
    }// fin setNomCst()
    
    /**
     * Nom hardware : getNomHrd()
     * @return 
     */
    public String getNomHrd() { 
        return nomHrd; 
    }// fin getNomHrd()
    
    /**
     * Nom hardware : setNomHrd()
     * @param nomHrd 
     */
    public void setNomHrd(String nomHrd) { 
        this.nomHrd = nomHrd.toUpperCase(); 
    }// fin setNomHrd() 
    
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
     * Hardwares : getHardware()
     * @return 
     */
    public List<Hardware> getHardware() {
        return hardwares;
    }// fin getHardwares()
    
    /**
     * Hardwares : setHardwares()
     * @param hardwares 
     */
    public void setHardware(List<Hardware> hardwares) {
        this.hardwares = hardwares;
    }// fin setHardwares()
    
    /**
     * InstallFormTypec : printFile
     * @return 
     */
    public Collection<InstallFormTypec> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(Collection<InstallFormTypec> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * IdFormC : getIdFormC()
     * @return 
     */
    public Long getIdFormC() {
        return idFormC;
    }// fin getIdFormC()
    
    /**
     * IdFormC : setIdFormC()
     * @param idFormC
     */
    public void setIdFormC(Long idFormC) {
        this.idFormC = idFormC; 
    }// fin setIdFormC()
    
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
     * Methodes utilitaires ----------------------------------------------------
     */
    
    /**
     * InstallFormTypec : loadInstallTypec()
     * Charge une fiche d'installation à partir du nom de client
     */
    public void loadInstallTypec() {
        ift = iftejb.getInstallByIdFormC(idFormC);
    }// fin loadPreInstall()
    
    /**
     * InstallFormTypec : updateInstallFormTypec()
     * @param installFormTypec
     * @return
     */
    public String updateInstallFormTypec(InstallFormTypec installFormTypec) {
        // Mettre à jour la fiche de pré-installation
        iftejb.update(installFormTypec);
        
        // Logger info
        logger.info("UPDATE : Les informations de la fiche d'installation du WP21A ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations de la fiche d'installation d'un WP21A " + installFormTypec.getNomCusto() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/detailInstallTypec";
    }// fin de updateInstallFormTypec()
    
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
        printFile = iftejb.getInstallFormTypecByNomCusto(idFormC, jour);
        // receiptForm = rfejb.getReceiptByNom(nomReceipt);
        
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
        parameters.put("idFormC", getIdFormC());  
        // parameters.put("destination", destination);
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FIC.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FIC.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche d'installation du WP21A a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=bioinstallwp21a.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\biopreinstall.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile
    
}// fin DetailInstallTypecController

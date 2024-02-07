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
import com.maglo.ManagerForm.ejb.PreInstallFormEJB;

import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.PreInstallForm;
import com.maglo.ManagerForm.jsf.util.Util;
// import java.time.LocalDateTime;

// import com.maglo.ManagerForm.jsf.util.EmailUtil;

// import com.mysql.jdbc.Driver;

/**
 *
 * @author junior.ndozeng
 * Classe utilitaire permettant d'afficher les détails d'une fiche de pre-installation préalablement
 * remplise via le formulaire prévue à cet effet.
 */

@Named(value = "detailPreInstallController")
@ViewScoped 
public class DetailPreInstallController implements Serializable {
    
    // Declaration des variables de la classe
    private static final long serialVersionUID = 1L;
    
    private static final Logger logger = Logger.getLogger(DetailPreInstallController.class.getName());
    
    // Format de la date
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    
    // Date courante
    Date date = new Date();
    
    @EJB
    private PreInstallFormEJB pifejb;
    
    @EJB 
    private ClientsEJB cejb;
    
    @EJB
    private HardwareEJB hejb;
    
    private PreInstallForm pif;
    private Clients nomClient;
    private Hardware nomHard;
    
    // Variables de recuperation 
    private String nomCst;
    private String nomHrd;
    private Long idPreForm;
    private Date hdebut;
    private Date jour;
    
    private Collection<PreInstallForm> printFile;
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
     * Heure debut : getHdebut()
     * @return 
     */
    public Date getHdebut() {
        return hdebut;
    }// fin getHdebut()

    /**
     * Heure debut : setHdebut()
     * @param hdebut 
     */
    public void setHdebut(Date hdebut) {
        this.hdebut = hdebut;
    }// fin setHdebut()
    
    /**
     * PreInstallForm : getPreInstallForm()
     * @return
     */
    public PreInstallForm getPreInstallForm() {
        return pif;
    }// fin getPreInstallForm()
    
    /**
     * PreInstallForm : setPreInstallForm()
     * @param pif
     */
    public void setPreInstallForm(PreInstallForm pif) {
        this.pif = pif;
    }// fin setPreInstallForm()
    
    /**
     * Clients : getNomClient()
     * @return
     */
    public Clients getNomClient() {
        return nomClient;
    }// fin getNomClient()
    
    /**
     * Clients : setNomClient()
     * @param nomClient
     */
    public void setNomClient(Clients nomClient) {
        this.nomClient = nomClient;
    }// fin setNomClient()
    
    /**
     * Hardware : getNomHard()
     * @return
     */
    public Hardware getNomHard() {
        return nomHard;
    }// fin getNomHard()
    
    /**
     * Hardware : setNomHard()
     * @param nomHard
     */
    public void setNomHard(Hardware nomHard) {
        this.nomHard = nomHard;
    }// fin setNomHard()
    
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
     * PreInstallForm : printFile
     * @return 
     */
    public Collection<PreInstallForm> getPrintFile() {
        return printFile;
    }// fin getPrintFile()
    
    /**
     * ReceiptForm : printFile
     * @param printFile 
     */
    public void setPrintFile(Collection<PreInstallForm> printFile) {
        this.printFile = printFile;
    }// fin setPrintFile()
    
    /**
     * IdPreForm : getIdPreForm()
     * @return 
     */
    public Long getIdPreForm() {
        return idPreForm;
    }// fin getIdPreForm()
    
    /**
     * IdPreForm : setIdPreForm()
     * @param idPreForm
     */
    public void setIdPreForm(Long idPreForm) {
        this.idPreForm = idPreForm; 
    }// fin setIdPreForm()
    
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
     * PreInstallForm : loadPreInstall()
     * Charge une fiche de pre-install à partir du nom de client
     */
    public void loadPreInstall() {
        // pif = pifejb.getInstallByNomClient(nomClient);
        pif = pifejb.getInstallByIdPreForm(idPreForm);
    }// fin loadPreInstall()
    
    /**
     * PreInstallForm : updateInstallForm()
     * @param installForm
     * @return
     */
    public String updateInstallForm(PreInstallForm installForm) {
        // Mettre à jour la fiche de pré-installation
        pifejb.update(installForm);
        
        // Logger info
        logger.info("UPDATE : Les informations de la fiche de pré-installation ont été mises à jour avec succès. Le : " + format.format(date) + ".");
        
        // Message de confirmation
        Util.messageInfo("Les informations de la fiche de pré-installation " + installForm.getNomClient() + " ont été mises à jour avec succès.", "Information :");
        
        return "/managers/detailPreInstall";
    }// fin de updateInstallForm()
    
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
        printFile = pifejb.getPreInstallFormByNomClient(idPreForm, jour);
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
        parameters.put("idPreForm", getIdPreForm());  
        // parameters.put("nomHardware", getNomHard());
        
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/classes/static/FPI.jasper");
        // String reportPath = "C:\\Users\\junior.ndozeng\\Documents\\NetBeansProjects\\ManagerForm\\web\\WEB-INF\\classes\\static\\FPI.jasper";
        
        File file = new File(reportPath);
        JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters, connection);
        // JasperPrint jp = JasperFillManager.fillReport(file.getPath(), parameters);
        
        // logger info
        logger.info("DOWNLOAD FILE : La fiche de pré-installation a été téléchargé avec succès.");
        
        HttpServletResponse hsr = (HttpServletResponse) context.getExternalContext().getResponse();
        hsr.addHeader("Content-disposition", "attachment;filename=biopreinstall.pdf");
        
        ServletOutputStream sos = hsr.getOutputStream();
        // JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\junior.ndozeng\\Downloads\\biopreinstall.pdf");
        JasperExportManager.exportReportToPdfStream(jp, sos); 
        
        // sos.flush();
        // sos.close(); 
        FacesContext.getCurrentInstance().responseComplete();
        // connection.close();
        
        // EmailUtil.sendMail("junior.ndozeng@chanasassurances.com");
    }// fin de downloadFile()
    
}// fin DetailPreInstallController

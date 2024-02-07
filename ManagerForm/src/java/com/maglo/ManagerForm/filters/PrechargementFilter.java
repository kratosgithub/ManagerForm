/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.filters;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import  javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.maglo.ManagerForm.entities.Agents;
import com.maglo.ManagerForm.entities.Clients;
import com.maglo.ManagerForm.entities.Hardware;
import com.maglo.ManagerForm.entities.Users;
import com.maglo.ManagerForm.entities.InstallFormTypec;
import com.maglo.ManagerForm.entities.PreInstallForm;
import com.maglo.ManagerForm.entities.ReceiptForm;

import com.maglo.ManagerForm.ejb.AgentsEJB;
import com.maglo.ManagerForm.ejb.ClientsEJB;
import com.maglo.ManagerForm.ejb.HardwareEJB;
import com.maglo.ManagerForm.ejb.UserEJB;
import com.maglo.ManagerForm.ejb.InstallFormTypecEJB;
import com.maglo.ManagerForm.ejb.PreInstallFormEJB;
import com.maglo.ManagerForm.ejb.ReceiptFormEJB;

/**
 *
 * @author NDOZENG
 * Classe utilitaire permettant de charger les données enregistrées dans la BDD durant une session ouverte
 * par un utilisateur.
 */

@WebFilter(urlPatterns = {"/*"})
public class PrechargementFilter implements Filter {
    
    // Comportement de la classe 
    
    // Declaration des variables de la classe
    public static final String ATT_SESSION_AGENTS = "agents";
    public static final String ATT_SESSION_CLIENTS = "clients";
    public static final String ATT_SESSION_HARDWARE = "hardware";
    public static final String ATT_SESSION_USERS = "user";
    public static final String ATT_SESSION_INSTALL_TYPEC = "installFormTypec";
    public static final String ATT_SESSION_PREINSTALL_FORMS = "preInstallForm";
    public static final String ATT_SESSION_RECEIPT_FORMS = "receiptForm";
    
    @EJB
    private AgentsEJB aejb;
    
    @EJB
    private ClientsEJB cejb;
    
    @EJB
    private HardwareEJB hejb;
    
    @EJB
    private UserEJB uejb;
    
    @EJB
    private InstallFormTypecEJB iftejb;
    
    @EJB
    private PreInstallFormEJB pifejb;
    
    @EJB
    private ReceiptFormEJB rfejb;
    
    /**
     * Prechargement Filter
     * Methode : init()
     * @param config
     * @throws ServletException 
     */
    @Override
    public void init(FilterConfig config) throws ServletException {}// fin init()
    
    /**
     * Prechargement Filter
     * Methode : destroy()
     */
    @Override
    public void destroy() {}// fin destroy()
    
    /**
     * Prechargement Filter
     * Methode : doFilter()
     * @param req
     * @param res
     * @param chain
     * @throws ServletException 
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        /* cast de l'objet */
        HttpServletRequest request = (HttpServletRequest) req;
        
        // Recuperation de la session depuis la requete
        HttpSession session = request.getSession();
        
        /**
         * Si la map des agents n'existent pas en session, alors l'utilisateur se connecte pour la premiere fois et nous devons precharger en sessions les infos
         * obtenues contenues dans la base de données
         */
        if (session.getAttribute(ATT_SESSION_AGENTS) == null) {
            // Recuperation des agents existants et enregistrement en session
            List<Agents> listeAgents = aejb.getAllAgents();
            
            Map<Long, Agents> mapAgents = new HashMap<Long, Agents>();
            
            for(Agents agents : listeAgents) {
                mapAgents.put(agents.getIdAgent(), agents);
            }// fin de for 
            
            session.setAttribute(ATT_SESSION_AGENTS, mapAgents); 
        }// fin de fin 
        
        // De même pour les clients
        if(session.getAttribute(ATT_SESSION_CLIENTS) == null) {
            // Recuperation des clients existants et enregistrement en session
            List<Clients> listeClients = cejb.getAllClients();
            
            Map<Long, Clients> mapClients = new HashMap<Long, Clients>();
            
            for(Clients clients : listeClients) {
                mapClients.put(clients.getIdClient(), clients);
            }// fin de for 
            
            session.setAttribute(ATT_SESSION_CLIENTS, mapClients);
        }// fin de if ... 
        
        // De même pour les equipements
        if(session.getAttribute(ATT_SESSION_HARDWARE) == null) {
            // Recuperation des equipements existants et enregistrement en session
            List<Hardware> listeHardware = hejb.getAllHardware();
            
            Map<Long, Hardware> mapHardware = new HashMap<Long, Hardware>();
            
            for(Hardware hardware : listeHardware) {
                mapHardware.put(hardware.getIdHardware(), hardware);
            }// fin de for 
            
            session.setAttribute(ATT_SESSION_HARDWARE, mapHardware); 
        }// fin de if 
        
        // De même pour les utilisateurs
        if(session.getAttribute(ATT_SESSION_USERS) == null) {
            // Recuperation des utilisateurs existants et enregistrement en session
            List<Users> listUsers = uejb.getAllUsers();
            
            Map<Long, Users> mapUsers = new HashMap<Long, Users>();
            
            for(Users users : listUsers) {
                mapUsers.put(users.getIdUsers(), users);
            }// fin de for 
            
            session.setAttribute(ATT_SESSION_USERS, mapUsers); 
        }// fin de if
        
        // De même pour les fiches de pre-installations
        if(session.getAttribute(ATT_SESSION_PREINSTALL_FORMS) == null) {
            // Recuperation des fiches existants et enregistrement en session
            List<PreInstallForm> listPreInstalls = pifejb.getAllPreInstallForm();
            
            Map<Long, PreInstallForm> mapInstallForms = new HashMap<Long, PreInstallForm>();
            
            for(PreInstallForm installForm : listPreInstalls) {
                mapInstallForms.put(installForm.getIdPreForm(), installForm);
            }// fin de for 
            
            session.setAttribute(ATT_SESSION_PREINSTALL_FORMS, mapInstallForms); 
        }// fin de if
        
        // De même pour les fiches de reception
        if(session.getAttribute(ATT_SESSION_RECEIPT_FORMS) == null) {
            // Recuperation des fiches existants et enregistrement en session
            List<ReceiptForm> listReceiptForms = rfejb.getAllReceiptForm();
            
            Map<Long, ReceiptForm> mapReceiptForms = new HashMap<Long, ReceiptForm>();
            
            for(ReceiptForm receiptForm : listReceiptForms) {
                mapReceiptForms.put(receiptForm.getIdReceiptForm(), receiptForm);
            }// fin de for 
            
            session.setAttribute(ATT_SESSION_RECEIPT_FORMS, mapReceiptForms); 
        }// fin de if
        
        // De même pour les fiches d'installation WP21A
        if(session.getAttribute(ATT_SESSION_INSTALL_TYPEC) == null) {
            // Recuperation des fiches existants et enregistrement en session
            List<InstallFormTypec> listInstallFormTypecs = iftejb.getAllInstallTypec();
            
            Map<Long, InstallFormTypec> mapInstallFormTeypecs = new HashMap<Long, InstallFormTypec>();
            
            for(InstallFormTypec formTypec : listInstallFormTypecs) {
                mapInstallFormTeypecs.put(formTypec.getIdFormC(), formTypec);
            }// fin de for 
            
            session.setAttribute(ATT_SESSION_INSTALL_TYPEC, mapInstallFormTeypecs); 
        }// fin de if
        
        // Pour terminer, poursuivre la requete en cours
        chain.doFilter(request, res); 
    }// fin doFilter()
    
}// fin de la classe PrechargementFilter

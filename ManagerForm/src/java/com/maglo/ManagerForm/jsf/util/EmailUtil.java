/*
 * Chanas Assurances S.A.
 * Professional Computer.
 */
package com.maglo.ManagerForm.jsf.util;


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;

/**
 *
 * @author junior.ndozeng
 */

@Singleton
@Startup 
public class EmailUtil {
    
    // Declaration des variables de la classe 
    private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());
    
    /**
     * Methode utilise pour envoyer simplement un email
     * @param recepient 
     * @throws Exception 
     */
    public static void sendMail(String recepient) throws Exception {
        logger.info("\n SEND START : Preparing send email -----");
        
        Properties properties = new Properties();
        
        // properties 
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.debug", "true"); 
        
        String myAccount = "junior.ndozeng@chanasassurances.com";
        String password = "M@glo2021++";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, password);
            }// fin getPasswordAuthentification()
        });
        
        Message message = prepareMessage(session, myAccount, recepient);
        
        Transport.send(message);
        logger.info("SEND : L'e-mail a été envoyé avec succès.");
    }// fin de sendMail()
    
    /**
     * Message
     * @param session 
     * @param myAccount 
     * @param recepient 
     * @return 
     */
    public static Message prepareMessage(Session session, String myAccount, String recepient) {
        
        try {// try ... catch 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient)); 
            message.setSubject("INBOX BIOECOMS MAINT");
            message.setText("Hey, \n CAROLE NDOZENG.");
            
            return message;
        } catch (Exception ex) {
            Logger.getLogger(EmailUtil.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }// fin try ... catch 
        
        return null;
    }// fin prepareMessage()
   
}// fin de la classe EmailUtil

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBeans;

/**
 *
 * @author __Root0__
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail {

    private static final String FROM = "contact.claris.info@gmail.com";
    private static final String HOST = "smtp.gmail.com";
    private static final String USER = "contact.claris.info@gmail.com";
    private static final String PWD = "clarisinfo";

    public static boolean sendMail(String to, String subject, String msg) throws MException {

        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        /*props.put("mail.smtp.starttls.enable", "true");*/
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PWD);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(FROM));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject(subject);
            // Config for HTML emails
            Multipart multipart = new MimeMultipart();
            MimeBodyPart bodyMessagePart = new MimeBodyPart();
            bodyMessagePart.setContent(msg, "text/html;charset=utf-8");
            multipart.addBodyPart(bodyMessagePart);
            // Put the content of your message
            message.setContent(multipart);
            // Send message
            Transport.send(message);
            System.out.println("######Sent message successfully....###########");

        }
        catch (MessagingException e) {
            
            System.err.println("#######An error occur while trying to send mail######");
            throw new MException(e);
        }
        return true;
    }
    
    /*public static void main(String[] args) {
        Mail.sendMail("jeffrey.dvk.fr@gmail.com", "test test jee - ifri faqs", "ceci est un test d'envoie de mail");
    }*/
}

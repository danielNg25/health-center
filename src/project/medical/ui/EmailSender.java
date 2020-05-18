package project.medical.ui;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class EmailSender {
	private String sender ;
    private String password; 
    private String host;
    private Session session;
    Properties properties = System.getProperties();
    private List<String> emailToday;
    private List<String> emailTomorrow;
    private String messToday;
    private String messTomorrow;
    private String header;
    public EmailSender() {
    	sender = "hakasubteam@gmail.com";
    	password  = "nguyentranbaonguyentranbao";
    	host =  "smtp.gmail.com";
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    	session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(sender, password);

            }

        });
    	header = "Reminder from healthy care center";
    	messToday = " Hi friends, You have an appointment at our center today!";
    	messTomorrow = " Hi friends, You have an appointment at our center tomorrow"; 
    }

    public EmailSender(List<String> thelisttoday, List<String> thelisttomorrow) {
    	this();
    	emailToday = thelisttoday;
    	emailTomorrow = thelisttomorrow;
    }
    public void sendToday() {
    	
    	for (String receiver : emailToday) {
    		try {
    	        // Create a default MimeMessage object.
    	        MimeMessage message = new MimeMessage(session);
    	        // Set From: header field of the header.
    	        message.setFrom(new InternetAddress(sender));
    	        // Set To: header field of the header.
    	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
    	        // Set Subject: header field
    	        message.setSubject(header);

    	        // Now set the actual message 
    	        message.setContent(messToday,"text/html");
    	      
    	        System.out.println("Sending...");
    	        // Send message
    	        Transport.send(message);
    	        System.out.println("Sent email successfully....");
    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	    }
 	
    	}
    }
    public void sendTomorrow() {
    	
    	for (String receiver : emailTomorrow) {
    		try {
    	        // Create a default MimeMessage object.
    	        MimeMessage message = new MimeMessage(session);
    	        // Set From: header field of the header.
    	        message.setFrom(new InternetAddress(sender));
    	        // Set To: header field of the header.
    	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
    	        // Set Subject: header field
    	        message.setSubject(header);

    	        // Now set the actual message 
    	        message.setContent(messTomorrow,"text/html");
    	      
    	        System.out.println("Sending...");
    	        // Send message
    	        Transport.send(message);
    	        System.out.println("Sent email successfully....");
    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Error");
    	    }
 	
    	}
    	JOptionPane.showMessageDialog(null,"Sent email successfully");
    }
    	
    	
 
	

}

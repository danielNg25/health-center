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

import project.medical.core.Event;

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
    private String messEvent;
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
    	      
    	        JOptionPane.showMessageDialog(null,"Sending...");
    	        // Send message
    	        Transport.send(message);
    	     
    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	    }
    		JOptionPane.showMessageDialog(null,"Sent email successfully");
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
    	        JOptionPane.showMessageDialog(null,"Sending...");
    	        // Send message
    	        Transport.send(message);

    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Error");
    	    }

 	
    	}
    	JOptionPane.showMessageDialog(null,"Sent email successfully");
    }
    
    public void sendEvent(Event event, List<String> emails) {
    	messEvent = " Hi friends, Our center is going to hold an event. This Event: " + event.getName() + 
    			", Date: "+ event.getDate() + ", Description: "+ event.getDescription()+ 
    			". Contact with us to get more details. Have a nice day !"; 
    	for (String receiver : emails) {
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
    	        message.setContent(messEvent,"text/html");
    	        JOptionPane.showMessageDialog(null,"Sending...");
    	        // Send message
    	        Transport.send(message);
    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	    }
    		JOptionPane.showMessageDialog(null,"Sent email successfully");
    		break;
    	}
    	
    }
    	
 
	

}

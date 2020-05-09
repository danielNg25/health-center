package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.PersonDAO;
import project.medical.core.Person;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;

public class PersonReminder extends JFrame {

	private JPanel contentPane;
    private PersonApp personApp;
    private PersonDAO personDAO;
    private List<Person> TodayPersons;
    private List<Person> TomorrowPersons;
    private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonReminder frame = new PersonReminder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonReminder( PersonApp thePersonApp,PersonDAO thePersonDAO) {
		this();
		personApp = thePersonApp;
		personDAO = thePersonDAO;
	}
	public PersonReminder() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Reminder");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Today");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TodayPersons = personDAO.getTodayPerson();
					for (Person temp : TodayPersons) {
						System.out.println(temp);
					}
					if(TodayPersons.size() == 0) {
						JOptionPane.showMessageDialog(PersonReminder.this,"Today has no Person","Not found",JOptionPane.ERROR_MESSAGE);
					}else {
						KidTableModel model = new KidTableModel(TodayPersons);
		                table.setModel(model);
					}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(PersonReminder.this, "Error: "+ exc, "error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Tomorrow");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TomorrowPersons = personDAO.getTomorrowPerson();
					for (Person temp : TomorrowPersons) {
						System.out.println(temp);
					}
					if(TomorrowPersons.size() == 0) {
						JOptionPane.showMessageDialog(PersonReminder.this,"Tomorrow has no Person","Not Found", JOptionPane.ERROR_MESSAGE);
					} else {
					KidTableModel model = new KidTableModel(TomorrowPersons);
	                table.setModel(model);
					}
					
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(PersonReminder.this, "Error: "+ exc, "error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		panel.add(btnNewButton_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
	}

	protected void sendEmail(String receiver, String mess) {
		 	
	        // Sender's email ID needs to be mentioned
	        String sender = "hakasubteam@gmail.com";
	        String password = "nguyentranbaonguyentranbao";

	        // Assuming you are sending email from through gmails smtp
	        String host = "smtp.gmail.com";

	        // Get system properties
	        Properties properties = System.getProperties();

	        // Setup mail server
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");

	        // Get the Session object.// and pass username and password
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {

	                return new PasswordAuthentication(sender, password);

	            }

	        });

	        // Used to debug SMTP issues
	        //session.setDebug(true);

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(sender));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
	            //
	            
	            // Set Subject: header field
	            message.setSubject("Reminder from healthy care center");

	            // Now set the actual message 
	            message.setContent(mess,"text/html");
	          
	            System.out.println("Sending...");
	            // Send message
	            Transport.send(message);
	            System.out.println("Sent email successfully....");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }

		
	}

}

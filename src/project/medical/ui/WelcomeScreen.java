package project.medical.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Component;

public class WelcomeScreen {

	private JFrame MainScreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen window = new WelcomeScreen();
					window.MainScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainScreen = new JFrame();
		MainScreen.setType(Type.UTILITY);
		MainScreen.getContentPane().setBackground(new Color(192, 192, 192));
		MainScreen.setTitle("HealthCenter");
		MainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainScreen.setBackground(new Color(0, 0, 0));
		MainScreen.setBounds(100, 100, 609, 475);
		MainScreen.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Vaccination");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VaccinationScreen  vaccinationUI = new VaccinationScreen();
				vaccinationUI.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(0, 44, 118, 46);
		MainScreen.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kid Index");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(116, 44, 101, 46);
		MainScreen.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Appointment");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(321, 44, 129, 46);
		MainScreen.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Mom Index");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBounds(212, 44, 111, 46);
		MainScreen.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Clinic Infor");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_4.setBounds(446, 42, 147, 48);
		MainScreen.getContentPane().add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\admin\\eclipse-workspace\\Medical\\Image\\healthycare-screen.jpg"));
		lblNewLabel.setBounds(0, 88, 593, 348);
		MainScreen.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome to TNT Healthcare Center");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBackground(Color.CYAN);
		lblNewLabel_1.setBounds(0, 0, 593, 46);
		MainScreen.getContentPane().add(lblNewLabel_1);
	}
}

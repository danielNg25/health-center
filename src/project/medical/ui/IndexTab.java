package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.HistoryMedicalDAO;
import project.medical.DAO.WeightHeightDAO;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class IndexTab extends JFrame {

	private JPanel contentPane;
	private DrawChart myDrawer;
	private String personID;
	private JPanel panel;
	private JTextField advicesField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private WeightHeightDAO whDAO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndexTab frame = new IndexTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public IndexTab(String thepersonID) throws Exception {
		this();
		this.personID = thepersonID;
		whDAO = new WeightHeightDAO();
		myDrawer = new DrawChart(personID);
	}
	
	
	public IndexTab() {
		setTitle("Kid Index");
		setBounds(100, 100, 377, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnNewButton = new JButton("Height Chart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDrawer.drawingHeightChart();
			}
		});
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Weight Chart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myDrawer.drawingWeightChart();
			}
		});
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Advices");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double cur_bmi = 0;
				try {
					cur_bmi = whDAO.getBmiByID(personID);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				String state = null, advices = null;
				
				if (cur_bmi == 0) {
				} else if (cur_bmi < 16) {
					state = "Servere Thinness";
					advices = "You should do excercises and eat healthy";
				} else if (cur_bmi < 17) {
					state = "Moderate Thinness";
					advices = "You should do  excercises and eat healthy";
				} else if (cur_bmi < 18.5) {
					state = "Mild Thinness";
					advices = "You should do  excercises and eat healthy";
				} else if (cur_bmi < 25) {
					state = "Normal";
					advices = "OK. Keep eating healthy";
				} else if (cur_bmi < 30) {
					state = "Overweight";
					advices = "You should do more excercises and start eating diet food";
				} else if (cur_bmi < 35) {
					state = "Obese class I";
					advices = "You should do more excercises and start eating diet food";
				} else if (cur_bmi < 40 ) {
					state = "Obese class II";
					advices = "You should do more excercises and start eating diet food";
				} else if (cur_bmi > 40) {
					state = "Obese class III";
					advices = "You should do more excercises and start eating diet food";
				}
				String mota = "BMI index:" + cur_bmi +"\n"+
				 " You are in " + state + "\n" +advices;
				advicesField.setText(mota);
			}
		});
		panel.add(btnNewButton_2);
		
		advicesField = new JTextField();
		advicesField.setHorizontalAlignment(SwingConstants.CENTER);
		advicesField.setText("Mo ta o day");
		contentPane.add(advicesField, BorderLayout.CENTER);
		advicesField.setColumns(10);
	}
}

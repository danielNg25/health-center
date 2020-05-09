package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.KidDAO;
import project.medical.DAO.MomDAO;
import project.medical.core.Kid;
import project.medical.core.Person;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JSplitPane;

public class VaccinationScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField kidNameField;
	private JTextField momNameField;
	private KidDAO kidDAO;
	private MomDAO momDAO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VaccinationScreen frame = new VaccinationScreen();
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
	public VaccinationScreen() {
		setType(Type.UTILITY);
		setTitle("Vaccination");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("Add");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Search Kids");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = kidNameField.getText();
					
					List <Kid> kids = null;
					
					if(name != null && name.trim().length() > 0 ) {
						kids = kidDAO.getKidByName(name);
					}
					else {
						kids = kidDAO.getAllKid();
					}
					
					KidTableModel kidModel = new KidTableModel(kids);
					table.setModel(kidModel);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(VaccinationScreen.this, "Error: "+ e1, "error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton_2);
		
		kidNameField = new JTextField();
		panel.add(kidNameField);
		kidNameField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Search Moms");
		panel.add(btnNewButton_3);
		
		momNameField = new JTextField();
		panel.add(momNameField);
		momNameField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}

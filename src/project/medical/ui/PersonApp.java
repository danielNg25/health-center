package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import project.medical.DAO.PersonDAO;
import project.medical.core.Person;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class PersonApp extends JFrame {

	private JPanel contentPane;
	private JTextField searchnameField;
	private PersonDAO personDAO;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonApp frame = new PersonApp();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonApp() {
		try {
			personDAO = new PersonDAO();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "error: "+e1, "error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton_3 = new JButton("Reminder");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonReminder reminder = new PersonReminder(PersonApp.this,personDAO);
				reminder.setVisible(true);
			}
		});
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Medical Application");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel(); 
		panel_1.setBackground(new Color(240, 240, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(PersonApp.this,"Please select a person","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Person temp = (Person) table.getValueAt(row, PersonTableModel.OBJECT_COL);
				
				PersonDialog dialog = new PersonDialog(PersonApp.this, personDAO, temp, true);
				
				dialog.setVisible(true);
				
			}
		});
		panel_1.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Search by name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1);
		
		searchnameField = new JTextField();
		searchnameField.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(searchnameField);
		searchnameField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					String name = searchnameField.getText();
				
					List <Person> persons = null;
					
					if(name != null && name.trim().length() > 0 ) {
						persons = personDAO.getPersonbyName(name);
					}
					else {
						persons = personDAO.getAllPerson();
					}
					
					for (Person temp : persons) {
						System.out.println(temp);
					}
                    PersonTableModel model = new PersonTableModel(persons);
                    table.setModel(model);
                    
	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(PersonApp.this, "Error: "+ exc, "error",JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add");
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonDialog dialog = new PersonDialog(PersonApp.this,personDAO, null, false);
				dialog.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	
	}

	public void refreshPersonView() {
		try {
			List<Person> persons = personDAO.getAllPerson();
			
			PersonTableModel model = new PersonTableModel(persons);
			
			table.setModel(model);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Error: " + e , "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
}

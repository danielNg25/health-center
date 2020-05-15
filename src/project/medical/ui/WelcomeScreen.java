package project.medical.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;





import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.border.CompoundBorder;

import project.medical.DAO.KidDAO;
import project.medical.DAO.MomDAO;
import project.medical.core.Kid;
import project.medical.core.Mom;
import project.medical.core.Person;

import javax.swing.JToolBar;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class WelcomeScreen {

	private JFrame MainScreen;
	private JTextField kidNameField;
	private KidDAO kidDAO;
	private MomDAO momDAO;
	private JTable kidTable;
	private JTable momTable;
	private JTextField momNameField;

	/**
	 * Launch the application.
	 * @throws Exception 
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
	 * @throws Exception 
	 */
	
	public WelcomeScreen() throws Exception {
		kidDAO = new KidDAO();
		momDAO = new MomDAO();
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
		MainScreen.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new CompoundBorder());
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 13));
		MainScreen.getContentPane().add(tabbedPane);
		
		JPanel panel_home = new JPanel();
		panel_home.setBackground(Color.ORANGE);
		tabbedPane.addTab("HOME", null, panel_home, null);
		
		JPanel panel_kids = new JPanel();
		panel_kids.setBorder(new CompoundBorder());
		tabbedPane.addTab("KIDS", null, panel_kids, null);
		panel_kids.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		panel_kids.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUpdateDialog adddialog = new AddUpdateDialog(panel_kids, kidDAO, null, null, null, false, false);
				adddialog.setVisible(true);
					
			}
		});
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = kidTable.getSelectedRow();
				if(row <0) {
					JOptionPane.showMessageDialog(panel_kids,"Please select a kid","Warning",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Kid temp = (Kid) kidTable.getValueAt(row, KidTableModel.OBJECT_COL);
				
				AddUpdateDialog updatedialog = new AddUpdateDialog(panel_kids, kidDAO, temp, null, null, false, true);
				
				updatedialog.setVisible(true);
				
			}		
		});
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Search");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name = kidNameField.getText();
				
					List <Kid> kids = null;
					
					if(name != null && name.trim().length() > 0 ) {
						kids = kidDAO.getKidByName(name);
					}
					else {
						kids = kidDAO.getAllKid();
					}
					
                    KidTableModel model = new KidTableModel(kids);
                    kidTable.setModel(model);
                    
	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(panel_kids, "Error: "+ exc, "Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		toolBar.add(btnNewButton_3);
		
		kidNameField = new JTextField();
		toolBar.add(kidNameField);
		kidNameField.setColumns(10);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setOrientation(SwingConstants.VERTICAL);
		panel_kids.add(toolBar_1, BorderLayout.WEST);
		
		JButton btnNewButton_7 = new JButton("Appointment");
		btnNewButton_7.setHorizontalAlignment(SwingConstants.LEFT);
		toolBar_1.add(btnNewButton_7);
		
		JButton btnNewButton_4 = new JButton("Vaccination");
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.setMinimumSize(new Dimension(93, 23));
		btnNewButton_4.setMaximumSize(new Dimension(93, 23));
		toolBar_1.add(btnNewButton_4);
		
		JButton btnNewButton_6 = new JButton("Index");
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setMaximumSize(new Dimension(93, 23));
		btnNewButton_6.setMinimumSize(new Dimension(93, 23));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar_1.add(btnNewButton_6);
		
		JButton btnNewButton_5 = new JButton("Information");
		btnNewButton_5.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_5.setMinimumSize(new Dimension(93, 23));
		btnNewButton_5.setMaximumSize(new Dimension(93, 23));
		toolBar_1.add(btnNewButton_5);
		
		
		kidTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(kidTable);
		panel_kids.add(scrollPane, BorderLayout.CENTER);
		
		
		
		
		JPanel panel_moms = new JPanel();
		tabbedPane.addTab("MOMS", null, panel_moms, null);
		panel_moms.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_2 = new JToolBar();
		panel_moms.add(toolBar_2, BorderLayout.NORTH);
		
		JButton btnNewButton_8 = new JButton("Add");
		toolBar_2.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Update");
		toolBar_2.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Delete");
		toolBar_2.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("Search");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String name = momNameField.getText();
				
					List <Mom> moms = null;
					
					if(name != null && name.trim().length() > 0 ) {
						moms = momDAO.getMomByName(name);
					}
					else {
						moms = momDAO.getAllMom();
					}
					
                    MomTableModel model2 = new MomTableModel(moms);
                    momTable.setModel(model2);
                    
	
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(panel_kids, "Error: "+ exc, "Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		toolBar_2.add(btnNewButton_11);
		
		momNameField = new JTextField();
		toolBar_2.add(momNameField);
		momNameField.setColumns(10);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setOrientation(SwingConstants.VERTICAL);
		panel_moms.add(toolBar_3, BorderLayout.WEST);
		
		JButton btnNewButton_13 = new JButton("New button");
		toolBar_3.add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("New button");
		toolBar_3.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("New button");
		toolBar_3.add(btnNewButton_15);
		
		JButton btnNewButton_12 = new JButton("New button");
		toolBar_3.add(btnNewButton_12);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_moms.add(scrollPane_1, BorderLayout.CENTER);
		
		momTable = new JTable();
		scrollPane_1.setViewportView(momTable);
		
		JPanel panel_clinic = new JPanel();
		tabbedPane.addTab("CLINIC", null, panel_clinic, null);
		
		JPanel panel_about = new JPanel();
		tabbedPane.addTab("ABOUT", null, panel_about, null);
	}
	

	
	
  }


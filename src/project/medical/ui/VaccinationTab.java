package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.HistoryMedicalDAO;
import project.medical.core.HistoryMedical;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class VaccinationTab extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String personID;
	private HistoryMedicalDAO histDAO;
	private JTable vaccinationTable;
	private JTextField decorateField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VaccinationTab frame = new VaccinationTab();
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
	public VaccinationTab(String theID) throws Exception {
		this();
		personID = theID;
		histDAO = new HistoryMedicalDAO();
		List<HistoryMedical> hists=null;

		hists = histDAO.getHistoryMedicalByName(personID);
		
        VaccinationTableModel model = new VaccinationTableModel(hists);
        
        vaccinationTable.setModel(model);
		
        String txt = "Vaccination Hisory of "+ personID;
        decorateField.setText(txt);
		
    
		
	}
	public VaccinationTab() {
		setTitle("Vaccination History");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Add new history");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  AddVaccinationDialog diag = new AddVaccinationDialog(histDAO ,personID);
				  diag.setVisible(true);

			}
		});
		toolBar.add(btnNewButton);
		
		decorateField = new JTextField();
		decorateField.setEditable(false);
		toolBar.add(decorateField);
		decorateField.setColumns(10);
		
		vaccinationTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(vaccinationTable);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
	}

}

package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.medical.DAO.HistoryMedicalDAO;
import project.medical.core.HistoryMedical;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddVaccinationDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private JTextField dateofinjection;
	private JTextField typeVaccineField;
	private JTextField vaccineIDfield;
	private JTextField interactionField;
	private JTextField AddressField;
	private JTextField nextAppointmentField;
	private String personID;
	private HistoryMedicalDAO histDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddVaccinationDialog dialog = new AddVaccinationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddVaccinationDialog(HistoryMedicalDAO thehistDAO,String theIDPerson) {
		this();
		this.personID = theIDPerson;
		this.histDAO = thehistDAO;
		
	}

	
	public AddVaccinationDialog() {
		setTitle("Add new history");
		setType(Type.POPUP);
		setBounds(100, 100, 548, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date Injection");
		lblNewLabel.setBounds(10, 14, 93, 14);
		contentPanel.add(lblNewLabel);
		
		dateofinjection = new JTextField();
		dateofinjection.setBounds(170, 11, 107, 20);
		contentPanel.add(dateofinjection);
		dateofinjection.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Type Vaccine");
		lblNewLabel_1.setBounds(10, 55, 68, 14);
		contentPanel.add(lblNewLabel_1);
		
		typeVaccineField = new JTextField();
		typeVaccineField.setBounds(170, 52, 299, 20);
		contentPanel.add(typeVaccineField);
		typeVaccineField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("VaccineID");
		lblNewLabel_2.setBounds(310, 14, 63, 14);
		contentPanel.add(lblNewLabel_2);
		
		vaccineIDfield = new JTextField();
		vaccineIDfield.setBounds(383, 11, 86, 20);
		contentPanel.add(vaccineIDfield);
		vaccineIDfield.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Interaction");
		lblNewLabel_3.setBounds(10, 141, 86, 14);
		contentPanel.add(lblNewLabel_3);
		
		interactionField = new JTextField();
		interactionField.setBounds(170, 138, 300, 20);
		contentPanel.add(interactionField);
		interactionField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setBounds(10, 90, 68, 14);
		contentPanel.add(lblNewLabel_4);
		
		AddressField = new JTextField();
		AddressField.setBounds(171, 87, 299, 20);
		contentPanel.add(AddressField);
		AddressField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date Appointment ");
		lblNewLabel_5.setBounds(10, 186, 101, 14);
		contentPanel.add(lblNewLabel_5);
		
		nextAppointmentField = new JTextField();
		nextAppointmentField.setBounds(170, 183, 107, 20);
		contentPanel.add(nextAppointmentField);
		nextAppointmentField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							saveHist();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("Add");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveHist() throws Exception {
		String dateInjectstring = dateofinjection.getText();
		int idvaccine = Integer.parseInt(vaccineIDfield.getText());
		String type = typeVaccineField.getText();
		String interaction = interactionField.getText();
		String nextappoint = nextAppointmentField.getText();
		String address = AddressField.getText();
		
		Date injectdate = formatter.parse(dateInjectstring);
		Date appointmentdate = formatter.parse(nextappoint);
		HistoryMedical newhist  = new HistoryMedical(injectdate, type, idvaccine, address, interaction, null, appointmentdate);
				

		histDAO.addHistoryMedical(newhist, personID);
		JOptionPane.showMessageDialog(AddVaccinationDialog.this, "Added successfully", "Added successfully ", JOptionPane.INFORMATION_MESSAGE);

			
		setVisible(false);
		dispose();

		
	}
}

package project.medical.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import project.medical.core.*;
import project.medical.DAO.*;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField IDField;
	private JTextField FirstNameField;
	private JTextField LastNameField;
	private JTextField DateofbirthField;
	private JTextField GenderField;
	private JTextField HeightField;
	private JTextField WeightField;
	private JTextField OccupationField;
	private JTextField AddressField;
	private JTextField EmailField;
	private JTextField PhoneField;
    
	private PersonApp personApp;
    private PersonDAO personDAO;
    private Person previousPerson = null;
    private boolean updateMode = false;
	
    public PersonDialog(PersonApp thePersonApp,PersonDAO thePersonDAO, Person thePreviousPerson, boolean theUpdateMode) {
    	this();
    	personDAO = thePersonDAO;
    	personApp = thePersonApp;
    	
    	previousPerson = thePreviousPerson;
    	updateMode= theUpdateMode;
    	if(updateMode) {
    		setTitle("Update Person");
    		populateGui(previousPerson);
    	}
    }
	private void populateGui(Person thepreviousPerson) {
		FirstNameField.setText(thepreviousPerson.getFirst_Name());
		LastNameField.setText(thepreviousPerson.getLast_Name());
		EmailField.setText(thepreviousPerson.getEmail());
		DateofbirthField.setText(thepreviousPerson.getDate_of_birth());
		OccupationField.setText(thepreviousPerson.getOccupation());
		PhoneField.setText(thepreviousPerson.getPhone_number());
		HeightField.setText(Integer.toString(thepreviousPerson.getHeight()));
		WeightField.setText(Integer.toString(thepreviousPerson.getWeight()));
		GenderField.setText(thepreviousPerson.getGender());
		IDField.setText(thepreviousPerson.getID());
		AddressField.setText(thepreviousPerson.getAddress());
	
	}
	/**
	 * Create the dialog.
	 */
	public PersonDialog() {
		if (updateMode) {
			setTitle("Update Information");
		}
		else {
			setTitle("Add New");
		}
		setBounds(100, 100, 450, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("ID");
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			contentPanel.add(lblNewLabel, "2, 2, center, default");
		}
		{
			IDField = new JTextField();
			contentPanel.add(IDField, "4, 2");
			IDField.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("First Name");
			contentPanel.add(lblNewLabel_1, "2, 4, right, default");
		}
		{
			FirstNameField = new JTextField();
			contentPanel.add(FirstNameField, "4, 4");
			FirstNameField.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Last Name");
			contentPanel.add(lblNewLabel_2, "2, 6, right, default");
		}
		{
			LastNameField = new JTextField();
			contentPanel.add(LastNameField, "4, 6, fill, default");
			LastNameField.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Date of birth");
			contentPanel.add(lblNewLabel_3, "2, 8, center, default");
		}
		{
			DateofbirthField = new JTextField();
			contentPanel.add(DateofbirthField, "4, 8, fill, default");
			DateofbirthField.setColumns(10);
		}
		{
			JLabel lblNewLabel_4 = new JLabel("Gender");
			contentPanel.add(lblNewLabel_4, "2, 10, center, default");
		}
		{
			GenderField = new JTextField();
			contentPanel.add(GenderField, "4, 10, fill, default");
			GenderField.setColumns(10);
		}
		{
			JLabel lblNewLabel_5 = new JLabel("Height");
			contentPanel.add(lblNewLabel_5, "2, 12, center, default");
		}
		{
			HeightField = new JTextField();
			contentPanel.add(HeightField, "4, 12, fill, default");
			HeightField.setColumns(10);
		}
		{
			JLabel lblNewLabel_6 = new JLabel("Weight");
			contentPanel.add(lblNewLabel_6, "2, 14, center, default");
		}
		{
			WeightField = new JTextField();
			contentPanel.add(WeightField, "4, 14, fill, default");
			WeightField.setColumns(10);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("Occupation");
			contentPanel.add(lblNewLabel_7, "2, 16, right, default");
		}
		{
			OccupationField = new JTextField();
			contentPanel.add(OccupationField, "4, 16, fill, default");
			OccupationField.setColumns(10);
		}
		{
			JLabel lblNewLabel_8 = new JLabel("Address");
			contentPanel.add(lblNewLabel_8, "2, 18, center, default");
		}
		{
			AddressField = new JTextField();
			contentPanel.add(AddressField, "4, 18, fill, default");
			AddressField.setColumns(10);
		}
		{
			JLabel lblNewLabel_9 = new JLabel("Email");
			contentPanel.add(lblNewLabel_9, "2, 20, center, default");
		}
		{
			EmailField = new JTextField();
			contentPanel.add(EmailField, "4, 20, fill, default");
			EmailField.setColumns(10);
		}
		{
			JLabel lblNewLabel_10 = new JLabel("Phone");
			contentPanel.add(lblNewLabel_10, "2, 22, center, default");
		}
		{
			PhoneField = new JTextField();
			contentPanel.add(PhoneField, "4, 22, fill, default");
			PhoneField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						savePerson();				
					}
				});
				okButton.setActionCommand("OK");
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
	protected void savePerson() {
		// TODO Auto-generated method stub
		// get information
		String id = IDField.getText();
		String last_name = LastNameField.getText();
		String first_name = FirstNameField.getText();
		String date_of_birth = DateofbirthField.getText();
		String gender = GenderField.getText();
		String height_s = HeightField.getText(); 
		String weight_s = WeightField.getText();
		int height = Integer.parseInt(height_s);
		int weight = Integer.parseInt(weight_s);
		String occupation = OccupationField.getText();
		String email = EmailField.getText();
		String address = AddressField.getText();
		String phone = PhoneField.getText();
		
		
		
		
		Person temp = null;
		if(updateMode) {
			temp = previousPerson;
			temp.setLast_Name(last_name);
			temp.setFirst_Name(first_name);
			temp.setDate_of_birth(date_of_birth);
			temp.setGender(gender);
			temp.setEmail(email);
			temp.setHeight(height);
			temp.setWeight(weight);
			temp.setPhone_number(phone);
			temp.setAddress(address);
			temp.setOccupation(occupation);
		}
		else {
			temp = new Person(id, last_name, first_name, date_of_birth, gender, occupation, address, email, phone, height, weight);
		}
		// save
		try {
			
			if (updateMode) {
				personDAO.updatePerson(temp);
				JOptionPane.showMessageDialog(personApp, "Updated successfully","Updated successfully ", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				personDAO.addPerson(temp);
				JOptionPane.showMessageDialog(personApp, "Added successfully","Added successfully ", JOptionPane.INFORMATION_MESSAGE);
			}
			
			setVisible(false);
			dispose();
			
			personApp.refreshPersonView();
			
			
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(personApp,"Error saving: "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
				
	}
	

}

package project.medical.core;

import java.util.Date;

public class  Person {
	protected String ID; // khóa chính để tìm "String"
	protected String lastName; 
	protected String firstName;
	protected Date dateOfBirth;
	protected String address;
	protected String email;
	protected HistoryMedical [] histories;
	
	
	public HistoryMedical[] getHistories() {
		return histories;
	}
	public void setHistories(HistoryMedical[] histories) {
		this.histories = histories;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Person(String iD, String lastName, String firstName, Date dateOfBirth, String address, String email,
			HistoryMedical[] histories) {
		ID = iD;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.email = email;
		this.histories = histories;
	}
	
	public Person() {
		this.ID = null;
		this.lastName = null;
		this.firstName = null;
		this.dateOfBirth = null;
		this.address = null;
		this.email = null;
		this.histories = null;
	}
	public Person(String iD, String lastName, String firstName, Date dateOfBirth, String address, String email) {
		this.ID = iD;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.email = email;
		this.histories = null;
	}
	
	
	
	

}

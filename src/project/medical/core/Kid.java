package project.medical.core;

import java.util.Date;

public class Kid extends Person{
	
	
	private String helloKhangdmdmd;
	private String gender;
	private WeightHeight[] kidWH;
	private Person parent;
	private String abc;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public WeightHeight[] getKidWH() {
		return kidWH;
	}
	public void setKidWH(WeightHeight[] kidWH) {
		this.kidWH = kidWH;
	}
	public Person getParent() {
		return parent;
	}
	public void setParent(Person parent) {
		this.parent = parent;
	}
	public Kid() {
		super();
		this.kidWH= null;
		this.gender = null;
	}
	public Kid(String id, String lastName, String firstName, Date dateOfBirth, String address, String email,
			HistoryMedical[] histories, String gender, WeightHeight[] kidWH, Person parent) {
		super(id, lastName, firstName, dateOfBirth, address, email, histories);
		this.kidWH= kidWH;
		this.gender = gender;
		this.parent = parent;
		
	}
	public Kid(String id, String lastName, String firstName, Date dateOfBirth, String address, String email,
			String gender, Person parent) {
		super(id, lastName, firstName, dateOfBirth, address, email);
		this.gender = gender;
		this.parent = parent;
	}

    
    


}

package project.medical.core;

import java.util.Date;

public class Kid extends Person{
	
	private String gender;
	private WeightHeight[] kidWH;
	private String parentName;
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

	public Kid() {
		super();
		this.kidWH= null;
		this.gender = null;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Kid(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum,
			HistoryMedical[] histories, String gender, WeightHeight[] kidWH) {
		super(id, lastName, firstName, dateOfBirth, address, email, phoneNum, histories);
		this.kidWH= kidWH;
		this.gender = gender;		
	}
	public Kid(String id, String lastName, String firstName, Date dateOfBirth, String address, String email, String phoneNum,
			String gender, String parentName) {
		super(id, lastName, firstName, dateOfBirth, address, email, phoneNum);
		this.gender = gender;
		this.parentName = parentName;

	}

    
    


}

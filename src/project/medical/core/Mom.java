package project.medical.core;

import java.util.Date;

public class Mom extends Person{
	private WeightHeight[] fetalWH;

	public WeightHeight[] getFetalWH() {
		return fetalWH;
	}

	public void setFetalWH(WeightHeight[] fetalWH) {
		this.fetalWH = fetalWH;
	}

	public Mom() {
		super();
		this.fetalWH = null;
	}

	public Mom(String iD, String lastName, String firstName, Date dateOfBirth, String address, String email,
			HistoryMedical[] histories, WeightHeight[] fetalWH ) {
		super(iD, lastName, firstName, dateOfBirth, address, email, histories);
		this.fetalWH =  fetalWH;
	}

	public Mom(String id, String lastName, String firstName, Date dateOfBirth, String address, String email) {
		super(id, lastName, firstName, dateOfBirth, address, email);
	}
	

}

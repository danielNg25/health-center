package project.medical.core;

import java.awt.image.BufferedImage;
import java.util.Date;


public class HistoryMedical {
	private int IDHistory;
	private Date dateOfInjection;
	private String typeOfVaccine;
	private int IDVaccine;
	private String address;
	private String interaction;
	private BufferedImage imageHist;
	private Date nextAppointment;
	
	
	public int getIDHistory() {
		return IDHistory;
	}
	public void setIDHistory(int iDHistory) {
		IDHistory = iDHistory;
	}
	public Date getDateOfInjection() {
		return dateOfInjection;
	}
	public void setDateOfInjection(Date dateOfInjection) {
		this.dateOfInjection = dateOfInjection;
	}
	public String getTypeOfVaccine() {
		return typeOfVaccine;
	}
	public void setTypeOfVaccine(String typeOfVaccine) {
		this.typeOfVaccine = typeOfVaccine;
	}
	public int getIDVaccine() {
		return IDVaccine;
	}
	public void setIDVaccine(int iDVaccine) {
		IDVaccine = iDVaccine;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInteraction() {
		return interaction;
	}
	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}
	public BufferedImage getImageHist() {
		return imageHist;
	}
	public void setImageHist(BufferedImage imageHist) {
		this.imageHist = imageHist;
	}
	public Date getNextAppointment() {
		return nextAppointment;
	}
	public void setNextAppointment(Date nextAppointment) {
		this.nextAppointment = nextAppointment;
	}
	public HistoryMedical(int iDHistory, Date dateOfInjection, String typeOfVaccine, int iDVaccine, String address,
			String interaction, BufferedImage imageHist, Date nextAppointment) {
		IDHistory = iDHistory;
		this.dateOfInjection = dateOfInjection;
		this.typeOfVaccine = typeOfVaccine;
		IDVaccine = iDVaccine;
		this.address = address;
		this.interaction = interaction;
		this.imageHist = imageHist;
		this.nextAppointment = nextAppointment;
	}
	
	
	
	
	
	
    
	
	
	
	
	
}


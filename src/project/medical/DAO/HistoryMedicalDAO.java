package project.medical.DAO;

import project.medical.core.*;
import java.io.FileInputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class HistoryMedicalDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public HistoryMedicalDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
		System.out.println("Connect Successfull");
	}
	
	//  Get all HistoryMedicals of "a person " according to person ID 
	public  List<HistoryMedical> getHistoryMedicalByName(String theIDPerson) throws Exception {
		List<HistoryMedical> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			theIDPerson += "%";
			myStmt = myCon.prepareStatement("select * from HistoryMedical where id = ? ");
			myStmt.setString(1, theIDPerson);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				HistoryMedical tempHistoryMedical = convertRowToHistoryMedical(myRs);
				list.add(tempHistoryMedical);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	// Adding a HistoryMedical object to table according to person ID 
	public void addHistoryMedical(HistoryMedical newHistoryMedical, String thePersonID) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into HistoryMedical"
				+ "(ID, dateOfInjection, typeOfVaccine, IDVaccine, address, interaction, imageHist, nextAppointment)"
				+ " values (?, ? ,?, ?, ?, ? ,?, ?)"; 
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDateInjection = formatter.format(newHistoryMedical.getDateOfInjection());
		String stringDateNextAppoint = formatter.format(newHistoryMedical.getNextAppointment());
		//String StringUrlImage = ;
		myStmt.setString(1, thePersonID );
		myStmt.setString(2, stringDateInjection);
		myStmt.setString(3, newHistoryMedical.getTypeOfVaccine());
		myStmt.setInt(4, newHistoryMedical.getIDVaccine());
		myStmt.setString(5, newHistoryMedical.getAddress());
		myStmt.setString(6, newHistoryMedical.getInteraction());
		//myStmt.setString(7, StringUrlImage);
		myStmt.setString(8, stringDateNextAppoint);
		
			
		
		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	
	// Converting one HistoryMedical in table -> object HistoryMedical (with out ID)
	private HistoryMedical convertRowToHistoryMedical(ResultSet myRs) throws SQLException {
		
		String stringDateInjection = myRs.getString("dateOfInjection");
		String stringDateNextAppoint =  myRs.getString("nextAppointment");
		Date dateInjectionInDate, nextAppoinmentInDate = null;
		try {
			dateInjectionInDate = formatter.parse(stringDateInjection);
			nextAppoinmentInDate = formatter.parse(stringDateNextAppoint);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String type = myRs.getString("typeOfVaccine");
		int idvaccine = myRs.getInt("IDVaccine");
		String url = myRs.getString("imageHist");
		
	    HistoryMedical tempHistoryMedical = new HistoryMedical(dateInjectionInDate, typeOfVaccine, iDVaccine, address, interaction, imageHist, nextAppoinmentInDate)
	    
		return tempHistoryMedical;
	}
	
	

	
	private static void close(Connection myCon, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		} 
		
		if (myCon != null) {
			myCon.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}



}

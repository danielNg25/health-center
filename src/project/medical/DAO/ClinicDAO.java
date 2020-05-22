package project.medical.DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import project.medical.core.Clinic;
import project.medical.core.clinic;
import project.medical.core.Clinic;


public class ClinicDAO {
    private Connection myCon;

	public ClinicDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
		System.out.println("Connect Successfull");
	}
	
	// Get all Clinics from table into a list
		public  List<Clinic> getAllClicnic() throws Exception {
			
			List<Clinic> listAllClinic = new ArrayList<>();
			
			Statement myStmt = null;
			ResultSet myRs = null;
			
			try {
				myStmt = myCon.createStatement();
				myRs = myStmt.executeQuery("select * from clinic");
				
				while (myRs.next()) {
					Clinic tempClinic = convertRowToClinic(myRs);
					listAllClinic.add(tempClinic);
				}

				return listAllClinic;		
			}
			finally {
				close(myStmt, myRs);
			}
		}
	
		// Get all Clinics by name from table into a list 
		public  List<Clinic> getClinicByName(String name) throws Exception {
			List<Clinic> list = new ArrayList<>();

			PreparedStatement myStmt = null;
			ResultSet myRs = null;

			try {
				
				name += "%";
				myStmt = myCon.prepareStatement("select * from Clinic where clinicName like ? ");
				myStmt.setString(1, name);		
				myRs = myStmt.executeQuery();
				
				while (myRs.next()) {
					Clinic tempClinic = convertRowToClinic(myRs);
					list.add(tempClinic);
				}
				
				return list;
			}
			finally {
				close(myStmt, myRs);
			}
		}
		
		// Adding a Clinic object to table
		public void addClinic(Clinic newClinic) throws Exception{
			PreparedStatement myStmt = null;
			try {
			String sql  = "Insert into clinic"
					+ "(clinicID, clinicName, address, email, phoneNum, typee)"
					+ " values (?, ?, ?, ?, ?, ?) " ;
			
			myStmt  = myCon.prepareStatement(sql);
			
			myStmt.setString(1, newClinic.getID());
			myStmt.setString(2, newClinic.getClinicName());
			myStmt.setString(5, newClinic.getAddress());
			myStmt.setString(6, newClinic.getEmail());
			myStmt.setString(7, newClinic.getPhoneNum());
			myStmt.setString(8, newClinic.getType());
			
			myStmt.executeUpdate();
		    }
		    finally {
		    	myStmt.close();
		    }
		}	
		
		// Updating clinic 
		public void updateclinic(Clinic temp) throws SQLException {
			PreparedStatement myStmt = null;
			try {
				String sql  = "update clinic "
						+ " set clinicName = ?, address= ?,email=?, phoneNum=?,"
						+ " type = ?,"
						+ " where clinicID = ? " ;
				
				myStmt  = myCon.prepareStatement(sql);
				
				myStmt.setString(1, temp.getClinicName());
				myStmt.setString(2, temp.getAddress());
				myStmt.setString(3, temp.getEmail());
				myStmt.setString(4, temp.getPhoneNum());
				myStmt.setString(5, temp.getType());
				myStmt.setString(6, temp.getID());			
				
				myStmt.executeUpdate();
		    }
		    finally {
		    	myStmt.close();
		    }
			
		}
		
		
		private Clinic convertRowToClinic(ResultSet myRs) throws SQLException, ParseException {
			String id = myRs.getString("clinicID");
			String clinicName = myRs.getString("clinicName");
			String email = myRs.getString("email");
			String address = myRs.getString("address"); 
			String phoneNum = myRs.getString("phoneNum");
			String type = myRs.getString("type");
			
		    
			Clinic tempClinic = new Clinic(id, clinicName, address, email, phoneNum, type);
			
			return tempClinic;
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

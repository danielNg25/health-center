package project.medical.DAO;

import project.medical.core.*;
import java.io.FileInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class MotherDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	public MotherDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/Mother.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
		System.out.println("Connect Successfull");
	}
	// Get all mothers from table into a list
	public  List<Mother> getAllMom() throws Exception {
		
		List<Mother> listAllMom = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myCon.createStatement();
			myRs = myStmt.executeQuery("select * from mother");
			
			while (myRs.next()) {
				Mother tempMother = convertRowToMother(myRs);
				listAllMom.add(tempMother);
			}

			return listAllMom;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// Get all mothers by name from table into a list 
	public  List<Mother> getMotherByName(String name) throws Exception {
		List<Mother> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			name += "%";
			myStmt = myCon.prepareStatement("select * from mother where firstName like ? or lastName like ? ");
			myStmt.setString(1, name);
			myStmt.setString(2, name);			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Mother tempMother = convertRowToMother(myRs);
				list.add(tempMother);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	// Adding a Mother object to table
	public void addMother(Mother newMother) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into Mother"
				+ "(ID, lastName, firstName, dateOfBirth,Address,Email)"
				+ " values (?, ? ,? , ?, ?,? ) " ;
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDate = formatter.format(newMother.getDateOfBirth());
		
		myStmt.setString(1, newMother.getID() );
		myStmt.setString(2, newMother.getLastName());
		myStmt.setString(3, newMother.getFirstName());
		myStmt.setString(4, stringDate);
		myStmt.setString(5, newMother.getAddress());
		myStmt.setString(6, newMother.getEmail());
		
		
		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	
	// Converting one Mother in table -> object Mother
	private Mother convertRowToMother(ResultSet myRs) throws SQLException {
		
		String id = myRs.getString("ID");
		String lastName = myRs.getString("lastName");
		String firstName = myRs.getString("firstName");
		Date dateOfBirth = myRs.getDate("dateOfBirth");
		String email = myRs.getString("email");
		String address = myRs.getString("address"); 
		
	    Mother tempMother = new Mother(id, lastName, firstName, dateOfBirth, address, email);
		
		return tempMother;
	}
	
	// Update one peson 
	public void updateMother(Mother theMother) throws SQLException {
		PreparedStatement myStmt = null;
		try {
		     String sql =  "UPDATE Mother "
					+ " SET Last_Name = ?, First_Name = ?, Date_of_birth=?,"
					+ "Gender= ?, Email = ?, Address = ?, Phone_number = ?, Occupation = ?,"
					+ "Height= ? , Weight = ? "
					+ " WHERE ID = ?" ;
			myStmt = myCon.prepareStatement(sql);
			
			myStmt.setString(1, theMother.getLast_Name());
			myStmt.setString(2, theMother.getFirst_Name());
			myStmt.setString(3, theMother.getDate_of_birth());
			myStmt.setString(4, theMother.getGender());
			myStmt.setString(8, theMother.getOccupation());
			myStmt.setString(6, theMother.getAddress());
			myStmt.setString(5, theMother.getEmail());
			myStmt.setString(7, theMother.getPhone_number());
			myStmt.setInt(9, theMother.getHeight());
			myStmt.setInt(10, theMother.getWeight());
			myStmt.setString(11, theMother.getID());
			
			myStmt.executeUpdate(); 
		}
		finally {
			close(myStmt,null);
		}
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

	public static void main(String[] args) throws Exception {
		MotherDAO dao = new MotherDAO();
		System.out.println(dao.getAllMother());

	}


}

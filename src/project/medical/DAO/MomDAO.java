package project.medical.DAO;

import project.medical.core.*;
import java.io.FileInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class MomDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public MomDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
		System.out.println("Connect Successfull");
	}
	// Get all Moms from table into a list
	public  List<Mom> getAllMom() throws Exception {
		
		List<Mom> listAllMom = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myCon.createStatement();
			myRs = myStmt.executeQuery("select * from mom");
			
			while (myRs.next()) {
				Mom tempMom = convertRowToMom(myRs);
				listAllMom.add(tempMom);
			}

			return listAllMom;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// Get all Moms by name from table into a list 
	public  List<Mom> getMomByName(String name) throws Exception {
		List<Mom> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			name += "%";
			myStmt = myCon.prepareStatement("select * from Mom where firstName like ? or lastName like ? ");
			myStmt.setString(1, name);
			myStmt.setString(2, name);			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Mom tempMom = convertRowToMom(myRs);
				list.add(tempMom);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	// Adding a Mom object to table
	public void addMom(Mom newMom) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into Mom"
				+ "(ID, lastName, firstName, dateOfBirth,Address,Email)"
				+ " values (?, ? ,? , ?, ?, ? ) " ;
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDate = formatter.format(newMom.getDateOfBirth());
		
		myStmt.setString(1, newMom.getID() );
		myStmt.setString(2, newMom.getLastName());
		myStmt.setString(3, newMom.getFirstName());
		myStmt.setString(4, stringDate);
		myStmt.setString(5, newMom.getAddress());
		myStmt.setString(6, newMom.getEmail());
		
		
		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	
	// Converting one Mom in table -> object Mom
	private Mom convertRowToMom(ResultSet myRs) throws SQLException {
		
		String id = myRs.getString("ID");
		String lastName = myRs.getString("lastName");
		String firstName = myRs.getString("firstName");
		Date dateOfBirth = myRs.getDate("dateOfBirth");
		String email = myRs.getString("email");
		String address = myRs.getString("address"); 
		
	    Mom tempMom = new Mom(id, lastName, firstName, dateOfBirth, address, email);
		
		return tempMom;
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
		MomDAO dao = new MomDAO();
		System.out.println(dao.getAllMom());

	}


}

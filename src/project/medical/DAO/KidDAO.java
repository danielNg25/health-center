package project.medical.DAO;

import project.medical.core.*;
import java.io.FileInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


public class KidDAO {
    private Connection myCon;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public KidDAO() throws Exception{
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/person.properties"));
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		myCon= DriverManager.getConnection(dburl,user,password);
		System.out.println("Connect Successfull");
	}
	// Get all Kids from table into a list
	public  List<Kid> getAllKid() throws Exception {
		
		List<Kid> listAllKid = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myCon.createStatement();
			myRs = myStmt.executeQuery("select * from Kid");
			
			while (myRs.next()) {
				Kid tempKid = convertRowToKid(myRs);
				listAllKid.add(tempKid);
			}

			return listAllKid;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	// Get all Kids by name from table into a list 
	public  List<Kid> getKidByName(String name) throws Exception {
		List<Kid> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			name += "%";
			myStmt = myCon.prepareStatement("select * from Kid where firstName like ? or lastName like ? ");
			myStmt.setString(1, name);
			myStmt.setString(2, name);			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Kid tempKid = convertRowToKid(myRs);
				list.add(tempKid);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	// Adding a Kid object to table
	public void addKid(Kid newKid) throws Exception{
		PreparedStatement myStmt = null;
		try {
		String sql  = "Insert into Kid"
				+ "(ID, lastName, firstName, dateOfBirth, address, email, phoneNum, gender)"
				+ " values (?, ?, ?, ?, ?, ?, ?) " ;
		
		myStmt  = myCon.prepareStatement(sql);
		
		
		String stringDate = formatter.format(newKid.getDateOfBirth());
		
		myStmt.setString(1, newKid.getID() );
		myStmt.setString(2, newKid.getLastName());
		myStmt.setString(3, newKid.getFirstName());
		myStmt.setString(4, stringDate);
		myStmt.setString(5, newKid.getAddress());
		myStmt.setString(6, newKid.getEmail());
		myStmt.setString(8, newKid.getGender());
		myStmt.setString(7, newKid.getPhoneNum());
		
		
		myStmt.executeUpdate();
	    }
	    finally {
	    	myStmt.close();
	    }
	}	
	
	
	// Converting one Kid in table -> object Kid
	private Kid convertRowToKid(ResultSet myRs) throws SQLException {
		
		String id = myRs.getString("ID");
		String lastName = myRs.getString("lastName");
		String firstName = myRs.getString("firstName");
		Date dateOfBirth = myRs.getDate("dateOfBirth");
		String email = myRs.getString("email");
		String address = myRs.getString("address"); 
		String gender = myRs.getString("gender");
		String phoneNum = myRs.getString("phoneNum");
		
	    Kid tempKid = new Kid(id, lastName, firstName, dateOfBirth, address, email, phoneNum, gender);
		
		return tempKid;
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
		KidDAO dao = new KidDAO();
		System.out.println(dao.getAllKid());

	}


}

package GUI;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Main.testWindow;

public class GUIModel {

	String authDataLocation = "C:\\Database\\authorization.accdb";
	String mainDataLocation = "C:\\Database\\mainDatabase.accdb";
	String authData = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + authDataLocation;
	String mainData = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + mainDataLocation;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	

	
	public void dataconnect(String database) { // Connects to either the authorization or customer database.
		// Use either "auth" or "customer" to select which one. Separated in different functions for ease of programming.
		if (database == "auth") {
			try {
				connection = DriverManager.getConnection(authData);
				// JOptionPane.showMessageDialog(null, "Connection Successful");
				
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex);
			}
		} else if (database == "customer") {
			try {
				connection = DriverManager.getConnection(mainData);
				// JOptionPane.showMessageDialog(null, "Connection Successful");
				
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex);
			}
		}
		
	}
	
	public ArrayList<ArrayList<String>> sendAuthCheck(String username) { // Gets the queried information in an arraylist from the authorization database.
		ArrayList<ArrayList<String>> returnedList = new ArrayList<ArrayList<String>>();
		dataconnect("auth");
		try {
			statement = connection.createStatement();
			PreparedStatement userCheck = connection.prepareStatement("SELECT COUNT(*) FROM Users WHERE username = ?");
			
			while (result.next()) {
				
				ArrayList<String> provided = new ArrayList<String>();
				int id = result.getInt(1);
				provided.add(String.valueOf(id));
				String fName = result.getString(2);
				provided.add(fName);
				String lName = result.getString(3);
				provided.add(lName);
				String pass = result.getString(4);
				provided.add(pass);
				System.out.println(provided);
				returnedList.add(provided);
				
				
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
		}
		return returnedList;
		
	}
	
	
	public ArrayList<ArrayList<String>> sendAuthQuery(String username) { // Gets the queried information in an arraylist from the authorization database.
		ArrayList<ArrayList<String>> returnedList = new ArrayList<ArrayList<String>>();
		dataconnect("auth");
		try {
			statement = connection.createStatement();
			PreparedStatement userCheck = connection.prepareStatement("SELECT COUNT(*) FROM Users WHERE username = ?");
			
			while (result.next()) {
				
				ArrayList<String> provided = new ArrayList<String>();
				int id = result.getInt(1);
				provided.add(String.valueOf(id));
				String fName = result.getString(2);
				provided.add(fName);
				String lName = result.getString(3);
				provided.add(lName);
				String pass = result.getString(4);
				provided.add(pass);
				System.out.println(provided);
				returnedList.add(provided);
				
				
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
		}
		return returnedList;
		
	}
	
	
	public ArrayList<ArrayList<String>> sendCustQuery(String query) { // Does the same thing as sendAuthQuery, but for the authorization database.
		ArrayList<ArrayList<String>> returnedList = new ArrayList<ArrayList<String>>();
		dataconnect("customer");
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			
			while (result.next()) {
				
				ArrayList<String> provided = new ArrayList<String>();
				int id = result.getInt(1);
				provided.add(String.valueOf(id));
				String fName = result.getString(2);
				provided.add(fName);
				String lName = result.getString(3);
				provided.add(lName);
				String email = result.getString(4);
				provided.add(email);
				System.out.println(provided);
				returnedList.add(provided);
				
				
			}
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
		}
		return returnedList;
		
	}
	
	public void modifyCustQuery(String query) { // Modifies the customer database. Similar to the above two functions, but does not return anything.
		dataconnect("customer");
		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			System.out.println("Successful query.");
			
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
			
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIModel x = new GUIModel();
		JOptionPane.showMessageDialog(null, x.sendAuthQuery("SELECT * FROM authorization"));
		JOptionPane.showMessageDialog(null, x.sendCustQuery("SELECT * FROM Customer"));

	}
}

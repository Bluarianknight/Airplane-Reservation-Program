package GUI;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Main.testWindow;

public class GUIModel {
	String databaseLocation;
	String startData = "jdbc:ucanaccess://Databases/authorization.accdb";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	
	
	public GUIModel() {
		this.databaseLocation = startData;
	}
	
	public GUIModel(String location) {
		this.databaseLocation = location;
	}

	
	public void dataconnect() {
		String datapath = this.databaseLocation;
		try {
			connection = DriverManager.getConnection(startData);
			JOptionPane.showMessageDialog(null, "Connection Successful");
			
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIModel x = new GUIModel();
		JOptionPane.showMessageDialog(null, x.databaseLocation);
		x.dataconnect();

	}
}

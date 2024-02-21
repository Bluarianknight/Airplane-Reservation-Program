package Main;
import java.sql.*;

import javax.swing.JOptionPane;

public class databaseConnection {
	String databaseLocation;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public databaseConnection() {
		this.databaseLocation = "CHANGE!";
	}
	
	public databaseConnection(String location) {
		this.databaseLocation = location;
	}
	
	public void setDatabase(String data) {
		this.databaseLocation = data;
	}
	public void connectDatabase() {
		try {
			connection = DriverManager.getConnection("jdbc:ucanaccess://" + this.databaseLocation);
		} catch(SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex.getMessage());
		}
	}
	

}

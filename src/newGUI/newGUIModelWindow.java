package newGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



public class newGUIModelWindow {
	String authDataLocation = "C://Database//authorization.accdb";
	String mainDataLocation = "C://Database//mainDatabase.accdb";
	String connectString = "jdbc:ucanaccess://";
	String authData = connectString + authDataLocation;
	String mainData = connectString + mainDataLocation;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	
	public void Connect(String database) {
		if (database == "auth") {
			try {
				
				connection = DriverManager.getConnection(authData);
				// JOptionPane.showMessageDialog(null, "Connection Successful");
				
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex);
			}
		} else  {
			try {
				System.out.print(mainData);
				connection = DriverManager.getConnection(mainData);
				// JOptionPane.showMessageDialog(null, "Connection Successful");
				
			} catch (SQLException sqlex) {
				JOptionPane.showMessageDialog(null, sqlex);
			}
		}
		
	}
	
	public flightList getFlightData() {
		flightList flight = new flightList();
		
		try {
			Connect("");
			String strString = "SELECT * FROM Flights";
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = statement.executeQuery(strString);
			result.beforeFirst();
			System.out.println("");
			while (result.next()) {
				flight.ID.add(result.getInt(1));
				flight.model.add(result.getString(2));
				flight.ArrivalAirport.add(result.getString(3));
				flight.DepartureAirport.add(result.getString(4));
				flight.TimeLeft.add(result.getString(4));
				flight.TimeArrived.add(result.getString(5));
				System.out.print(result.getString(2) + " ");
				System.out.println(result.getString(3) + " ");
			}
			
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
		}
		return flight;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		newGUIModelWindow x = new newGUIModelWindow();
		x.getData();
		

	}
	
}

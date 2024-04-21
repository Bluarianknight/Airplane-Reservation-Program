package newGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
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
	
	public DefaultListModel<flightList> getFlightData() {
		
		DefaultListModel<flightList> listModel = new DefaultListModel<flightList>();
		try {
			Connect("");
			String strString = "SELECT * FROM Flights";
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = statement.executeQuery(strString);
			result.beforeFirst();
			
			while (result.next()) {
				listModel.addElement(new flightList(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getTime(5), result.getTime(6), result.getBigDecimal(7)));
			}
			
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
		}
		flightList test = listModel.elementAt(0);
		System.out.println(test.ArrivalAirport);
		return listModel;
	}
	
public DefaultListModel<flightList> getFilteredFlightData(String firstSearch, String secondSearch) {
		
		DefaultListModel<flightList> listModel = new DefaultListModel<flightList>();
		try {
			Connect("");
			try {
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				result = statement.executeQuery("SELECT * FROM Flights WHERE " + firstSearch + " = " + "'" + secondSearch + "'");
				result.beforeFirst();
			} catch (ArrayIndexOutOfBoundsException exception ) {
				flightList test = listModel.elementAt(0);
				System.out.println(test.ArrivalAirport);
				return listModel;
			}

			while (result.next()) {
				listModel.addElement(new flightList(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getTime(5), result.getTime(6), result.getBigDecimal(7)));
			}
			
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
			listModel.addElement(new flightList());
			return listModel;
		}
		return listModel;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		newGUIModelWindow x = new newGUIModelWindow();
		x.getFlightData();
		

	}
	
}

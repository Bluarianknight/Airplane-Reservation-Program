package newGUI;


/*
 * This class contains the 'model' for the main window of the airline reservation program - it handles the calculating and connections to the databases.
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Customer.Customer;

public class newGUIModelWindow {
	// The strings for the database locations - it assumes the databases will be in the C:/Database folder. If not, it won't work.
    String authDataLocation = "C://Database//authorization.accdb";
    String mainDataLocation = "C://Database//mainDatabase.accdb";
    String connectString = "jdbc:ucanaccess://";
    String authData = connectString + authDataLocation;
    String mainData = connectString + mainDataLocation;
    Connection connection = null;

    public void Connect(String database) {
        String url = database.equals("auth") ? authData : mainData; // Code that checks if the URL is the authorization database or the main database set above.
        
        try {
            if (connection != null) {
                connection.close(); // Close existing connection if open
            }
            connection = DriverManager.getConnection(url);
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + sqlex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean authenticateUser(String username, String password) {
        Connect("auth");
        try (PreparedStatement ps = connection.prepareStatement("SELECT password FROM authorization WHERE username = ?")) { // Corrected table name
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return password.equals(storedPassword);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (connection != null) {
                    connection.close(); // Ensure the connection is closed
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return false;
    }

    public DefaultListModel<flightList> getFlightData() {
        DefaultListModel<flightList> listModel = new DefaultListModel<>();
        Connect("main");
        try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet result = statement.executeQuery("SELECT * FROM Flights")) {
            while (result.next()) {
                listModel.addElement(new flightList(
                    result.getInt("ID"),  
                    result.getString("Model"),  
                    result.getString("ArrivalAirport"),  
                    result.getString("DepartureAirport"),  
                    result.getTime("TimeLeft"), 
                    result.getTime("TimeArrived"), 
                    result.getBigDecimal("Cost")  
                ));
            }
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, sqlex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return listModel;
    }
    
public DefaultListModel<flightList> getFilteredFlightData(String firstSearch, String secondSearch) {
		
		DefaultListModel<flightList> listModel = new DefaultListModel<flightList>();
		try {
			Connect("");
			Statement statement;
			ResultSet result;
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

    public boolean addUser(String username, String password, String email) {
        Connect("auth");
        // Use try-with-resources to ensure the PreparedStatement is closed after the operation
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO authorization (username, password, email) VALUES (?, ?, ?)")) {
            ps.setString(1, username);
            ps.setString(2, password); // This should be a hashed password in a real application
            ps.setString(3, email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding user: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public boolean deleteUser(String username) {
        Connect("auth");
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM authorization WHERE username = ?")) {
            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting user: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

	public Customer getCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	public boolean verifyUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}

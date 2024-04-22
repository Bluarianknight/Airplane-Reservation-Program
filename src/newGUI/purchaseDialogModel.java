package newGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class purchaseDialogModel {
    
    // Database file locations and connection strings
    String authDataLocation = "C://Database//authorization.accdb";
    String mainDataLocation = "C://Database//mainDatabase.accdb";
    String connectString = "jdbc:ucanaccess://";
    String authData = connectString + authDataLocation;
    String mainData = connectString + mainDataLocation;
    
    // Database connection variables
    Connection connection = null;
    Statement statement = null;
    ResultSet result = null;

    // Method to establish a database connection
    public void Connect(String database) {
        if (database.equals("auth")) {
            try {
                connection = DriverManager.getConnection(authData);
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(null, sqlex);
            }
        } else {
            try {
                connection = DriverManager.getConnection(mainData);
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(null, sqlex);
            }
        }
    }
    
    // Method to set reservation data in the database
    public void setReservationData(reservation newReservation) {
        reservation res = newReservation;
        try {
            Connect(""); // Establish database connection
            PreparedStatement prep = connection.prepareStatement("INSERT INTO purchase (firstname, lastname, ccnumber, ccexpire, ccsecurity, tickets) VALUES (?, ?, ?, ?, ?, ?)");
            prep.setString(1, res.firstName);
            prep.setString(2, res.lastName);
            prep.setString(3, res.cc);
            prep.setString(4, res.ccExp);
            prep.setInt(5, res.ccSecurity);
            prep.setInt(6, res.tickets);
            prep.execute();
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, sqlex);
        }
    }
    
    // Method to retrieve reservation data from the database
    public DefaultListModel<reservation> getReservationData() {
        DefaultListModel<reservation> listModel = new DefaultListModel<reservation>();
        try {
            Connect(""); // Establish database connection
            String strString = "SELECT * FROM Flights";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = statement.executeQuery(strString);
            result.beforeFirst();
            while (result.next()) {
                listModel.addElement(new reservation(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getInt(6)));
            }
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, sqlex);
        }
        reservation test = listModel.elementAt(0); // Retrieve first element to test
        System.out.println(test.firstName); // Print test result
        return listModel;
    }
}

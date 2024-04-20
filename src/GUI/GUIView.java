package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;


public class GUIView {
	String authDataLocation = "C://Database//authorization.accdb";
	String mainDataLocation = "C://Database//mainDatabase.accdb";
    private static final String DB_URL = "jdbc:ucanaccess://C://Database//authorization.accdb"; // Update this path for the login database
    private static final String MAIN_DB_URL = "jdbc:ucanaccess://C://Database//mainDatabase.accdb";
    private static final String AIRPORT_DB_URL = "jdbc:ucanaccess://C:\\Users\\Pkalp\\OneDrive\\Desktop\\Airplane-Reservation-Program\\Databases\\Airport.accdb";


    // Method to establish connection to the Airport database
    private static Connection getAirportDBConnection() throws SQLException {
        return DriverManager.getConnection(AIRPORT_DB_URL);
    }

    public static Vector<String> getAirportNames() {
        Vector<String> airportNames = new Vector<>();
        String query = "SELECT Name FROM Airport "; // Make sure the table name is also correct

        try (Connection connection = getAirportDBConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // Ensure that the column name here matches the name used in the SELECT statement
                airportNames.add(resultSet.getString("Name")); // This needs to match the exact column name from the query
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching airport names: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return airportNames;
    }


    public static boolean authenticateUser(String username, char[] password) {
        try (Connection connection = DriverManager.getConnection(DB_URL.replace("\"", "")); // Fixed the DB_URL
             PreparedStatement ps = connection.prepareStatement("SELECT password FROM authorization WHERE username = ?")) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return new String(password).equals(storedPassword); // Convert char[] to String for comparison
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // User not found or password does not match
    }

    private static void displayPassengerSelection(JFrame frame) {
        JDialog dialog = new JDialog(frame, "Select Passengers", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margins around components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;

        dialog.add(new JLabel("Passengers"), gbc);

        gbc.gridy++;
        dialog.add(new JLabel("Adults (Age 12+):"), gbc);
        JSpinner adultsSpinner = new JSpinner(new SpinnerNumberModel(1, 0, 9, 1));
        gbc.gridx++;
        dialog.add(adultsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        dialog.add(new JLabel("Children (Age 2–11):"), gbc);
        JSpinner childrenSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9, 1));
        gbc.gridx++;
        dialog.add(childrenSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        dialog.add(new JLabel("Infants (Under 2 years):"), gbc);
        JSpinner infantsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9, 1));
        gbc.gridx++;
        dialog.add(infantsSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy++;
        dialog.add(new JLabel("Please note: You can book a maximum of 9 passengers per booking."), gbc);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose());
        gbc.gridwidth = 1;
        gbc.gridy++;
        dialog.add(okButton, gbc);

        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private static void createMainFrame() {
        JFrame frame = new JFrame("Flight Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Initialize airportNames vector with database values
        Vector<String> airportNames = getAirportNames();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Consistent padding
        gbc.weightx = 0.5;
        gbc.weighty = 0; // No vertical stretching

        // Departure Airport
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(new JLabel("Departure Airport"), gbc);

        JComboBox<String> cmbDepartureAirport = new JComboBox<>(new DefaultComboBoxModel<>(airportNames));
        JTextField txtDepartureAirport = (JTextField) cmbDepartureAirport.getEditor().getEditorComponent();
        setupAutoComplete(txtDepartureAirport, cmbDepartureAirport);
        gbc.gridx = 1;
        frame.add(cmbDepartureAirport, gbc);

        // Arrival Airport
        gbc.gridx = 2;
        frame.add(new JLabel("Arrival Airport"), gbc);

        JComboBox<String> cmbArrivalAirport = new JComboBox<>(new DefaultComboBoxModel<>(airportNames));
        JTextField txtArrivalAirport = (JTextField) cmbArrivalAirport.getEditor().getEditorComponent();
        setupAutoComplete(txtArrivalAirport, cmbArrivalAirport);
        gbc.gridx = 3;
        frame.add(cmbArrivalAirport, gbc);

        // Departing and Returning Dates
        MaskFormatter dateFormatter;
        try {
            dateFormatter = new MaskFormatter("##/##/####");
            dateFormatter.setPlaceholderCharacter('_');
            dateFormatter.setValidCharacters("0123456789/");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(frame, "Error creating date formatter: " + e.getMessage(), "Formatter Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        gbc.gridx = 4;
        frame.add(new JLabel("Departing Date"), gbc);

        gbc.gridx = 5;
        JFormattedTextField departingDateField = new JFormattedTextField(dateFormatter);
        frame.add(departingDateField, gbc);

        gbc.gridx = 6;
        frame.add(new JLabel("Returning Date"), gbc);

        gbc.gridx = 7;
        JFormattedTextField returningDateField = new JFormattedTextField(dateFormatter);
        frame.add(returningDateField, gbc);

        // Passengers
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JLabel("Passengers:"), gbc);

        // Choose Passengers Button
        gbc.gridx = 1;
        JButton passengersButton = new JButton("Choose Passengers");
        passengersButton.addActionListener(e -> displayPassengerSelection(frame));
        frame.add(passengersButton, gbc);

        // Class
        gbc.gridx = 2;
        frame.add(new JLabel("Class"), gbc);

        gbc.gridx = 3;
        frame.add(new JComboBox<>(new String[]{"Economy Class", "Business Class", "First Class"}), gbc);

        // Initialize the adultsSpinner
        gbc.gridx = 0;
        gbc.gridy = 1; // Adjust the row as per your design
        gbc.gridwidth = 1;
        JSpinner adultsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
        frame.add(adultsSpinner, gbc);


        // Search Button setup
        JButton searchButton = new JButton("Search flights");
        searchButton.setBackground(Color.RED);
        searchButton.setForeground(Color.WHITE);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        gbc.gridx = 6; // Adjust gridx, gridy as needed
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Spans across two columns
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validation to check if all information is present
                if (((SpinnerNumberModel) adultsSpinner.getModel()).getNumber().intValue() <= 0) {
                    JOptionPane.showMessageDialog(frame, "Please select at least one adult.");
                } else {
                    // Proceed with flight search
                    // This is where you'd implement the actual search logic
                    JOptionPane.showMessageDialog(frame, "Searching for flights...");
                }
            }
        });
        frame.add(searchButton, gbc);
        frame.add(searchButton, gbc);
        // Set the frame to be visible
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void setupAutoComplete(JTextField txtDepartureAirport,
                                          JComboBox<java.lang.String> cmbDepartureAirport) {
        // TODO Auto-generated method stub

    }

    // Method to filter ComboBox items based on text field input
    private static void comboFilter(String enteredText, JComboBox<String> comboBox) {
        if (enteredText.isEmpty()) {
            comboBox.setModel(new DefaultComboBoxModel<>(getAirportNames()));
            comboBox.hidePopup();
        } else {
            Vector<String> filteredItems = new Vector<>();
            for (String item : getAirportNames()) {
                if (item.toLowerCase().startsWith(enteredText.toLowerCase())) {
                    filteredItems.add(item);
                }
            }
            if (!filteredItems.isEmpty()) {
                comboBox.setModel(new DefaultComboBoxModel<>(filteredItems));
                comboBox.setSelectedItem(enteredText);
                comboBox.showPopup();
            } else {
                comboBox.hidePopup();
            }
        }
    }

    // Method to filter combo box items based on text field input
    private static void comboFilter(String enteredText, Vector<String> items) {

        System.out.println("User typed: " + enteredText);
        // In a real application, you would update a pop-up list or similar widget
    }


    @SuppressWarnings("unused")
    private static void createNavigationWindow() {
        JFrame navigationFrame = new JFrame("Customer Information");
        navigationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        navigationFrame.setLayout(new FlowLayout());

        JButton viewCustomersButton = new JButton("View Customers");
        viewCustomersButton.addActionListener(e -> displayCustomerData(navigationFrame));
        navigationFrame.add(viewCustomersButton);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(e -> displayAddUserForm());
        navigationFrame.add(addUserButton);

        // Corrected 'Login' button to go to the main interface
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            navigationFrame.dispose(); // Dispose of the navigation frame
            goToMainInterface(); // Call the method that creates and shows the main GUI
        });
        navigationFrame.add(loginButton); // Add the login button to the navigation frame

        // Pack and show the frame
        navigationFrame.pack();
        navigationFrame.setLocationRelativeTo(null);
        navigationFrame.setVisible(true);
    }


    // Display customer data
    private static void displayCustomerData(JFrame parentFrame) {
        try (Connection connection = DriverManager.getConnection(MAIN_DB_URL)) {
            PreparedStatement ps = connection.prepareStatement("SELECT ID, fName, lName FROM Customer");
            ResultSet rs = ps.executeQuery();
            //ResultSet rs1 = ps.executeQuery();

            // Create a dialog to display customer names
            JDialog dialog = new JDialog(parentFrame, "Customers", true);
            dialog.setLayout((LayoutManager) new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

            while (rs.next()) {
                String customerInfo = rs.getString("ID") + " - " + rs.getString("fName") + " " + rs.getString("lName");
                String customerId = rs.getString("ID"); // Capture the ID for use in the lambda

                JButton customerButton = new JButton(customerInfo);
                customerButton.addActionListener(e -> {
                    dialog.dispose();
                    displayUpdateCustomerForm(customerId); // Use captured ID directly
                });
                dialog.add(customerButton);
            }

            dialog.pack();
            dialog.setLocationRelativeTo(parentFrame);
            dialog.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching customer data: " + ex.getMessage());
        }
    }

    // Display form for updating customer data
    private static void displayUpdateCustomerForm(String customerId) {
        JFrame frame = new JFrame("Update Customer");
        frame.setLayout(new GridLayout(0, 2));

        JTextField fNameField = new JTextField(20);
        JTextField lNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);

        frame.add(new JLabel("First Name:"));
        frame.add(fNameField);
        frame.add(new JLabel("Last Name:"));
        frame.add(lNameField);
        frame.add(new JLabel("Email:"));
        frame.add(emailField);

        // Fetch current customer data
        try (Connection connection = DriverManager.getConnection(MAIN_DB_URL)) {
            PreparedStatement ps = connection.prepareStatement("SELECT fName, lName, email FROM Customer WHERE ID = ?");
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                fNameField.setText(rs.getString("fName"));
                lNameField.setText(rs.getString("lName"));
                emailField.setText(rs.getString("email"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching customer data: " + ex.getMessage());
            return; // Exit the method if there's an error
        }

        // Update button
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            try (Connection connection = DriverManager.getConnection(MAIN_DB_URL)) {
                PreparedStatement ps = connection.prepareStatement("UPDATE Customer SET fName = ?, lName = ?, email = ? WHERE ID = ?");
                ps.setString(1, fNameField.getText());
                ps.setString(2, lNameField.getText());
                ps.setString(3, emailField.getText());
                ps.setString(4, customerId);
                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(frame, "Customer updated successfully.");

                    //SwingUtilities.invokeLater(() -> createMainFrame()); // Call createMainFrame directly
                }

                else {
                    JOptionPane.showMessageDialog(frame, "Error updating customer.", "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Error updating customer data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(updateButton);

        // New code for the Delete button
        JButton deleteButton = new JButton("Delete Account");
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete your account?", "Delete Account", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection connection = DriverManager.getConnection(MAIN_DB_URL)) {
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM Customer WHERE ID = ?");
                    ps.setString(1, customerId);
                    int affectedRows = ps.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(frame, "Account deleted successfully.");
                        frame.dispose();
                        // SwingUtilities.invokeLater(() -> createLoginScreen());
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error deleting account.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error deleting account: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(deleteButton);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void displayAddUserForm() {
        JFrame addUserFrame = new JFrame("Add New Customer");
        addUserFrame.setLayout(new GridLayout(0, 2));

        JTextField fNameField = new JTextField(20);
        JTextField lNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);

        addUserFrame.add(new JLabel("First Name:"));
        addUserFrame.add(fNameField);
        addUserFrame.add(new JLabel("Last Name:"));
        addUserFrame.add(lNameField);
        addUserFrame.add(new JLabel("Email:"));
        addUserFrame.add(emailField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try (Connection connection = DriverManager.getConnection(MAIN_DB_URL)) {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer (fName, lName, email) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, fNameField.getText());
                ps.setString(2, lNameField.getText());
                ps.setString(3, emailField.getText());
                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(addUserFrame, "New customer added successfully.");
                    addUserFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(addUserFrame, "Could not add the customer.", "Add Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(addUserFrame, "Error adding new customer: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addUserFrame.add(addButton);

        addUserFrame.pack();
        addUserFrame.setLocationRelativeTo(null);
        addUserFrame.setVisible(true);
    }


    private static void createLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginFrame.add(new JLabel("User Name:"), gbc);

        gbc.gridx = 1;
        JTextField userNameField = new JTextField(15);
        loginFrame.add(userNameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        loginFrame.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        loginFrame.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = userNameField.getText();
            char[] password = passwordField.getPassword();

            if (GUIView.authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                loginFrame.dispose(); // Close the login window
                createNavigationWindow(); // Redirect to the navigation window
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginFrame.add(loginButton, gbc);


        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private static void goToMainInterface() {

        SwingUtilities.invokeLater(() -> createMainFrame());
    }

    private static char[] String(char[] password) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createLoginScreen());
    }
}
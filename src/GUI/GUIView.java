package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.BoxLayout;
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

public class GUIView {

    private static final String DB_URL = "jdbc:ucanaccess://Databases/authorization.accdb"; // Update this path for the login database
    private static final String MAIN_DB_URL = "jdbc:ucanaccess://Databases/mainDatabase.accdb";
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
        frame.setPreferredSize(new Dimension(1100, 200)); // Set the size to match your layout preference

        // Use GridBagLayout for flexibility
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Departure Airport
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Top, left, bottom, right padding
        frame.add(new JLabel("Departure Airport"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Text field spans two columns
        frame.add(new JTextField(""), gbc);

        // Arrival Airport
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Reset to default
        frame.add(new JLabel("Arrival Airport"), gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Text field spans two columns
        frame.add(new JTextField(), gbc);

        // Departing and Returning Dates
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Reset to default
        frame.add(new JLabel("Departing"), gbc);

        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(new JFormattedTextField(new SimpleDateFormat("dd MMM yyyy")), gbc);

        gbc.gridx = 11;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(new JLabel("Returning"), gbc);

        gbc.gridx = 13;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(new JFormattedTextField(new SimpleDateFormat("dd MMM yyyy")), gbc);

        // Passengers
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Span one column
        frame.add(new JLabel("Passengers:"), gbc);

        gbc.gridx = 1; // Move to the next cell on the right for the button
        gbc.gridy = 1; // Keep it on the same row
        JButton passengersButton = new JButton("Choose Passengers");
        passengersButton.addActionListener(e -> displayPassengerSelection(frame));
        frame.add(passengersButton, gbc);

        // Class
        gbc.gridx = 3;
        gbc.gridy = 1;
        frame.add(new JLabel("Class"), gbc);

        String[] classOptions = {"Economy Class", "Business Class", "First Class"};
        gbc.gridx = 4;
        gbc.gridy = 1;
        frame.add(new JComboBox<>(classOptions), gbc);

        // Search Button
        JButton searchButton = new JButton("Search flights");
        searchButton.setBackground(Color.RED);
        searchButton.setForeground(Color.WHITE);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);

        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridwidth = 3; // Button spans three columns
        gbc.insets = new Insets(10, 5, 10, 10); // Top, left, bottom, right padding
        frame.add(searchButton, gbc);

        // Set the frame to be visible
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
        // You may want to pass user information to the main frame
        // if it's required for the user experience.
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
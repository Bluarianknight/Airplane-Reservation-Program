package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    private static final String DB_URL = "jdbc:ucanaccess:\"C:\\Users\\Pkalp\\OneDrive\\Desktop\\Airplane-Reservation-Program\\Databases\\authorization.accdb\""; // Update this path


    public static boolean authenticateUser(String username, char[] password) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = connection.prepareStatement("SELECT password FROM authorization WHERE username = ?")) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedPasswordHash = rs.getString("password");
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hashedPassword = digest.digest(new String(password).getBytes(StandardCharsets.UTF_8));
                    return Arrays.equals(hashedPassword, storedPasswordHash.getBytes(StandardCharsets.UTF_8));
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return false; // If the algorithm isn't found, authentication cannot proceed
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        gbc.insets = new Insets(10, 10, 10, 5); // Top, left, bottom, right padding
        frame.add(new JLabel("Departure Airport"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Text field spans two columns
        frame.add(new JTextField(""), gbc);

        // Arrival Airport
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Reset to default
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
        frame.add(new JLabel("Returning"), gbc);

        gbc.gridx = 13;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
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

            if (GUIView.authenticateUser(username, String(password))) {
                JOptionPane.showMessageDialog(null, "Login Successful!");
                loginFrame.dispose(); // Close the login window
                createMainFrame(); // Open the main GUI
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        loginFrame.add(loginButton, gbc);


        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }


    private static char[] String(char[] password) {
        // TODO Auto-generated method stub
        return null;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createLoginScreen());
    }
}


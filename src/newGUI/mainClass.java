package newGUI;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class mainClass {

    // Entry point of the application
    public static void main(String[] args) {
        // Instantiate the model that will handle data processing and business logic
        newGUIModelWindow model = new newGUIModelWindow();

        // Create text fields for the username and password input
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        // Create an array of objects representing the components to include in the dialog
        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };

        // Show a confirm dialog to accept the username and password
        int option = JOptionPane.showConfirmDialog(null, message, "Login", 
                       JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // If the user clicked OK, process the login
        if (option == JOptionPane.OK_OPTION) {
            // Extract the username and password from the text fields
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Authenticate the user
            if (model.authenticateUser(username, password)) {
                // If authentication is successful, show the main application window
                EventQueue.invokeLater(() -> {
                    newGUIViewWindow window = new newGUIViewWindow();
                    window.setVisible(true); // Set the main window to be visible
                });
            } else {
                // If authentication fails, show an error message
                JOptionPane.showMessageDialog(null, "Invalid username or password", 
                                              "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // If the user clicked cancel or closed the dialog, show a cancellation message and exit
            JOptionPane.showMessageDialog(null, "Login cancelled", "Login Cancelled", 
                                          JOptionPane.WARNING_MESSAGE);
            System.exit(0); // Terminate the program
        }
    }
}

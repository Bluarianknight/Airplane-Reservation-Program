package newGUI;

import java.awt.EventQueue;

import javax.swing.*;

import Customer.Customer;

public class LoginView {

    // Shows a message dialog with the given message
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    // Shows a confirm dialog with the given message and title, returning the user's choice
    public static int confirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }

    // Prompts the user for new user data (username, password, email) for registration
    public static String[] getNewUserData() {
        // Text fields for user input
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField emailField = new JTextField(20);
        // Dialog message components
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField,
                "Email:", emailField
        };

        // Option types for the dialog
        Object[] options = {"OK", "Cancel"};
        // Shows the dialog
        int option = JOptionPane.showOptionDialog(null, message, "Register New User", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // If the OK option was chosen, return the entered data
        if (option == 0) {
            String username = usernameField.getText();
            // For security, password should be hashed in a real application
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            return new String[]{username, password, email};
        }
        // If the cancel option was chosen, return null
        return null;
    }

    // Prompts the user to enter a username for verification
    public static String requestUsername() {
        return JOptionPane.showInputDialog("Enter your username for verification:");
    }

    // Displays customer information in a message dialog
    public static void displayCustomerInfo(Customer customer) {
        JOptionPane.showMessageDialog(null, "Customer Info:\n" +
                "First Name: " + customer.getName() +
                "\nLast Name: " + customer.getName() +  // Might be an error: should be getLastname if it exists
                "\nEmail: " + customer.getEmail());
    }

    // Allows editing of customer information with input fields pre-populated with current data
    public static boolean editCustomerInfo(Customer customer) {
        // Text fields for user input
        JTextField usernameField = new JTextField(customer.getName());
        JPasswordField passwordField = new JPasswordField();
        passwordField.setText(customer.getPassword()); // Assumes getPassword() returns a String, not secure
        JTextField emailField = new JTextField(customer.getEmail());

        // Dialog message components
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField,
                "Email:", emailField
        };

        // Shows the confirm dialog for editing user info
        int option = JOptionPane.showConfirmDialog(null, message, "Edit User", JOptionPane.OK_CANCEL_OPTION);

        // If the OK option was chosen, update the customer data
        if (option == JOptionPane.OK_OPTION) {
            customer.setName(usernameField.getText());
            customer.setPassword(new String(passwordField.getPassword())); // Again, password handling should be secure
            customer.setEmail(emailField.getText());
            return true;
        }
        // If the cancel option was chosen, return false
        return false;
    }

    // Confirm dialog for account deletion
    public static boolean confirmDeletion() {
        return JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    // Shows a dialog with options post-login: add user, edit user, or log in
    public static void postLoginOptions(LoginController controller) {
        Object[] options = {"Add User", "Edit User", "Log In"};
        int choice = JOptionPane.showOptionDialog(null, "Select an option", "Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // Handle the user choice
        switch (choice) {
            case 0: // Add User
                String[] userData = getNewUserData();
                if (userData != null) {
                    controller.addUser1(userData[0], userData[1], userData[2]);
                }
                break;
            case 1: // Edit User
                String usernameToEdit = requestUsername();
                Customer customer = controller.getModel().getCustomerByUsername1(usernameToEdit);
                if (customer != null) {
                    if (editCustomerInfo(customer)) {
                        controller.updateCustomer(customer);
                        JOptionPane.showMessageDialog(null, "User information updated successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "User not found.");
                }
                break;
            case 2: // Log In
                // Show the main GUI if user clicks "Log In"
                EventQueue.invokeLater(() -> {
                    newGUIViewWindow.getInstance().showWindow();
                });
                break;
            default:
                // Handle other cases or ignore
        }
    }

    // Shows the login request dialog
    public static boolean requestLogin(newGUIModelWindow model, LoginController controller) {
        // Text fields for user input
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        // Panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        // Add the Account button
        JButton accountButton = new JButton("Account");
        panel.add(accountButton);

        // Attach ActionListener to Account button
        accountButton.addActionListener(e -> postLoginOptions(controller));

        // Show the option dialog with OK and Cancel buttons
        Object[] options = {"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "Login", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        // If the OK button was clicked, attempt to authenticate the user
        if (option == 0) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            return controller.authenticateUser(username, password);
        }
        // If the cancel button was clicked or the dialog was closed, return false
        return false;
    }
}

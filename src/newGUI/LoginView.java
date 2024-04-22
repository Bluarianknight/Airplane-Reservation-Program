package newGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Customer.Customer;

public class LoginView {
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static int confirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }

    public static String[] getNewUserData() {
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField emailField = new JTextField(20);
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField,
                "Email:", emailField
        };

        Object[] options = {"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, message, "Register New User", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (option == 0) { // OK option
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword()); // Ideally, this should be hashed
            String email = emailField.getText();
            return new String[]{username, password, email};
        }
        return null;
    }

    public static String requestUsername() {
        return JOptionPane.showInputDialog("Enter your username for verification:");
    }


public static void displayCustomerInfo(Customer customer) {
    // Assuming Customer is a class representing customer data
    JOptionPane.showMessageDialog(null, "Customer Info:\n" +
            "First Name: " + customer.getName() +
            "\nLast Name: " + customer.getName() +
            "\nEmail: " + customer.getEmail());
}

public static boolean editCustomerInfo(Customer customer) {
    JTextField fNameField = new JTextField(customer.getName());
    JTextField lNameField = new JTextField(customer.getName());
    JTextField emailField = new JTextField(customer.getEmail());
    Object[] message = {
            "First Name:", fNameField,
            "Last Name:", lNameField,
            "Email:", emailField
    };
    int option = JOptionPane.showConfirmDialog(null, message, "Edit Customer", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        // Update customer data
        customer.setName(fNameField.getText());
        customer.setName(lNameField.getText());
        customer.setEmail(emailField.getText());
        return true;
    }
    return false;
}

public static boolean confirmDeletion() {
    return JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?", 
            "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
}

public static void postLoginOptions(LoginController controller) {
    Object[] options = {"Add User", "Edit User", "Delete User"};
    int choice = JOptionPane.showOptionDialog(null, "Select an option", "Options",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

    switch (choice) {
        case 0: // Add User
            String[] userData = getNewUserData();
            if (userData != null) {
                controller.addUser1(userData[0], userData[1], userData[2]);
            }
            break;
        case 1: // Edit User
            // The edit user functionality will depend on your implementation
            break;
        case 2: // Delete User
            String usernameToDelete = requestUsername();
            if (confirmDeletion()) {
                controller.deleteUser(usernameToDelete);
            }
            break;
        default:
            // Handle other cases or ignore
    }
}

public static boolean requestLogin(newGUIModelWindow model, LoginController controller) {
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    Object[] loginFields = {
            "Username:", usernameField,
            "Password:", passwordField
    };

    Object[] options = {"OK", "Cancel"};
    int option = JOptionPane.showOptionDialog(null, loginFields, "Login", JOptionPane.DEFAULT_OPTION,
            JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    if (option == 0) { // OK option
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        return controller.authenticateUser(username, password);
    }
    return false;
}
}


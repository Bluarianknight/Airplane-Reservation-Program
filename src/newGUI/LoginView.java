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
        JTextField fNameField = new JTextField(20);
        JTextField lNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        Object[] message = {
            "First Name:", fNameField,
            "Last Name:", lNameField,
            "Email:", emailField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add New Customer", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            return new String[] {fNameField.getText(), lNameField.getText(), emailField.getText()};
        }
        return null;
    }

public static String requestUsername() {
    String username = JOptionPane.showInputDialog("Enter your username for verification:");
    return username;
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
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[0]);

    switch (choice) {
        case 0: // Add User
            String[] userData = getNewUserData();
            if (userData != null) {
                controller.addUser(userData[0], userData[1], userData[2]);
            }
            break;
        case 1: // Edit User
            Customer customer = new Customer(); // You'd actually get this from the controller
            if (editCustomerInfo(customer)) {
                // Assuming the controller has an 'updateCustomer' method
                controller.updateCustomer(customer);
            }
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

public static boolean requestLogin(newGUIModelWindow model) {
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    Object[] loginFields = {
        "Username:", usernameField,
        "Password:", passwordField
    };

    int option = JOptionPane.showConfirmDialog(null, loginFields, "Login", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Use the model to authenticate the user
        return model.authenticateUser(username, password);
    }
    return false;
}


public static void mainLoginScreen(LoginController controller) {
    JFrame frame = new JFrame("Login");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create fields for username and password
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);

    // Create buttons for login, add user, edit user, and delete user
    JButton loginButton = new JButton("Login");
    JButton newUserButton = new JButton("Add New User");
    JButton editUserButton = new JButton("Edit User");
    JButton deleteUserButton = new JButton("Delete User");

    // Add action listeners to the buttons
    loginButton.addActionListener(e -> {
        controller.authenticateUser(usernameField.getText(), new String(passwordField.getPassword()));
    });

    newUserButton.addActionListener(e -> {
        controller.addUser();
    });

    editUserButton.addActionListener(e -> {
        // Implement edit user logic
    });

    deleteUserButton.addActionListener(e -> {
        String username = requestUsername();
        if (username != null && !username.trim().isEmpty()) {
            controller.deleteUser(username);
        }
    });

    // Layout the components vertically
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(new JLabel("Username:"));
    panel.add(usernameField);
    panel.add(new JLabel("Password:"));
    panel.add(passwordField);
    panel.add(loginButton);
    panel.add(newUserButton);
    panel.add(editUserButton);
    panel.add(deleteUserButton);

    // Add the panel to the frame
    frame.add(panel);
    
    // Adjust frame size and make it visible
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
}
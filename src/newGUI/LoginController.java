package newGUI;

import javax.swing.JOptionPane;
import Customer.Customer;

public class LoginController {
    // Instance variable for the model associated with this controller.
    private newGUIModelWindow model;

    // Static variable for the singleton instance of the controller.
    private static LoginController instance;

    // Static method to get the singleton instance of the controller.
    // If the instance doesn't exist, it creates a new one.
    public static LoginController getInstance(newGUIModelWindow model) {
        if (instance == null) {
            instance = new LoginController(model);
        }
        return instance;
    }

    // Constructor that takes a model as an argument and sets it to the instance variable.
    public LoginController(newGUIModelWindow model) {
        this.model = model;
    }

    // Method that attempts to authenticate a user with the given username and password.
    // It returns true if the authentication is successful, false otherwise.
    public boolean authenticateUser(String username, String password) {
        if (model.authenticateUser(username, password)) {
            LoginView.showMessage("Login Successful!");
            LoginView.postLoginOptions(this);
            return true;
        } else {
            LoginView.showMessage("Invalid username or password");
            return false;
        }
    }

    // Method for adding a user with the specified username, password, and email.
    // Displays a message whether the addition is successful or not.
    public void addUser1(String username, String password, String email) {
        if (model.addUser(username, password, email)) {
            LoginView.showMessage("New customer added successfully.");
            newGUIViewWindow.getInstance().showWindow(); // Make window visible
        } else {
            LoginView.showMessage("Failed to add new customer.");
        }
    }

    // Method for deleting a user based on customerId.
    // It confirms the deletion with the user before proceeding.
    public void deleteUser(String customerId) {
        if (LoginView.confirmDialog("Are you sure you want to delete your account?", "Delete Account") == JOptionPane.YES_OPTION) {
            if (model.deleteUser(customerId)) {
                LoginView.showMessage("Account deleted successfully.");
            } else {
                LoginView.showMessage("Failed to delete account.");
            }
        }
    }

    // Method to update the information of an existing customer.
    // The customer data is passed as an argument to the model's updateCustomer1 method.
    public void updateCustomer(Customer customer) {
        model.updateCustomer1(customer);
    }


    // Method placeholder for adding a user. Currently not implemented.
    // A proper implementation is needed for adding users with first and last names.
    public void addUser(String firstName, String lastName, String email) {
        // TODO: Implement add user logic
    }

    // Setter method for the model.
    // It allows changing the model associated with this controller.

/*
    public void addUser(String firstName, String lastName, String email) {
        // TODO: Implement add user logic
    }
*/

    public void setModel(newGUIModelWindow model2) {
        this.model = model2;
    }

    // Getter method for the model.
    // It returns the current model associated with this controller.
    public newGUIModelWindow getModel() {
        return model;
    }
}

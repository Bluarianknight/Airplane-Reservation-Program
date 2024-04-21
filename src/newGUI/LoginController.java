package newGUI;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Customer.Customer;

public class LoginController {
    private newGUIModelWindow model;

    public LoginController(newGUIModelWindow model) {
        this.model = model;
    }

    public boolean authenticateUser(String username, String password) {
        if (model.authenticateUser(username, password)) {
            LoginView.showMessage("Login Successful!");
            LoginView.postLoginOptions(this);
        } else {
            LoginView.showMessage("Invalid username or password");
        }
        return false;
    }

    public void addUser() {
        String[] userData = LoginView.getNewUserData();
        if (userData != null && model.addUser(userData[0], userData[1], userData[2])) {
            LoginView.showMessage("New customer added successfully.");
        } else {
            LoginView.showMessage("Failed to add new customer.");
        }
    }

    public void deleteUser(String customerId) {
        if (LoginView.confirmDialog("Are you sure you want to delete your account?", "Delete Account") == JOptionPane.YES_OPTION) {
            if (model.deleteUser(customerId)) {
                LoginView.showMessage("Account deleted successfully.");
            } else {
                LoginView.showMessage("Failed to delete account.");
            }
        }
    }

    public void updateCustomer(Customer customer) {
        // TODO: Implement update logic
    }

    public void addUser(String firstName, String lastName, String email) {
        // TODO: Implement add user logic
    }
}

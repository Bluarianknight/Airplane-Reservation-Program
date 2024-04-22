package newGUI;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Customer.Customer;

public class LoginController {
    private newGUIModelWindow model;
    private static LoginController instance;

    public static LoginController getInstance(newGUIModelWindow model) {
        if (instance == null) {
            instance = new LoginController(model);
        }
        return instance;
    }

    // Constructor with model argument
    public LoginController(newGUIModelWindow model) {
        this.model = model;
    }

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

    public void addUser1(String username, String password, String email) {
        if (model.addUser(username, password, email)) {
            LoginView.showMessage("New customer added successfully.");
            newGUIViewWindow.getInstance().showWindow(); // Make window visible
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
        model.updateCustomer1(customer);
    }

    public void addUser(String firstName, String lastName, String email) {
        // TODO: Implement add user logic
    }

    public void setModel(newGUIModelWindow model2) {
        this.model = model2;
    }

    public newGUIModelWindow getModel() {
        return model;
    }
}

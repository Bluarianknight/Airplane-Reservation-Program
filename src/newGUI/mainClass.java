package newGUI;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class mainClass {

    public static void main(String[] args) {
        newGUIModelWindow model = new newGUIModelWindow();
        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (model.authenticateUser(username, password)) {
                EventQueue.invokeLater(() -> {
                    newGUIViewWindow window = new newGUIViewWindow();
                    window.setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login cancelled", "Login Cancelled", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
}



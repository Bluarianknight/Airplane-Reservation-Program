package newGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class purchaseDialogView extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtfldCreditCardDigits;
    private JTextField txtfldLastName;
    private JTextField txtfldFirstName;
    private JTextField txtfldExpire;
    private JTextField txtfldSecDigit;
    private JTextField txtfldTicket;
    flightList flight;

    purchaseDialogController control = new purchaseDialogController();

    /**
     * Create the dialog.
     */
    public purchaseDialogView(flightList newFlight) {
        
        // Initializing the dialog
        flight = newFlight;
        setTitle("Purchase Dialog");
        setBounds(100, 100, 703, 453);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        SpringLayout sl_contentPanel = new SpringLayout();
        contentPanel.setLayout(sl_contentPanel);
        
        // Labels for flight information
        JLabel lblTitle = new JLabel("Selected Flight");
        sl_contentPanel.putConstraint(SpringLayout.NORTH, lblTitle, 10, SpringLayout.NORTH, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.WEST, lblTitle, 28, SpringLayout.WEST, contentPanel);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPanel.add(lblTitle);
        
        // Labels for purchase details
        JLabel lblTitlePurchase = new JLabel("Purchase Details");
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblTitlePurchase, 0, SpringLayout.SOUTH, lblTitle);
        sl_contentPanel.putConstraint(SpringLayout.EAST, lblTitlePurchase, -114, SpringLayout.EAST, contentPanel);
        lblTitlePurchase.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPanel.add(lblTitlePurchase);
        
        // Panels for flight and purchase information
        JPanel panel = new JPanel();
        Component lblSelArriveAirport = null;
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 116, SpringLayout.EAST, lblSelArriveAirport);
        sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -64, SpringLayout.EAST, contentPanel);
        panel.setBorder(new LineBorder(Color.GRAY, 2));
        panel.setBackground(Color.WHITE);
        Component lblarrivalairport = null;
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, lblarrivalairport);
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, 256, SpringLayout.SOUTH, lblTitlePurchase);
        contentPanel.add(panel);
        SpringLayout sl_panel = new SpringLayout();
        panel.setLayout(sl_panel);
        
        // Text fields for user input
        txtfldCreditCardDigits = new JTextField();
        txtfldLastName = new JTextField();
        txtfldFirstName = new JTextField();
        txtfldExpire = new JTextField();
        txtfldSecDigit = new JTextField();
        txtfldTicket = new JTextField();
        
        // Setting up OK and Cancel buttons
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(e-> {
                    // Action to be performed when OK button is clicked
                    reservation res = new reservation(txtfldFirstName.getText(), txtfldLastName.getText(), txtfldCreditCardDigits.getText(), txtfldExpire.getText(), Integer.valueOf(txtfldSecDigit.getText()), Integer.valueOf(txtfldTicket.getText()));
                    control.sendReservation(res);
                    System.exit(0);
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        
        // Setting up dialog properties
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}

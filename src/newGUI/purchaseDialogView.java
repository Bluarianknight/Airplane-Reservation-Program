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
		
		flight = newFlight;
		setTitle("Purchase Dialog");
		setBounds(100, 100, 703, 453);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		
		JLabel lblTitle = new JLabel("Selected Flight");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblTitle, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblTitle, 28, SpringLayout.WEST, contentPanel);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblTitle);
		
		JLabel lblarrivalairport = new JLabel("Arrival Airport:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblarrivalairport, 17, SpringLayout.SOUTH, lblTitle);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblarrivalairport, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lblarrivalairport);
		
		JLabel lbldepartairport = new JLabel("Departing Airport:");
		sl_contentPanel.putConstraint(SpringLayout.WEST, lbldepartairport, 10, SpringLayout.WEST, contentPanel);
		contentPanel.add(lbldepartairport);
		
		JLabel lblArrivalTime = new JLabel("Arrival Time:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lbldepartairport, 6, SpringLayout.SOUTH, lblArrivalTime);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblArrivalTime, 6, SpringLayout.SOUTH, lblarrivalairport);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblArrivalTime, 0, SpringLayout.WEST, lblarrivalairport);
		contentPanel.add(lblArrivalTime);
		
		JLabel lblDepartureTime = new JLabel("Departure Time:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblDepartureTime, 6, SpringLayout.SOUTH, lbldepartairport);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblDepartureTime, 0, SpringLayout.WEST, lblarrivalairport);
		contentPanel.add(lblDepartureTime);
		
		JLabel lblSelArriveAirport = new JLabel(flight.ArrivalAirport);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblSelArriveAirport, 0, SpringLayout.NORTH, lblarrivalairport);
		contentPanel.add(lblSelArriveAirport);
		
		JLabel lblSelArrivalTime = new JLabel(String.valueOf(flight.TimeArrived));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblSelArrivalTime, 0, SpringLayout.NORTH, lblArrivalTime);
		contentPanel.add(lblSelArrivalTime);
		
		JLabel lblSelDepartAir = new JLabel(flight.DepartureAirport);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblSelArriveAirport, 0, SpringLayout.EAST, lblSelDepartAir);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblSelArrivalTime, 0, SpringLayout.WEST, lblSelDepartAir);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblSelDepartAir, 0, SpringLayout.NORTH, lbldepartairport);
		contentPanel.add(lblSelDepartAir);
		
		JLabel lblSelDepartTime = new JLabel(String.valueOf(flight.TimeLeft));
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblSelDepartAir, 0, SpringLayout.WEST, lblSelDepartTime);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblSelDepartTime, 0, SpringLayout.NORTH, lblDepartureTime);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblSelDepartTime, 40, SpringLayout.EAST, lblDepartureTime);
		contentPanel.add(lblSelDepartTime);
		
		JLabel lblTitlePurchase = new JLabel("Purchase Details");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblTitlePurchase, 0, SpringLayout.SOUTH, lblTitle);
		sl_contentPanel.putConstraint(SpringLayout.EAST, lblTitlePurchase, -114, SpringLayout.EAST, contentPanel);
		lblTitlePurchase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblTitlePurchase);
		
		JPanel panel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 116, SpringLayout.EAST, lblSelArriveAirport);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -64, SpringLayout.EAST, contentPanel);
		panel.setBorder(new LineBorder(Color.GRAY, 2));
		panel.setBackground(Color.WHITE);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, lblarrivalairport);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, panel, 256, SpringLayout.SOUTH, lblTitlePurchase);
		contentPanel.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblFirstName = new JLabel("First Name:");
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		sl_panel.putConstraint(SpringLayout.EAST, lblFirstName, 0, SpringLayout.EAST, lblLastName);
		panel.add(lblLastName);
		
		JLabel lblCreditCardInfo = new JLabel("Credit Card Digits:");
		sl_panel.putConstraint(SpringLayout.WEST, lblLastName, 0, SpringLayout.WEST, lblCreditCardInfo);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblLastName, -6, SpringLayout.NORTH, lblCreditCardInfo);
		panel.add(lblCreditCardInfo);
		
		txtfldCreditCardDigits = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, lblCreditCardInfo, 3, SpringLayout.NORTH, txtfldCreditCardDigits);
		sl_panel.putConstraint(SpringLayout.EAST, lblCreditCardInfo, -6, SpringLayout.WEST, txtfldCreditCardDigits);
		sl_panel.putConstraint(SpringLayout.WEST, txtfldCreditCardDigits, 127, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, txtfldCreditCardDigits, -13, SpringLayout.EAST, panel);
		panel.add(txtfldCreditCardDigits);
		txtfldCreditCardDigits.setColumns(10);
		
		txtfldLastName = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, txtfldLastName, 9, SpringLayout.EAST, lblLastName);
		sl_panel.putConstraint(SpringLayout.EAST, txtfldLastName, -13, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, txtfldCreditCardDigits, 3, SpringLayout.SOUTH, txtfldLastName);
		sl_panel.putConstraint(SpringLayout.NORTH, txtfldLastName, 31, SpringLayout.NORTH, panel);
		txtfldLastName.setColumns(10);
		panel.add(txtfldLastName);
		
		txtfldFirstName = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, txtfldFirstName, 9, SpringLayout.EAST, lblFirstName);
		sl_panel.putConstraint(SpringLayout.SOUTH, txtfldFirstName, -4, SpringLayout.NORTH, txtfldLastName);
		sl_panel.putConstraint(SpringLayout.EAST, txtfldFirstName, -13, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.NORTH, lblFirstName, 3, SpringLayout.NORTH, txtfldFirstName);
		txtfldFirstName.setColumns(10);
		panel.add(txtfldFirstName);
		
		JLabel lblCreditCardExpire = new JLabel("Credit Card Expiration:");
		panel.add(lblCreditCardExpire);
		
		txtfldExpire = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, lblCreditCardExpire, 3, SpringLayout.NORTH, txtfldExpire);
		sl_panel.putConstraint(SpringLayout.EAST, lblCreditCardExpire, -6, SpringLayout.WEST, txtfldExpire);
		sl_panel.putConstraint(SpringLayout.WEST, txtfldExpire, 10, SpringLayout.WEST, txtfldCreditCardDigits);
		sl_panel.putConstraint(SpringLayout.EAST, txtfldExpire, -13, SpringLayout.EAST, panel);
		txtfldExpire.setColumns(10);
		panel.add(txtfldExpire);
		
		JLabel lblCreditCardSecurity = new JLabel("CC. Security Digits:");
		sl_panel.putConstraint(SpringLayout.EAST, lblCreditCardSecurity, 0, SpringLayout.EAST, lblCreditCardExpire);
		panel.add(lblCreditCardSecurity);
		
		txtfldSecDigit = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, lblCreditCardSecurity, 3, SpringLayout.NORTH, txtfldSecDigit);
		sl_panel.putConstraint(SpringLayout.SOUTH, txtfldExpire, -8, SpringLayout.NORTH, txtfldSecDigit);
		sl_panel.putConstraint(SpringLayout.NORTH, txtfldSecDigit, 106, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, txtfldSecDigit, 10, SpringLayout.WEST, txtfldExpire);
		sl_panel.putConstraint(SpringLayout.EAST, txtfldSecDigit, -13, SpringLayout.EAST, panel);
		txtfldSecDigit.setColumns(10);
		panel.add(txtfldSecDigit);
		
		JLabel lblTickets = new JLabel("Tickets:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblTickets, 7, SpringLayout.SOUTH, lblCreditCardSecurity);
		sl_panel.putConstraint(SpringLayout.WEST, lblTickets, 19, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblTickets, -199, SpringLayout.EAST, panel);
		panel.add(lblTickets);
		
		txtfldTicket = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, txtfldTicket, 7, SpringLayout.SOUTH, lblCreditCardSecurity);
		sl_panel.putConstraint(SpringLayout.WEST, txtfldTicket, 69, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, txtfldTicket, -172, SpringLayout.EAST, panel);
		txtfldTicket.setColumns(10);
		panel.add(txtfldTicket);
		
		JLabel lblPrice = new JLabel("Cost:");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblPrice, 0, SpringLayout.WEST, lblarrivalairport);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, lblPrice, -38, SpringLayout.SOUTH, contentPanel);
		contentPanel.add(lblPrice);
		
		JLabel lblselPrice = new JLabel("$" + String.valueOf(flight.cost));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblselPrice, 0, SpringLayout.NORTH, lblPrice);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblselPrice, 82, SpringLayout.EAST, lblPrice);
		lblselPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPanel.add(lblselPrice);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e-> {
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
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}

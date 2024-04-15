package newGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JTextPane;

public class newGUIViewWindow {

	private JFrame frmAirlineReservationProgram;

	/**
	 * Launch the application.
	 */
	
	newGUIControllerWindow control = new newGUIControllerWindow();
	JList<flightList> listFlightList = new JList<flightList>();
	DefaultListModel<flightList> listModel = new DefaultListModel<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newGUIViewWindow window = new newGUIViewWindow();
					window.frmAirlineReservationProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public newGUIViewWindow() {
		initialize();
		loadList();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAirlineReservationProgram = new JFrame();
		frmAirlineReservationProgram.setResizable(false);
		frmAirlineReservationProgram.setTitle("Airline Reservation Program");
		frmAirlineReservationProgram.setBounds(100, 100, 720, 480);
		frmAirlineReservationProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmAirlineReservationProgram.getContentPane().setLayout(springLayout);
		
		JPanel mainPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, mainPanel, 10, SpringLayout.NORTH, frmAirlineReservationProgram.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, mainPanel, 144, SpringLayout.WEST, frmAirlineReservationProgram.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, mainPanel, 0, SpringLayout.SOUTH, frmAirlineReservationProgram.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, mainPanel, -10, SpringLayout.EAST, frmAirlineReservationProgram.getContentPane());
		frmAirlineReservationProgram.getContentPane().add(mainPanel);
		
		JButton btnSettings = new JButton("Settings");
		springLayout.putConstraint(SpringLayout.NORTH, btnSettings, -73, SpringLayout.SOUTH, frmAirlineReservationProgram.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnSettings, 10, SpringLayout.WEST, frmAirlineReservationProgram.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSettings, -10, SpringLayout.SOUTH, frmAirlineReservationProgram.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnSettings, -6, SpringLayout.WEST, mainPanel);
		frmAirlineReservationProgram.getContentPane().add(btnSettings);
		
		JButton btnBilling = new JButton("Billing");
		springLayout.putConstraint(SpringLayout.NORTH, btnBilling, -69, SpringLayout.NORTH, btnSettings);
		springLayout.putConstraint(SpringLayout.WEST, btnBilling, 0, SpringLayout.WEST, btnSettings);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBilling, -6, SpringLayout.NORTH, btnSettings);
		springLayout.putConstraint(SpringLayout.EAST, btnBilling, 128, SpringLayout.WEST, btnSettings);
		frmAirlineReservationProgram.getContentPane().add(btnBilling);
		
		JButton btnFlightSearch = new JButton("Flight Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnFlightSearch, -69, SpringLayout.NORTH, btnBilling);
		springLayout.putConstraint(SpringLayout.WEST, btnFlightSearch, 6, SpringLayout.WEST, frmAirlineReservationProgram.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnFlightSearch, -6, SpringLayout.NORTH, btnBilling);
		springLayout.putConstraint(SpringLayout.EAST, btnFlightSearch, -6, SpringLayout.WEST, mainPanel);
		frmAirlineReservationProgram.getContentPane().add(btnFlightSearch);
		
		JButton btnHome = new JButton("Home Page");
		springLayout.putConstraint(SpringLayout.NORTH, btnHome, -104, SpringLayout.NORTH, btnFlightSearch);
		springLayout.putConstraint(SpringLayout.WEST, btnHome, 0, SpringLayout.WEST, btnSettings);
		springLayout.putConstraint(SpringLayout.SOUTH, btnHome, -6, SpringLayout.NORTH, btnFlightSearch);
		springLayout.putConstraint(SpringLayout.EAST, btnHome, -6, SpringLayout.WEST, mainPanel);
		SpringLayout sl_mainPanel = new SpringLayout();
		mainPanel.setLayout(sl_mainPanel);
		
		JLabel lblWelcome = new JLabel("Hello User");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, lblWelcome, 29, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, lblWelcome, 134, SpringLayout.WEST, mainPanel);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mainPanel.add(lblWelcome);
		
		listFlightList.setModel(listModel);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, listFlightList, 100, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, listFlightList, 139, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, listFlightList, -10, SpringLayout.SOUTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, listFlightList, -212, SpringLayout.EAST, mainPanel);
		mainPanel.add(listFlightList);
		
		JLabel lblFlightListing = new JLabel("Flight Listings");
		sl_mainPanel.putConstraint(SpringLayout.WEST, lblFlightListing, 186, SpringLayout.WEST, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, lblFlightListing, -6, SpringLayout.NORTH, listFlightList);
		lblFlightListing.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lblFlightListing);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		sl_mainPanel.putConstraint(SpringLayout.NORTH, panel, 48, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.WEST, panel, 24, SpringLayout.EAST, listFlightList);
		sl_mainPanel.putConstraint(SpringLayout.SOUTH, panel, 217, SpringLayout.NORTH, mainPanel);
		sl_mainPanel.putConstraint(SpringLayout.EAST, panel, -26, SpringLayout.EAST, mainPanel);
		mainPanel.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblFlightID = new JLabel("Flight ID:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblFlightID, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblFlightID, 10, SpringLayout.WEST, panel);
		panel.add(lblFlightID);
		
		JLabel lblSelID = new JLabel("X");
		sl_panel.putConstraint(SpringLayout.NORTH, lblSelID, 0, SpringLayout.NORTH, lblFlightID);
		sl_panel.putConstraint(SpringLayout.WEST, lblSelID, 6, SpringLayout.EAST, lblFlightID);
		panel.add(lblSelID);
		
		JLabel lblDepart = new JLabel("Departure Time:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblDepart, 5, SpringLayout.SOUTH, lblFlightID);
		sl_panel.putConstraint(SpringLayout.WEST, lblDepart, 0, SpringLayout.WEST, lblFlightID);
		panel.add(lblDepart);
		
		JLabel lblArrivalTime = new JLabel("Arrival Time:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblArrivalTime, 6, SpringLayout.SOUTH, lblDepart);
		sl_panel.putConstraint(SpringLayout.WEST, lblArrivalTime, 0, SpringLayout.WEST, lblFlightID);
		panel.add(lblArrivalTime);
		
		JLabel lblSelDep = new JLabel("X");
		sl_panel.putConstraint(SpringLayout.NORTH, lblSelDep, 0, SpringLayout.NORTH, lblDepart);
		sl_panel.putConstraint(SpringLayout.WEST, lblSelDep, 6, SpringLayout.EAST, lblDepart);
		panel.add(lblSelDep);
		
		JLabel lblSelArrive = new JLabel("X");
		sl_panel.putConstraint(SpringLayout.NORTH, lblSelArrive, 6, SpringLayout.SOUTH, lblDepart);
		sl_panel.putConstraint(SpringLayout.WEST, lblSelArrive, 6, SpringLayout.EAST, lblArrivalTime);
		panel.add(lblSelArrive);
		
		JLabel lblInfoMain = new JLabel("Information");
		sl_mainPanel.putConstraint(SpringLayout.NORTH, lblInfoMain, -1, SpringLayout.NORTH, lblWelcome);
		sl_mainPanel.putConstraint(SpringLayout.EAST, lblInfoMain, -63, SpringLayout.EAST, mainPanel);
		lblInfoMain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lblInfoMain);
		frmAirlineReservationProgram.getContentPane().add(btnHome);
	}
	
	public void loadList() {
		listModel = control.getFlightData();
		listFlightList.setModel(listModel);
	}
}

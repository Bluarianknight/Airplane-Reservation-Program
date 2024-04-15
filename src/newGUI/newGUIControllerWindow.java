package newGUI;

import javax.swing.DefaultListModel;

public class newGUIControllerWindow {
	newGUIModelWindow model = new newGUIModelWindow();


	public DefaultListModel<flightList> getFlightData() {
		DefaultListModel<flightList> x = model.getFlightData();
		flightList test = x.elementAt(0);
		System.out.println(test.ArrivalAirport);
		return x;
	}
}

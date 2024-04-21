package newGUI;

import javax.swing.DefaultListModel;

public class newGUIControllerWindow {
	newGUIModelWindow model = new newGUIModelWindow();


	public DefaultListModel<flightList> getFlightData() {
		DefaultListModel<flightList> x = model.getFlightData();
		return x;
	}
	
	public DefaultListModel<flightList> getFilteredFlightData(String firstSearch, String secondSearch) {
		DefaultListModel<flightList> x = model.getFilteredFlightData(firstSearch, secondSearch);
		return x;
	}
}

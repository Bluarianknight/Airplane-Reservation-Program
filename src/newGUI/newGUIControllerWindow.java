package newGUI;

import javax.swing.DefaultListModel;

public class newGUIControllerWindow {
    
    // Instance variable that holds the reference to the model.
    // The model handles data logic and database interactions.
    newGUIModelWindow model = new newGUIModelWindow();

    // Method that retrieves the full list of flight data from the model.
    // It returns a DefaultListModel of flightList objects, which can be used by the view (e.g., in a JList).
    public DefaultListModel<flightList> getFlightData() {
        // Call the method in the model that retrieves the flight data.
        DefaultListModel<flightList> x = model.getFlightData();
        // Return the retrieved list to the caller.
        return x;
    }
    
    // Method that retrieves a filtered list of flight data based on search criteria.
    // 'firstSearch' and 'secondSearch' parameters are used for filtering the data.
    public DefaultListModel<flightList> getFilteredFlightData(String firstSearch, String secondSearch) {
        // Call the method in the model that retrieves filtered flight data.
        DefaultListModel<flightList> x = model.getFilteredFlightData(firstSearch, secondSearch);
        // Return the retrieved filtered list to the caller.
        return x;
    }
}
package newGUI;

import java.util.List;

public class flightList {
	
	List<Integer> ID;
	List<String> model;
	List<String> ArrivalAirport;
	List<String> DepartureAirport;
	List<String> TimeLeft;
	List<String> TimeArrived;
	
	public flightList() {
		
	}
	
	public flightList(List<Integer> newID, List<String> newModel, List<String> arrived, List<String> depart, List<String> timelefted, List<String> timearrive) {
		this.ID = newID;
		this.model = newModel;
		this.ArrivalAirport = arrived;
		this.DepartureAirport = depart;
		this.TimeLeft = timelefted;
		this.TimeArrived = timearrive;
	}
}

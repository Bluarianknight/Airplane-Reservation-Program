package newGUI;

import java.util.List;

public class flightList {
	
	Integer ID;
	String model;
	String ArrivalAirport;
	String DepartureAirport;
	String TimeLeft;
	String TimeArrived;
	
	public flightList() {
		
	}
	
	public flightList(Integer newID, String newModel, String arrived, String depart, String timelefted, String timearrive) {
		this.ID = newID;
		this.model = newModel;
		this.ArrivalAirport = arrived;
		this.DepartureAirport = depart;
		this.TimeLeft = timelefted;
		this.TimeArrived = timearrive;
	}
	
	
	@Override
	public String toString() {
		return this.model + " " + this.ID + ": " + this.DepartureAirport + " to " + this.ArrivalAirport;
	}
}

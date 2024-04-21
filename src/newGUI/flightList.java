package newGUI;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;

public class flightList {
	
	Integer ID;
	String model;
	String ArrivalAirport;
	String DepartureAirport;
	Time TimeLeft;
	Time TimeArrived;
	BigDecimal cost;
	
	public flightList() {
		
	}
	
	public flightList(Integer newID, String newModel, String arrived, String depart, Time timelefted, Time timearrive, BigDecimal newMoney) {
		this.ID = newID;
		this.model = newModel;
		this.ArrivalAirport = arrived;
		this.DepartureAirport = depart;
		this.TimeLeft = timelefted;
		this.TimeArrived = timearrive;
		this.cost = newMoney;
	}
	
	
	@Override
	public String toString() {
		if (this.model != null) {
			return this.model + " " + this.ID + ": " + this.DepartureAirport + " to " + this.ArrivalAirport;
		} 
		return "No Flights found.";
		
	}
}

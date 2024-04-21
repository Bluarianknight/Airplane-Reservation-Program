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
	
	 public flightList(Integer ID, String Model, String ArrivalAirport, String DepartureAirport, Time TimeLeft, Time TimeArrived, BigDecimal Cost) {
	        this.ID = ID;
	        this.model = Model;
	        this.ArrivalAirport = ArrivalAirport;
	        this.DepartureAirport = DepartureAirport;
	        this.TimeLeft = TimeLeft;
	        this.TimeArrived = TimeArrived;
	        this.cost = Cost;
	    }
	
	
	@Override
	public String toString() {
		return this.model + " " + this.ID + ": " + this.DepartureAirport + " to " + this.ArrivalAirport;
	}

	public char[] getArrivalAirport() {
		// TODO Auto-generated method stub
		return null;
	}
}

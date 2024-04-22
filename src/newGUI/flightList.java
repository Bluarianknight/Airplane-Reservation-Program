package newGUI;

import java.math.BigDecimal;
import java.sql.Time;

public class flightList {
    
    // Class variables to hold flight details
    Integer ID;
    String model;
    String ArrivalAirport;
    String DepartureAirport;
    Time TimeLeft;
    Time TimeArrived;
    BigDecimal cost;
    
    // Default constructor
    public flightList() {
        // Nothing special happens in the default constructor
    }
    
    // Constructor with parameters to initialize a flightList object
    public flightList(Integer ID, String Model, String ArrivalAirport, 
                      String DepartureAirport, Time TimeLeft, 
                      Time TimeArrived, BigDecimal Cost) {
        this.ID = ID; // Unique identifier for the flight
        this.model = Model; // Model of the airplane
        this.ArrivalAirport = ArrivalAirport; // Airport where the flight will arrive
        this.DepartureAirport = DepartureAirport; // Airport from where the flight will depart
        this.TimeLeft = TimeLeft; // Departure time
        this.TimeArrived = TimeArrived; // Arrival time
        this.cost = Cost; // Cost of the flight
    }
    
    // Overrides the default toString method to provide a string representation of a flightList object
    @Override
    public String toString() {
        // Checks if the model is not null, which means the flightList object has been initialized
        if (this.model != null) {
            // Returns the flight details in a readable format
            return this.model + " " + this.ID + ": " + this.DepartureAirport + " to " + this.ArrivalAirport;
        } 
        // If the model is null, it means no flight details are available
        return "No Flights found.";
    }

    
    public char[] getArrivalAirport() {
        // TODO: This method needs to be implemented or fixed to match the return type
        return null;
    }
}

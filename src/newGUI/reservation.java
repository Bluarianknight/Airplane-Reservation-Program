package newGUI;

/**
 * The reservation class represents a reservation for purchasing flight tickets.
 * It stores information about the passenger and their payment details.
 */
public class reservation {
    
    // Fields to store passenger information
    String firstName;
    String lastName;
    String cc; // Credit card number
    String ccExp; // Credit card expiration date
    Integer ccSecurity; // Credit card security code
    Integer tickets; // Number of tickets
    
    /**
     * Default constructor for the reservation class.
     */
    public reservation() {
        
    }
    
    /**
     * Parameterized constructor for the reservation class.
     * @param newfirst The passenger's first name
     * @param newlast The passenger's last name
     * @param newcc The passenger's credit card number
     * @param ccExpire The expiration date of the credit card
     * @param ccsec The security code of the credit card
     * @param newtickets The number of tickets being purchased
     */
    public reservation(String newfirst, String newlast, String newcc, String ccExpire, Integer ccsec, Integer newtickets) {
        this.firstName = newfirst;
        this.lastName = newlast;
        this.cc = newcc;
        this.ccExp = ccExpire;
        this.ccSecurity = ccsec;
        this.tickets = newtickets;
    }
}

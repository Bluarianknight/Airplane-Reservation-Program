package newGUI;



public class reservation {
	
	String firstName;
	String lastName;
	String cc;
	String ccExp;
	Integer ccSecurity;
	Integer tickets;
	
	public reservation() {
		
	}
	
	public reservation(String newfirst, String newlast, String newcc, String ccExpire, Integer ccsec, Integer newtickets) {
		this.firstName = newfirst;
		this.lastName = newlast;
		this.cc = newcc;
		this.ccExp = ccExpire;
		this.ccSecurity = ccsec;
		this.tickets = newtickets;
	}
	
	

}

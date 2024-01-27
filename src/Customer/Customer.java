package Customer;

public class Customer {
// The customer class.
	
	private String name;
	private String email;
	private int userID;
	
	
	public void setID(int newID) {
		this.userID = newID;
	}
	
	public int getID() {
		return this.userID;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	
}

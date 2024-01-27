package Customer;

public class CustomerView {
// The view for the Customer class.
	
	public void printUserDetails(String name, int userID, String email) {
		System.out.print("Name: " + name);
		System.out.println("ID: " + userID);
		System.out.println("Email: " + email);
	}
}

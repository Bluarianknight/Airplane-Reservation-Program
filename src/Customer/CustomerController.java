package Customer;

public class CustomerController {
// The controller for the Customer class.
	private CustomerView view = new CustomerView();
	private Customer model = new Customer();
	
	
	public CustomerController(CustomerView newView, Customer newModel) {
		this.view = newView;
		this.model = newModel;
	}
	
	
	public void printCustomerInfo() {
		view.printUserDetails(model.getName(), model.getID(), model.getEmail());
	}
	
	public void setID(String name) {
		model.setName(name);
	}
	
	public int getID() {
		return model.getID();
	}
	
	public void setName(String name) {
		model.setName(name);
	}
	
	public String getName() {
		return model.getName();
	}
	
	public void setEmail(String email) {
		model.setEmail(email);
	}
	
	public String getEmail() {
		return model.getEmail();
	}
	
	
}

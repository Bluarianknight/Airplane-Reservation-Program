package GUI;

import java.util.ArrayList;

public class GUIController {
	
	GUIModel model = new GUIModel();
	GUIView view = new GUIView();
	modifiedGUIView newview = new modifiedGUIView();
	
	public ArrayList<ArrayList<String>> QueryAuth(String query){ // Sends a query to the authorization database. It returns it in a nested arraylist.
		return model.sendAuthQuery(query);
	}
	
	public ArrayList<ArrayList<String>> QueryCustomerData(String query){ // Sends a query to the customer database, returning it in a nested arraylist.
		return model.sendCustQuery(query);
	}
	
	public void ModifyCustomerData(String query) { // Sends a query to modify data - separated from the others for now just in case it may need modification.
		model.modifyCustQuery(query);
	}

	
	public static void main(String[] args) {
		
		
	}
}

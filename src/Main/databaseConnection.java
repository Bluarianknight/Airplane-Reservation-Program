package Main;
import java.sql.*;

public class databaseConnection {
	String databaseLocation;
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public databaseConnection() {
		this.databaseLocation = "CHANGE!";
	}
	
	public databaseConnection(String location) {
		this.databaseLocation = location;
	}
	
	

}

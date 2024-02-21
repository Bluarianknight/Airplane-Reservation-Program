package Main;
import javax.swing.*;
import javax.swing.JFileChooser;
public class testWindow extends baseWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	databaseConnection database = new databaseConnection();
	String loc;
	public testWindow(String newTitle, int nx, int ny) {
		super(newTitle, nx, ny);
		setDataLocation();
		// TODO Auto-generated constructor stub
	}

	public testWindow(String newTitle) {
		super(newTitle);
		setDataLocation();
	}
	
	public void setDataLocation() {
		JFileChooser filelocation = new JFileChooser();
		filelocation.showOpenDialog(null);
		database.setDatabase(filelocation.getName());
		database.connectDatabase();
		
		
		

		
	}
	
}

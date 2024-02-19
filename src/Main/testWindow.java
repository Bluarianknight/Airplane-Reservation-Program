package Main;
import javax.swing.*;
public class testWindow extends baseWindow {

	databaseConnection database = new databaseConnection();
	public testWindow(String newTitle, int nx, int ny) {
		super(newTitle, nx, ny);
		// TODO Auto-generated constructor stub
	}

	public testWindow(String newTitle) {
		super(newTitle);
	}
	
	public void setDataLocation() {
		JFileChooser filelocation = new JFileChooser();
		filelocation.showOpenDialog(null);
		
		try {
			
		}
		
	}
	
}

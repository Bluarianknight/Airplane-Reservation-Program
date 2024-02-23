package GUI;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Main.testWindow;

public class GUIModel {
	File databaseLocation;
	String startData = "jdbc:ucanaccess://";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public GUIModel() {
		setData();
	}
	
	public GUIModel(File location) {
		this.databaseLocation = location;
	}
	
	public void setData() {
		JFileChooser filelocation = new JFileChooser();
		filelocation.showOpenDialog(null);
		databaseLocation = filelocation.getSelectedFile();
		
	}
	
	public void dataconnect() {
		String datapath = this.databaseLocation.getPath();
		datapath.replaceAll("\\", "/");
		
		String dataloc = this.startData + datapath;
		JOptionPane.showMessageDialog(null, dataloc);
		try {
			connection = DriverManager.getConnection(startData);
			JOptionPane.showMessageDialog(null, "Connection Successful");
			
		} catch (SQLException sqlex) {
			JOptionPane.showMessageDialog(null, sqlex);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIModel x = new GUIModel();
		JOptionPane.showMessageDialog(null, x.databaseLocation);
		x.dataconnect();

	}
}

package Main;
import java.util.ArrayList;
import javax.swing.*;


public abstract class baseWindow extends JFrame {

	String title = "Title";
	int x = 1280;
	int y = 720;
	
	public baseWindow(String newTitle) {
		this.title = newTitle;
		this.x = 1280;
		this.y = 720;
	}
	
	public baseWindow(String newTitle, int nx, int ny) {
		this.title = newTitle;
		this.x = nx;
		this.y = ny;
		
	}
	
	public void startWindow() {
		JFrame win = new JFrame(this.title);
		win.pack();
		win.setSize(this.x, this.y);
		win.setVisible(true);
	}
	
	
	
}

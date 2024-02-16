
import java.awt.Frame;


public abstract class baseWindow extends Frame {

	String title;
	int x;
	int y;
	
	public baseWindow(String newTitle) {
		this.title = newTitle;
	}
	
	public baseWindow(String newTitle, int nx, int ny) {
		this.title = newTitle;
		this.x = nx;
		this.y = ny;
		
	}
	
	public void startWindow() {
		
	}
	
	
	
}

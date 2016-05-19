import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class MotionDisplay {
	
	/* Show if they want to see the display 
	 * 
	 *  all motions for motors 
	 *  1 = forward, 0 = stopped, -1 = backward 
	 *  killed = 1 if killAll command sent
	 */
	public boolean show = false;
	private int rTrack = 0;
	private int lTrack = 0;
	private int gearArms = 0;
	private int bucket = 0;
	private int hopper = 0;
	private int mainArm = 0;
	private int killed = 0;
	
	
	
	public MotionDisplay()
	{
		show = true;
	}
	
	public void display()
	{
		if(show)
		{
			JFrame frame = new JFrame();
	   		frame.setVisible(true);
	   		frame.setSize(new Dimension(300,200));
	   		frame.setFocusable(true);
	   		
	   		//TODO: make arrows
	   		Rectangle rtrackUp = new Rectangle(); 
		}
   		
	}
	
	public void update(int[] changes)
	{
		rTrack = changes[0];
		lTrack = changes[1];
		gearArms = changes[2];
		bucket = changes[3];
		hopper = changes[4];	
		mainArm = changes[5];
	}
	public void killAll()
	{
		killed = 1;
	}

}

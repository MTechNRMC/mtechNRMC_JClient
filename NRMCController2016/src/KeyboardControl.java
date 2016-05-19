import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;

/* KeyboardControl Class
 * @author Mack Sutherland
 * @date 2/8/2106
 * 
 * Keyboard controller will open a java window
 * and begin to listen for keys typed. It sends the correct signal to controller to handle
 * 
 */

public class KeyboardControl extends InputInterface
{
	
	private boolean running;
	private JClientUDP sct = new JClientUDP(5000);
    private String ipAddr = "192.168.1.23";
    
   public KeyboardControl()
   {
	   running = false;
	   controller = new Controller();
   }
    private void listen()
    {
    	JFrame frame = new JFrame();
   		frame.setVisible(true);
   		frame.setSize(new Dimension(300,200));
   		frame.setFocusable(true);
   		frame.addKeyListener(new KeyListener()
   		{
   			@Override
			public void keyPressed(KeyEvent event)
   			{
   				if(!moving.contains(event.getKeyChar()))
				{
					moving.add(event.getKeyChar());
					
					controller.handleCommand(event.getKeyCode(), true);
				}
				
			}
			@Override
			public void keyReleased(KeyEvent event) 
			{
				moving.remove(event.getKeyChar());
				controller.handleCommand(event.getKeyCode(), false);
				
				if(event.getKeyChar() == 'q')
				{
					System.out.println("quiting");
					running = false;
					System.exit(0);
				}
				
			}
			@Override
			public void keyTyped(KeyEvent event)
			{
				// nothing				
			}
   			
   		});
   		
   		if(!sct.startReceive())
			System.err.println("[ERR]startReveive: " + sct.getLastException().getMessage());
   		short mvPacket = controller.getPacket();
   		
   		/******ping msg
   		 * 
   		 */
//   		if(!sct.send(controller.createMsg((byte)0x00, mvPacket), ipAddr))   			
//			System.err.println("[ERR]Send: " + sct.getLastException().getMessage());
   		
   		while(running)
		{
   			if(controller.getKillStatus())
   			{
   				running = false;
   			}
			// send the msg to the robot
   			mvPacket = controller.getPacket();
   			   			   			
			if(!sct.send(controller.createMsg((byte)0x01, mvPacket), ipAddr))
				System.err.println("[ERR]Send: " + sct.getLastException().getMessage());
			
						
			try 
			{
				Thread.sleep(100);				
			} 
			catch (InterruptedException e) 			
			{/*Ignore*/}
			
		}
   		
		System.out.println("exiting...");
		System.out.print("Closing Socket...");
		
		if(!sct.stopReceive())
			System.err.println("[ERR]Send: " + sct.getLastException().getMessage());
			
		sct.close();
		
		System.out.println("Closed");
		System.out.println("done");
		frame.dispose();
		}     
    
    public void changeIP()
    {
    	ipAddr = "192.168.1.15";
    }
       

    public void activate (  )
    {
    	running = true;
       listen();
       return;
    }
    /**
     * Operation
     *
     */
    public void deactivate (  )
    {
        running = false; 
    }
    
    public static void main(String [] args)
	{
		KeyboardControl k = new KeyboardControl();
		Scanner scan = new Scanner(System.in);
		System.out.println("Connect to robot?y/n");
		String robot= scan.next();
		scan.close();
		
		if(robot.equals("y"))
			k.changeIP();
		
		k.activate();
		return;
	}
}


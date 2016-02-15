import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
       while(running)
       {}          
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
		k.activate();
		return;
	}
}


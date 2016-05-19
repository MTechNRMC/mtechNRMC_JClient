import java.awt.event.KeyEvent;
import java.nio.ByteBuffer;


/* Controller Class
 * @author Mack Sutherland
 * @date 2/8/2106
 * 
 * The purpose of this class is to send messages to the robot for motion control
 * handleCommand() is the meat of the program. 
 * If buttons/motions are added, add the appropriate case and packet Binary
 */



// ## Implementation preserve start class opening. 
// ## Implementation preserve end class opening. 
// ## Implementation preserve start class import. 
// ## Implementation preserve end class import. 

public class Controller
{
	private short mvPacket = 0x00;
	private int[] currentMotion = new int[6];
	private MotionDisplay md;
	private boolean killAll = false;
	
	public Controller()
	{
		md = new MotionDisplay();
	}
	
	
    public void handleCommand (int command , boolean pressed)
    {
    	if(!pressed)
    	{
	    	switch (command)
			{
			case KeyEvent.VK_W:
				System.out.println("Stop");
				currentMotion[0] = 0;
				currentMotion[1] = 0;
				mvPacket &= 0xFFCC;
				break;
			case KeyEvent.VK_A:
				System.out.println("Stop");
				currentMotion[0] = 0;
				mvPacket &= 0xFFCC;
				break;
			case KeyEvent.VK_S:
				System.out.println("Stop");
				currentMotion[0] = 0;
				currentMotion[1] = 0;
				mvPacket &= 0xFFCC;
				break;
			case KeyEvent.VK_D:
				System.out.println("Stop");
				currentMotion[1] = 0;
				mvPacket &= 0xFFCC;
				break;
				/*
			case KeyEvent.VK_UP:
				System.out.println("Main Arm Stop");
				currentMotion[5] = 0;
				mvPacket &= 0xCCFF;
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("Main Arm Stop");
				currentMotion[5] = 0;
				mvPacket &= 0xCCFF;
				break;*********/
				
			case KeyEvent.VK_T:
				System.out.println("Vibrator Stop");
				currentMotion[4] = 0;
				mvPacket &= 0xFFF7;
				break;
			case KeyEvent.VK_CLOSE_BRACKET:
				System.out.println("Hopper Stop");
				currentMotion[4] = 0;
				mvPacket &= 0xEFFF;
				break;
				/*************
			case KeyEvent.VK_U:
				System.out.println("L Actuator Stop");
				currentMotion[2] = 0;
				mvPacket &= 0xFFCF;
				break;
	
			case KeyEvent.VK_J:
				System.out.println("L Actuator Stop");
				currentMotion[2] = 0;
				mvPacket &= 0xFFCF;
				break;*****************/
			case KeyEvent.VK_O:
				System.out.println("Chain arms out Stop");
				currentMotion[2] = 0;
				mvPacket &= 0xBFFF;
				break;
			case KeyEvent.VK_L:
				System.out.println("Chain arms in Stop");
				currentMotion[2] = 0;
				mvPacket &= 0x7FFF;
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("Bucket Actuator IN Stop");
				currentMotion[3] = 0;
				mvPacket &= 0xFDFF;
				
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("Bucket Actuator OUT Stop");
				currentMotion[3] = 0;
				mvPacket &= 0xFEFF;
				break;
			case KeyEvent.VK_MINUS:
				//running = false;
				killAll();
				break;
			}
    	}
    	/* Start Key pressed
		 * 
		 * 
		 */
    	
    	else
    	{
    		    		
    		switch(command)
    		{		
	    		case KeyEvent.VK_W:
					System.out.println("Forward");
					currentMotion[0] = 1;
					currentMotion[1] = 1;
					mvPacket |= 0x0012;
					break;
				case KeyEvent.VK_A:
					System.out.println("Turn Left");
					currentMotion[0] = 1;
					mvPacket |= 0x0011;
					break;
				case KeyEvent.VK_S:
					System.out.println("Backward");
					currentMotion[0] = -1;
					currentMotion[1] = -1;
					mvPacket |= 0x0021;
					break;
				case KeyEvent.VK_D:
					System.out.println("Turn Right");
					currentMotion[1] = 1;
					mvPacket |= 0x0022;
					break;
					/*
				case KeyEvent.VK_P:
					System.out.println("Emerg Stop");
					currentMotion = new int[6];
					mvPacket &= 0x0000;
	//				sct.send(createMsg((byte) 0x0C, 0), ipAddr);
					break;*******/
				case KeyEvent.VK_EQUALS:
					System.out.println("Speed Up");
		//			sct.send(createMsg((byte) 0x08, 0), ipAddr);
					break;
				case KeyEvent.VK_MINUS:
					System.out.println("Slow Down");
		//			sct.send(createMsg((byte) 0x09, 0), ipAddr);
					break;
					/*
				case KeyEvent.VK_UP:
					System.out.println("Main Arms Up");
					currentMotion[5] = 1;
					mvPacket |= 0x2200;
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("Main Arms Down");
					currentMotion[5] = -1;
					mvPacket |= 0x1100;
					break;******/
					
					// No longer used case
				/*case KeyEvent.VK_OPEN_BRACKET:
					System.out.println("Hopper Down");
					currentMotion[4] = -1;
					mvPacket |= 0x0800;
					break;**************/
				case KeyEvent.VK_T:
					System.out.println("Vibrator On");
					currentMotion[4] = 1;
					mvPacket |= 0x0008;
					break;
					
				case KeyEvent.VK_CLOSE_BRACKET:
					System.out.println("BELT Up");
					currentMotion[4] = 1;
					mvPacket |= 0x1000;
					break;
					
					/* No longer used cases. Motor arms are in sync
				case KeyEvent.VK_U:
					System.out.println("L Actuator Up");
					currentMotion[2] = 1;
					mvPacket |= 0x0020;
					break;
				case KeyEvent.VK_J:
					System.out.println("L Actuator Down");
					currentMotion[2] = -1;
					mvPacket |= 0x0010;
					break;************/
				case KeyEvent.VK_O:
					System.out.println("Chain arms In");
					currentMotion[2] = 1;
					mvPacket |= 0x4000;
					break;
				case KeyEvent.VK_L:
					System.out.println("Chain arms Extend");
					currentMotion[2] = -1;
					mvPacket |= 0x8000;
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("Bucket Actuator In");
					currentMotion[3] = 1;
					mvPacket |= 0x0200;
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("Bucket Actuator Out");
					currentMotion[3] = -1;
					mvPacket |= 0x0100;					
					break;		
		    		}	
		}    	
    	md.update(currentMotion);    	
    }
    /**
     * Operation
     *
     */
    public void killAll (  )
    {
        // ## Implementation preserve start class method.killAll@void@@ 
        // ## Implementation preserve end class method.killAll@void@@ 
    	killAll = true;
    }
    
    public boolean getKillStatus()
    {
    	return killAll;
    }
    /**
     * Operation
     *
     * @param cmd
     * @param mvPacket
     * @return byte[]
     */
    public byte[] createMsg ( byte cmd, short mvPacket )
    {
    	int index = 0;
		byte[] msg = new byte[3];
		
		// set the command byte
		msg[index++] = cmd;
		
		// add the mvPacket bytes
		for(byte b : ByteBuffer.allocate(2).putShort(mvPacket).array())
			msg[index++] = b;	
		
		
		return msg;
    }
    public void display()
    {
    	md.display();
    }
    
    /**
     * Operation
     *
     * @param msg
     * @return byte
     */
    public byte genCheckSum ( byte[] msg )
    {
    	byte sum = 0x00;
		// xor all the bytes but the last byte
		for(int b = 0; b < msg.length-1; b++)
			sum ^= msg[b];
		
		return sum;
    }
    
    public short getPacket()
    {
    	return mvPacket;
    }
  
}


import java.awt.event.KeyEvent;
import java.nio.ByteBuffer;
import java.awt.event.KeyEvent;

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
	private int mvPacket = 0x00;
	//private JClientUDP sct = new JClientUDP(5000);
	
    public void handleCommand (int command , boolean pressed)
    {
    	if(!pressed)
    	{
	    	switch (command)
			{
			case KeyEvent.VK_W:
				System.out.println("Stop");
				mvPacket &= 0xFFF0;
				break;
			case KeyEvent.VK_A:
				System.out.println("Stop");
				mvPacket &= 0xFFF0;
				break;
			case KeyEvent.VK_S:
				System.out.println("Stop");
				mvPacket &= 0xFFF0;
				break;
			case KeyEvent.VK_D:
				System.out.println("Stop");
				mvPacket &= 0xFFF0;
				break;
			case KeyEvent.VK_UP:
				System.out.println("Main Arm Stop");
				mvPacket &= 0xFF0F;
				break;
			case KeyEvent.VK_DOWN:
				System.out.println("Main Arm Stop");
				mvPacket &= 0xFF0F;
				break;
			case KeyEvent.VK_OPEN_BRACKET:
				System.out.println("Hopper Stop");
				mvPacket &= 0xF3FF;
				break;
			case KeyEvent.VK_CLOSE_BRACKET:
				System.out.println("Hopper Stop");
				mvPacket &= 0xF3FF;
				break;
			case KeyEvent.VK_U:
				System.out.println("L Actuator Stop");
				mvPacket &= 0xFFCF;
				break;
	
			case KeyEvent.VK_J:
				System.out.println("L Actuator Stop");
				mvPacket &= 0xFFCF;
				break;
			case KeyEvent.VK_O:
				System.out.println("R Actuator Stop");
				mvPacket &= 0xFF3F;
				break;
			case KeyEvent.VK_L:
				System.out.println("R Actuator Stop");
				mvPacket &= 0xFF3F;
				break;
			case KeyEvent.VK_LEFT:
				System.out.println("Bucket Actuator Stop");
				mvPacket &= 0xFCFF;
				break;
			case KeyEvent.VK_RIGHT:
				System.out.println("Bucket Actuator Stop");
				mvPacket &= 0xFCFF;
				break;
			case KeyEvent.VK_Q:
				//running = false;
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
					mvPacket |= 0x0005;
					break;
				case KeyEvent.VK_A:
					System.out.println("Turn Left");
					mvPacket |= 0x0006;
					break;
				case KeyEvent.VK_S:
					System.out.println("Backward");
					mvPacket |= 0x000A;
					break;
				case KeyEvent.VK_D:
					System.out.println("Turn Right");
					mvPacket |= 0x0009;
					break;
				case KeyEvent.VK_P:
					System.out.println("Emerg Stop");
					mvPacket &= 0x0000;
	//				sct.send(createMsg((byte) 0x0C, 0), ipAddr);
					break;
				case KeyEvent.VK_EQUALS:
					System.out.println("Speed Up");
		//			sct.send(createMsg((byte) 0x08, 0), ipAddr);
					break;
				case KeyEvent.VK_MINUS:
					System.out.println("Slow Down");
		//			sct.send(createMsg((byte) 0x09, 0), ipAddr);
					break;
				case KeyEvent.VK_UP:
					System.out.println("Main Arms Up");
					mvPacket |= 0x0050;
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("Main Amrs Down");
					mvPacket |= 0x00A0;
					break;
				case KeyEvent.VK_OPEN_BRACKET:
					System.out.println("Hopper Down");
					mvPacket |= 0x0800;
					break;
				case KeyEvent.VK_CLOSE_BRACKET:
					System.out.println("Hopper Up");
					mvPacket |= 0x0400;
					break;
				case KeyEvent.VK_U:
					System.out.println("L Actuator Up");
					mvPacket |= 0x0020;
					break;
				case KeyEvent.VK_J:
					System.out.println("L Actuator Down");
					mvPacket |= 0x0010;
					break;
				case KeyEvent.VK_O:
					System.out.println("R Actuator Up");
					mvPacket |= 0x0040;
					break;
				case KeyEvent.VK_L:
					System.out.println("R Actuator Down");
					mvPacket |= 0x0080;
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("Bucket In");
					mvPacket |= 0x0100;
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("Bucket Out");
					mvPacket |= 0x0200;
					break;		
		    		}	
		}
    }
    /**
     * Operation
     *
     */
    public void killAll (  )
    {
        // ## Implementation preserve start class method.killAll@void@@ 
        // ## Implementation preserve end class method.killAll@void@@ 
    }
    /**
     * Operation
     *
     * @param cmd
     * @param mvPacket
     * @return byte[]
     */
    public byte[] createMsg ( byte cmd, int mvPacket )
    {
    	int index = 0;
		byte[] msg = new byte[6];
		
		// set the command byte
		msg[index++] = cmd;
		
		// add the mvPacket bytes
		for(byte b : ByteBuffer.allocate(4).putInt(mvPacket).array())
			msg[index++] = b;
		
		// get the check sum
		msg[index] = genCheckSum(msg);
		return msg;
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
  
}


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;


public class motion {
	public static boolean running = true;
	
	private int mvPacket = 0x00;
	private JClientUDP sct;
	private String ipAddr;
	
	public motion(String ip)
	{
		ipAddr = ip;
	}
	
	public void start()
	{
		sct = new JClientUDP(5000);
		final Set<Character> pressed = new HashSet<Character>();
		
		// start listening
		if(!sct.startReceive())
			System.err.println("[ERR]startReveive: " + sct.getLastException().getMessage());

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(new Dimension(10,10));
		frame.setFocusable(true);
		frame.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent event)
			{

			}
			public void keyReleased(KeyEvent event)
			{
				pressed.remove(event.getKeyChar());

				int code = event.getKeyCode();
				switch (code)
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
					running = false;
					break; 
				}
			}
			public void keyPressed(KeyEvent event)
			{
				if(!pressed.contains(event.getKeyChar()))
				{
					pressed.add(event.getKeyChar());
					int code = event.getKeyCode();
					switch (code)
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
						sct.send(createMsg((byte) 0x0C, 0), ipAddr);
						break;
					case KeyEvent.VK_EQUALS:
						System.out.println("Speed Up");
						sct.send(createMsg((byte) 0x08, 0), ipAddr);
						break;
					case KeyEvent.VK_MINUS:
						System.out.println("Slow Down");
						sct.send(createMsg((byte) 0x09, 0), ipAddr);
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


		});


		while(running)
		{
			// send the msg to the robot
			if(!sct.send(createMsg((byte)0x00, mvPacket), ipAddr))
				System.err.println("[ERR]Send: " + sct.getLastException().getMessage());
			
			System.out.println(mvPacket);
			
			try 
			{
				Thread.sleep(10);
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
	}
	
	byte[] createMsg(byte cmd, int mvPacket)
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
	
	byte genCheckSum(byte[] msg)
	{		
		byte sum = 0x00;
		// xor all the bytes but the last byte
		for(int b = 0; b < msg.length-1; b++)
			sum ^= msg[b];
		
		return sum;
	}
}

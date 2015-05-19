import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class JClientUDP extends UDP_Sock {

	public JClientUDP(DatagramSocket socket) 
	{
		super(socket);
	}
	 public JClientUDP(int port)
	 {
		 super(port);
	 }
	 public JClientUDP(int port, int timeout)
	 {
		 super(port, timeout);
	 }

	@Override
	public void handler(DatagramPacket pckt) 
	{
		// print any and all received data
		System.out.println(pckt.getAddress().getHostAddress() + ": " + new String(pckt.getData()));
	}
	
}

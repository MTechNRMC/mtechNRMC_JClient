import java.util.Scanner;


public class JClient {
	public static void main(String[] args)
	{
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter a IP address: ");
		String ipAddr = scnr.nextLine();
		
		motion m = new motion(ipAddr);
		
		m.start();
		scnr.close();
		return;
	}
	
}

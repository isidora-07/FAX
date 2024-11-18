package Zadatak2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;

public class Server {
	
	public static String ADDRESS = "228.1.2";
	public static int PORT = 7889;
	public static void main(String[] args) throws IOException {
		
		InetAddress address = InetAddress.getByName(ADDRESS);
		MulticastSocket ms = new MulticastSocket(PORT);
		ms.setTimeToLive(2);
		ms.joinGroup(address);
		
		byte[] buffer = new byte[1024];
		String message = null;
		
		while(true) {
			Date date = new Date();
			message = date.toLocaleString();
			System.out.println("Trenutno vreme je: " + message);
			
			DatagramPacket p = new DatagramPacket(message.getBytes(), message.getBytes().length, address, PORT);
			ms.send(p);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}

package Zadatak2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import Zadatak2.Server;

public class Client {

	public static void main(String[] args) throws IOException {
		InetAddress address = InetAddress.getByName(Server.ADDRESS);
		MulticastSocket ms = new MulticastSocket(Server.PORT);
		ms.joinGroup(address);
		
		while(true) {
			byte[] buffer = new byte[1024];
			DatagramPacket p = new DatagramPacket(buffer, buffer.length);
			ms.receive(p);
			
			String message = new String(p.getData());
			System.out.println(message);
		}
	}

}

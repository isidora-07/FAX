import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("228.5.6.7");
			MulticastSocket socket = new MulticastSocket(5776);
			socket.joinGroup(address);
			
            byte[] data = new byte[1024];
			while(true)
			{
				DatagramPacket packet = new DatagramPacket(data, data.length, address, 5776);
				
				socket.receive(packet);
				
				String poruka = new String(packet.getData());
				
				System.out.println(poruka.trim());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

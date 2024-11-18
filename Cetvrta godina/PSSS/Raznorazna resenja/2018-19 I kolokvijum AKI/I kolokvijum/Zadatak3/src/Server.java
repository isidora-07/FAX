import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	public static void main(String[] args) {
		int socketPort = 5776;
		
		try {
			InetAddress adress = InetAddress.getByName("228.5.6.7");
			MulticastSocket socket = new MulticastSocket(socketPort);
			socket.setTimeToLive(1);
			socket.joinGroup(adress);

			while(true)
			{
				String message = "Radno vreme banke od 9:00 o 17:00.";
				byte[] data = message.getBytes();
				
				DatagramPacket packet = new DatagramPacket(data, data.length, adress, socketPort);
				socket.send(packet);
				Thread.sleep(5000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}

}

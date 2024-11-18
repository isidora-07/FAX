package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
	public static void main(String []args){
        try{
            byte[] buffer = new byte[256];
            DatagramSocket socket = new DatagramSocket(1234);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("Listening for packets...");
            socket.receive(packet);
            System.out.println("Packet recieved! it contains: ");
            for(byte b: packet.getData()){
                System.out.print((char) b);
            }
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

}

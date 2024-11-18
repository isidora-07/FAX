package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static void main(String []args){
        try{
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket("Helloo!".getBytes(),
            		7,InetAddress.getByName("localhost"),1234);
            socket.send(packet);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}

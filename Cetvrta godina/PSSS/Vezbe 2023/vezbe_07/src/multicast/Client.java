package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(Common.ADDRESS);
            MulticastSocket socket = new MulticastSocket(Common.PORT);
            socket.joinGroup(address);

            byte[] data = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(data, data.length, address, Common.PORT);
                socket.receive(packet);
                String message = new String(packet.getData());
                System.out.println(message);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

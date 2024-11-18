package zadatak1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(Server.ADDRESS);
            MulticastSocket ms = new MulticastSocket(Server.PORT);
            ms.joinGroup(address);

            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                ms.receive(packet);

                String primljenaPoruka = new String(packet.getData(), 0, packet.getLength());
                System.out.println("primam sa servera " + primljenaPoruka);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

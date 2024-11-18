package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Server {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(Common.ADDRESS);
            MulticastSocket socket = new MulticastSocket(Common.PORT);
            socket.setTimeToLive(1);
            socket.joinGroup(address);

            while (true) {
                String message = "Zdravo svima !!!";
                byte[] data = message.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, address, Common.PORT);
                socket.send (packet);
                System.out.println("Server je poslao podatke");
                Thread.sleep(5000);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

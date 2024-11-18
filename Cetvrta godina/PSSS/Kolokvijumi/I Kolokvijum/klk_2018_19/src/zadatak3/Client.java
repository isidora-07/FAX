package zadatak3;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(Server.PORT);

            while (true) {
                byte[] buffer = new byte[256];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String primljenaPoruka = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Primljena poruka " + primljenaPoruka);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

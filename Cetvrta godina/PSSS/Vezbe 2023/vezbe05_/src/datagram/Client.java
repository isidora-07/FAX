package datagram;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String message = "Hellloooooo";
            DatagramPacket packet = new DatagramPacket(message.getBytes(),
                    message.getBytes().length,
                    InetAddress.getByName("localhost"),
                    1234);
            socket.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

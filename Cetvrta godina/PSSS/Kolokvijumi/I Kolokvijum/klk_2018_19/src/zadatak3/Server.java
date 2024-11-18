package zadatak3;

import java.io.IOException;
import java.net.*;

public class Server {
    public static int PORT = 1234;

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();
            String vreme = "Radno vreme banke je od 9:00 do 17:00";
            DatagramPacket packet = new DatagramPacket(vreme.getBytes(), vreme.getBytes().length, address, PORT);
            while (true) {
                System.out.println("Saljem poruku: " + vreme);
                socket.send(packet);
                Thread.sleep(5000);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

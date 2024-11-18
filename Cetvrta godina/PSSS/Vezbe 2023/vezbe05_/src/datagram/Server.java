package datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1234);

            // paket koji citamo sa mreze
            // dobija niz bajtova koji zapravo citamo
            byte[] buffer = new byte[256];
            // smesta ih u bafer duzine buff.length
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("Server je pokrenut na portu 1234 i ceka na paket");

            // primamo paket sa mreze
            // kazemo soketu da prima paket
            socket.receive(packet);
            System.out.println("Paket primljen");

            for (byte b : packet.getData()) {
                System.out.println((char) b);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

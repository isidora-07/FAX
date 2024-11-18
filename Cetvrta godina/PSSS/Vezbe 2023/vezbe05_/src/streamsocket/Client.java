package streamsocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        System.out.println("Konekcija ka serveru");

        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 1235);

            System.out.println("Connected");
            socket.getOutputStream().write("HELLLOOOOOO".getBytes());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

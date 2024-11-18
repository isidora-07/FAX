package streamsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// kada smo koristili datagram i kada smo primili paket mi sa tim paketom nismo morali da radimo nista
// stream socket
// kada mi procitamo podatke sa soketom, i dalje postoji veza izmedju klijenta i servera
// ta veza postoji sve dok neko ne pozove socket.close
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1235);
            Socket socket = serverSocket.accept(); // ne kreiramo nov soket
            // server prihvati neki soket koji dobija od klijenta
            // accept poziv je blokirajuci
            byte[] buffer = new byte[256];

            socket.getInputStream().read(buffer);
            for (byte b : buffer) {
                System.out.println((char) b);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

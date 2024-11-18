package zadatak2;

import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    static int PORT = 4566;

    public static void main(String[] args) {
        try {
            System.out.println("Startovan server....");
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet;

            while (true) {
                String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
                System.out.println("Saljem: " + timeStamp);
                packet = new DatagramPacket(timeStamp.getBytes(), timeStamp.getBytes().length, address, PORT);
                socket.send(packet);
                Thread.sleep(5000);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

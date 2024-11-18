package zadatak1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Server {
    public static String ADDRESS = "228.1.2";
    public static int PORT = 8080;

    public static void main(String[] args) {
        String message = "imipmf";
        boolean flag = false;
        try {
            InetAddress address = InetAddress.getByName(ADDRESS);
            MulticastSocket ms = new MulticastSocket(PORT);
            ms.setTimeToLive(2);

            while (true) {
                if (flag == true)
                    message = rotirajString(message);

                System.out.println("saljem: " + message);
                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, address, PORT);
                ms.send(packet);
                flag = true;
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

    private static String rotirajString(String s) {
        char[] niz = s.toCharArray();
        int duzina = niz.length;
        char prviKarakter = niz[0];
        for (int i = 0; i < duzina - 1; i++) {
            niz[i] = niz[i + 1];
        }
        niz[duzina - 1] = prviKarakter;
        return new String(niz);
    }
}

package ticketsonline.clients.user;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ManifestationInfoClient extends Thread {

    private String address;
    private int port;

    public ManifestationInfoClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            InetAddress inetAddress = InetAddress.getByName(this.address);
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(inetAddress);
            byte[] data = new byte[2048];
            while(true)
            {
                DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
                socket.receive(packet);
                String message = new String(packet.getData()).trim();
                System.out.println(message);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

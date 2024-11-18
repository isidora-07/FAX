package ticketsonline.webservices;

import ticketsonline.ticketstore.Store;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;

public class ManifestationInfoService extends Thread {


    private Store ticketStore;
    private String address;
    private int port;

    public ManifestationInfoService(Store ticketStore, String address, int port) {
        this.ticketStore = ticketStore;
        this.address = address;
        this.port = port;
    }


    @Override
    public void run() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName(this.address);
            MulticastSocket socket = new MulticastSocket(this.port);
            socket.setTimeToLive(1);
            socket.joinGroup(inetAddress);
            System.out.println("Multicast service started at: " + address + ":"+port);
            while(true)
            {
                String message = Arrays.toString(ticketStore.getAvailableManifestations().toArray());
                byte[] data = message.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, this.port);
                socket.send(packet);
                Thread.sleep(1000 * 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

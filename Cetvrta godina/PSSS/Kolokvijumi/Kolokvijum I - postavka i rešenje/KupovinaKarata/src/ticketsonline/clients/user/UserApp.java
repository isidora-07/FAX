package ticketsonline.clients.user;

import ticketsonline.webservices.ManifestationInfoService;
import ticketsonline.webservices.WebService;
import ticketsonline.webservices.messages.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class UserApp {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(WebService.class.getClassLoader().getResourceAsStream("config.properties"));

        new ManifestationInfoClient(properties.getProperty("webInfoServiceAddress"),
                Integer.parseInt(properties.getProperty("webInfoServicePort"))).start();

        SocketAddress address = new InetSocketAddress(
                InetAddress.getByName( properties.getProperty("webServiceAddress")),
                Integer.parseInt(properties.getProperty("webServicePort")));

        SocketChannel channel = SocketChannel.open(address);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesite kolicinu novca na racunu.");
        String line = scanner.nextLine();
        double totalDeposit = Double.parseDouble(line.trim());

        System.out.println("Unesite naziv manifestacije i broj karata");
        line = scanner.nextLine().trim();
        String[] split = line.split(",");

        PriceRequest priceRequest = new PriceRequest(split[0].trim(), Integer.parseInt(split[1].trim()));


        ByteBuffer bufferWrite = ByteBuffer.wrap(Util.toXml(priceRequest).getBytes());
        channel.write(bufferWrite);


        ByteBuffer buffer = ByteBuffer.allocate(256);
        buffer.clear();
        StringBuilder sb = new StringBuilder();
        channel.configureBlocking(true);
        while( channel.read(buffer) > 0) {
            buffer.flip(); // priprema za citanje iz bafera
            byte[] bytes = new byte[buffer.limit()];
            buffer.get(bytes);
            sb.append(new String(bytes));
            buffer.clear();
            channel.configureBlocking(false);
        }
        String message = sb.toString();
        buffer.clear();
        channel.configureBlocking(true);

        Object response = Util.fromXml(message);
        if (response instanceof PriceResponse){
            PriceResponse priceResponse = (PriceResponse) response;
            if (priceResponse.getTotalPrice() > totalDeposit) {
                System.out.println("Nemate dovoljno novca na racunu");
                channel.close();
                return;
            }
            System.out.println("Ukupna cena karata je : " + priceResponse.getTotalPrice());
            System.out.println("Da li ste saglasni s kupovinom? DA/NE ");
            line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("DA")){
                ByTicketsRequest byTicketsRequest = new ByTicketsRequest(
                        priceRequest.getManifestationName(),
                        priceRequest.getTicketsCount(),
                        priceResponse.getTotalPrice());
                String xmlRequest = Util.toXml(byTicketsRequest);
                ByteBuffer bufferByTickets = ByteBuffer.wrap(xmlRequest.getBytes());
                channel.write(bufferByTickets);
                totalDeposit -= priceResponse.getTotalPrice();
                sb = new StringBuilder();
                buffer.clear();
                channel.configureBlocking(true);
                while( channel.read(buffer) > 0) {
                    buffer.flip(); // priprema za citanje iz bafera
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    sb.append(new String(bytes));
                    buffer.clear();
                    channel.configureBlocking(false);
                }
                message = sb.toString();
                response = Util.fromXml(message);
                if (response instanceof ByTicketsResponse){
                    ByTicketsResponse byTicketsResponse = (ByTicketsResponse) response;

                    System.out.println("Kupljnene karte");
                    System.out.println(Arrays.toString(byTicketsResponse.getTickets().toArray()));
                }


            }
        }


    }
}

package ticketsonline.webservices;

import ticketsonline.ticketstore.Manifestation;
import ticketsonline.ticketstore.Store;
import ticketsonline.ticketstore.Ticket;
import ticketsonline.webservices.admin.AdministratorService;
import ticketsonline.webservices.messages.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.*;

public class WebService {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.load(WebService.class.getClassLoader().getResourceAsStream("config.properties"));

        Store ticketStore = new Store();
        ticketStore.addNewManifestation(new Manifestation("arsenal", 1000,2000));

        AdministratorService administratorService = new AdministratorService(ticketStore);
        LocateRegistry.createRegistry(Integer.parseInt(properties.get("administratorPort").toString()));
        String administratorServiceURI = "//localhost:"+ properties.get("administratorPort")+"/Administrator";
        Naming.rebind(administratorServiceURI, administratorService);
        System.out.println("Ticket store administrator service started at: " + administratorServiceURI);

        new ManifestationInfoService(ticketStore,
                properties.getProperty("webInfoServiceAddress"),
                Integer.parseInt(properties.getProperty("webInfoServicePort"))).start();

        // NIO service
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(Integer.parseInt(properties.get("webServicePort").toString()));
        serverSocket.bind(address);
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Ticket store shop service started at: " + properties.get("webServicePort"));

        while(true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keys = selectionKeys.iterator();

            while(keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (key.isValid() == false){
                    continue;
                }
                if(key.isAcceptable()) {
                    ServerSocketChannel _serverSocketChanel = (ServerSocketChannel)key.channel();
                    SocketChannel client =  _serverSocketChanel.accept();
                    client.configureBlocking(false);

                    String ipAdress = client.socket().getInetAddress()
                            + ":" + client.socket().getPort();
                    client.register(selector, SelectionKey.OP_READ, ipAdress);
                }
                else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    StringBuilder sb = new StringBuilder();
                    while( client.read(buffer) > 0) {
                        buffer.flip(); // priprema za citanje iz bafera
                        byte[] bytes = new byte[buffer.limit()];
                        buffer.get(bytes);
                        sb.append(new String(bytes));
                        buffer.clear();
                    }
//                    System.out.println(sb.toString());
                    Object message = Util.fromXml(sb.toString());
                    if (message instanceof ByTicketsRequest){
                        System.out.println( (new Date()) + " Start of request ByTicketsRequest");
                        ByTicketsRequest byTicketsRequest = (ByTicketsRequest) message;

                        List<Ticket> tickets = ticketStore.byTickets(byTicketsRequest.getManifestationName(),
                                                    byTicketsRequest.getTicketsCount(),
                                                    byTicketsRequest.getDeposit());
                        ByTicketsResponse byTicketsResponse = new ByTicketsResponse(tickets);
                        ByteBuffer byTicketsResponseBuffer = ByteBuffer.wrap(Util.toXml(byTicketsResponse).getBytes());
                        client.write(byTicketsResponseBuffer);

                        client.close();
                        System.out.println((new Date()) + " End of request ByTicketsRequest");
                    }
                    else if (message instanceof PriceRequest){
                        System.out.println((new Date()) + " Start of request PriceRequest");
                        PriceRequest priceRequest = (PriceRequest) message;
                        double totalPrice =  ticketStore.getTotalPrice(priceRequest.getManifestationName(),
                                                    priceRequest.getTicketsCount());
                        PriceResponse priceResponse = new PriceResponse(totalPrice);
                        ByteBuffer priceResponseBuffer = ByteBuffer.wrap(Util.toXml(priceResponse).getBytes());
                        client.write(priceResponseBuffer);
                        System.out.println((new Date()) + " End of request PriceRequest");
                    }
                    else {
                        System.out.println((new Date()) + " Bad XML request");
                        BadRequest badRequest = new BadRequest();
                        ByteBuffer badRequestBuffer = ByteBuffer.wrap(Util.toXml(badRequest).getBytes());
                        client.write(badRequestBuffer);
                    }
                }
            }
        }

    }
}

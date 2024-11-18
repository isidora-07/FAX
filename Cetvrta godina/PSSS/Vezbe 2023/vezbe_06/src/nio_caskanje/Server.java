package nio_caskanje;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class Server {
    static int PORT = 9090;
    static ServerSocketChannel ssc = null;
    static Selector selector = null;

    public static void main(String[] args) throws IOException {
        ssc = ServerSocketChannel.open();
        ServerSocket socket = ssc.socket();
        InetSocketAddress isa = new InetSocketAddress(Server.PORT);
        socket.bind(isa);

        // menjamo ponasanje servera, po defaultu je blokirajuci
        ssc.configureBlocking(false); // neblokirajuci

        selector = Selector.open();
        // moramo da spojimo selektor sa nasim ss kanalon
        // to se radi pomocu metode za registraciju
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Nio server je startovan na portu " + Server.PORT);

        while (true) {
            selector.select();
            // uzimamo kljuceve, to je neki skup
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keys = selectionKeys.iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                if (key.isAcceptable()) { // prvo ulazi u ovaj if, njega registujemo sa kljucem za read pa ulazi u else deo
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    clientChannel.configureBlocking(false);
                    String ipAdresa = clientChannel.socket().getInetAddress() + ":" + clientChannel.socket().getPort();
                    clientChannel.register(selector, SelectionKey.OP_READ, ipAdresa);
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);

                    buffer.clear();
                    int read = 0;
                    StringBuilder sb = new StringBuilder();

                    while ((read = client.read(buffer)) > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.limit()];
                        buffer.get(bytes);
                        sb.append(new String(bytes));
                        buffer.clear();
                    }
                    String message = key.attachment() + " > " + sb.toString();
                    System.out.println(message);
                    sendToAll(message);
                }
            }
        }
    }

    public static void sendToAll(String message) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        for (SelectionKey selectionKey : selector.keys()) {
            if (selectionKey.isValid() && selectionKey.channel() instanceof SocketChannel) {
                SocketChannel client = (SocketChannel) selectionKey.channel();
                client.write(buffer);
                buffer.rewind();
            }
        }
    }
}

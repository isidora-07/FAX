package nio_caskanje;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketAddress address = new InetSocketAddress(InetAddress.getByName("localhost"), Server.PORT);
        SocketChannel channel = SocketChannel.open(address);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                ByteBuffer podaciSaServera = ByteBuffer.allocate(256);
                podaciSaServera.clear();

                while (true) {
                    try {
                        int read = 0;
                        StringBuilder sb = new StringBuilder();
                        channel.configureBlocking(true);
                        while ((read = channel.read(podaciSaServera)) > 0) {
                            podaciSaServera.flip();
                            byte[] bytes = new byte[podaciSaServera.limit()];
                            podaciSaServera.get(bytes);
                            sb.append(new String(bytes));
                            podaciSaServera.clear();
                            channel.configureBlocking(false);
                        }

                        System.out.println(sb.toString());
                        podaciSaServera.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        thread.start();
        System.out.println("Client startovan");

        Scanner scanner = new Scanner(System.in);
        ByteBuffer buffer = ByteBuffer.allocate(256);
        while (true) {
            String line = scanner.nextLine();
            buffer = ByteBuffer.wrap(line.getBytes());
            channel.write(buffer);
            buffer.clear();
        }
    }
}

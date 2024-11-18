package primer_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;

        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();

            HttpClientRequest httpClientRequest = new HttpClientRequest(socket);
            httpClientRequest.start();
        }

    }
}

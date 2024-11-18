package chat.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    public static int PORT = 6678;

    public Server() {
    }

    public static void main(String[] args) {
        // stream socket

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Startovan server na postu " + PORT);

            // pamtimo klasu kroz koju mozemo da pisemo do klijenta
            HashMap<String, BufferedWriter> clients = new HashMap<String, BufferedWriter>();

            while (true) {
                // blokirajuci poziv
                Socket client = serverSocket.accept();
                // kako identifikujemo nekog klijenta? imenom i soketom
                // prihvatio klijent
                // startujemo nit koja je zaduzena za tog klijenta
                ServerThreadForClient nit = new ServerThreadForClient(clients, client);
                nit.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

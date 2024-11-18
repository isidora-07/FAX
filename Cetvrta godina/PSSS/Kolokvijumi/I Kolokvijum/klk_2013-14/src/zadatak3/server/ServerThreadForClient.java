package zadatak3.server;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.Socket;

public class ServerThreadForClient extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String clientName;

    public ServerThreadForClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write("Unesi ime: ");
            // svi podaci koju su upisani u outputStream se odmah salju klijentu
            // ne ostaju u bufferu
            writer.flush();
            clientName = reader.readLine();
            Server.dodajKlijenta(clientName, writer);

            while (true) {
                writer.write("Izaberite opciju:\n1. Nabavka\n2. Prodaja\n3. Izlaz\n");
//                writer.write("1. Nabavka\n");
//                writer.write("2. Prodaja\n");
//                writer.write("3. Izlaz\n");
                writer.flush();

                String opcija = reader.readLine();

                if (opcija.equals("1")) {
                    Server.prodajProizvod(writer, reader);
                    break;
                }

                if (opcija.equals("2")) {
                    Server.kupiProizvod(writer, reader);
                    break;
                }

                if (opcija.equals("3")) {
                    Server.ukloniKlijenta(clientName);
                    writer.write("exit...\n");
                    writer.flush();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
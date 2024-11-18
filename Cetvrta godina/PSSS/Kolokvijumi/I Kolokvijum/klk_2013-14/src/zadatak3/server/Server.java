package zadatak3.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private static HashMap<String, BufferedWriter> clientWriters = new HashMap<String, BufferedWriter>();
    private static int dostupnaKolicina = 10;
    public static final int PORT = 4567;
    private static final int MAX_KLIJENT = 5;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server je startovan na portu " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(clientWriters.size());

            if (clientWriters.size() < MAX_KLIJENT) {
                ServerThreadForClient serverThread = new ServerThreadForClient(socket);
                serverThread.start();
            } else {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("Server je pun. Probaj kasnije...\n");
                writer.flush();
                socket.close();
            }
        }
    }

    public static synchronized void dodajKlijenta(String clientName, BufferedWriter writer) throws IOException {
        if (clientWriters.containsKey(clientName)) {
            writer.write("Ime vec postoji. Probaj neko drugo..\n");
            writer.flush();
            writer.close();
        } else {
            clientWriters.put(clientName, writer);
            System.out.println("Klijent " + clientName + " se konektovao na server.");
        }
    }

    public static synchronized void ukloniKlijenta(String clientName) {
        clientWriters.remove(clientName);
        System.out.println("Klijent " + clientName + " napusta server.");
    }

    public static synchronized void prodajProizvod(BufferedWriter writer, BufferedReader reader) throws IOException {
        writer.write("Trenutna kolicina koja je dostupna: " + dostupnaKolicina + "\n");
        writer.write("Unesi kolicinu koju zelis da prodas: ");
        writer.flush();
        int prodataKolicina = Integer.parseInt(reader.readLine());
        if (prodataKolicina <= dostupnaKolicina) {
            dostupnaKolicina -= prodataKolicina;
            writer.write("Transakcije je uspela. " + prodataKolicina + " prodatih stvari.\n");
            writer.flush();
        } else {
            writer.write("Transakcije nije uspela. Nema dovoljno proizvoda na stanju.\n");
            writer.flush();
        }
    }

    public static synchronized void kupiProizvod(BufferedWriter writer, BufferedReader reader) throws IOException {
        writer.write("Trenutna kolicina proizvoda koja je dostupna: " + dostupnaKolicina + "\n");
        writer.write("Unesi kolicinu proizvoda koju zelis da kupis: ");
        writer.flush();
        int kupljenoProizvoda = Integer.parseInt(reader.readLine());
        dostupnaKolicina += kupljenoProizvoda;
        writer.write("Transakcije je uspela. Uspesno ste kupili proizvod. " + kupljenoProizvoda + " kupljenih stvari.\n");
        writer.flush();
    }
}

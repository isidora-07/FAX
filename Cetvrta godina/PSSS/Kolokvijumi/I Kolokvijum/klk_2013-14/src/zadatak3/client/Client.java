package zadatak3.client;

import zadatak3.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket(InetAddress.getByName("localhost"), Server.PORT);
            System.out.println("Klijent startovan...");

//          printWriter salje poruke na server i BufferedReader prima odgovore
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("lala " + in.read());
            String userInput;
            // salje na server ime, blokirajuci poziv
            userInput = stdIn.readLine();
            System.out.println(userInput);

//            while ((userInput = stdIn.readLine()) != null) {
//                out.println(userInput);
//
//                String response = in.readLine(); // cita odgovore sa servera
//                System.out.println(response);
//                if (response.equals("KRAJ")) {
//                    break;
//                }
//            }
            clientSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

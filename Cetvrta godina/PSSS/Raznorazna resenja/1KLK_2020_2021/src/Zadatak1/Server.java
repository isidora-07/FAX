package Zadatak1;

import java.io.BufferedWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
	public static int PORT = 6789;
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("Server je startovan na portu: " + PORT);
		
		HashMap<Integer, BufferedWriter> workers = new HashMap<Integer, BufferedWriter>();
		
		while(true) {
			Socket s = ss.accept();
			
			ServerThreadForClient thread = new ServerThreadForClient(workers, s);
			thread.start();
		}
	}
}

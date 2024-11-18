package Zadatak1;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Random;
import java.util.Scanner;

public class Worker {

	public static void main(String[] args) throws Exception {
		
		PrihvatanjeProracuna pp = new PrihvatanjeProracuna();
		LocateRegistry.createRegistry(Server.PORT);
		Naming.bind("//localhost:" + Server.PORT + "//PrihvatanjeProracuna", pp);
		
		Random r = new Random();
		int brojPorta = r.nextInt() % 9000;
		brojPorta += 1000;
		
		Prijava prijava = new Prijava(brojPorta);
		Socket s = new Socket(InetAddress.getByName("localhost"), Server.PORT);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		bw.write(prijava.toString() + "\n");
		
		Scanner scanner = new Scanner(System.in);
		String line = null;
		
		while(true) {
			line = scanner.nextLine();
			while(line == "") {
				line = scanner.nextLine();
			}
			
			if(line.equals("STOP")) {
				OdjavaRadnika odjava = new OdjavaRadnika(brojPorta);
				bw.write(odjava.toString() + "\n");
			}
		}
	}

}

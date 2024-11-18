package Zadatak1;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;


public class Client {

	public static void main(String[] args) throws Exception{
		Socket s = new Socket(InetAddress.getByName("localhost"), Server.PORT);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		Thread fromServer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						String line = br.readLine();
						XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
						Object o = decoder.readObject();
						
						if(o instanceof OdbijentZahtev) {
							OdbijentZahtev odbijen = (OdbijentZahtev)o;
							System.out.println("Vas zahtev je odbijen!");
						} else if(o instanceof RezultatProracuna) {
							RezultatProracuna rezultat = (RezultatProracuna)o;
							System.out.println("Rezultat proracuna je: " + rezultat.rezultat);
						}
						
						Thread.sleep(5000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		fromServer.start();
		
		Thread toServer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ZahtevZaProracun zahtev = new ZahtevZaProracun();
				while(true) {
					Random r = new Random();
					int broj1 = r.nextInt();
					int broj2 = r.nextInt();
					
					zahtev.broj1 = broj1;
					zahtev.broj2 = broj2;
					
					try {
						bw.write(zahtev.toString() + "\n");
						
						Thread.sleep(5000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		toServer.start();
		
		fromServer.join();
		toServer.join();
	}

}

package Zadatak1;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.Naming;
import java.util.HashMap;

public class ServerThreadForClient extends Thread implements Serializable{
	
	private static final long serialVersionUID = 1L;
	HashMap<Integer, BufferedWriter> workers = null;
	Socket socket = null;
	
	public ServerThreadForClient() {
		
	}
	
	public ServerThreadForClient(HashMap<Integer, BufferedWriter> workers, Socket socket) {
		this.workers = workers;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String line = br.readLine();
			XMLDecoder decoder = new XMLDecoder(new ByteArrayInputStream(line.getBytes()));
			Object o = decoder.readObject();
			
			if(o instanceof ZahtevZaProracun) {
				ZahtevZaProracun zahtev = (ZahtevZaProracun)o;
				int brojPorta = 9001;
				
				IPrihvatanje proracun = (IPrihvatanje) Naming.lookup("rmi://localhost:" + brojPorta + "/PrihvatanjeProracuna");
				proracun.rezulatProracuna(zahtev.broj1, zahtev.broj2);
			} else if (o instanceof OdjavaRadnika) {
				OdjavaRadnika odjava = (OdjavaRadnika)o;
				int port = odjava.PORT;
				synchronized (workers) {
					workers.remove(port);
				}
			} else if (o instanceof Prijava) {
				Prijava prijava = (Prijava)o;
				synchronized (prijava) {
					if(!workers.containsKey(prijava.PORT)) {
						workers.put(prijava.PORT, bw);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

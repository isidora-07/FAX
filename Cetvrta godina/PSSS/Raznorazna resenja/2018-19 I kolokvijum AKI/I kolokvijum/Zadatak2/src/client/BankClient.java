package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import common.ProtocolMessage;

public class BankClient {

	SocketChannel soketKanal;
	ByteBuffer bufferToServer;
	
	 public BankClient() throws Exception {
		SocketAddress adresa;
		
		adresa = new InetSocketAddress(InetAddress.getByName("localhost"), 9090);
		soketKanal = SocketChannel.open(adresa);

		bufferToServer = ByteBuffer.allocate(256);

		System.out.println("Izaberite red za cekanje: 1, 2 ili 3");
		// Citaj poruke sa tastature i salji ih serveru
		Scanner scanner = new Scanner(System.in);

		String lineNewClient = scanner.nextLine().trim();
		lineNewClient = ProtocolMessage.NEW_CLIENT + ":" + lineNewClient;
		bufferToServer.put(lineNewClient.getBytes());
		bufferToServer.flip();
		soketKanal.write(bufferToServer);
		bufferToServer.clear();
		
		
		Thread nit = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("za prekid unesite napusti");
				String line = scanner.nextLine().trim();
				if (line.toLowerCase().startsWith("napusti")) {
					String messageLeave = ProtocolMessage.LEAVE_QUEUE;
		        	bufferToServer.clear();
					bufferToServer.put(messageLeave.getBytes());
					bufferToServer.flip();
					try {
						soketKanal.write(bufferToServer);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

			}

		});

		nit.start();

		soketKanal.configureBlocking(true);
		
		ByteBuffer bufferFromServer = ByteBuffer.allocate(256);
		while (true) 
		{
			try {
				soketKanal.configureBlocking(true);
				bufferFromServer.clear();
				StringBuilder sb = new StringBuilder();
				while( soketKanal.read(bufferFromServer) > 0 ) 
				{
					bufferFromServer.flip();
					byte[] bytes = new byte[bufferFromServer.limit()];
					bufferFromServer.get(bytes);
					sb.append(new String(bytes));
					bufferFromServer.clear();
					soketKanal.configureBlocking(false);
				}

				String poruka = sb.toString();
				System.out.println("primio poruku: " + poruka);
				if (poruka.contains(ProtocolMessage.START)) 
				{
					System.out.println("Pristupam salteru");
					
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Odlazim sa saltera");

					String messageFinnish = ProtocolMessage.CLIENT_FINISHED;
					bufferFromServer.clear();
					bufferToServer.put(messageFinnish.getBytes());
					bufferToServer.flip();
					soketKanal.write(bufferToServer);
					System.out.println("poslao ");

				} 
				else if (poruka.contains(ProtocolMessage.CLIENT_FINISHED_CONFIRM) || poruka.contains(ProtocolMessage.LEAVE_QUEUE_CONFIRM)) 
				{
					System.out.println("Kraj");
					break;
				} 
				else {
					System.out.println(poruka);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.exit(0); 
	}
	
	public static void main(String[] args) throws Exception {
		new BankClient();
	}
}

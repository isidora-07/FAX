package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import common.ProtocolMessage;

public class Banka {
	int port = 9090;
	ServerSocketChannel ssc;
	Selector selector;
	
	Queue<SocketChannel> red1 = new LinkedList<SocketChannel>();
	Queue<SocketChannel> red2 = new LinkedList<SocketChannel>();
	Queue<SocketChannel> red3 = new LinkedList<SocketChannel>();
	
	public Banka() {
		try {
			startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void startServer() throws Exception
	{
		ssc = ServerSocketChannel.open();
		ServerSocket serverSocket = ssc.socket();
		
		InetSocketAddress adresa = new InetSocketAddress(port);
		serverSocket.bind(adresa);
		
		ssc.configureBlocking(false);
		
		selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		while(true)
		{
			selector.select();
			
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keys = selectedKeys.iterator();
			
			while(keys.hasNext())
			{
				SelectionKey kljuc = keys.next();
				keys.remove();
				
				if(kljuc.isAcceptable())
				{
					ServerSocketChannel serverSocketKanal = (ServerSocketChannel)kljuc.channel();
					SocketChannel klijentskiKanal = serverSocketKanal.accept();
					String ip = klijentskiKanal.socket().getInetAddress() + 
								":" + klijentskiKanal.socket().getPort();
					
					ByteBuffer bafer = ByteBuffer.allocate(256);
					bafer.clear();
					StringBuilder sb = new StringBuilder();
					klijentskiKanal.configureBlocking(true);

					while( klijentskiKanal.read(bafer) > 0 ) 
					{
						bafer.flip();
						byte[] bytes = new byte[bafer.limit()];
						bafer.get(bytes);
						sb.append(new String(bytes));
						bafer.clear();
						klijentskiKanal.configureBlocking(false);
					}

					String poruka = sb.toString();
					poruka = poruka.split(":")[1];
					int red = Integer.parseInt(poruka.trim());
					if(red == 1)
					{
						red1.add(klijentskiKanal);
						sendMessageToQueue(red1, "Novi klijent", false);
					}
					else if(red == 2)
					{
						red2.add(klijentskiKanal);
						sendMessageToQueue(red2, "Novi klijent", false);
					}
					else 
					{
						red3.add(klijentskiKanal);
						sendMessageToQueue(red3, "Novi klijent", false);
					}
					klijentskiKanal.register(selector, SelectionKey.OP_READ, ip);
				}
				else if(kljuc.isValid() && kljuc.isReadable())
				{
					SocketChannel klijent = (SocketChannel)kljuc.channel();
					
					ByteBuffer bafer = ByteBuffer.allocate(256);
					bafer.clear();
					StringBuilder sb = new StringBuilder();
					
					while( klijent.read(bafer) > 0 ) 
					{
						bafer.flip();
						byte[] bytes = new byte[bafer.limit()];
						bafer.get(bytes);
						sb.append(new String(bytes));
						bafer.clear();
					}

					String poruka = sb.toString();
					
					if(poruka.startsWith(ProtocolMessage.LEAVE_QUEUE))
					{
						String message  = ProtocolMessage.LEAVE_QUEUE_CONFIRM; 
						ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
						klijent.write(buffer);
						
						removeFromQueue(klijent, "Klijent napustio red cekanja", false);
					}
					else if(poruka.startsWith(ProtocolMessage.CLIENT_FINISHED))
					{
						String messageCF  = ProtocolMessage.CLIENT_FINISHED_CONFIRM; 
						ByteBuffer bufferCF = ByteBuffer.wrap(messageCF.getBytes());
						klijent.write(bufferCF);
						
						removeFromQueue(klijent, "Klijent oslobodio salter", true);
					}
					
				}
			}
		}
	}
	
	void removeFromQueue(SocketChannel klijent, String prefix, boolean callFirst) throws IOException
	{
		if(red1.contains(klijent))
		{
			red1.remove(klijent);
			sendMessageToQueue(red1, prefix, callFirst);							
		}
		else if(red2.contains(klijent)) {
			red2.remove(klijent);
			sendMessageToQueue(red2, prefix, callFirst);
		}
		else if(red3.contains(klijent)) {
			red3.remove(klijent);
			sendMessageToQueue(red3, prefix, callFirst);
		}
		klijent.close();
	}
	
	void sendMessageToQueue(Queue<SocketChannel> queue, String prefix, boolean callFirst) throws IOException
	{
		int ispred = 0;
		for (SocketChannel klijent : queue) {
			String message  = prefix + ": broj osoba ispred Vas je " + ispred; 
			ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
			klijent.write(buffer);
			ispred++;
		}
		
		// bio je samo jedan kljent ili je oslobodjen salter
		if(ispred == 1 || callFirst)
		{
			SocketChannel klijent = queue.peek();
			if(klijent != null)
			{
				String message  = ProtocolMessage.START;
				ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
				klijent.write(buffer);
			}
		}
	}
	
	public static void main(String[] args) {
		new Banka();
	}
}

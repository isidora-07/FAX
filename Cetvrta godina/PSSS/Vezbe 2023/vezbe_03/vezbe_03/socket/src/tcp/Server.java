package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String []args){
        try{
            System.out.println("Creating server socket at port 1234...");
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Acceptiong client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client accepted, wating 5 seconds for buffer...");
            byte [] buffer = new byte[256];
            Thread.sleep(5000);
            
            System.out.println("Reading buffer...");
            socket.getInputStream().read(buffer);
            for(byte b: buffer){
                    System.out.print((char) b);
            }
            System.out.println("That is all, closing socket...");
            socket.close();
        }catch(IOException e){
            System.err.print(e.getMessage());
        }catch(InterruptedException e){
            System.err.print(e.getMessage());
        }
    }
}

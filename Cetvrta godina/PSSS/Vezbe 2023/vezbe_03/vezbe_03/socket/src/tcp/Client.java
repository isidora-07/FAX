package tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

   public static void main(String []args){
        try{
            System.out.println("Connecting to server...");
            Socket socket = new Socket(InetAddress.getByName("localhost"), 1234);

            System.out.println("Connected to server! Writing \"Hello!\" to socket's output stream...");
          
            socket.getOutputStream().write("hello!".getBytes());
            
            System.out.println("Wrote to the stream! Closing...");
            socket.close();
            
            System.out.println("Closed! Goodbye :) ");
        }catch(IOException e){
            System.err.print(e.getMessage());
        }
	}
}

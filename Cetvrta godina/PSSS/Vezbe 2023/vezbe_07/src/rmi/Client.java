package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        System.out.println("Konektovanje na server");

        try {
            IBrojac server = (IBrojac) Naming.lookup("rmi://localhost:1099/Server");
            System.out.println("Brojac: " + server.count());
            System.out.println("Brojac: " + server.count());
            System.out.println("Brojac: " + server.count());
            System.out.println("Brojac: " + server.count());


        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

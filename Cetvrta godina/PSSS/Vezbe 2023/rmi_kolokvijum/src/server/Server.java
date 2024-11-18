package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            WebService webService = new WebService();
            LocateRegistry.createRegistry(1100);
            Naming.rebind("//localhost:1100/WebService", webService);
            System.out.println("Started at: //localhost:1100/WebService");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}

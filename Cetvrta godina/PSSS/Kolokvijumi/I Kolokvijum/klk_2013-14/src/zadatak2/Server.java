package zadatak2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            Service service = new Service();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//localhost:1099/Server", service);

            System.out.println("Server je startovan na adresi //localhost:1099/Server");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

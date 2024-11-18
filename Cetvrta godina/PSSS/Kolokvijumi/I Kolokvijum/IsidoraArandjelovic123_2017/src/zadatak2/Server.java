package zadatak2;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    static int PORT = 1099;
    public static void main(String[] args) throws RemoteException {
        try {
            Service service = new Service();
            LocateRegistry.createRegistry(PORT);
            Naming.bind("//localhost:1099/Server", service);
            System.out.println("Server je startovan ");
        } catch (AlreadyBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}

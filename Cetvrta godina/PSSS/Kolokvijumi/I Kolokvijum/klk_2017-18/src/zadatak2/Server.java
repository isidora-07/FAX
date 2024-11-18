package zadatak2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws Exception {
        DistribuiraniRed distribuiraniRed = new DistribuiraniRed(1000);
        LocateRegistry.createRegistry(1099);
        Naming.rebind("//localhost:1099/Server", distribuiraniRed);

        System.out.println("Server je startovan na adresi //localhost:1099/Server");
    }
}
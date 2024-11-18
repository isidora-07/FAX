package ticketsonline.webservices.admin;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAdministrator extends Remote {

    void addManifestation(String name, int ticketsCount, double ticketPrice) throws RemoteException;
    double totalManifestationIncome(String manifestation) throws RemoteException;
}

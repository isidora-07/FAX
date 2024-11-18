package ticketsonline.webservices.admin;

import ticketsonline.ticketstore.Manifestation;
import ticketsonline.ticketstore.Store;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdministratorService extends UnicastRemoteObject implements  IAdministrator {

    private Store ticketsStore;
    public AdministratorService(Store ticketsStore) throws RemoteException{
        this.ticketsStore = ticketsStore;
    }

    @Override
    public void addManifestation(String name, int ticketsCount, double ticketPrice) throws RemoteException {
        ticketsStore.addNewManifestation(new Manifestation(name, ticketPrice,ticketsCount));
    }

    @Override
    public double totalManifestationIncome(String manifestation) throws RemoteException {
        return ticketsStore.getManifestationIncome(manifestation);
    }
}

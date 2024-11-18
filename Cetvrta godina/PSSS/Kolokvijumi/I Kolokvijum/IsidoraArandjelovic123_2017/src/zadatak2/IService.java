package zadatak2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IService extends Remote {
    List<String> preuzmiSpisak() throws RemoteException;
    void dodajRec(String rec) throws RemoteException;
    List<String> preuzmiSpisakIgraca() throws RemoteException;
    void dodajIgraca(String igrac) throws RemoteException;
}

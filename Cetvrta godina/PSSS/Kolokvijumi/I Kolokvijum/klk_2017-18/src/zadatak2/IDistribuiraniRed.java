package zadatak2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDistribuiraniRed extends Remote {
    void dodajBroj(long broj) throws RemoteException;
    long uzmiBroj() throws RemoteException;
}

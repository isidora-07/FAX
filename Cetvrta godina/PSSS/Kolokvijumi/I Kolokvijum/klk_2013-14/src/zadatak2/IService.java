package zadatak2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {
    boolean daLiJePalindrom(String message) throws RemoteException;
    double stepen(double x, double y) throws RemoteException;
}

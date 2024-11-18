package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBrojac extends Remote {
    public int count() throws RemoteException;
}

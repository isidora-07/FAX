package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Brojac extends UnicastRemoteObject implements IBrojac {
    private int count = 0;

    protected Brojac() throws RemoteException {
    }

    @Override
    public int count() throws RemoteException {
        count++;
        return count;
    }
}

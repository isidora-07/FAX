package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculation extends Remote {
    double mean(String token, double[] array) throws RemoteException;
}

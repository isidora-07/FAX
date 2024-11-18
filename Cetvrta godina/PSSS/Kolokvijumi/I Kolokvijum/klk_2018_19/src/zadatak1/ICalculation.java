package zadatak1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculation extends Remote {
    double calculateMean(String token, double[] array) throws RemoteException;
}

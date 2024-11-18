package zadatak2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Service extends UnicastRemoteObject implements IService {
    protected Service() throws RemoteException {
    }

    @Override
    public boolean daLiJePalindrom(String message) throws RemoteException {
        int duzina = message.length();
        for (int i = 0; i < duzina / 2; i++) {
            if (message.charAt(i) != message.charAt(duzina - i - 1))
                return false;
        }
        return true;
    }

    @Override
    public double stepen(double x, double y) throws RemoteException {
        return Math.pow(x, y);
    }
}

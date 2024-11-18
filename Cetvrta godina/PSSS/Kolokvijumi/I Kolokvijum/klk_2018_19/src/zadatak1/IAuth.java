package zadatak1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuth extends Remote {
    String generateToken(String username) throws RemoteException;
    void logout(String token) throws RemoteException;
}

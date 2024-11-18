package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAuth extends Remote {
    String generateToken(String username) throws RemoteException;
    void Logout(String token) throws RemoteException;
}

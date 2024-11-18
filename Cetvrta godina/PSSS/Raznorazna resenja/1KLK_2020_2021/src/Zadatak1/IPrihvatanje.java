package Zadatak1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrihvatanje extends Remote {
	long rezulatProracuna(int broj1, int broj2) throws RemoteException;
}

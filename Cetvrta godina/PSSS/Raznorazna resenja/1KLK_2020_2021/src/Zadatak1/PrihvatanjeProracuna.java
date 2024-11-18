package Zadatak1;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class PrihvatanjeProracuna extends UnicastRemoteObject implements IPrihvatanje {

	private static final long serialVersionUID = 1L;

	protected PrihvatanjeProracuna() throws RemoteException {
		super();	
	}
	
	@Override
	public long rezulatProracuna(int broj1, int broj2) throws RemoteException {
		Random r = new Random();
		int broj = r.nextInt() % 15;
		broj += 3;
		broj *= 1000;
		
		try {
			Thread.sleep(broj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return broj1 * broj2;
	}
	

}

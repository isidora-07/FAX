package zadatak2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class DistribuiraniRed extends UnicastRemoteObject implements IDistribuiraniRed {
    private List<Long> red = null;
    private int velicina;
    private int indeks;

    protected DistribuiraniRed(int velicina) throws RemoteException {
        this.velicina = velicina;
        indeks = 0;
        red = new ArrayList<Long>(velicina);
    }

    @Override
    public synchronized void dodajBroj(long broj) throws RemoteException {
        while (indeks == velicina) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        red.add(broj);
        indeks++;
        notifyAll();
    }

    @Override
    public synchronized long uzmiBroj() throws RemoteException {
        while (indeks == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long broj = red.get(0);
        red.remove(0);
        indeks--;
        notifyAll();
        return broj;
    }
}

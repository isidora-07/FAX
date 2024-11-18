package zadatak2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Service extends UnicastRemoteObject implements IService {
    private List<String> spisakTajnihReci = null;
    private List<String> spisakIgraca = null;
    protected Service() throws RemoteException {
        spisakTajnihReci = new ArrayList<String>();
        spisakIgraca = new ArrayList<String>();
    }

    @Override
    public List<String> preuzmiSpisak() throws RemoteException {
        synchronized (spisakTajnihReci) {
            return spisakTajnihReci;
        }
    }

    @Override
    public void dodajRec(String rec) throws RemoteException {
        boolean postoji = false;
        synchronized (spisakTajnihReci) {
            postoji = spisakTajnihReci.contains(rec);
            if (postoji == true) {
                System.out.println("Rec vec postoji, unesite drugu rec...");
                return;
            }

            spisakTajnihReci.add(rec);
        }
    }

    @Override
    public List<String> preuzmiSpisakIgraca() throws RemoteException {
        synchronized (spisakIgraca) {
            return spisakIgraca;
        }
    }

    public void dodajIgraca(String igrac) throws RemoteException {
        boolean postoji = false;
        synchronized (spisakIgraca) {
            postoji = spisakIgraca.contains(igrac);
            if (postoji == true) {
                System.out.println("Igrac vec postoji, upisite drugo ime...");
                return;
            }

            spisakIgraca.add(igrac);
        }
    }
}

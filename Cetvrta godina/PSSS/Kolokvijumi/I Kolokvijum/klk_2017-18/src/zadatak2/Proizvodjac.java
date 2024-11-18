package zadatak2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;

public class Proizvodjac extends Thread {
    public static void main(String[] args) throws Exception {
        System.out.println("Konektovanje na server... proizvodjac");
        IDistribuiraniRed red = (IDistribuiraniRed) Naming.lookup("rmi://localhost:1099/Server");
        Random random = new Random();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    long broj = random.nextLong(1000000);
                    try {
                        red.dodajBroj(broj);
                        System.out.println("Proizvodjac je dodao broj " + broj);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t1.start();
    }
}

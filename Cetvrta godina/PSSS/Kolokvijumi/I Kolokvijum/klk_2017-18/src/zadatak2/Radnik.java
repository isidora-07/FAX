package zadatak2;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Radnik {
    public static void main(String[] args) throws Exception {
        System.out.println("Konektovanje na server... radnik");
        IDistribuiraniRed red = (IDistribuiraniRed) Naming.lookup("rmi://localhost:1099/Server");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000/3; i++) {
                    long broj;
                    try {
                        broj = red.uzmiBroj();
                        System.out.println("Radnik je uzeo broj " + broj);
                        if (jeProst(broj)) {
                            System.out.println("Broj " + broj + " je prost.");
                        }
//                        Thread.sleep(2000);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
    }
    private static boolean jeProst(long broj) {
        if (broj < 2) {
            return false;
        }
        for (long i = 2; i <= Math.sqrt(broj); i++) {
            if (broj % i == 0) {
                return false;
            }
        }
        return true;
    }
}

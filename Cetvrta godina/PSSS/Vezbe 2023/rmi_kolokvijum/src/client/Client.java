package client;

import server.IAuth;
import server.ICalculation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        try {
            IAuth auth = (IAuth) Naming.lookup("rmi://localhost:1100/WebService");
            ICalculation calculation = (ICalculation) Naming.lookup("rmi://localhost:1100/WebService");
            ;
            String username = "isi";
            String token = auth.generateToken(username);

            double[] array = new double[1000];
            Random r = new Random();
            for (int i = 0; i < array.length; i++) {
                array[i] = r.nextDouble() * 1000;
            }

            double mean = calculation.mean(token, array);
            System.out.println("Srednja vrednost je " + mean);

            System.out.println("Odjava..");
            auth.Logout(token);

            mean = calculation.mean(token, array);
            System.out.println("Srednja vrednost je " + mean);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

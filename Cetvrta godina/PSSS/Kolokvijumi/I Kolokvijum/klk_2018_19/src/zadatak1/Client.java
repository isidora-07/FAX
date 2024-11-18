package zadatak1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            IAuth auth = (IAuth) Naming.lookup("rmi://localhost:1099/Server");
            ICalculation calculation = (ICalculation) Naming.lookup("rmi://localhost:1099/Server");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Unesi ime");
            String ime = scanner.nextLine();

            String token = auth.generateToken(ime);
            System.out.println("Vas token: " + token);

            Random r = new Random();
            double[] nizBrojeva = new double[1000];
            for (int i = 0; i < nizBrojeva.length; i++) {
                nizBrojeva[i] = r.nextDouble() * 1000;
            }

            double srVr = calculation.calculateMean(token, nizBrojeva);
            System.out.println("Srednja vrednost: " + srVr);

            auth.logout(token);
            srVr = calculation.calculateMean(token, nizBrojeva);
            System.out.println("Srednja vrednost: " + srVr);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}

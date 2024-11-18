package zadatak2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Konektovanje na server");

        try {
            IService server = (IService) Naming.lookup("rmi://localhost:1099/Server");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Unesi poruku");
            String message = scanner.nextLine();

            boolean palindrom = server.daLiJePalindrom(message);
            if(palindrom == true)
                System.out.println("Jeste palindrom");
            else
                System.out.println("Nije palindrom");

            System.out.println("Unesi x i y");
            double x = scanner.nextLong();
            double y = scanner.nextLong();

            double stepen = server.stepen(x, y);
            System.out.println("Stepen je " + stepen);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

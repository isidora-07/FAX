package zadatak2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            IService server = (IService) Naming.lookup("rmi://localhost:1099/Server");
            String tajnaRec;
            List<String> spisakTajnihReci = null;
            List<String> spisakIgraca = null;

            Scanner scanner = new Scanner(System.in);
            System.out.println("Unesite ime: ");
            String ime = scanner.nextLine();
            spisakTajnihReci = server.preuzmiSpisak();
            spisakIgraca = server.preuzmiSpisakIgraca();
            while (spisakIgraca.contains(ime)) {
                System.out.println("Ime " + ime + " postoji.");
                System.out.println("Unesite ime: ");
                ime = scanner.nextLine();
            }
            server.dodajIgraca(ime);

            System.out.println("Unesite tajnu rec: ");
            tajnaRec = scanner.nextLine();
            while (spisakTajnihReci.contains(tajnaRec)) {
                System.out.println("Rec " + tajnaRec + " postoji.");
                System.out.println("Unesite tajnu rec: ");
                tajnaRec = scanner.nextLine();
            }
            server.dodajRec(tajnaRec);

            spisakTajnihReci = server.preuzmiSpisak();
            System.out.println("Spisak tajnih reci: " + spisakTajnihReci);

            spisakIgraca = server.preuzmiSpisakIgraca();
            System.out.println("Spisak igraca " + spisakIgraca);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


}

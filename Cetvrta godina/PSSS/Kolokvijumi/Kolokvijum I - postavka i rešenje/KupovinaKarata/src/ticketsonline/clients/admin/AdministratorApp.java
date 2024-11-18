package ticketsonline.clients.admin;

import ticketsonline.webservices.WebService;
import ticketsonline.webservices.admin.IAdministrator;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Properties;
import java.util.Scanner;

public class AdministratorApp {

    public static void main(String[] args) throws IOException, NotBoundException {

        Properties properties = new Properties();
        properties.load(WebService.class.getClassLoader().getResourceAsStream("config.properties"));

        IAdministrator administrator;
        administrator = (IAdministrator) Naming.lookup("rmi://"
                + properties.getProperty("administratorAddress") + ":"
                + properties.getProperty("administratorPort")
                + "/Administrator");

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Komande: ADD, INCOME, EXIT");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("ADD")){
                System.out.println("Uneti naziv, broj karata, cenu karte odvojeno zarezom");
                line = scanner.nextLine().trim();
                String[] splitted = line.split(",");

                administrator.addManifestation(splitted[0].trim(),
                        Integer.parseInt(splitted[1].trim()),
                        Double.parseDouble(splitted[2].trim()));
            }
            else if (line.equalsIgnoreCase("INCOME")){
                System.out.println("Uneti naziv manifestacije");
                line = scanner.nextLine().trim();
                double income = administrator.totalManifestationIncome(line);
                System.out.println("Ukupna dosadasnja zarada je : " + income);
            }
            else if(line.equalsIgnoreCase("EXIT")){
                return;
            }
        }
    }
}

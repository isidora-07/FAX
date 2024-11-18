import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Pocetno  {


    private String nazivBanke;
    private String lokacija;
    private String serijskiBrojATM;

    public Pocetno(String nazivBanke,String lokacija,String serijskiBrojATM) {
        this.nazivBanke=nazivBanke;
        this.lokacija=lokacija;
        this.serijskiBrojATM=serijskiBrojATM;
    }


    public int printWelcome(){
        System.out.println("\nTrenutno ste u stanju: Početno");
        System.out.println("\t\tDobrodošli");
        System.out.println("\tBanka: "+nazivBanke);
        System.out.println("\tLokacija: "+lokacija);
        System.out.println("\tBankomat: "+serijskiBrojATM);
        System.out.println("\n\nIzaberite šta želite uraditi:\n0 - Završi program\n1 - Učitaj kartice");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void zavrsiProgram(ArrayList<String> istorija){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        String stanja="Banka: "+nazivBanke+"\nLokacija: "+lokacija+"\nBankomat: "+serijskiBrojATM+"\n\n";
        for(String s: istorija){
            stanja+=s+" -> ";
        }
        stanja+="kraj";
        try{
        FileWriter myWriter = new FileWriter("atm_"+timeStamp+".out");

        myWriter.write(stanja);
        myWriter.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}

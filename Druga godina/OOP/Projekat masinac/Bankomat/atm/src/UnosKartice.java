import java.util.ArrayList;
import java.util.Scanner;

public class UnosKartice {

    private ArrayList<Kartica> sveKartice;

    public UnosKartice(ArrayList<Kartica> sveKartice) {
        this.sveKartice = sveKartice;
    }

    public int izlistajKartice(){
        System.out.println("Trenutno ste u stanju: Unos Kartice");
        System.out.println("Izaberite redni broj kartice:\n");
        System.out.println("0 - Odustani");
        for(int i=1;i<=sveKartice.size();i++){

            System.out.println(i+" - "+sveKartice.get(i-1).getSer());
        }
        Scanner sc = new  Scanner(System.in);
        return sc.nextInt();
    }
}

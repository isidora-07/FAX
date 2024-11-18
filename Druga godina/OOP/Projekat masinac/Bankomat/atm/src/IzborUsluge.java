import java.util.Scanner;

public class IzborUsluge {
    private Kartica trenutnaKartica;
    Scanner sc = new Scanner(System.in);

    public IzborUsluge(Kartica trenutnaKartica) {

        this.trenutnaKartica = trenutnaKartica;

    }

    public int izlistajUsluge(){
        System.out.println("\nTrenutno ste u stanju: Izbor usluge");

        System.out.println("\nSerijski broj kartice: "+trenutnaKartica.getSer());
        System.out.println("Prezime korisnika kartice: "+trenutnaKartica.getPrezime());
        System.out.println("Ime korisnika kartice: "+trenutnaKartica.getIme());


        System.out.println("\nUsluge koje pružamo: ");
        System.out.println("1 - Podizanje novca");
        System.out.println("2 - Štampanje izveštaja o kartici");
        System.out.println("3 - Prekid rada");
        System.out.println("\nUnesite redni broj usluge:");

        return sc.nextInt();
    }

    public void setTrenutnaKartica(Kartica trenutnaKartica) {
        this.trenutnaKartica = trenutnaKartica;
    }
}

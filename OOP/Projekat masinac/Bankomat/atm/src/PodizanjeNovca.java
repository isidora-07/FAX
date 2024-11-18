import java.util.Scanner;

public class PodizanjeNovca {
    private Kartica trenutnaKartica;
    Scanner sc = new Scanner(System.in);

    public PodizanjeNovca(Kartica trenuntaKartica) {
        this.trenutnaKartica = trenuntaKartica;
    }

    public int zeljeniIznos(){
        System.out.println("Trenutno ste u stanju: podizanje novca");

        System.out.println("\nTrenutno stanje na kartici: "+trenutnaKartica.getSredstva());
        System.out.println("Koliko novca želite podići: ");
        return sc.nextInt();
    }

    public boolean podigniNovac(int novac){
        return trenutnaKartica.podizanjeSredstva(novac);
    }

    public void setTrenutnaKartica(Kartica trenutnaKartica) {
        this.trenutnaKartica = trenutnaKartica;
    }
}

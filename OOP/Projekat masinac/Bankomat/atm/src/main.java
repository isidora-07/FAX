public class main {
    public static void main(String[] args) {
        ATM bankomat= new ATM("Komercijalna", "Kralja Aleksandra 1","20394");
        Kartica korisnik1 = new Kartica("Petar", "Petrović",17000,bankomat.getSveKartice());
        Kartica korisnik2 = new Kartica("Marko", "Marković",50000,bankomat.getSveKartice());
        bankomat.dodajKarticu(korisnik1);
        bankomat.dodajKarticu(korisnik2);

        bankomat.pocetno();



    }

}

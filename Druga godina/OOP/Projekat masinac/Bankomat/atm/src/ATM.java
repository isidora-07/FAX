import java.util.ArrayList;

public class ATM{

    private String nazivBanke;
    private String lokacija;
    private String serijskiBrojAtm;
    //lista svih kartica sa kojima bankomat moze raditi
    private ArrayList<Kartica> sveKartice;
    //kartica koja je "u bankomatu" zato sto Bankomat moze raditi samo sa jednom karticom u jednom trenutku
    private Kartica trenutnaKartica;

    //zadatkom navedena stanja
    private Pocetno pocetno;
    private UnosKartice unosKartice;
    private IzborUsluge izborUsluge;
    private StampanjeIzvestaja stampanjeIzvestaja;
    private PodizanjeNovca podizanjeNovca;

    //cuva kroz koja stanja je prosao bankomat tokom jednog koriscenja od strane nekog korisnika
    private ArrayList<String> istorijaStanja;

    public ATM(String nazivBanke, String lokacija,String serijskiBrojAtm) {
        this.nazivBanke = nazivBanke;
        this.lokacija = lokacija;
        this.serijskiBrojAtm = serijskiBrojAtm;

        sveKartice = new ArrayList<Kartica>();
        istorijaStanja= new ArrayList<String>();

        //inicijalizacija svih stanja u kojima se moze naci bankomat, a data su postavkom zadatka
        pocetno=new Pocetno(nazivBanke,lokacija,serijskiBrojAtm);
        unosKartice = new UnosKartice(sveKartice);
        this.izborUsluge = new IzborUsluge(trenutnaKartica);
        this.stampanjeIzvestaja = new StampanjeIzvestaja(trenutnaKartica);
        this.podizanjeNovca = new PodizanjeNovca(trenutnaKartica);


    }

    public void dodajKarticu(Kartica k){
        sveKartice.add(k);
    }

    public void pocetno(){
        istorijaStanja.add("početno");
        int i=pocetno.printWelcome();

        if(i==0){
            pocetno.zavrsiProgram(istorijaStanja);
        }else if(i==1){

            this.izlistajKartice();

        }else{
            System.out.println("\nNevalidna opcija, molimo unesite validnu opciju(0 ili 1): ");
            this.pocetno();
        }


    }

    private void izlistajKartice(){
        istorijaStanja.add("unos kartice");
        int i=unosKartice.izlistajKartice();
        if(i==0){
            this.pocetno();
        }else if(i>=0&&i<=sveKartice.size()){
            trenutnaKartica=sveKartice.get(i-1);

            this.izlistajUsluge();


        }else{
            System.out.println("Pogrešan redni broj kartice! Pokušajte ponovo. ");
            this.izlistajKartice();
        }
    }

    private void izlistajUsluge(){
        izborUsluge.setTrenutnaKartica(trenutnaKartica);
        istorijaStanja.add("izbor usluge");
        int i = izborUsluge.izlistajUsluge();
        if(i==1){
            this.podigniNovac();

        }else if(i==2){

            this.stampajIzvestaj();
        }else if(i==3){
            pocetno.zavrsiProgram(istorijaStanja);

        }else{
            System.out.println("Pogrešan redni broj usluge! Pokušajte ponovo. ");
            this.izlistajUsluge();
        }

    }

    private void stampajIzvestaj(){
        stampanjeIzvestaja.setTrenutnaKartica(trenutnaKartica);

        istorijaStanja.add("štampanje izveštaja");
        boolean isPrinted=stampanjeIzvestaja.stampajIzvestaj();
        if(isPrinted){
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

        this.izlistajUsluge();}
    }

    private void podigniNovac(){
        podizanjeNovca.setTrenutnaKartica(trenutnaKartica);

        istorijaStanja.add("podizanje novca");
        int novac=podizanjeNovca.zeljeniIznos();
        boolean isValid=podizanjeNovca.podigniNovac(novac);
        while(!isValid){
            novac=podizanjeNovca.zeljeniIznos();
            isValid=podizanjeNovca.podigniNovac(novac);
        }

        //cisto stampanje stanja kroz koja smo prosli, nije navedeno u zadatku
        pocetno.zavrsiProgram(istorijaStanja);

    }

    public ArrayList<Kartica> getSveKartice() {
        return sveKartice;
    }

//    public String getNazivBanke() {
//        return nazivBanke;
//    }
//
//    public String getLokacija() {
//        return lokacija;
//    }
//
//    public String getSerijskiBrojAtm() {
//        return serijskiBrojAtm;
//    }


}


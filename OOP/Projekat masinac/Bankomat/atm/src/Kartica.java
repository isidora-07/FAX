import java.util.ArrayList;
import java.util.Random;

public class Kartica {

    private String ime;
    private String prezime;
    private String serBroj;
    private int sredstva;

    public Kartica(String ime, String prezime, int sredstva,ArrayList<Kartica> sveKartice) {

        this.ime = ime;
        this.prezime = prezime;
        this.sredstva = sredstva;
        //sveKartice predstavljaju sve kartice sa kojim Bankomat vec moze da radi(odnosno koje se nalaze u klasi ATM)
        Random rng=new Random();
        //kartica je jedinstveni broj od 16 cifara
        int duzina=16;
        boolean nijeJedinstven;
        //broj koji generisemo
        String genSerBroj;

        do{
            genSerBroj = "";
            for (int c = 0; c < duzina; c++) {
                //generisanje int od 0 do 9, stavljanje u wrapper class Integer i pozivanje metode toString
                genSerBroj += ((Integer)rng.nextInt(10)).toString();
            }

            // provera da li je novo generisani broj jedinstven
            nijeJedinstven = false;
            for (Kartica k : sveKartice) {
                if (genSerBroj.compareTo(k.getSer()) == 0) {
                    nijeJedinstven = true;
                    break;
                }
            }


        }while(nijeJedinstven);

        this.serBroj=genSerBroj;

    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getSer(){
        return this.serBroj;
    }

    public void dopuniSredstva(int novac){
        this.sredstva+=novac;
        System.out.println("Uplaćen novac na karticu, trenutno stanje: "+this.sredstva);
    }

    public boolean podizanjeSredstva(int novac){
        try{
            if(this.sredstva-novac<0)
                throw new WrongAmmountException(this.sredstva);
            else{
                this.sredstva-=novac;
                System.out.println("Trenutno stanje na kartici: "+this.getSredstva());
                return true;}
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public int getSredstva() {
        return sredstva;
    }
}

class WrongAmmountException extends Exception{
    private int stanje;
    WrongAmmountException(int sredstva){
        this.stanje=sredstva;
    }
    public String toString(){
        return("Nedovoljno sredstava na računu, trenutno stanje: "+this.stanje);
    }
}
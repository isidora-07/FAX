import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class StampanjeIzvestaja {

    private Kartica trenutnaKartica;
    Scanner sc = new Scanner(System.in);

    public StampanjeIzvestaja(Kartica trenutnaKartica) {
        this.trenutnaKartica = trenutnaKartica;
    }

    public boolean stampajIzvestaj() {
        System.out.println("Trenutno ste u stanju: Štampanje Izveštaja");

        System.out.println("Štampanje u toku...");

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        String stanja = "Ime: " + trenutnaKartica.getIme() + "\nPrezime: " + trenutnaKartica.getPrezime() + "\nSerijski broj kartice: "
                + trenutnaKartica.getSer() + "\nDatum i vreme stampanja: " + timeStamp + "\nRaspoloživa sredstva: " + trenutnaKartica.getSredstva() + "\n\n";
        try {
            FileWriter myWriter = new FileWriter("report_" + timeStamp + ".out");

            myWriter.write(stanja);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return true;
    }

    public void setTrenutnaKartica(Kartica trenutnaKartica) {
        this.trenutnaKartica = trenutnaKartica;
    }
}

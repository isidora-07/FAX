import java.util.Random;

import luka.Luka;
import luka.Skladiste;
import luka.brodovi.Brod;
import luka.brodovi.IPrevozni;
import luka.brodovi.PutnickiBrod;
import luka.brodovi.TeretniBrod;
import putnici.BiznisKlasa;
import putnici.EkonomskaKlasa;
import putnici.IPopust;
import putnici.Karta;
import putnici.Putnik;

public class Test {

	public static void main(String[] args) {

		Skladiste[] skladista = new Skladiste[] { new Skladiste(100), new Skladiste(120), new Skladiste(80) };

		Luka luka = new Luka(10, skladista);

		Putnik[] putnici = new Putnik[28];
		for (int i = 0; i < putnici.length; i++)
			putnici[i] = new Putnik("Putnik " + (i + 1));

		Random rand = new Random();
		rand.setSeed(777);

		for (int i = 0; i < putnici.length; i++) {
			Karta karta = null;
			int code = rand.nextInt(3);

			if (code != 0) {
				if (code == 1)
					karta = new BiznisKlasa(200, true, 50);
				else if (code == 2)
					karta = new EkonomskaKlasa(100, 5, 2);

				luka.prodajKartu(putnici[i], karta);
			}
		}
		
		ispisiPodatkeOPutnicima(putnici);

		Brod[] brodovi = new Brod[] { 
			new PutnickiBrod("Putnicki 1", "Kapetan putnickog 1", 4, 3),
			new PutnickiBrod("Putnicki 2", "Kapetan putnickog 2", 2, 3),
			new TeretniBrod("Teretnjak 1", "Kapetan teretnjaka 1", 5, 10, 3),
			new TeretniBrod("Teretnjak 2", "Kapetan teretnjaka 2", 6, 15, 2),
			new TeretniBrod("Teretnjak 3", "Kapetan teretnjaka 3", 3, 15, 6),
			new TeretniBrod("Teretnjak 4", "Kapetan teretnjaka 4", 7, 10, 4) 
		};

		ukrcajPutnikeNaBrodove(putnici, brodovi);
		System.out.println("Ukupan popust na svim brodovima: " + dajUkupanPopustZaSveBrodove(brodovi));
		usidriSveBrodove(luka, brodovi);

		luka.izlistajUsidreneBrodove();
		luka.izlistajSkladista();
		
		iskrcajPutnikeIzBroda(putnici, brodovi[0]);
		
		luka.izlistajUsidreneBrodove();
	}

}

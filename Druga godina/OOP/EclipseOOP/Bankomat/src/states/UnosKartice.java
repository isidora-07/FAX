package states;

import classes.Bankomat;
import classes.PlatnaKartica;

public class UnosKartice extends Stanje{

	public UnosKartice() {
		super("Unos kartice");
	}

	@Override
	public Stanje pokreni() {
		System.out.println("\nPlatne kartice:");
		for(PlatnaKartica kartica : Bankomat.platneKartice) {
			System.out.println("\t" + kartica.toString());
		}
		System.out.println("\t" + "0: Odustani");
		
		int redniBrojKartice = -1;
		PlatnaKartica odabranaKartica = null;
		while(redniBrojKartice == -1 || odabranaKartica == null) {
			try {
				redniBrojKartice = Integer.parseInt(readLine());
				try {
					if(redniBrojKartice == 0) return Stanje.getStanje(PocetnoStanje.class);
					
					odabranaKartica = pronadjiKarticu(redniBrojKartice);
					IzborUsluge izborUslugeStanje = (IzborUsluge) Stanje.getStanje(IzborUsluge.class);
					izborUslugeStanje.setKartica(odabranaKartica);
					return izborUslugeStanje;
				}catch(Exception e) {
					System.out.println("Ta kartica ne postoji, pokusajte ponovo...");
				}
			}catch(NumberFormatException e) {
				System.out.println("Molim vas unesite broj...");
			}
		}
		
		return null;
	}
	
	private PlatnaKartica pronadjiKarticu(int odabranaKartica) throws Exception {
		for(PlatnaKartica kartica : Bankomat.platneKartice) {
			if(kartica.getSerijskiBroj() == odabranaKartica) {
				return kartica;
			}
		}
		throw new Exception();
	}
}











package states;

import classes.PlatnaKartica;
import exceptions.WrongAmmountException;

public class PodizanjeNovca extends Stanje{

	private PlatnaKartica kartica;
	
	public PodizanjeNovca() {
		super("Podizanje novca");
	}
	
	public void setKartica(PlatnaKartica kartica) {
		this.kartica = kartica;
	}

	@Override
	public Stanje pokreni() {
		System.out.println("Unesite iznos koju zelite da podignete sa racuna:");
		int iznos = -1;
		while(iznos == -1) {
			try {
				iznos = Integer.parseInt(readLine());
				try {
					kartica.umanjiIznosSredstava(iznos);
					System.out.println("Uspesno ste podigli " + iznos + "din. sa racuna " + kartica.getSerijskiBroj() + ", na racunu je sada ostalo " + kartica.getIznosSredstava());
				}catch(WrongAmmountException e) {
					iznos = -1;
					System.out.println(e.getMessage());
					System.out.println("Pokusajte ponovo...");
				}
			}
			catch(NumberFormatException e) {
				iznos = -1;
				System.out.println("Molim vas unesite broj...");
			}
		}
		
		IzborUsluge izborUslugeStanje = (IzborUsluge) Stanje.getStanje(IzborUsluge.class);
		izborUslugeStanje.setKartica(this.kartica);
		return izborUslugeStanje;
	}
	
}
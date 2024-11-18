package states;

import java.util.Map;
import java.util.Map.Entry;

import classes.Bankomat;
import classes.PlatnaKartica;

public class IzborUsluge extends Stanje{
	private PlatnaKartica kartica;
	
	public IzborUsluge() {
		super("Izbor usluge");
	}
	
	public void setKartica(PlatnaKartica kartica) {
		this.kartica = kartica;
	}

	@Override
	public Stanje pokreni() {
		System.out.println("\nOdabrana je kartica:");
		System.out.println(this.kartica.toString());
		System.out.println("Odaberite vrstu usluge:");
		for(Entry<Integer, String> set : PlatnaKartica.usluge.entrySet()) {
			System.out.println("\t" + set.getKey() + ": " + set.getValue());
		}
		System.out.println("\t0: Odustani");
		
		int redniBrojUsluge = -1;
		while(redniBrojUsluge == -1) {
			try {
				redniBrojUsluge = Integer.parseInt(readLine());
				try {
					String odabranaUsluga = pronadjiUslugu(redniBrojUsluge);
					switch(odabranaUsluga) {
						case "Podizanje novca":{
							PodizanjeNovca podizanjeNovcaStanje = (PodizanjeNovca) Stanje.getStanje(PodizanjeNovca.class);
							podizanjeNovcaStanje.setKartica(this.kartica);
							return podizanjeNovcaStanje;
						}
						case "Stampanje izvestaja o kartici":{
							StampanjeIzvestajaKartice stampanjeIzvestajaKarticeStanje = (StampanjeIzvestajaKartice) Stanje.getStanje(StampanjeIzvestajaKartice.class);
							stampanjeIzvestajaKarticeStanje.setKartica(this.kartica);
							return stampanjeIzvestajaKarticeStanje;
						}
						case "Odustani":{
							UnosKartice unosKarticeStanje = (UnosKartice) Stanje.getStanje(UnosKartice.class);
							return unosKarticeStanje;
						}
					}
				}catch(Exception e) {
					System.out.println("Usluga pod rednim brojem " + redniBrojUsluge + " ne postoji, pokusajte ponovo...");
					redniBrojUsluge = -1;
				}
			}catch(NumberFormatException e) {
				System.out.println("Molim vas unesite broj...");
			}
		}
		
		return null;
	}
	
	private String pronadjiUslugu(int odabranaUsluga) throws Exception{
		if(odabranaUsluga == 0) return "Odustani";
		for(Entry<Integer, String> set : PlatnaKartica.usluge.entrySet()) {
			if(set.getKey() == odabranaUsluga) {
				return set.getValue();
			}
		}
		throw new Exception();
	}
	
	
}











package states;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import classes.Bankomat;

public class PocetnoStanje extends Stanje{
	HashMap<Integer, Stanje> narednaStanja = new HashMap<Integer,Stanje>();
	
	public PocetnoStanje(){
		super("Pocetno stanje");
		this.narednaStanja.put(1, Stanje.getStanje(UnosKartice.class));
		this.narednaStanja.put(2, Stanje.getStanje(ZavrsnoStanje.class));
	}
	
	public Stanje pokreni(){
		System.out.println("Molimo unesite vase ime:");
		Console cnsl = System.console();
		String imeKorisnika = readLine();
		
		System.out.println("Dobrodosli " + imeKorisnika);
		Stanje novoStanje = null;
		int odabranoStanje = -1;
		while(novoStanje == null || odabranoStanje == -1) {
			System.out.println("Odaberite opciju:");
			izlistajStanja();
			
			try {
				odabranoStanje = Integer.parseInt(readLine());
				try {
					novoStanje = pronadjiStanje(odabranoStanje);
				}catch(Exception e) {
					System.out.println("Ta opcija ne postoji, pokusajte ponovo...");
				}
			}catch(NumberFormatException e) {
				System.out.println("Molim vas unesite broj...");
			}
		}
		return novoStanje;
	}
	
	private void izlistajStanja() {
		narednaStanja.forEach((indeks, narednoStanje) -> {
			System.out.println("\t" + indeks + ": " + narednoStanje.nazivStanja);
		});
	}
	
	private Stanje pronadjiStanje(int odabranoStanje) throws Exception {
		for(Entry<Integer, Stanje> set : this.narednaStanja.entrySet()) {
			if(set.getKey() == odabranoStanje) {
				return set.getValue();
			}
		}
		throw new Exception();
	}
}







package classes;

import java.util.ArrayList;
import java.util.List;

import states.*;

public class Bankomat {
	private int serijskiBroj;
	private String nazivBanke;
	private String lokacija;
	private static Bankomat instanca = null;
	private Stanje trenutnoStanje;
	public static List<PlatnaKartica> platneKartice = new ArrayList<PlatnaKartica>();
	
	static {
		PlatnaKartica kartica1 = new PlatnaKartica("Ana");
		kartica1.dopuniIznosSredstava(1000);
		PlatnaKartica kartica2 = new PlatnaKartica("Milica");
		kartica2.dopuniIznosSredstava(2000);
		PlatnaKartica kartica3 = new PlatnaKartica("Nikola");
		kartica3.dopuniIznosSredstava(3000);
		
		platneKartice.add(kartica1);
		platneKartice.add(kartica2);
		platneKartice.add(kartica3);
	}
	
	private Bankomat() {
		this.trenutnoStanje = Stanje.getStanje(PocetnoStanje.class);
	}
	
	public static Bankomat getInstance() {
		if(instanca == null) {
			instanca = new Bankomat();
		}
		return instanca;
	}

	public int getSerijskiBroj() {
		return serijskiBroj;
	}

	public void setSerijskiBroj(int serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}

	public String getNazivBanke() {
		return nazivBanke;
	}

	public void setNazivBanke(String nazivBanke) {
		this.nazivBanke = nazivBanke;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.serijskiBroj + ": " + this.nazivBanke + "(" + this.lokacija + ")";
	}
	
	public void pokreniProgram() {
		while(this.trenutnoStanje != null) {
			this.trenutnoStanje = this.trenutnoStanje.pokreni();
		}
	}
}




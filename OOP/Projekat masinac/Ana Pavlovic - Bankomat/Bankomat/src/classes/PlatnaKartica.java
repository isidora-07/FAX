package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exceptions.WrongAmmountException;

public class PlatnaKartica {
	private static int globalSerijskiBroj = 1;
	private int serijskiBroj;
	private String imeKlijenta;
	private int iznosSredstava;
	public static HashMap<Integer, String> usluge = new HashMap<Integer, String>();
	
	static {
		usluge.put(1, "Podizanje novca");
		usluge.put(2, "Stampanje izvestaja o kartici");
	}
	
	public PlatnaKartica(String imeKlijenta) {
		this.imeKlijenta = imeKlijenta;
		this.serijskiBroj = globalSerijskiBroj++;
	}

	public int getSerijskiBroj() {
		return serijskiBroj;
	}

	public void setSerijskiBroj(int serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}

	public String getImeKlijenta() {
		return imeKlijenta;
	}

	public void setImeKlijenta(String imeKlijenta) {
		this.imeKlijenta = imeKlijenta;
	}

	public int getIznosSredstava() {
		return iznosSredstava;
	}

	public void setIznosSredstava(int iznosSredstava) {
		this.iznosSredstava = iznosSredstava;
	}
	
	public void dopuniIznosSredstava(int iznos) {
		this.iznosSredstava += iznos;
	}
	
	public void umanjiIznosSredstava(int iznos) throws WrongAmmountException{
		if(iznos > this.iznosSredstava) 
			throw new WrongAmmountException("Nemate dovoljno sredstva na racunu");
		else
			this.iznosSredstava -= iznos;
	}
	
	@Override
	public String toString() {
		return this.serijskiBroj + ": " + this.imeKlijenta + " (" + this.iznosSredstava + ")";
	}
}









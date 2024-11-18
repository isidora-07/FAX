package states;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import classes.Bankomat;
import classes.PlatnaKartica;

public class StampanjeIzvestajaKartice extends Stanje{

	private PlatnaKartica kartica;
	
	public StampanjeIzvestajaKartice() {
		super("Stampanje izvestaja kartice");
	}
	
	public void setKartica(PlatnaKartica kartica) {
		this.kartica = kartica;
	}

	@Override
	public Stanje pokreni() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String nazivFajla = "report_" + dtf.format(now) + ".out";
		String s = kartica.getImeKlijenta() + " - " + kartica.getSerijskiBroj() + " (" + dtf.format(now) + "): " + kartica.getIznosSredstava() + "din.";
		System.out.println("Stampanje izvestaja u toku...");
		filePrint(nazivFajla, s);
		
		IzborUsluge izborUslugeStanje = (IzborUsluge) Stanje.getStanje(IzborUsluge.class);
		izborUslugeStanje.setKartica(this.kartica);
		return izborUslugeStanje;
	}

}

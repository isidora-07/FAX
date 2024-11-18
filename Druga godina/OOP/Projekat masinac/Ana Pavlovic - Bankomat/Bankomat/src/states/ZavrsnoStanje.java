package states;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import classes.Bankomat;

public class ZavrsnoStanje extends Stanje{
	public ZavrsnoStanje() {
		super("Napusti program");
	}

	@Override
	public Stanje pokreni() {
		System.out.println("Kraj programa.");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String nazivFajla = "atm_" + dtf.format(now) + ".out";
		String s = Bankomat.getInstance().toString();
		filePrint(nazivFajla, s);
		return null;
	}
}

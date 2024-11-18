package states;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Stanje implements Cloneable{
	public String nazivStanja;
	private static List<Stanje> listaStanja = new ArrayList<Stanje>();
	
	static {
		listaStanja.add(new PodizanjeNovca());
		listaStanja.add(new IzborUsluge());
		listaStanja.add(new UnosKartice());
		listaStanja.add(new StampanjeIzvestajaKartice());
		listaStanja.add(new ZavrsnoStanje());
		listaStanja.add(new PocetnoStanje());
	}
	
	public Stanje(String nazivStanja) {
		this.nazivStanja = nazivStanja;
	}
	
	public static Stanje getStanje(Class c) {
		for(Stanje s : listaStanja) {
			if(s.getClass() == c) {
				return s.clone();
			}
		}
		return null;
	}
	
	public abstract Stanje pokreni();
	
	protected void filePrint(String nazivFajla, String text) {
		try {
			File file = new File(new File("").getAbsoluteFile() + "/src/outs/" + nazivFajla);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(text);
			bw.close();
			System.out.println("Stampanje uspesno zavrseno");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 public Stanje clone()
     {
        Object clone = null;
        try{
        	// klonira objekat, vraca referencu na drugu instancus
            clone = super.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return (Stanje) clone;
     }
	
	protected String readLine(){
	    if (System.console() != null) {
	        return System.console().readLine();
	    }
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}

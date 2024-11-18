package Zadatak1;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class ZahtevZaProracun implements Serializable{

	private static final long serialVersionUID = 1L;
	public int broj1;
	public int broj2;
	
	public ZahtevZaProracun() {
		
	}
	
	public ZahtevZaProracun(int broj1, int broj2) {
		super();
		this.broj1 = broj1;
		this.broj2 = broj2;
	}
	
	@Override
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(baos);
		encoder.writeObject(this);
		encoder.close();
		
		String s = new String(baos.toByteArray());
		s = s.replace("\n", " ");
		return s;
	}
	
}

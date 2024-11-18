package Zadatak1;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class OdbijentZahtev implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public OdbijentZahtev() {
	}
	
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

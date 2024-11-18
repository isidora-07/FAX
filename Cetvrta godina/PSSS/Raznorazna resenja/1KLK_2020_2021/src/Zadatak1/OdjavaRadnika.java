package Zadatak1;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class OdjavaRadnika implements Serializable{

	private static final long serialVersionUID = 1L;
	public int PORT;
	
	public OdjavaRadnika() {
		
	}
	
	public OdjavaRadnika(int port) {
		super();
		this.PORT = port;
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

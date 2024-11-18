package primer_06;

import java.io.Serializable;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String ime;
	String indeks;
	
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public String getIndeks() {
		return indeks;
	}
	
	public void setIndeks(String indeks) {
		this.indeks = indeks;
	}
	
	public Student() {
	}
	
	public Student(String ime, String indeks) {
		super();
		this.ime = ime;
		this.indeks = indeks;
	}

	@Override
	public String toString() {
		return "Student [ime=" + ime + ", indeks=" + indeks + "]";
	}
}

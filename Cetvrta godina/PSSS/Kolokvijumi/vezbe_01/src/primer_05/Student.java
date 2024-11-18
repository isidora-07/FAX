package primer_05;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String ime;
	String indeks;
	
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

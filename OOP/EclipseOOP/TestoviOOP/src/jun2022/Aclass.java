package jun2022;

public class Aclass {

	public static String ime;

	public static String getIme() {
		return ime;
	}

	public static void main(String[] args) {
		Aclass a = new Aclass();
		a.ime = "Milica";
		Aclass.ime = "Petar";
		System.out.println(Aclass.getIme());
		System.out.println(a.getIme());
	}

}

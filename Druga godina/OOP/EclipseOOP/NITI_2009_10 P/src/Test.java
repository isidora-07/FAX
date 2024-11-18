
public class Test {

	public static void main(String[] args) {
		Bafer bafer = new Bafer(15);

		Proizvodjac p1 = new Proizvodjac(bafer, "Proizvodjac1");
		Proizvodjac p2 = new Proizvodjac(bafer, "Proizvodjac2");

		Korisnik k1 = new Korisnik(bafer, "Korisnik1");
		Korisnik k2 = new Korisnik(bafer, "Korisnik2");

		p1.start();
		p2.start();

		k1.start();
		k2.start();
	}

}

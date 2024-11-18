
public class Korisnik extends Thread {
	Bafer bafer;

	public Korisnik(Bafer bafer, String name) {
		super(name);
		this.bafer = bafer;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				bafer.citaj(getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

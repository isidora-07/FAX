
public class Plivac extends Thread {
	Reka reka;
	int duzina;

	public Plivac(Reka reka, String name, int duzina) {
		super(name);
		this.reka = reka;
		this.duzina = duzina;
	}

	@Override
	public void run() {
		while (reka.krajPlivanja() == false) {
			try {
				reka.smanji(duzina, getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

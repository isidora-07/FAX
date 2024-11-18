
public class Proizvodjac extends Thread {
	Bafer bafer;

	public Proizvodjac(Bafer bafer, String name) {
		super(name);
		this.bafer = bafer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				bafer.upisi(getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

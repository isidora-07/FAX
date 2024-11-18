
public class Reka {
	private int ostalo;

	public Reka(int sirinaReke) {
		ostalo = sirinaReke;
	}

	public boolean krajPlivanja() {
		if (ostalo == 0)
			return true;
		return false;
	}

	public synchronized void smanji(int x, String name) throws InterruptedException {
		System.out.println(name + " pliva " + x + "m u dahu.");
		for (int i = 1; i <= x; i++) {
			ostalo -= 1;
			System.out.println(name + ", do susreta je ostalo " + ostalo);

			if (ostalo == 0) {
				System.out.println("Plivaci su se susreli");
				break;
			}
		}

		notifyAll();

		if (krajPlivanja() == false) {
			wait();
		}

		Thread.sleep(2000);
	}

}

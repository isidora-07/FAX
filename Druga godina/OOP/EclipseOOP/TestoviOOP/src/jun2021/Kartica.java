package jun2021;

public class Kartica {

	public static int brojac = 0;
	private int id;
	private int pin;

	public Kartica() {
	}

	public Kartica(int pin) {
		this.pin = pin;
		id = brojac++;
	}

	public static void main(String[] args) {
		System.out.println(Kartica.brojac);
		Kartica k = new Kartica();
		System.out.println(k.brojac);
		System.out.println(k.pin);
	}

}

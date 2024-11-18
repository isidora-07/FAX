
public class Bafer {
	private int brojPopunjenihMesta;
	private int kapacitet;

	public Bafer(int kapacitet) {
		this.kapacitet = kapacitet;
		brojPopunjenihMesta = 0;
	}

	public boolean slobodno() {
		if (brojPopunjenihMesta == kapacitet)
			return false;
		return true;
	}

	public synchronized void upisi(String ime) throws InterruptedException {
		while (slobodno() == false)
			wait();

		brojPopunjenihMesta++;
		System.out.println("Prosledjeno ime " + ime + ", novo stanje " + brojPopunjenihMesta);

		notifyAll();
	}

	public synchronized void citaj(String ime) throws InterruptedException {
		if (brojPopunjenihMesta == 0)
			wait();
		
		System.out.println("Ime " + ime + ", novo stanje: " + brojPopunjenihMesta);
		brojPopunjenihMesta--;

		notifyAll();
	}

}

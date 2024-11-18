
public class Salter {
	private int brojPrimljenihPosiljki;
	private int brojNeposlatihPosiljki;

	public Salter() {
		brojPrimljenihPosiljki = 0;
		brojNeposlatihPosiljki = 0;
	}

	public boolean brojPrimljenihSto() {
		return brojPrimljenihPosiljki >= 100;
	}

	public synchronized void primi(int brojPosiljki, String imePosiljaoca) {
		if (brojPrimljenihSto() == false) {
			brojPrimljenihPosiljki += brojPosiljki;
			brojNeposlatihPosiljki += brojPosiljki;
			System.out.println("Broj primljenih posiljki je: " + brojPrimljenihPosiljki + ", primio od " + imePosiljaoca);
			notifyAll();			
		}

	}
	
	public synchronized void posalji(int brojPaketa, String imePostara) throws InterruptedException {
		wait();
		if(brojNeposlatihPosiljki >= brojPaketa * 10) {
			// Postar salje pakete dakle smanjuje se broj neposlatih paketa
			brojNeposlatihPosiljki -= brojPaketa * 10;
			if(brojNeposlatihPosiljki < 0) brojNeposlatihPosiljki = 0;
			System.out.println("Poslato je" + (brojPaketa * 10) +  "posiljki i sada ima" + brojNeposlatihPosiljki + " neposlatih");
		}
	}
}














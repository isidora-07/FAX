import java.util.Random;

public class Posiljaoc extends Thread {
	Salter salter;

	public Posiljaoc(Salter salter, String name) {
		super(name);
		this.salter = salter;
	}

	@Override
	public void run() {
		while (salter.brojPrimljenihSto() == false) {
			Random random = new Random();
			int p = random.nextInt() % 20 + 1;
			salter.primi(p, getName());
		}
	}

}

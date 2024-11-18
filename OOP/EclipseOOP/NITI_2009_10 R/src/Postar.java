import java.util.Random;

public class Postar extends Thread {
	Salter salter;

	public Postar(Salter salter, String name) {
		super(name);
		this.salter = salter;
	}

	@Override
	public void run() {
		while (salter.brojPrimljenihSto() == false) {
			Random random = new Random();
			int p = random.nextInt() % 4 + 1;
			try {
				salter.posalji(p, getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

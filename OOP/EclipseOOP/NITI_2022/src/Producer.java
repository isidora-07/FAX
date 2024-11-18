import java.util.Random;

public class Producer extends Thread {
	Storage storage;

	public Producer(Storage storage, String name) {
		super(name);
		this.storage = storage;
	}

	@Override
	public void run() {
		Random random = new Random();
		try {
			storage.upisi(random.nextDouble(), getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


public class Consumer extends Thread {
	Storage storage;
	
	public Consumer(Storage storage, String name) {
		super(name);
		this.storage = storage;
	}
	
	@Override
	public void run() {
		try {
			storage.ispisi(getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

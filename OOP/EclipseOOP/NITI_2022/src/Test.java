
public class Test {
	public static void main(String[] args) {
		Storage storage = new Storage();

		Producer p1 = new Producer(storage, "Producer1");
		Producer p2 = new Producer(storage, "Producer2");
		Producer p3 = new Producer(storage, "Producer3");
		Producer p4 = new Producer(storage, "Producer4");

		Consumer c1 = new Consumer(storage, "Consumer1");
		Consumer c2 = new Consumer(storage, "Consumer2");

		p1.start();
		p2.start();
		c1.start();
		c2.start();
	}
}

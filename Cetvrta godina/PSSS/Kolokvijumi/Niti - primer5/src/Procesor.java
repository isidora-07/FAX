import java.util.LinkedList;
import java.util.Random;

public class Procesor {
	
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException {
		Random r = new Random();
		
		while(true) {
			synchronized (lock) {
				while(list.size() == LIMIT)
					lock.wait();
				int value = r.nextInt(1000);
				list.add(value);
				lock.notify();
			}
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			synchronized (lock) {
				while(list.size() == 0)
					lock.wait();
				System.out.println("list size " + list.size());
				int value =  list.removeFirst();
				System.out.println(", value " + value);
				lock.notify();
			}
		}
	}
	
}

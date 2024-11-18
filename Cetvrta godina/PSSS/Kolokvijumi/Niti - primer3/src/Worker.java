import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	private void addOne() {
		synchronized (lock1) {
			// u ovom delu je siguran kod 
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt());
		}
	}
	
	private void addTwo() {
		synchronized (lock2) {
			// u ovom delu je siguran kod 
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt());
		}
	}
	
	public void process() {
		for(int i=0; i<1000; i++) {
			addOne();
			addTwo();
		}
	}
	
	public void start() {
		System.out.println("starting..");
		long start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("ukupno vreme " + (end - start));
		System.out.println("list 1 " + list1.size());
		System.out.println("list 2 " + list2.size());
	}
	
	
}

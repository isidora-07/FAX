
public class Worker {
	
	int count = 0;
	
	public synchronized void increment() {
		for(int i = 0; i < 100000; i++) {
			count++;
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// blokada
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void citaj() {
		System.out.println("trenutna vrednost " + count);
		notify();
	}
	
	public void run() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				increment();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i++) {
					citaj();
				}
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
		
		System.out.println("obe niti zavrsile - count " + count);
	}
	

}

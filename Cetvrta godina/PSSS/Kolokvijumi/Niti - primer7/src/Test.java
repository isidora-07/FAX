
public class Test {
	public static void main(String[] args) {
		Runner r = new Runner();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					r.firstThread();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				r.secondThread();
			}
		});
		t2.start();
		
	
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		r.finished();
	
	}
}


public class Test {
	public static void main(String[] args) {
		Procesor procesor = new Procesor();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					procesor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		

		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					procesor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

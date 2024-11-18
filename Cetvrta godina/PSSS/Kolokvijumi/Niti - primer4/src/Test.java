import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; ; i++) {
					executorService.submit(new Processor(i));
					
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

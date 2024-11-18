import java.util.Random;

public class Processor implements Runnable {
		
	private int id;
	
	public Processor(int id) {
		super();
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("starting id " + id);
		
		Random r = new Random();
		try {
			Thread.sleep(r.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("completed " + id);
	}

}

package primer_01;

public class Runner extends Thread {
	volatile boolean running = true;
	
	@Override
	public void run() {
		while(running) {
			System.out.println("Nit aktivna");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void UgasiNit() {
		running = false;
	}
	
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}

}

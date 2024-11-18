import java.util.Scanner;

public class Procesor {
	public void produce() throws InterruptedException {
		//Thread.sleep(2000); // radi paralelno
		synchronized (this) {
			System.out.println("producer running..");
			wait(); // blokada
			System.out.println("resumed");
			
		}
	}
	
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		synchronized (this) { // lock nad trenutnim objektom
			System.out.println("waiting for return key");
			scanner.nextLine(); // blokirajuci poziv
			System.out.println("return key pressed");
			notify();
		}
		
	}
}

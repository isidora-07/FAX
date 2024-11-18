import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	int count = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private boolean condWait = true;
	
	private void increment() {
		for(int i=0; i<10000; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		// paralelno 
		
		lock.lock();
		
		// kriticni region
		while(condWait)
		{	
			System.out.println("waiting...");
			cond.await(); // await je deo contition, ne pozivamo wait, jer je to deo objekta
		}
		
		System.out.println("Nit se probudila i spremna je za dalji rad");
		try {
			increment();
		} finally {
			lock.unlock();			
		}
		
		// paralelno
	}
	
	public void secondThread() {
		lock.lock();
		
		System.out.println("unesi novi red");
		new Scanner(System.in).nextLine();
		System.out.println("unet novi red");
		
		condWait = false;
		cond.signal();
		// ukoliko neko ceka, bice probudjen
		
		try {
			increment();
		} finally {
			lock.unlock();			
		}
	}
	
	public void finished() {
		System.out.println("count " + count);
	}
}

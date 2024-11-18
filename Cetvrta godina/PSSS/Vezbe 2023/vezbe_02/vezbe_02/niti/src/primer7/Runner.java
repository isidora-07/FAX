package primer7;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// ReentrantLock
public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private boolean condWait = true;

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException
    {
//        Thread.sleep(10000);
        //
        lock.lock();

        System.out.println("Waiting ....");
        // proveri da li treba da cekas, ako treba cekaj, ako ne nastavi
        while (condWait)
            cond.await();


        System.out.println("Woken up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException
    {

        lock.lock();

        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");

        // promeni uslov pre signala
        condWait = false;
        // ukoliko neka nit ceka - probudi je
        cond.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
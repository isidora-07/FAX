package primer8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private final String[] buffer;
    private final int capacity;

    private int readPointer = 0; // odakle citamo
    private int writePointer = 0 ;
    private int count = 0; // koliko trenutno podataka imamo u bufferu

    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new String[this.capacity];

        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void deposit(String message) throws InterruptedException {
        lock.lock();

        try {
            // ne smemo da stavimo element ako je == kapacitetu
            // popunjen buffer

            while(count == capacity) {
                notFull.await(); // kada buffer nije pun javi mi
            }

            buffer[writePointer] = message;
            writePointer = (writePointer + 1) % capacity; // ovako mozemo da ispadnemo iz niza, pa nije kruzni buffer
            count++;

            // notify se koristi sa synchronized
            // kada koristimo condition koristimo signal da obavestimo
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    // kondicionali nam sluze da razmenimo poruku
    // kondicionale wrappujemo u neke uslove

    public String featch() throws InterruptedException {
        lock.lock();

        try {
            while(count == 0) {
                notEmpty.await();
            }

            String res = buffer[readPointer];
            readPointer = (readPointer + 1) % capacity;
            count--; // jedan podatak smo izbacili, sada ga smanjujemo

            notFull.signal(); // buffer sigurno nije pun
            return res;
        } finally {
            lock.unlock();
        }
    }

}

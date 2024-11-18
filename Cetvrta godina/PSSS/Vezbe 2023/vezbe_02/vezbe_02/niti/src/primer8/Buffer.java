package primer8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer
{
    private final String[] buffer;
    private final int capacity;

    private int readPointer;
    private int writePointer;
    private int count;

    private final Lock lock;

    private final Condition notFull;
    private final Condition notEmpty;

    public Buffer(int capacity)
    {
        this.capacity = capacity;
        buffer = new String[capacity];

        lock = new ReentrantLock();

        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void deposit(String data) throws InterruptedException
    {
        lock.lock();

        try {
            while (count == capacity) {
                notFull.await();
            }

            buffer[writePointer] = data;
            writePointer = (writePointer + 1) % capacity;
            count++;

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String fetch() throws InterruptedException {
        lock.lock();

        try {
            while (count == 0) {
                notEmpty.await();
            }

            String result = buffer[readPointer];
            readPointer = (readPointer + 1) % capacity;
            count--;

            notFull.signal();

            return result;
        } finally {
            lock.unlock();
        }
    }
}

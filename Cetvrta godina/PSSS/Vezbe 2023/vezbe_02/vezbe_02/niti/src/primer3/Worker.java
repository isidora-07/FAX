package primer3;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// Visestruko zakljucavanje

class Worker {

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    public void stageOne() {

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        list1.add(random.nextInt(100));

    }

    public void stageTwo() {

        list2.add(random.nextInt(100));
        synchronized (lock2) {// lock1
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public void process1() {
        for(int i=0; i<1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void process2() {
        for(int i=0; i<1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting ...");

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                process1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                process2();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }
}

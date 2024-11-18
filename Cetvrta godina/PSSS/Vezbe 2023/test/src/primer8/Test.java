package primer8;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                while(true) {
                    try {
                        Thread.sleep(r.nextInt(20));
                        buffer.deposit("Nit 1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                while(true) {
                    try {
                        Thread.sleep(r.nextInt(20));
                        buffer.deposit("Nit 2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                while(true) {
                    try {
                        Thread.sleep(4000);
                        System.out.println(buffer.featch());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package primer2;

// upotreba kljucne reci synchronized
public class Worker
{
    int count = 0;

    public synchronized void  increment()
    {
        for (int i = 0; i < 200000; i++) {
            count++;
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            wait();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized void citaj()
    {
        System.out.println("Current count: " + count);

        notify();
    }

    public void run()
    {
        Thread t1 = new Thread(new Runnable() {
            public void run() {

                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {

                for (int i = 0; i < 100; i++) {
                    citaj();

                }
            }
        });

        t2.start();
        t1.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println("Count = " + count);
    }
}


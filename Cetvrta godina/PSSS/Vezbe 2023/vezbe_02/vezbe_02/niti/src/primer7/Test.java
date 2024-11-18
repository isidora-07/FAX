package primer7;


public class Test {

    public static void main(String[] args) throws Exception
    {
        Runner r = new Runner();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    r.firstThread();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                };
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    r.secondThread();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                };
            }
        });

        t2.start();

        t1.join();
        t2.join();

        r.finished();
    }
}

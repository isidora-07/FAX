package primer4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test
{
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(10);


        new Thread(new Runnable() {

            @Override
            public void run() {
                for(int i=5; ; i++) {
                    executor.submit(new Processor(i));
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }
}
package primer1;

public class Runner extends Thread
{
    volatile boolean running = true;

    public void run()
    {
        while(running)
        {
            System.out.println("Nit radi");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void ugasiNit()
    {
        running = false;
    }
}

package primer4;


import java.util.Random;

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);

        try
        {
            Thread.sleep(new Random().nextInt(3000));
        }
        catch (InterruptedException e)
        {
        }

        System.out.println("Completed: " + id);
    }
}
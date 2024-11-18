package primer8;


public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Buffer buffer = new Buffer(10);

        new Thread(new Runnable() {
            public void run() {
                try {
                    while(true)
                    {
                        Thread.sleep(10);
                        buffer.deposit("A");
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    while(true){
                        Thread.sleep(10);
                        buffer.deposit("B");
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    while(true)
                    {
                        //Thread.sleep(100);
                        System.out.println(buffer.fetch());
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

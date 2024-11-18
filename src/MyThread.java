public class MyThread extends Thread{
    @Override
    public void run(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}

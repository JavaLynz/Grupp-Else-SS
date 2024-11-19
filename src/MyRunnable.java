public class MyRunnable implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(100);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

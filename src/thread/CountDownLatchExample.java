package thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            int id = i; // must be effectively final
            new Thread(() -> {
                try {
                    Thread.sleep(1000 * id); // FIXED
                    System.out.println("Worker " + id + " finished");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        System.out.println("Main thread waiting...");
        latch.await(); // main waits
        System.out.println("All workers completed. Main continues.");
    }
}

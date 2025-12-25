package thread;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(
                3,
                () -> System.out.println("All threads reached the barrier")
        );

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " working");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " waiting at barrier");
                barrier.await();   // all wait here
                System.out.println(Thread.currentThread().getName() + " continuing");
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < 3; i++) {
            new Thread(task).start();
        }
    }
}

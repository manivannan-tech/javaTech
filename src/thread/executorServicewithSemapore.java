
package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class executorServicewithSemapore {

    // Semaphores to control execution order
    private static final Semaphore odd = new Semaphore(1);   // ODD starts
    private static final Semaphore even = new Semaphore(0);  // EVEN waits

    public static void main(String[] args) {

        // ExecutorService with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable printOdd = () -> {
            for (int i = 1; i <= 9; i += 2) {
                try {
                    odd.acquire();                     // wait for ODD turn
                    System.out.println(
                        Thread.currentThread().getName() + " ODD  " + i
                    );
                    even.release();                    // allow EVEN
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable printEven = () -> {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    even.acquire();                    // wait for EVEN turn
                    System.out.println(
                        Thread.currentThread().getName() + " EVEN " + i
                    );
                    odd.release();                     // allow ODD
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Submit tasks to executor
        executor.submit(printOdd);
        executor.submit(printEven);

        // Graceful shutdown
        executor.shutdown();
    }
}

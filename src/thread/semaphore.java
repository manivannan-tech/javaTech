package thread;

import java.util.concurrent.Semaphore;

public class semaphore{

    // Semaphores must be inside the class
    private static Semaphore odd = new Semaphore(1);   // start with ODD
    private static Semaphore even = new Semaphore(0);  // EVEN waits

    public static void main(String[] args) {

        Runnable printOdd = () -> {
            for (int i = 1; i <= 9; i += 2) {
                try {
                    odd.acquire();                 // wait for ODD turn
                    System.out.println("ODD  " + i);
                    even.release();               // allow EVEN
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable printEven = () -> {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    even.acquire();               // wait for EVEN turn
                    System.out.println("EVEN " + i);
                    odd.release();                // allow ODD
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(printOdd, "Odd-Thread").start();
        new Thread(printEven, "Even-Thread").start();
    }
}

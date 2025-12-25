package thread;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {

    	BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
    	
        // Put initial token so ODD runs first
        queue.add(1);

        Runnable printOdd = () -> {
            for (int i = 1; i <= 9; i += 2) {
                try {
                    queue.take();                 // wait for turn
                    System.out.println("ODD " + i);
                    queue.put(1);                 // signal EVEN
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable printEven = () -> {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    queue.take();                 // wait for turn
                    System.out.println("EVEN " + i);
                    queue.put(1);                 // signal ODD
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(printOdd).start();
        new Thread(printEven).start();
    }
}

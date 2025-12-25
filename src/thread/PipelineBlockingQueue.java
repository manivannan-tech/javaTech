import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PipelineBlockingQueue {

    // Simulated data object
    static class Order {
        int id;
        String value;

        Order(int id, String value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Order{id=" + id + ", value='" + value + "'}";
        }
    }

    // Poison pill to stop pipeline
    static final Order POISON = new Order(-1, "STOP");

    public static void main(String[] args) {

        BlockingQueue<Order> inputQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Order> validatedQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Order> transformedQueue = new LinkedBlockingQueue<>();

        // ---------------- INPUT ----------------
        Runnable inputStage = () -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Order order = new Order(i, "order-" + i);
                    System.out.println("INPUT        : " + order);
                    inputQueue.put(order);
                    Thread.sleep(300);
                }
                inputQueue.put(POISON); // stop signal
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // ---------------- VALIDATION ----------------
        Runnable validationStage = () -> {
            try {
                while (true) {
                    Order order = inputQueue.take();
                    if (order == POISON) {
                        validatedQueue.put(POISON);
                        break;
                    }
                    System.out.println("VALIDATED    : " + order);
                    validatedQueue.put(order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // ---------------- TRANSFORMATION ----------------
        Runnable transformStage = () -> {
            try {
                while (true) {
                    Order order = validatedQueue.take();
                    if (order == POISON) {
                        transformedQueue.put(POISON);
                        break;
                    }
                    Order transformed =
                            new Order(order.id, order.value.toUpperCase());
                    System.out.println("TRANSFORMED  : " + transformed);
                    transformedQueue.put(transformed);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // ---------------- PERSISTENCE ----------------
        Runnable persistenceStage = () -> {
            try {
                while (true) {
                    Order order = transformedQueue.take();
                    if (order == POISON) {
                        break;
                    }
                    System.out.println("PERSISTED    : " + order);
                    Thread.sleep(200); // simulate DB write
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Start pipeline
        new Thread(inputStage).start();
        new Thread(validationStage).start();
        new Thread(transformStage).start();
        new Thread(persistenceStage).start();
    }
}

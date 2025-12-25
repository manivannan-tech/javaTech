package thread;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorPipeline {

    // ---------------- DATA MODEL ----------------
    static class Order {
        int id;
        String value;
        int retryCount = 0;

        Order(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public String toString() {
            return "Order{id=" + id + ", value='" + value + "', retry=" + retryCount + "}";
        }
    }

    static final Order POISON = new Order(-1, "STOP");
    static final int MAX_RETRIES = 2;

    public static void main(String[] args) {

        BlockingQueue<Order> inputQ = new LinkedBlockingQueue<>();
        BlockingQueue<Order> validatedQ = new LinkedBlockingQueue<>();
        BlockingQueue<Order> transformedQ = new LinkedBlockingQueue<>();
        BlockingQueue<Order> deadLetterQ = new LinkedBlockingQueue<>();

        ExecutorService inputExecutor = Executors.newSingleThreadExecutor();
        ExecutorService validationPool = Executors.newFixedThreadPool(3);   // 🔥 parallel validators
        ExecutorService transformPool = Executors.newFixedThreadPool(2);
        ExecutorService persistencePool = Executors.newSingleThreadExecutor();

        // ---------------- INPUT ----------------
        inputExecutor.submit(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    Order order = new Order(i, "order-" + i);
                    System.out.println("INPUT        : " + order);
                    inputQ.put(order);
                }
                inputQ.put(POISON);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // ---------------- VALIDATION (PARALLEL) ----------------
        Runnable validator = () -> {
            try {
                while (true) {
                    Order order = inputQ.take();

                    if (order == POISON) {
                        inputQ.put(POISON); // let others see poison
                        validatedQ.put(POISON);
                        break;
                    }

                    if (order.id % 3 == 0) { // simulate failure
                        handleRetry(order, inputQ, deadLetterQ, "VALIDATION FAILED");
                        continue;
                    }

                    System.out.println("VALIDATED    : " + order +
                            " by " + Thread.currentThread().getName());
                    validatedQ.put(order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        for (int i = 0; i < 3; i++) {
            validationPool.submit(validator);
        }

        // ---------------- TRANSFORMATION ----------------
        transformPool.submit(() -> {
            try {
                while (true) {
                    Order order = validatedQ.take();
                    if (order == POISON) {
                        transformedQ.put(POISON);
                        break;
                    }
                    order.value = order.value.toUpperCase();
                    System.out.println("TRANSFORMED  : " + order);
                    transformedQ.put(order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // ---------------- PERSISTENCE ----------------
        persistencePool.submit(() -> {
            try {
                while (true) {
                    Order order = transformedQ.take();
                    if (order == POISON) break;

                    if (order.id == 5) { // simulate DB failure
                        handleRetry(order, validatedQ, deadLetterQ, "DB FAILURE");
                        continue;
                    }

                    System.out.println("PERSISTED    : " + order);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // ---------------- DLQ MONITOR ----------------
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                while (true) {
                    Order o = deadLetterQ.take();
                    System.err.println("💀 DLQ       : " + o);
                }
            } catch (InterruptedException ignored) {}
        });
    }

    // ---------------- RETRY HANDLER ----------------
    static void handleRetry(
            Order order,
            BlockingQueue<Order> retryQueue,
            BlockingQueue<Order> dlq,
            String reason) throws InterruptedException {

        order.retryCount++;

        if (order.retryCount > MAX_RETRIES) {
            System.err.println("DLQ (" + reason + ") : " + order);
            dlq.put(order);
        } else {
            System.out.println("RETRY (" + reason + ") : " + order);
            retryQueue.put(order);
        }
    }
}

package javacode;
import java.util.Map;
import java.util.concurrent.*;
public class CompleteableFutureCache {




    // Thread-safe cache holding in-progress computations
    private static final Map<Integer, CompletableFuture<User>> cache =
            new ConcurrentHashMap<>();

    // Custom executor (recommended in production)
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(4);
    

    public static void main(String[] args) throws InterruptedException {

        // Simulate multiple threads requesting the same user
        Runnable task = () -> {
            User user = getUser(1);
            System.out.println(Thread.currentThread().getName()
                    + " got user: " + user);
        };

        // Start multiple threads simultaneously
        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }

        // Allow time for execution
        Thread.sleep(3000);
        executor.shutdown();
    }

    // Cache-enabled method
    public static User getUser(Integer id) {
        try {
            CompletableFuture<User> future =
                    cache.computeIfAbsent(id, key ->
                            CompletableFuture.supplyAsync(
                                    () -> loadUserFromDB(key), executor
                            )
                    );

            return future.join(); // wait if still loading

        } catch (Exception e) {
            cache.remove(id); // important: remove failed future
            throw e;
        }
    }

    // Simulated slow DB call
    private static User loadUserFromDB(Integer id) {
        System.out.println("Loading user " + id + " from DB...");
        try {
            Thread.sleep(1000); // simulate slow DB
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new User(id, "John Doe");
    }

    // Simple User class
    static class User {
        private final Integer id;
        private final String name;

        User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "'}";
        }
    }
}

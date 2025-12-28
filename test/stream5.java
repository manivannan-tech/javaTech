package test;



import java.util.*;
import java.util.stream.*;

class Order {
    int id;
    double amount;

    // Constructor
    Order(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    // Getters (optional)
    public int getId() { return id; }
    public double getAmount() { return amount; }
}

public class stream5 {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
            new Order(101, 250.50),
            new Order(102, 300.75),
            new Order(103, 199.99)
        );

        double totalAmount = orders.stream()
            .mapToDouble(order -> order.amount)
            .sum();

        System.out.println("Total Order Amount: " + totalAmount); // Output: 751.24
    }
}
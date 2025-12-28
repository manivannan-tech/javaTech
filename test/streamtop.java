package test;


import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    double price;

    // Constructor
    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Optional: toString for easy printing
    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

public class streamtop {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.0),
            new Product("Phone", 800.0),
            new Product("Monitor", 300.0),
            new Product("Tablet", 450.0),
            new Product("Smartwatch", 250.0)
        );

        List<Product> top3 = products.stream()
            .sorted(Comparator.comparingDouble((Product p) -> p.price).reversed())
            .limit(3)
            .collect(Collectors.toList());

        top3.forEach(System.out::println);
        // Output:
        // Laptop ($1200.0)
        // Phone ($800.0)
        // Tablet ($450.0)
    }
}
package javacode;


import javacode.Customer;

import java.util.*;

public class Customernameprint {
    public static void main(String[] args) {

        List<Customer> customers = Arrays.asList(
                new Customer(1, "John"),
                new Customer(2, "Alice"),
                new Customer(3, "Bob")
        );

        // Java 8 forEach
        customers.forEach(System.out::println);
        
    }
}

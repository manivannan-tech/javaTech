package test;


import java.util.Arrays;
import java.util.List;

public class stream1 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "John");

        names.stream()
             .filter(name -> name.length() > 3)
             .distinct()
             .sorted()
             .forEach(System.out::println);
    }
}
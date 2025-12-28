package test;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
public class printnondup {
    public static void main(String[] args) {
        String input = "banana";
        String result = input.chars() // IntStream of characters
                .mapToObj(c -> (char) c) // Convert int to Character
                .collect(Collectors.toCollection(LinkedHashSet::new)) // Keep order, remove duplicates
                .stream()
                .map(String::valueOf) // Convert Character to String
                .collect(Collectors.joining()); // Join to a single string

        System.out.println("After removing duplicates: " + result);
    }
}


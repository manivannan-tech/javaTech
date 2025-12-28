package test;



import java.util.*;
import java.util.stream.*;

public class streamgroup {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "ant", "banana", "bat", "ball", "cat");

        Map<Character, Long> result = words.stream()
            .collect(Collectors.groupingBy(
                word -> word.charAt(0),      // Group by first letter
                Collectors.counting()        // Count how many per group
            ));

        System.out.println(result); // Example Output: {a=2, b=3, c=1}
    }
}

package test;

import java.util.*;
import java.util.stream.*;

public class streamflatmap {
    public static void main(String[] args) {
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(3, 5, 7),
            Arrays.asList(1, 3, 9),
            Arrays.asList(2, 5, 6)
        );

        List<Integer> result = matrix.stream()
            .flatMap(List::stream)      // Flatten the matrix to a stream of integers
            .distinct()                 // Remove duplicates
            .sorted()                   // Sort ascending
            .collect(Collectors.toList());

        System.out.println(result); // Output: [1, 2, 3, 5, 6, 7, 9]
    }
}
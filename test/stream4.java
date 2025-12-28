package test;



import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    int score;

    // Constructor
    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Getters (optional, if using in more structured code)
    public String getName() { return name; }
    public int getScore() { return score; }
}

public class stream4 {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 80),
            new Student("Bob", 70),
            new Student("Charlie", 90),
            new Student("David", 60),
            new Student("Eve", 75)
        );

        List<String> result = students.stream()
            .filter(s -> s.score >= 75)
            .map(s -> s.name)
            .sorted()
            .collect(Collectors.toList());

        System.out.println(result); // Output: [Alice, Charlie, Eve]
    }
}
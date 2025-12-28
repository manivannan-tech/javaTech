import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        // TreeMap with natural ordering (keys sorted in ascending order)
        TreeMap<String, Integer> scores = new TreeMap<>();

        // Adding elements
        scores.put("Alice", 85);
        scores.put("Charlie", 92);
        scores.put("Bob", 78);
        scores.put("Diana", 95);

        // Displaying all entries in sorted order
        System.out.println("Student Scores (Sorted by Name):");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        // Getting a value
        System.out.println("\nCharlie's score: " + scores.get("Charlie"));

        // Removing an entry
        scores.remove("Alice");
        System.out.println("\nAfter removing Alice:");
        System.out.println(scores);

        // Checking for key
        System.out.println("\nContains Bob? " + scores.containsKey("Bob"));

        // Creating TreeMap with reverse order
        TreeMap<String, Integer> reverseScores = new TreeMap<>(scores.descendingMap());
        System.out.println("\nStudent Scores (Descending Order):");
        for (Map.Entry<String, Integer> entry : reverseScores.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
 









package javacode;


import java.util.function.Predicate;

public class predicate {
    public static void main(String[] args) {

        Predicate<Integer> isEven = num -> num % 2 == 0;

        System.out.println(isEven.test(10)); // true
        System.out.println(isEven.test(7));  // false
    }
}

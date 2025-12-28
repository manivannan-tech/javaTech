package javacode; 
import java.util.*;

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    @Override
    public String toString(){
        return name + " : " + salary;
    }
}

public class comparatortest {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 55000));
        employees.add(new Employee("David", 72000));
        employees.add(new Employee("Lisa", 65000));
        employees.add(new Employee("Adam", 72000));

        // real-time sorting by salary (ascending)
        Comparator<Employee> bySalary =
                (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary());
                
                  
        System.out.println("Sort by Salary:");
        employees.stream()
                .sorted(bySalary)
                .forEach(System.out::println);

        System.out.println("\nSort by Salary DESC then Name:");
        employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                  .reversed()
                                  .thenComparing(Employee::getName)
                )
                .forEach(System.out::println);
    }
}

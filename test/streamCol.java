package test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class streamCol {
	public static void main(String args[]) {
	
	List<String> names = Arrays.asList("apple", "banana", "apricot", "blueberry","bananan","apricot");

	Map<Character, List<String>> grouped = names.stream()
		//    .collect(Collectors.groupingBy(name -> name.ch);
	    .collect(Collectors.groupingBy(name -> name.charAt(0)));
    //collect(Collectors.groupingBy(name->name.toString()));

	System.out.println(grouped.getClass().getName());
	System.out.println(grouped);
}
}

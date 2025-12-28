package javacode;
import java.util.*;
public class streamtest123 {
	
	public static void main(String args[]) {
		List<String> str = Arrays.asList("manivannan","vallalar","test","vvvv");
		str.stream().filter(c->c.length()>5).distinct().sorted().forEach(System.out::println);
		
	}

}

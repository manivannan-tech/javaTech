package javacode;
import java.util.Arrays;
import java.util.List;

public class Streamtesting {
	
public static void main(String args[]) {
	
	List<String> names=Arrays.asList("mani","vallalar");
	names.stream().filter(c->c.length()>5).distinct().sorted().forEach(System.out::println);
	
}
}


	

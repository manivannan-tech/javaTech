package javacode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class threadsafe {
	
	public static void main(String args[]) {
		
	

	Map<Integer, String> cache = new ConcurrentHashMap<>();
	cache.putIfAbsent(1, "mani");
	String existing = cache.putIfAbsent(1, "John");
	System.out.println(cache.get(1));
	System.out.println(existing);

	if (existing == null) {
	    System.out.println("Inserted new value");
	} else {
	    System.out.println("Key already existed: " + existing);
	}

	 Map<Integer, String> cache1 = new ConcurrentHashMap<>();

     String value = cache1.computeIfAbsent(1, key -> "John");

     System.out.println("Value: " + value);
	}
}
	

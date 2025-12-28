package test;

import java.util.Map;
import java.util.TreeMap;

public class Treemap {
	
	public static void main(String args[]) {
		
		TreeMap<String,Integer> t = new TreeMap();
		//t.put(null, null);
		t.put("Vallalar",100);
		t.put("Perumal", 200);
		t.put("abc", 400);
		
		for(Map.Entry<String ,Integer> pr:t.entrySet())
		{
			System.out.println("the values "+pr.getKey() +"->" +pr.getValue());
		}
		
	}

}

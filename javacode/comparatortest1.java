package javacode;
import java.util.*;

	class customer1{
		private String name;
		public String getName() {
			return name;
		}



		private double salary;
		
		public double getSalary() {
			return salary;
		}

		public customer1(double sal,String name) {
			this.name=name;
			this.salary=sal;
	
		}
		@Override
	    public String toString(){
	        return name + " : " + salary;
	    }
	}
	
	public class comparatortest1 {
	public static void main(String args[]) {
		
		List<customer1> cust1=new ArrayList<>();
		cust1.add(new customer1(500,"manivannan"));
		cust1.add(new customer1(502,"vallalar"));
		cust1.add(new customer1(600,"shiva"));
		cust1.add(new customer1(700,"velmurugan"));
		cust1.add(new customer1(500,"arvi"));
		Comparator<customer1> cust=(c1,c2)->Double.compare(c1.getSalary(), c2.getSalary());
		cust1.stream().sorted(cust).forEach(System.out::println);
		cust1.stream().sorted(Comparator.comparing(customer1::getSalary).reversed().thenComparing(customer1::getName)).forEach(System.out::println);
		
		
		
		
		
		
		
			
		
		
		
		
		
		
		
	}

}

import java.math.BigInteger;

public class fact {
	int factresult;
	
	public static void main(String args[]) {
		int a =Integer.parseInt(args[0]);
		fact t = new fact();
		t.factorial(a);
		
	}
	
		int factorial(int a) {
		
			int result=1;
			BigInteger fact = BigInteger.ONE;
			for(int i=1;i<=a;i++) {
	            fact = fact.multiply(BigInteger.valueOf(i));
		   }
			
		System.out.println("vallalar valam"+fact);
		return result;
		
	}

}

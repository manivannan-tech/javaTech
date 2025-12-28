
import java.util.*;

class factorial1 {
    public String getPermutation(int n, int k) {
        // factorials
        int[] fact = new int[n + 1];
        System.out.println("the Factorial "+fact.length);
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
        	fact[i] = fact[i - 1] * i;
            System.out.println("the factorial value "+fact[i]);
        }

        // digits available
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
        	
        	digits.add(i);
        
        	System.out.println("the digital value "+digits.toString());
        }

        // k is 1-based; convert to 0-based
        k--;

        StringBuilder sb = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            int blockSize = fact[i - 1];
            System.out.println("the block size "+blockSize);
            int idx = k / blockSize;
            System.out.println("index based on block "+idx);
            sb.append(digits.get(idx));
            digits.remove(idx);
            
            k %= blockSize;
            System.out.println("Modulus "+k);
            System.out.println("the "+sb.toString());
        }

        System.out.println("the "+sb.toString());
        return sb.toString();
    }
    
    public static void main(String args[]) {
    	factorial1 fact1=new factorial1();
    	fact1.getPermutation(4, 7);
    }
}

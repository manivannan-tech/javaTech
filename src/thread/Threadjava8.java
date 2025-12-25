package thread;
import java.util.concurrent.CompletableFuture;

import java.util.stream.IntStream;
import java.util.function.IntPredicate;

public class Threadjava8 {
	private static Object obj =new Object();
	private static IntPredicate evenCondition =e->e%2==0;
	private static IntPredicate oddCondition =e->e%2!=0;
	
	public static void PrintevenoddResults(IntPredicate condition) {
		IntStream.rangeClosed(1, 10).filter(condition).forEach(Threadjava8::execute);
	}
	
	public static void main(String Args[]) throws InterruptedException  {
		CompletableFuture.runAsync(()->Threadjava8.PrintevenoddResults(evenCondition));
		CompletableFuture.runAsync(()->Threadjava8.PrintevenoddResults(oddCondition));
		Thread.sleep(1000);
	}
	public static void execute(int i) {
		synchronized(obj) {
			try {
			System.out.println("Thread Name " + Thread.currentThread().getName() +"count "+i);
			obj.notify();
			obj.wait();
			
		}
			catch(InterruptedException e) {
				
			}
		
	}
	}
	

}

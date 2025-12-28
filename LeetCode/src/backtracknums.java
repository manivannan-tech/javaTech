import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

//BackTracking Algoritham
public class backtracknums {
	 int [] nums= {3,4,5};
	
	public static void main(String args[]) {
	
		List<List<Integer>>  result;
		backtracknums backtrack = new backtracknums();
		result=backtrack.subset(backtrack.nums);
		System.out.println("The List values"+result);
	
	}
	
	public List<List<Integer>> subset(int [] nums){
		List<List<Integer>>  list = new ArrayList();
		
		Arrays.sort(nums);
	backtrack(list,new ArrayList<>(),nums,0 );
	return list;
	
	}
	
	public void backtrack( List<List<Integer>> list,List<Integer> templist,int [] nums,int start ){
		list.add(new ArrayList<>(templist));
		for(int i=start;i<nums.length;i++) {
			templist.add(nums[i]);
			backtrack(list,templist,nums,i+1);
			templist.remove(templist.size() - 1);
		}
		
	}

}

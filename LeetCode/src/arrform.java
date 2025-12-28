import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class arrform {
	
	int [] num = {4,6,8};
	
	public static void main(String args[]) {
		arrform arr = new arrform();
	
		List<List<Integer>> printlist =arr.subset(arr.num);
		
	System.out.println("The value is printing "+printlist);
	
		
	}

	public List<List<Integer>> subset(int [] num)
	{
		 List<List<Integer>> list = new ArrayList<>();
		 
		Arrays.sort(num);
		backtrack(list,new ArrayList<>(),num,0);
		return list;
	}
	
	private void backtrack(List<List<Integer>> list, List<Integer> tempList,int [] num,int start){
		list.add(new ArrayList<>(tempList));
		for(int i=start;i<num.length;i++) {
			tempList.add(num[i]);
			backtrack(list,tempList,num,i+1);
			tempList.remove(tempList.size()-1);
			
		}
		
	}
}

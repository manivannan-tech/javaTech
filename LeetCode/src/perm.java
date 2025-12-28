import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public class perm {
 int[] nums= {3,4,5,6};
public static void main(String args[]) {
	perm p = new perm();
	List<List<Integer>> result = p.subsets(p.nums);

    // Print it
    System.out.println(result);

}
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
    
}

private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
    list.add(new ArrayList<>(tempList));
   // System.out.println(" prepared list "+list);
    for(int i = start; i < nums.length; i++){
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
        
    }
}

}


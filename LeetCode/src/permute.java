import java.util.Arrays;

public class permute {
	 public static void main(String args[]) {
		 int [] nums = {1,2,3};
		 permute(nums,0);
		 
	 }
	 
	 public static void permute(int [] nums,int index )
	 {
	
		 if (index==nums.length) {
			 System.out.println(" the permutation combination value "+Arrays.toString(nums));
			 return;
		 }
	 
		 for (int i=index;i<nums.length;i++) {
			 swap(nums,index,i);
			 permute(nums,index+1);
			 swap(nums,index,i);
		 }
	 }
	 
	 static void swap(int [] nums,int i,int j)
	 {
		 int temp =nums[i];
		 nums[i]=nums[j];
		 nums[j]=temp;
	 }

}


import java.util.*;

class AllPermutations {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        permute(nums, 0);
    }

    static void permute(int[] nums, int index) {

        // Base case: one permutation formed
       // System.out.println(Arrays.toString(nums));
if (index == nums.length) {
            System.out.println(Arrays.toString(nums));
            return;
        }


        // Try each possibility
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);      // choose
            permute(nums, index + 1);  // explore
            swap(nums, index, i);      // un-choose (backtrack)
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

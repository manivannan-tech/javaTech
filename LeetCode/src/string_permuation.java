
public class string_permuation {
	
	public static void main(String args[]) {
		String str="ABC";
		//first convert to char array
		// follow the backtracking 
		char [] arr=str.toCharArray();
	
		permuteString(arr,0);
		
		}
	
	static void permuteString(char [] arr,int index) {
		
		if(index==arr.length) {
			System.out.println("Permutation: " + new String(arr));
            return; // IMPORTANT
			
		}
	
		for(int i=index;i<arr.length;i++) {
			swap(arr,index,i);
			permuteString(arr,index+1);
			swap(arr,index,i);
		}
	
	}
	
	static void swap(char [] arr,int i,int j) {
		char temp =arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

}

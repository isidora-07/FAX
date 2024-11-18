package integer_package;
//N = 4
//a[] = {0,3,1,2}
//Output: -1
//Explanation: N=4 and all elements from 0
//to (N-1 = 3) are present in the given
//array. Therefore output is -1.

//Input:
//N = 5
//a[] = {2,3,1,2,3}
//Output: 2 3 
//Explanation: 2 and 3 occur more than once
//in the given array.

public class FindDuplicatesInAnArray {
	static void findDuplicates(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
//			System.out.println("i = " + i + " " + arr[i] % n);
//			int suma = arr[arr[i] % n] + n;
//			System.out.println("suma = " + suma);
			arr[arr[i] % n] = arr[arr[i] % n] + n;
		}

		for (int i = 0; i < n; i++) {
			if (arr[i] >= n * 2) {
				System.out.println(i + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 0, 4, 3, 2, 7, 8, 2, 3, 1 };
		findDuplicates(arr, arr.length);
	}
}

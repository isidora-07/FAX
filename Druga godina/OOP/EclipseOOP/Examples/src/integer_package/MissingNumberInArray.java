package integer_package;
/*
 	N = 10
	A[] = {6,1,2,8,3,4,7,10,5}
	Output: 9
 */

public class MissingNumberInArray {

	public static int findNum(int arr[], int n) {
		n = n + 1; // zato sto fali jedan element zato je + 1
		int expected = n * (n + 1) / 2;
		for (int i = 0; i < arr.length; i++)
			expected -= arr[i];

		return expected;
	}

	public static void main(String[] args) {
		int[] arr = { 6, 1, 2, 8, 3, 4, 7, 10, 5 };
		System.out.println(findNum(arr, arr.length));
	}

}

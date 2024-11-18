package integer_package;
/*
	Find the first non-repeating element in a given array arr of N integers.
	Note: Array consists of only positive and negative integers and not zero.
 */

public class NonRepeatingElement {

	static int firstNonRepeating(int arr[], int n) {
		for (int i = 0; i < n; i++) {
			int j;

			for (j = 0; j < n; j++) {
				if (i != j && arr[i] == arr[j]) {
					break;
				}
			}

			if (j == n) {
				return arr[i];
			}
		}

		return -1;
	}

	static void check(int res) {
		if (res != -1)
			System.out.println(res);
		else
			System.out.println(0);
	}

	public static void main(String[] args) {
		int arr[] = { -1, 2, -1, 3, 2 };
		int res = firstNonRepeating(arr, arr.length);

		check(res);

		int arr2[] = { 1, 1, 1 };
		res = firstNonRepeating(arr2, arr2.length);

		check(res);

	}

}

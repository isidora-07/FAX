package integer_package;
import java.util.HashSet;

//Given three arrays sorted in increasing order.
//Find the elements that are common in all three arrays.

//Input:
//n1 = 6; A = {1, 5, 10, 20, 40, 80}
//n2 = 5; B = {6, 7, 20, 80, 100}
//n3 = 8; C = {3, 4, 15, 20, 30, 70, 80, 120}
//Output: 20 80
//Explanation: 20 and 80 are the only
//common elements in A, B and C.

public class CommonElements {

	static void findCommon(int a[], int b[], int c[], int n1, int n2, int n3) {
		HashSet<Integer> uset = new HashSet<>();
		HashSet<Integer> uset2 = new HashSet<>();
		HashSet<Integer> uset3 = new HashSet<>();

		for (int i = 0; i < n1; i++) {
			uset.add(a[i]);
		}

		for (int i = 0; i < n2; i++) {
			uset2.add(b[i]);
		}

		for (int i = 0; i < n3; i++) {
			if (uset.contains(c[i]) && uset2.contains(c[i])) {

				if (uset3.contains(c[i]) == false)
					System.out.print(c[i] + " ");

				uset3.add(c[i]);
			}
		}
	}

	public static void main(String[] args) {
		int arr1[] = { 1, 5, 10, 20, 40, 80 };
		int arr2[] = { 6, 7, 20, 80, 100 };
		int arr3[] = { 3, 4, 15, 20, 30, 70, 80, 120 };

		int n1 = arr1.length;
		int n2 = arr2.length;
		int n3 = arr3.length;

		findCommon(arr1, arr2, arr3, n1, n2, n3);

	}

}

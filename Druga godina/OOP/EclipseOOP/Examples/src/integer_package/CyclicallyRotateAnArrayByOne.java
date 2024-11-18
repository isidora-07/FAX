package integer_package;
import java.util.Arrays;

/*
	N = 8
	A[] = {9, 8, 7, 6, 4, 2, 1, 3}
	Output:
	3 9 8 7 6 4 2 1
 */
public class CyclicallyRotateAnArrayByOne {
	public static void rotate(int[] arr, int n) {
		int temp = arr[n - 1];

		for (int i = n - 1; i >= 1; i--) {
			arr[i] = arr[i - 1];
		}

		arr[0] = temp;
	}

	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 4, 2, 1, 3 };
		rotate(arr, arr.length);
		System.out.println(Arrays.toString(arr));
	}

}

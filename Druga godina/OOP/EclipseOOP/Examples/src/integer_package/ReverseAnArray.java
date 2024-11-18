package integer_package;
import java.util.Arrays;

// Given an array A of size N, print the reverse of it.s
// 1 2 3 4
// 4 3 2 1	

public class ReverseAnArray {

	public static void reverse(int[] arr) {
		int n = arr.length;
		int s = 0, t;
		n = n-1;
		while(s < n) {
			t = arr[s];
			arr[s] = arr[n];
			arr[n] = t;
			s++; n--;
		}
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		reverse(array);
		System.out.println(Arrays.toString(array));
	}

}

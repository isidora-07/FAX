package integer_package;
/*
	N = 5
	A[]  = {1, 345, 234, 21, 56789, 232, 3333, 8888888, -1}
	Output:
	min = 1, max = 56789
 */

public class FindMinimumAndNaximumElementInAnArray {
	public static long min(long[] arr, int n) {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			if (arr[i] < min) {
				min = (int) arr[i];
			}
		}

		return min;
	}

	public static long max(long[] arr, int n) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			if (arr[i] > max) {
				max = (int) arr[i];
			}
		}

		return max;
	}

	public static void main(String[] args) {
		long[] arr = { 7, 345, 234, 21, 56789, 232, 3333, 2 };
		System.out.println("(" + min(arr, arr.length) + ", " + max(arr, arr.length) + ")");
	}

}

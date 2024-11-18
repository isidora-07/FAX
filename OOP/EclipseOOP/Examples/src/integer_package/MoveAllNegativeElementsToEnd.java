package integer_package;
import java.util.Arrays;

/*
 * Given an unsorted array arr[] of size N having both negative and positive integers. 
 * The task is place all negative element at the end of array without changing the 
 * order of positive element and negative element.

  arr[] = {1, -1, 3, 2, -7, -5, 11, 6 }
  Output : 
   1  3  2  11  6  -1  -7  -5
 */

public class MoveAllNegativeElementsToEnd {

	public static void move(int[] arr, int n) {
		int i = 0;
		int j = 0;

		while (i < n && j < n) {
			if (arr[i] >= 0) {
				i++;
				j++;
			} else if (arr[i] < 0 && arr[j] < 0) {
				j++;
			} else if (arr[i] < 0 && arr[j] >= 0) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int arr[] = { 1, -1, 3, 2, -7, -5, 11, 6 };
		move(arr, arr.length);
		System.out.println(Arrays.toString(arr));

	}

}

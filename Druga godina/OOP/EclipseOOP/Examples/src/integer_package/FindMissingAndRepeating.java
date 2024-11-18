package integer_package;
import java.util.HashMap;
import java.util.Map;

public class FindMissingAndRepeating {
	static void find(int[] arr) {
		Map<Integer, Boolean> numberMap = new HashMap<>();

		int n = arr.length;

		for (Integer i : arr) {

			if (numberMap.get(i) == null) {
				numberMap.put(i, true);
			} else {
				System.out.println("ovaj broj se ponavlja " + i);
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if (numberMap.get(i) == null) {
				System.out.println("fali " + i);
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 6, 2, 1, 1 };
		find(arr);
		System.out.println("");
		int[] arr2 = { 1, 3, 3 };
		find(arr2);
		System.out.println("");
		int[] arr3 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 8, 9, 11 };
		find(arr3);
	}
}

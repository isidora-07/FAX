package integer_package;
import java.util.HashSet;

//	Given an array arr[] of size n, find the first repeating element.
// 	The element should occurs more than once and the index of its first occurrence 
// 	should be the smallest.

public class FirstRepeatingElement {

	static void printFirstRepeating(int arr[]) {
		// min sluzi za indeks
		int min = -1;

		// kreiranje praznog seta
		HashSet<Integer> set = new HashSet<>();

		// idemo kroz niz sa desna na levu
		for (int i = arr.length - 1; i >= 0; i--) {
			// ukoliko element vec postoji u hes mapi, update min
			if (set.contains(arr[i])) { // contains vraca true ukoliko ovaj skup sadrzi navedeni element
				min = i;
			} else {
				// dodaje elemente koji se ne ponavljaju
				set.add(arr[i]);
			}
		}

		if (min != -1)
			System.out.println("Element koji se ponavlja je " + arr[min] + " i njegov indeks je " + min);
		else
			System.out.println("Svi elementi su razliciti!");
	}

	public static void main(String[] args) {
		int arr[] = { 10, 5, 3, 4, 3, 5, 6 };
		printFirstRepeating(arr);

		int arr2[] = { 1, 2, 3, 4 };
		printFirstRepeating(arr2);
	}

}

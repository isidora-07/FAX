package string_package;

import java.util.Arrays;

public class ReverseWordsInAGivenString {

	static String[] RevString(String[] s, int n) {

		if (n % 2 == 0) {
			int j = n / 2;

			while (j <= n - 1) {
				String temp;
				temp = s[n - j - 1];
				s[n - j - 1] = s[j];
				s[j] = temp;
				j += 1;
			}
		} else {
			int j = (n / 2) + 1;
			while (j <= n - 1) {
				String temp;
				temp = s[n - j - 1];
				s[n - j - 1] = s[j];
				s[j] = temp;
				j += 1;
			}
		}

		return s;
	}

	public static void main(String[] args) {
		String s = "ana nidza isi aki ema vera pidza gogo";
		String[] words = s.split("\\s");
		System.out.println(Arrays.toString(words));

		words = RevString(words, words.length);
		s = String.join(" ", words);
		System.out.println(s);
	}

}

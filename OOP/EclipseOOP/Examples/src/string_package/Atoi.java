package string_package;

public class Atoi {

	static int myAtoi(String str) {
		int res = 0;

		for (int i = 0; i < str.length(); i++) 
			res = res * 10 + str.charAt(i) - '0';
		
		return res;
	}

	public static void main(String[] args) {
		String str = "89789";
		System.out.println(myAtoi(str));
	}

}

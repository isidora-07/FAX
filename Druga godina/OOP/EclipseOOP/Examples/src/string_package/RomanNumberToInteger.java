package string_package;

//Given a string in roman no format (s)  your task is to convert it to an integer . 
//Various symbols and their values are given below.
//I 1
//V 5
//X 10
//L 50
//C 100
//D 500
//M 1000

public class RomanNumberToInteger {

	static int value(char r) {
		if('I' == r) return 1;
		if('V' == r) return 5;
		if('X' == r) return 10;
		if('L' == r) return 50;
		if('C' == r) return 100;
		if('D' == r) return 500;
		if('M' == r) return 1000;
		return -1;
	}

	static int romanToDecimal(String str) {
		int sum = 0;
		int n = str.length();
		
		for (int i = 0; i < n; i++) 
		{
			if (i != n - 1 && value(str.charAt(i)) < value(str.charAt(i + 1))) 
			{
				sum += value(str.charAt(i + 1)) - value(str.charAt(i));
				i++;
			} 
			else 
			{
				sum += value(str.charAt(i));
			}
		}
		
		return sum;
	}

	public static void main(String[] args) {
		String str = "MCMIV";
		System.out.println(romanToDecimal(str));
	}
}

package string_package;

//I             1
//IV            4
//V             5
//IX            9
//X             10
//XL            40
//L             50
//XC            90
//C             100
//CD            400
//D             500
//CM            900 
//M             1000  

public class ConvertToRomanNo {

	static String convert(int num) {
		String m[] = { "", "M", "MM", "MMM" }; // 1000-3000
		String c[] = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }; // 100-900
		String x[] = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }; // 10-90
		String i[] = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }; // 1-9

		String hiljade = m[num / 1000];
		String stotine = c[(num % 1000) / 100];
		String desetine = x[(num % 100) / 10];
		String jedinice = i[num % 10];

		return (hiljade + stotine + desetine + jedinice);
	}

	public static void main(String[] args) {
		int number = 3549;
		System.out.println(convert(number));
	}

}

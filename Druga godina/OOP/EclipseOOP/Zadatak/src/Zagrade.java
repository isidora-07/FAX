
//(a + b) + c 
//
//a (+ )b+ c * (
//
//(((1+)*3)/4)-5

public class Zagrade {
	
	static boolean validate(String s) {
		int n = s.length();
		int c = 0;
		
		if(s.charAt(0) == ')')
			return false;
		
		for(int i=0; i<n; i++) {
			if(s.charAt(i) == '(')
				c++;
			
			if(s.charAt(i) == ')')
				c--;
			
			if(c < 0) return false;
		}
		
		if(c > 0) return false;
		if(c == 0) return true;
		

		return false;
	}

	public static void main(String[] args) {
		String s = "((()()()())";
		System.out.println(validate(s));

	}

}

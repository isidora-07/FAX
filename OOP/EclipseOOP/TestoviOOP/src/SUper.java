
public class SUper {
	public int i =0;
	public SUper(String text) {
		i = 1;
	}
	
	public static void main(String[] args) {
		Sub sub  = new Sub("Hello");
		System.out.println(sub.i);
	}
}

class Sub extends SUper{

	public Sub(String text) {
		super(text);
		i = 2;
	}
	
	
}

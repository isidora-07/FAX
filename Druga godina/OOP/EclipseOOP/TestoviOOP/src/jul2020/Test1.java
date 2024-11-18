package jul2020;


public class Test1 {
	int x = 5;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 a = new Test1();
		Test1 b = new Test1();
		Test1 c = b;
		b = a;
		a.x++;
		System.out.println(a.x);
		System.out.println(b.x);
		System.out.println(c.x);
	}

}

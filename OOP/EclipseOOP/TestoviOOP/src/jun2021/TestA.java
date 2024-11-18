package jun2021;

public class TestA {
	public static void main(String[] args) {
		A.staticX = 10;
		A a1 = new A();
		A a2 = new A();
		System.out.println("a1 = " + a1.x + ", a2 = " + a2.x);
		System.out.println(a2.x - a1.x);
		
		A a3 = a2;
		a3.x++;
		System.out.println("a3 = " + a3.x);
		System.out.println("a2 = " + a2.x);
		System.out.println("a1 = " + a1.x);
		System.out.println(a2.x - a1.x);
	}
}

class A {
	public int x;
	public static int staticX;

	public A() {
		staticX++;
		x = staticX;
	}

	public int getStaticX() {
		return staticX;
	}
}

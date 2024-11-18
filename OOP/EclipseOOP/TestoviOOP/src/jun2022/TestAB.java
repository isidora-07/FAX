package jun2022;

public class TestAB {
	public static void main(String[] args) {
		try {
			f();
		} catch (B e) {
			System.out.println("A catched");
		} finally {
			System.out.println("Done");
		}
	}

	public static void f() throws B {
		throw new B();
	}
}

class A extends Exception {
}

class B extends A {
}
package jul2020;

public class Test2 {

	public static void main(String[] args) {
		B b = (B) new A();
		b.jaSam();
	}

}

class A {

}

class B extends A {
	public void jaSam() {
		System.out.println("Ja sam B");
	}
}
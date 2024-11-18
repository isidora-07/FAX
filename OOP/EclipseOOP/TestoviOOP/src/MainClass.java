class A {
	static int i = 1111;

	static {
		i = i-- - --i;
		System.out.println("static A " + i);
	}

	{
		i = i++ + ++i;
		System.out.println("Blok A " + i);
	}
}

class B extends A {
	static {
		i = --i - i--;
		System.out.println("static B " + i);
	}

	{
		System.out.println("_Blok B " + i);
		i = ++i + i++;
		System.out.println("Blok B " + i);
	}
}

public class MainClass {
	public static void main(String[] args) {
		B b = new B();

		System.out.println(b.i);
	}
}
package jun2021;

public class TestG {
	public static void main(String[] args) {
		Aaa ref1 = new C();
		Bb ref2 = (Bb) ref1;
		System.out.println(ref1.g());

	}

}

class Aaa {
	private int f() {
		return 0;
	}

	public int g() {
		return 3;
	}
}

class Bb extends Aaa {
	private int f() {
		return 1;
	}

	public int g() {
		return f();
	}
}

class C extends Bb {
	private int f() {
		return 2;
	}
}

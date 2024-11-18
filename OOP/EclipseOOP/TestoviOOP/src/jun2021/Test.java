package jun2021;

public class Test {
	public static void main(String[] args) {
		AAKlasa.staticX = 10;
		AAKlasa a1 = new AAKlasa();
		AAKlasa a2 = a1;
		a2.x += 10;
		System.out.println("a1 = " + a1.x);
		System.out.println("a2 = " + a2.x);
		System.out.println(a2.x - a2.x);
		a2 = new AAKlasa();
		System.out.println("a2 = " + a2.x + ", a1 = " + a1.x);
		System.out.println(a2.x - a1.x);

	}

}

class AAKlasa {
	public int x;
	public static int staticX;

	public AAKlasa() {
		staticX++;
		x = staticX;
	}

	public int getStaticC() {
		return staticX;
	}
}

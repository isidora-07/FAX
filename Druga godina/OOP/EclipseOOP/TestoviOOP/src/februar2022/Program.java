package februar2022;

class Krug {
	public double r;
	public int x, y;

	public Krug(int x1, int y1) {
		x = x1;
		y = y1;
	}

	public int getX() {
		return x;
	}
}

class A {
}

class B extends A {
	public void jaSam() {
		System.out.println("Ja sam B");
	}
}

public class Program {
	public static void main(String[] args) {

		Krug k1 = new Krug(1, 1);
		Krug k2 = new Krug(1, 1);
		Krug k3 = k1;
		k1.x++;
		k2.x++;
		k3.x++;

		if (k1 == k2)
			System.out.println("k1 == k2");
		else
			System.out.println("k1 != k2");
		System.out.println("k3.x = " + k3.x);

		
		A b = new B();
		((B) b).jaSam();  
	}

}

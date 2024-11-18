
public class Test {

	public static void main(String[] args) {
		Reka reka = new Reka(20);

		Plivac p1 = new Plivac(reka, "Plivac1", 5);
		Plivac p2 = new Plivac(reka, "Plivac2", 2);

		p1.start();
		p2.start();

	}

}

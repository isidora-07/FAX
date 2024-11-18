package februar2022;

public class TestRectangle {

	public static void main(String[] args) {
		Rectangle niz[] = { new Rectangle(), new DecoratedRectangle() };
		niz[0].setAll(4, 4, 4, 4);
		niz[1].setAll(5, 5, 5, 5);

		for (Rectangle r : niz)
			System.out.println(r.ispis(r));
	}

}

class Rectangle {
	private int x, y, w, h;

	public static String ispis(Rectangle a) {
		return "x=" + a.x + ",y=" + a.y + ",w=" + a.w + ",h=" + a.h;
	}

	public void setAll(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}

class DecoratedRectangle extends Rectangle {
	private int borderWidth;

	public static String ispis(DecoratedRectangle a) {
		return a.ispis(a) + ", borderWidth = " + a.borderWidth;
	}
}

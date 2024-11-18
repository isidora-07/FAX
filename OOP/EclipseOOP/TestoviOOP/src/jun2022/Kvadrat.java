package jun2022;

interface merljivo {
	double getVelicina();
}

public class Kvadrat implements merljivo {

	public static void main(String[] args) {
//		Kvadrat m = new Kvadrat(7, 7);
//		merljivo m = new Kvadrat(7, 7);
//		merljivo m = new merljivo(7,7);
//		merljivo m = new merljivo();
//		merljivo m = new Kvadrat();
//		Kvadrat m =  new Kvadrat();
	}

	double x, y;

	public Kvadrat(double x1, double y1) {
		x = x1;
		y = y1;
	}

	@Override
	public double getVelicina() {
		// TODO Auto-generated method stub
		return 0;
	}

}

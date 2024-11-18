
public class Main {
	
	public static void main(String[] args) {
		Pivnica p = new Pivnica();
		
		Sanker s = new Sanker(p);
		Gost g1 = new Gost(p);
		Gost g2 = new Gost(p);
		Gost g3 = new Gost(p);
		Gost g4 = new Gost(p);
		Gost g5 = new Gost(p);
		s.start();
		g1.start();
		g2.start();
		g3.start();
		g4.start();
		g5.start();
	}

}

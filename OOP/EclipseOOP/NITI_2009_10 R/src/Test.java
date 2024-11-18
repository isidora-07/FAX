
public class Test {

	public static void main(String[] args) {
		Salter salter = new Salter();

		Posiljaoc p1 = new Posiljaoc(salter, "Posiljaoc1");
		Posiljaoc p2 = new Posiljaoc(salter, "Posiljaoc2");
		
		Postar po1 = new Postar(salter, "Postar1");
		Postar po2 = new Postar(salter, "Postar2");
		
		p1.start();
		p2.start();
		
		po1.start();
		po2.start();
	}

}

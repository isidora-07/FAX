import geometrija.GeometrijskiOblik;
import geometrija.Tacka;
import geometrija.TackeNisuUIstojDimenziji;
import geometrija._2D.Pravougaonik;
import geometrija._2D.Tacka2D;
import geometrija._2D.Trougao;
import geometrija._3D.Lopta;
import geometrija._3D.Tacka3D;
import geometrija._3D.TrostranaPiramida;

public class Test {

	public static void main(String[] args) 
	{
		Prostor prostor = new Prostor(5);
		
		// trougao
		Tacka2D a1 = new Tacka2D(2, 5);
		Tacka2D b1 = new Tacka2D(1, 8);
		Tacka2D c1 = new Tacka2D(4, 7);
		
		Trougao trougao = new Trougao(a1, b1, c1);
		
		
		/*
		 * Dodati trougao u prostor.
		 * */

		
		
		
		// lopta
		Tacka3D t = new Tacka3D(4, 5, 9);
		Lopta lopta = new Lopta(t, 10);
		
		
		/*
		 * Dodati loptu u prostor.
		 * */

		
		
		
		// pravougaonik
		Tacka gornjiLeviUgao = new Tacka2D(4, 5);
		Tacka donjiDesniUgao = new Tacka2D(9, 2);
		
		try 
		{
			Pravougaonik pravougaonik = new Pravougaonik(gornjiLeviUgao, donjiDesniUgao);
			
			/*
			 * Dodati pravougaonik u prostor.
			 * */
			
			

		} 
		catch (TackeNisuUIstojDimenziji e) 
		{
			System.out.println(e.toString());
		}
		
		// piramida
		Tacka a = new Tacka3D(2, 4, 8);
		Tacka b = new Tacka3D(5, 3, 8);
		Tacka c = new Tacka3D(2, 2, 8);
		
		Tacka vrhPiramide = new Tacka3D(3, 3, 15);
		
		TrostranaPiramida tp = new TrostranaPiramida(a, b, c, vrhPiramide);
		/*
		 * Dodati trostranu piramidu u prostor.
		 * */

		
		
		
		// Ispisati ukupnu povrsinu svih geometrijskih oblika
		
		
		// Ispisati ukupnu zapreminu geometrijskih oblika
		
		
		// Ispisati povrsine svih baza geometrijskih tela
		
	}

}

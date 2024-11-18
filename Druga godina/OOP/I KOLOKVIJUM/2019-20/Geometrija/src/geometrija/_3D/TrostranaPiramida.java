package geometrija._3D;

// Konkretan tip TrostranaPiramida koji je GeometrijskoTelo 
class TrostranaPiramida 
{

	// Promenljivu tipa GeometrijskaFigura koja predstavlja osnovu piramide i vidi se samo u klasi
	GeometrijskaFigura baza;
	// Promenljivu tipa Tacka koja predstavlja vrh piramide
	Tacka vrhPiramide;
	
	// Javni konstruktor koji prima tri tacke trougla u osnovi i tacku koja predstavlja vrh piramide i setuje obe svoje promenljive
	TrostranaPiramida(Tacka a, Tacka b, Tacka c, Tacka vrhPiramide)
	{

	}
	

	// Implementiran metod dajZapreminu koja racuna zapreminu trostrane piramide
	double dajZapreminu() 
	{
		return 0.0;
	}


	// Implementiran metod dajPovrsinu koja racuna povrsinu trostrane piramide
	double dajPovrsinu() 
	{
		
		return 0.0;
	}

	// Implementiran metod dajPovrsinuBaze koja racuna povrsinu trougla koji je u osnovi piramide 
	double dajPovrsinuBaze() {
		return 0.0;
	}

	// Implementiran metod dajObimBaze koji racuna obim trougla koji je u osnovi piramide
	GeometrijskiOblik dajBazu() {
		return null;
	}

	// Implementiran metod dajBazu koji vraca osnovu trostrane piramide
	double dajObimBaze() {
		return 0.0;
	}

}

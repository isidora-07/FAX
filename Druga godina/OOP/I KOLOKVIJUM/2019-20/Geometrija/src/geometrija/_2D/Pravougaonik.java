package geometrija._2D;


// Konkretan tip Pravougaonik je GeometrijskaFigura
public class Pravougaonik {
	
	// Promenljivu gornjiLeviUgao tipa Tacka2D koja ima vidljivost samo u paketu i predstavlja gornje levo teme pravougaonika
	Tacka2D gornjiLeviUgao;
	// Promenljivu donjiDesniUgao tipa Tacka2D koja ima vidljivost samo u paketu i predstavlja donje desno teme pravougaonika
	Tacka2D donjiDesniUgao;
	
	// Javni podrazumevani konstruktor u kome se setuje gornjiLeviUgao na tacku (-1,1) i  donjiDesniUgao na tacku (-1,1).
	Pravougaonik()
	{

	}
	
	//Javni konstruktor koji prihvata oba temena i setuje ih. 
	// Ukoliko tacke nisu iz dvodimenzionalnog prostora baciti izuzetak TackeNisuUIstojDimenziji
	Pravougaonik(Tacka gornjiLeviUgao, Tacka donjiDesniUgao) 
	{
		
	}


	// Implementiran metod dajObim koja racuna obim pravougaonika
	double dajObim() {
		return 0.0;
	}


	// Implementiran metod dajPovrsinu koja racuna povrsinu pravougaonika
	double dajPovrsinu() {
		return 0.0;
	}

}

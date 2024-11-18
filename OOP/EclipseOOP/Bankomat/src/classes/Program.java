package classes;

public class Program {
	public static void main(String[] args) {
		Bankomat bankomat = Bankomat.getInstance();
		bankomat.setLokacija("Kragujevac");
		bankomat.setNazivBanke("Erste");
		bankomat.setSerijskiBroj(1234);

		bankomat.pokreniProgram();
	}
}
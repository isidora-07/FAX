public class Pivnica {

	int brojPivaNaStolu;
	Integer brojPopijenih = 0;
	Integer brojLjudiZaStolom;

	public Pivnica() {
		super();
		this.brojPivaNaStolu = 0;
		this.brojLjudiZaStolom = 0;
	}

	void natociPivo() throws Exception {
		
		synchronized (this) 
		{
			while(this.brojPopijenih <5)
			{
				System.out.println("Cekam da se popije");
				wait();				
			}
			
		}
		
		Thread.sleep(3000);
		

		synchronized (this) {
			brojPopijenih = 0;
			System.out.println("Dopunio sam pivo");
			notifyAll();
		}
	}

	public void popijPivo() throws Exception {

		//seo za sto
		synchronized (this) {
			while (this.brojLjudiZaStolom >= 5) {
				System.out.println("Cekam mesto");
				wait();
			}
			this.brojLjudiZaStolom++;
			notifyAll();
		}
		
		//ceka da sto bude popunjen
		synchronized (this) {
			while(this.brojLjudiZaStolom != 5 || this.brojPopijenih != 0)
			{
				System.out.println("Cekam ljude ili da svi ostali popiju ili da sanker dopuni");
				wait();
			}
			
			notifyAll();
		}
		
		//kriticna sekcija

		
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Popio sam pivo");
			this.brojPopijenih++;
			this.brojLjudiZaStolom--;
			notifyAll();
		}

	}

}
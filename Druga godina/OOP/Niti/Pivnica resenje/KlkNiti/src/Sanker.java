
public class Sanker extends Thread
{
	Pivnica pivnica;
	
	public Sanker(Pivnica pivnica) {
		this.pivnica = pivnica;
	}
	
	@Override
	public void run() {
		
		while(true)
		{
			try {
				pivnica.natociPivo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

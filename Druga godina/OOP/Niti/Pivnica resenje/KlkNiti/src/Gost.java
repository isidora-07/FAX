
public class Gost extends Thread
{
	
	Pivnica pivnica;
	
	public Gost(Pivnica pivnica) {
		this.pivnica = pivnica;
	}
	
	@Override
	public void run() {
		
		while(true)
		{
			try {
				pivnica.popijPivo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

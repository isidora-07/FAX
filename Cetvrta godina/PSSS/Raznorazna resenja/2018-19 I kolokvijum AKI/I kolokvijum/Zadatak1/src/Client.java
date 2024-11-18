import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class Client {

	public static void main(String[] args) {
		System.out.println("Konektovanje na servis.");
		
		try {
			IAuth auth = (IAuth) Naming.lookup("rmi://localhost:1100/WebService");
			ICalculation calc = (ICalculation) Naming.lookup("rmi://localhost:1100/WebService");	

			String username = "Aki";
			String token = auth.generateToken(username);
			
			Random r = new Random();
            double[] array = new double[1000];
            for(int i = 0; i<array.length; i++)
                array[i] = r.nextDouble() * 1000;
            double res = calc.calculateMean(token, array);
            System.out.println(res);
            
            System.out.println("Odjava.");
            auth.logout(token);
            
            res = calc.calculateMean(token, array);
            System.out.println(res);
         
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

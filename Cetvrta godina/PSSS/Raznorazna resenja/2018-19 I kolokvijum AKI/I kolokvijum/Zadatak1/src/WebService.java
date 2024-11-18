import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class WebService extends UnicastRemoteObject implements IAuth, ICalculation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> listaTokena = new ArrayList<String>();
	
	protected WebService() throws RemoteException
	{
		super();
	}
	
	@Override
	public double calculateMean(String token, double[] array) throws RemoteException {
		boolean valid = false;
		synchronized (listaTokena) {
			if(listaTokena.contains(token))
				valid = true;
		}
		
		if(valid)
		{
			double zbir = 0;
			for (double d : array) {
				zbir += d;
			}
			
			return zbir / array.length;
		}
		else
			return -1;
	}

	@Override
	public String generateToken(String username) throws RemoteException {
		String token = sha1(username);
		
		synchronized(listaTokena) {
			listaTokena.add(token);
		}
		
		return token;
	}

	@Override
	public void logout(String token) throws RemoteException {
		synchronized(listaTokena)
		{
			listaTokena.remove(token);
		}
	}
	
	public String sha1(String input) {
		String sha1 = null;
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sha1;
	}
}

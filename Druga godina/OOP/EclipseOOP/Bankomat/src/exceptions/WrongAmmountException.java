package exceptions;

public class WrongAmmountException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongAmmountException(String poruka) {
		super(poruka);
	}
	
}

package logic.exceptions;

public class TakenUsernameException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TakenUsernameException(String message) {
		super("This was the error :" +message);
	}
}

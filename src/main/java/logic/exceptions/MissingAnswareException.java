package logic.exceptions;

/* this exception will be thrown if the user doesn't answare a question of the personality test*/

public class MissingAnswareException extends Exception{
	private static final long serialVersionUID = -885754806201021449L;
	private final String message;
	
	public MissingAnswareException(String msg) {
		message = msg;
	}
	
	public String getMsg() {
		return this.message;
	}
}

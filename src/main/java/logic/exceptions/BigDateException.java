package logic.exceptions;

/* this exception will be thrown when there is an error in comparing two dates*/

public class BigDateException extends Exception{
	private static final long serialVersionUID = 1L;
	private final String cause;
	
	public BigDateException(String message) {
		cause = message;
	}
	
	public String getMsg() {
		return this.cause;
	}
}

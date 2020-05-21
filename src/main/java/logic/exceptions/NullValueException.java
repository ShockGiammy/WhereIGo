package logic.exceptions;

public class NullValueException extends Exception{
	private static final long serialVersionUID = 1L;
	private final String errorMsg;
	
	public NullValueException(String message) {
		errorMsg = message;
	}
	
	public String getNullExcMsg() {
		return this.errorMsg;
	}
}

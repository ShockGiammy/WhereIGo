package logic.exceptions;

/* this exception will be thrown if the user inserts a field which as more characters then the ones allowed*/

public class LengthFieldException extends Exception{
	private static final long serialVersionUID = 4684690583266623488L;
	private final String message;
	
	public LengthFieldException(String mess) {
		message = mess;
	}
	
	public String getMsg() {
		return this.message;
	}
}

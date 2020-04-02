package logic.exceptions;

public class ServerDownException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ServerDownException(String message){
        super(message);
    }

}

package logic.exceptions;

public class GroupNameTakenException extends Exception{
	private static final long serialVersionUID = 1L;

	public GroupNameTakenException(String message){
        super(message);
    }
}

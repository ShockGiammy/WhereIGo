package logic;

import logic.model.UserModel;

/*This singleton class keeps the two fundamentals user datas, so that each class 
 always knows which is the logged user*/

public class LoggedUser extends UserModel{
	private static LoggedUser loggedIstance = null;
	
	protected LoggedUser(String userName) {
		username = userName;
	}
	
	public static synchronized LoggedUser getIstance(String username) {
		if(LoggedUser.loggedIstance == null){
			LoggedUser.loggedIstance = new LoggedUser(username);
		}
		return LoggedUser.loggedIstance;
	}
}

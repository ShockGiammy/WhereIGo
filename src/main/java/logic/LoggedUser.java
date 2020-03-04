package logic;

public class LoggedUser {
	private static String username;
	private static String personality;
	private static String typeOfUser;
	
	public static void setUserName(String usrName) {
		username = usrName;
	}
	
	public static void setPersonality(String pers) {
		personality = pers;
	}
	
	public static void setType(String type) {
		typeOfUser = type;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getPersonality() {
		return personality;
	}
	
	public String getUserType() {
		return typeOfUser;
	}
}

package logic;

import javafx.scene.image.Image;

public class LoggedUser {
	private static String username;
	private static String personality;
	private static String typeOfUser;
	private static Image proPic;
	
	public static void setUserName(String usrName) {
		username = usrName;
	}
	
	public static void setPersonality(String pers) {
		personality = pers;
	}
	
	public static void setType(String type) {
		typeOfUser = type;
	}
	
	public static void setImage(Image image) {
		proPic = image;
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
	
	public Image getImage() {
		return proPic;
	}
}

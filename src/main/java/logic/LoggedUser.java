package logic;

public class LoggedUser {
	private static String username;
	private static String personality;
	private static UserType typeOfUser;
	private static byte[] proPic;
	
	public static void setUserName(String usrName) {
		username = usrName;
	}
	
	public static void setPersonality(String pers) {
		personality = pers;
	}
	
	public static void setType(String type) {
		if (type.equals("Renter")) {
			typeOfUser = UserType.RENTER;
		}
		else if (type.equals("Traveler")) {
			typeOfUser = UserType.TRAVELER;
		}
	}
	
	public static void setImage(byte[] image) {
		proPic = image;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getPersonality() {
		return personality;
	}
	
	public UserType getUserType() {
		return typeOfUser;
	}
	
	public byte[] getImage() {
		return proPic;
	}
}

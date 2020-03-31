package logic.model;

public class UserModel {
	private String name;
	private String surname;
	private String dateOfBirth;
	private String gender;
	private String username;
	private String personality;
	private String userType;
	private byte[] profPic;
	
	public void setCredentials(String nameOfUsr, String surOfUsr, String dateOfB, String gend) {
		this.name = nameOfUsr;
		this.surname = surOfUsr;
		this.dateOfBirth = dateOfB;
		this.gender = gend;
	}
	
	public void setUserPersonality(String pers) {
		this.personality = pers;
	}
	
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public void setPic(byte[] propic) {
		profPic = propic;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public String getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public String getUserPersonality(){
		return this.personality;
	}
	
	public String getUserType() {
		return this.userType;
	}
	
	public byte[] getProfilePic() {
		return this.profPic;
	}
}

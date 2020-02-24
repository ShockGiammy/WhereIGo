package logic.beans;

public class UserDataBean {
	private String name;
	private String surname;
	private String dateOfBirth;
	private String gender;
	private String typeOfUser;
	private String personality;
	private String userName;
	private String psw;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setPersonality(String pers) {
		this.personality = pers;
	}
	
	public void setType(String typeOfUsr) {
		this.typeOfUser = typeOfUsr;
	}
	
	public void setUserName(String usrName) {
		this.userName = usrName;
	}
	
	public void setPsw(String passwd) {
		this.psw = passwd;
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
	
	public String getPersonality() {
		return this.personality;
	}
	
	public String getType() {
		return this.typeOfUser;
	}
	
	public String getUsername() {
		return this.userName;
	}
	
	public String getPassword() {
		return this.psw;
	}
}

package logic.beans;

public class UserDataBean {
	String name;
	String surname;
	String dateOfBirth;
	String email;
	String gender;
	String userName;
	String psw;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void setEmail(String eMail) {
		this.email = eMail;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPsw(String psw) {
		this.psw = psw;
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
}

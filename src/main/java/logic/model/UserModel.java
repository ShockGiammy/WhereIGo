package logic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModel {
	private String name;
	private String surname;
	private LocalDate dateOfBirth;
	private String gender;
	private String username;
	private String password;
	private String personality;
	private String userType;
	private byte[] profPic;
	private File profImage;
	
	public void setCredentials(String nameOfUsr, String surOfUsr, LocalDate dateOfB, String gend) {
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
	
	public void setPaswd(String pass) {
		this.password = pass;
	}
	
	public void setUserType(String utype) {
		this.userType = utype;
	}
	
	public void setPic(byte[] propic) {
		profPic = propic;
	}
	
	public void setImage(File usrImage) {
		this.profImage = usrImage;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public LocalDate getDateOfBirth() {
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
	
	public String getPaswd() {
		return this.password;
	}
	
	public byte[] getProfilePic() {
		return this.profPic;
	}
	
	public InputStream getInputFile() {
		InputStream imageInputFile = null;
		try {
			imageInputFile = new FileInputStream(this.profImage);
		} catch (FileNotFoundException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
		return imageInputFile;
	}
	
	public long getFileLength() {
		return this.profImage.length();
	}
}

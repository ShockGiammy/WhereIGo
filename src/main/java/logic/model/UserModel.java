package logic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.beans.UserDataBean;

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
	
	public void setCredentialsByBean(UserDataBean dataBean) {
		this.name = dataBean.getName();
		this.surname = dataBean.getSurname();
		this.dateOfBirth = dataBean.getLocDateOfBirth();
		this.gender = dataBean.getGender();
		this.username = dataBean.getUsername();
		this.password = dataBean.getPassword();
		this.userType = dataBean.getType();
		this.profImage = dataBean.getFileImage();
	}
	
	public void setUsrAndPswByBean(UserDataBean dataBean) {
		this.username = dataBean.getUsername();
		this.password = dataBean.getPassword();
	}
	
	public void setPersByBean(UserDataBean dataBean) {
		this.personality = dataBean.getPersonality();
	}
	
	public void setUsrNameByBean(UserDataBean dataBean) {
		this.username = dataBean.getUsername();
	}
	
	public void setUsrAndPersByBean(UserDataBean dataBean) {
		this.username = dataBean.getUsername();
		this.personality = dataBean.getPersonality();
	}
	
	public void setLogUsrCred(String typeOfUsr, byte[] usrImg) {
		this.userType = typeOfUsr;
		this.profPic = usrImg;
	}
	
	public void setUsrNameAndPic(String usrName, byte[] img) {
		this.username = usrName;
		this.profPic = img;
	}
	
	public void setUserPersonality(String pers) {
		this.personality = pers;
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

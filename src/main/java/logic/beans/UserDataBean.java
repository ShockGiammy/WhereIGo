package logic.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDataBean {
	private String name;
	private String surname;
	private String dateOfBirth;
	private String gender;
	private String typeOfUser;
	private String personality;
	private String userName;
	private String psw;
	private byte[] profImStream;
	private File profImage;
	private Logger logger = Logger.getLogger("WIG");
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth.toString();
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
	
	public void setByteSteam(byte[] stream) {
		this.profImStream = stream;
	}
	
	public void setUsrImage(File usrImage) {
		this.profImage = usrImage;
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
	
	public byte[] getByteStream() {
		return this.profImStream;
	}
	
	public InputStream getInputFile() {
		InputStream imageInputFile = null;
		if (this.profImage != null) {
			try {
				imageInputFile = new FileInputStream(this.profImage);
			} catch (FileNotFoundException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return imageInputFile;
	}

	public long getFileLength() {
		long len = 0;
		if (this.profImage != null) {
			len = this.profImage.length();
		}
		return len;
	}
	
	public File getFileImage() {
		return this.profImage;
	}
	
	/* method needed to the Servlets*/
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

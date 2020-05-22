package logic.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;


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
	
	public UserDataBean() {
		
	}
	
	public UserDataBean(String usrname, String paswd) {
		this.userName = usrname;
		this.psw = paswd;
	}
	
	public UserDataBean(String uname) {
		this.userName = uname;
	}
	
	public void setName(String name) throws LengthFieldException, NullValueException {
		if(name == null || name.equalsIgnoreCase("")) {
			throw new NullValueException("Please insert a valid name");
		}
		else if(name.length() > 20) {
			throw new LengthFieldException("Too many character for name field");
		}
		this.name = name;
	}
	
	public void setSurname(String surname) throws LengthFieldException, NullValueException{
		if((surname == null || surname.equalsIgnoreCase(""))){
			throw new NullValueException("Please insert a valid surname");
		}
		else if(surname.length() > 20) {
			throw new LengthFieldException("Too many character for surname field");
		}
		this.surname = surname;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) throws NullValueException {
		if(dateOfBirth == null){
			throw new NullValueException("Please insert a valid date of birth");
		}
		this.dateOfBirth = dateOfBirth.toString();
	}
	
	public void setDateOfBirth(String dateOfBirth) throws NullValueException {
		if(dateOfBirth == null || dateOfBirth.equalsIgnoreCase("")){
			throw new NullValueException("Please insert a valid date of birth");
		}
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
	
	public void setUserName(String usrName) throws LengthFieldException, NullValueException {
		if(usrName == null || usrName.equalsIgnoreCase("")) {
			throw new NullValueException("Please insert a valid username");
		}
		else if(usrName.length() > 20) {
			throw new LengthFieldException("Too many character for username field");
		}
		this.userName = usrName;
	}
	
	public void setPsw(String passwd) throws LengthFieldException, NullValueException {
		if(passwd == null || passwd.equalsIgnoreCase("")) {
			throw new NullValueException("Please insert a valid password");
		}
		else if(passwd.length() > 20) {
			throw new LengthFieldException("Too many character for password field");
		}
		this.psw = passwd;
	}
	
	public void setByteSteam(byte[] stream) {
		this.profImStream = stream;
	}
	
	public void setUsrImage(File usrImage) throws NullValueException {
		if(usrImage == null || usrImage.length() == 0) {
			throw new NullValueException("Please insert user image");
		}
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
	
	public LocalDate getLocDateOfBirth() {
		return LocalDate.parse(this.dateOfBirth);
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
}

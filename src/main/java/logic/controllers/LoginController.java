package logic.controllers;

import logic.dao.UserDao;
import logic.exceptions.DuplicateUsernameException;
import logic.model.UserModel;
import logic.beans.UserDataBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.LoggedUser;

public class LoginController {
	private UserDao usrDao;
	private UserModel usrModel;
	public LoginController() {
		this.usrDao = new UserDao();
		this.usrModel = new UserModel();
	}
	
	public int checkLogInControl(UserDataBean logBean) {
		int ret;
		this.usrModel.setUserName(logBean.getUsername());
		this.usrModel.setPaswd(logBean.getPassword());
		ret = this.usrDao.checkLogInInfo(this.usrModel);
		if(ret == 1) {
			LoggedUser.setUserName(logBean.getUsername());
			LoggedUser.setPersonality(usrModel.getUserPersonality());
			LoggedUser.setType(this.usrModel.getUserType());
			LoggedUser.setImage(this.usrModel.getProfilePic());
		}
		return ret;
	}
	
	public void insertNewUserControl(UserDataBean usrBean) throws DuplicateUsernameException {
		this.usrModel.setCredentials(usrBean.getUsername(), usrBean.getSurname(), LocalDate.parse(usrBean.getDateOfBirth()), usrBean.getGender());
		this.usrModel.setUserName(usrBean.getUsername());
		this.usrModel.setPaswd(usrBean.getPassword());
		this.usrModel.setImage(usrBean.getFileImage());
		this.usrModel.setUserType(usrBean.getType());
		LoggedUser.setUserName(usrBean.getUsername());
		LoggedUser.setType(usrBean.getType());
		byte[] imm = new byte[(int)usrBean.getFileLength()];
		/*try {
			imm = Files.readAllBytes(usrBean.getFileImage().toPath());
		} catch (IOException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}*/
		try (FileInputStream is = new FileInputStream(usrBean.getFileImage())){
			int count = 0;
			while((count = is.read(imm)) > 0) {
				Logger.getLogger("WIG").log(Level.FINE, "Reading file");
			}
		} catch (FileNotFoundException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		} catch (IOException e1) {
			Logger.getLogger("WIG").log(Level.SEVERE, e1.getMessage());
		}
		LoggedUser.setImage(imm);
		this.usrDao.insertNewUser(this.usrModel);
	}
}

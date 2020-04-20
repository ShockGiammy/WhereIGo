package logic.controllers;

import logic.dao.UserDao;
import logic.exceptions.DuplicateUsernameException;
import logic.model.UserModel;
import logic.beans.UserDataBean;

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
	
	public int insertNewUserControl(UserDataBean usrBean) throws DuplicateUsernameException {
		if(usrBean.getUsername() != null && usrBean.getPassword() != null && usrBean.getName() != null && usrBean.getSurname() != null && usrBean.getDateOfBirth() != null && usrBean.getGender() != null && usrBean.getType() != null ) {
			this.usrModel.setCredentials(usrBean.getUsername(), usrBean.getSurname(), LocalDate.parse(usrBean.getDateOfBirth()), usrBean.getGender());
			this.usrModel.setUserName(usrBean.getUsername());
			this.usrModel.setPaswd(usrBean.getPassword());
			this.usrModel.setImage(usrBean.getFileImage());
			this.usrModel.setUserType(usrBean.getType());
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setType(usrBean.getType());
			byte[] imm = null;
			try {
				imm = Files.readAllBytes(usrBean.getFileImage().toPath());
			} catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			LoggedUser.setImage(imm);
			this.usrDao.insertNewUser(this.usrModel);
			return 0;
		}
		else {
			return -1;
		}
	}
}

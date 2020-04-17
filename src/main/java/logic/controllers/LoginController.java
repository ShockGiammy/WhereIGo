package logic.controllers;

import logic.dao.UserDao;
import logic.exceptions.DuplicateUsernameException;
import logic.beans.UserDataBean;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.LoggedUser;

public class LoginController {
	private UserDao usrDao;
	
	public LoginController() {
		this.usrDao = new UserDao();
	}
	
	public int checkLogInControl(UserDataBean logBean) {
		int ret;
		ret = this.usrDao.checkLogInInfo(logBean);
		if(ret == 1) {
			LoggedUser.setUserName(logBean.getUsername());
			LoggedUser.setPersonality(logBean.getPersonality());
			LoggedUser.setType(logBean.getType());
			LoggedUser.setImage(logBean.getByteStream());
		}
		return ret;
	}
	
	public int insertNewUserControl(UserDataBean usrBean) throws DuplicateUsernameException {
		if(usrBean.getUsername() != null && usrBean.getPassword() != null && usrBean.getName() != null && usrBean.getSurname() != null && usrBean.getDateOfBirth() != null && usrBean.getGender() != null && usrBean.getType() != null ) {
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setPersonality(usrBean.getPersonality());
			LoggedUser.setType(usrBean.getType());
			byte[] imm = null;
			try {
				imm = Files.readAllBytes(usrBean.getFileImage().toPath());
			} catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			LoggedUser.setImage(imm);
			this.usrDao.insertNewUser(usrBean);
			return 0;
		}
		else {
			return -1;
		}
	}
}

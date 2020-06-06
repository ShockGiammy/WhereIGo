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
		this.usrModel.setUsrAndPswByBean(logBean);
		ret = this.usrDao.checkLogInInfo(this.usrModel);
		if(ret == 1) {
			LoggedUser.setUserName(logBean.getUsername());
			LoggedUser.setPersonality(usrModel.getUserPersonality());
			LoggedUser.setType(this.usrModel.getUserType());
			LoggedUser.setImage(this.usrModel.getProfilePic());
		}
		return ret;
	}
	
	public boolean insertNewUserControl(UserDataBean usrBean) throws DuplicateUsernameException {
		if(valideDateOfBirth(usrBean.getLocDateOfBirth()) == -1) {
			return false;
		}
		else {
			this.usrModel.setCredentialsByBean(usrBean);
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setType(usrBean.getType());
			byte[] imm = new byte[(int)usrBean.getFileLength()];
			try {
				imm = Files.readAllBytes(usrBean.getFileImage().toPath());
			} catch (IOException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
			LoggedUser.setImage(imm);
			this.usrDao.insertNewUser(this.usrModel);
		}
		return true;
	}
	
	private int valideDateOfBirth(LocalDate dateOfBirth) {
		LocalDate today = LocalDate.now();
		// the user is for sure adult
		if(((today.getYear() - dateOfBirth.getYear()) > 18) || ((today.getYear() - dateOfBirth.getYear()) == 18 && dateOfBirth.getDayOfMonth() <= today.getDayOfMonth() && dateOfBirth.getMonthValue() <= today.getMonthValue())) {
			return 0;
		}
		return -1;
	}
}

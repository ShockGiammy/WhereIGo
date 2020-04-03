package logic.controllers;

import logic.dao.UserDao;
import logic.exceptions.DuplicateUsernameException;
import logic.beans.UserDataBean;
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
		}
		return ret;
	}
	
	public int insertNewUserControl(UserDataBean usrBean) throws DuplicateUsernameException {
		if(usrBean.getUsername() != null && usrBean.getPassword() != null && usrBean.getName() != null && usrBean.getSurname() != null && usrBean.getDateOfBirth() != null && usrBean.getGender() != null && usrBean.getType() != null ) {
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setPersonality(usrBean.getPersonality());
			LoggedUser.setType(usrBean.getType());
			this.usrDao.insertNewUser(usrBean);
			return 0;
		}
		else {
			return -1;
		}
	}
}

package logic.controllers;

import logic.LoggedUser;
import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.dao.UserDao;


public class LoginController {
	private UserDao usrDao;
	
	public LoginController() {
		this.usrDao = new UserDao();
	}
	
	public int checkLogInControl(UserDataBean usrBean, LogInBean logBean) {
		int ret;
		ret = this.usrDao.checkLogInInfo(logBean, usrBean);
		if(ret == 1) {
			saveLoggedUser(usrBean);
		}
		return ret;
	}
	
	public int insertNewUserControl(UserDataBean usrBean) {
		if(usrBean.getUsername() == null || usrBean.getPassword() == null || usrBean.getName() == null || usrBean.getSurname() == null || usrBean.getDateOfBirth() == null || usrBean.getGender() == null || usrBean.getType() == null ) {
			return 0;
		}
		else {
			this.usrDao.insertNewUser(usrBean);
			saveLoggedUser(usrBean);
			return 1;
		}
	}
	
	public void saveLoggedUser(UserDataBean usrBean) {
		LoggedUser logUsr = LoggedUser.getIstance(usrBean.getUsername());
		logUsr.setUserDatas(usrBean);
	}
}

package logic.controllers;

import logic.LoggedUser;
import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.dao.UserDao;


public class LoginController {
	private UserDao usrDao;
	private LoggedUser logUsr;
	
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
	
	public void insertNewUserControl(UserDataBean usrBean) {
		this.usrDao.insertNewUser(usrBean);
		saveLoggedUser(usrBean);
	}
	
	public void saveLoggedUser(UserDataBean usrBean) {
		logUsr = LoggedUser.getIstance(usrBean.getUsername());
		logUsr.setUserDatas(usrBean);
	}
}

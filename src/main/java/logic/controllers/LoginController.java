package logic.controllers;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.dao.UserDao;
import logic.model.UserModel;


public class LoginController {
	private UserDao usrDao;
	private UserModel userModel;
	
	public LoginController() {
		this.userModel = new UserModel();
		this.usrDao = new UserDao();
	}
	
	public int checkLogInControl(UserDataBean usrBean, LogInBean logBean) {
		int ret;
		ret = this.usrDao.checkLogInInfo(logBean, usrBean);
		if(ret == 1) {
			this.userModel.setUserDatas(usrBean);
		}
		return ret;
	}
	
	public void insertNewUserControl(UserDataBean usrBean) {
		int ret;
		/*if(usrBean.getName() == null || usrBean.getSurname() == null || usrBean.getDateOfBirth() == null || usrBean.getGender() == null || usrBean.getUsername() == null || usrBean.getPassword() == null || usrBean.getType() == null) {
			return 0;
		}*/
		this.usrDao.insertNewUser(usrBean);
	}
}

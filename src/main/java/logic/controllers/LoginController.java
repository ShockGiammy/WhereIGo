package logic.controllers;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.model.UserModel;

public class LoginController {
	UserModel userModel;
	
	public int checkLogInInfo(UserDataBean usrBean, LogInBean logBean) {
		userModel = UserModel.getUserModelIstance();
		return userModel.isUserLogged(logBean, usrBean);
	}
}

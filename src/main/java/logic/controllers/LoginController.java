package logic.controllers;
import logic.dao.UserDao;
import logic.beans.UserDataBean;
import logic.beans.LogInBean;
import logic.LoggedUser;

public class LoginController {
	private UserDao usrDao;
	
	public LoginController() {
		this.usrDao = new UserDao();
	}
	
	public int checkLogInControl(UserDataBean usrBean, LogInBean logBean) {
		int ret;
		ret = this.usrDao.checkLogInInfo(logBean, usrBean);
		if(ret == 1) {
			usrBean.setUserName(logBean.getUserName());
			saveLoggedUser(usrBean);
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setPersonality(usrBean.getPersonality());
			LoggedUser.setType(usrBean.getType());
		}
		return ret;
	}
	
	public int insertNewUserControl(UserDataBean usrBean) {
		if(usrBean.getUsername() == null || usrBean.getPassword() == null || usrBean.getName() == null || usrBean.getSurname() == null || usrBean.getDateOfBirth() == null || usrBean.getGender() == null || usrBean.getType() == null ) {
			return 0;
		}
		else {
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setPersonality(usrBean.getPersonality());
			LoggedUser.setType(usrBean.getType());
			this.usrDao.insertNewUser(usrBean);
			saveLoggedUser(usrBean);
			return 1;
		}
	}
	
	public void saveLoggedUser(UserDataBean usrBean) {
		LoggedUser.setUserName(usrBean.getUsername());
		if(usrBean.getPersonality() != null) {
			LoggedUser.setPersonality(usrBean.getPersonality());
		}
	}
}

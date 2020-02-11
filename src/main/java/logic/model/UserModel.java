package logic.model;

import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.dao.UserDao;

public class UserModel {
	private String name;
	private String surname;
	private String dateOfBirth;
	private String gender;
	private String username;
	private String personality;
	private UserDao usrDao;
	private static UserModel istance = null;
	
	protected UserModel() {
		this.usrDao = new UserDao();
	}
	
	public int isUserLogged(LogInBean logBean, UserDataBean usrBean) {
		int ret;
		ret = this.usrDao.checkLogInInfo(logBean, usrBean);
		this.username = logBean.getUserName();
		setUserDatas(usrBean);
		return ret;
	}
	
	private void setUserDatas(UserDataBean usrBean) {
		this.name = usrBean.getName();
		this.surname = usrBean.getSurname();
		this.dateOfBirth = usrBean.getDateOfBirth();
		this.gender = usrBean.getGender();
	}
	
	public static UserModel getUserModelIstance() {
		if(UserModel.istance == null) {
			UserModel.istance = new UserModel();
		}
		return istance;
	}
	
	public void saveUserPersonality(String pers) {
		LogInBean lBean = new LogInBean();
		lBean.setUserName(this.username);
		usrDao.insertPersonality(pers, this.username);
	}
}

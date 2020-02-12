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
		if(usrBean.getPersonality() == null) {
			this.personality = "None";
		}
		else {
			this.personality = usrBean.getPersonality();
		}
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
		this.personality = pers;
	}
	
	public UserDataBean getUserDatas() {
		UserDataBean usrBean = new UserDataBean();
		usrBean.setName(this.name);
		usrBean.setSurname(this.surname);
		usrBean.setDateOfBirth(this.dateOfBirth);
		usrBean.setGender(this.gender);
		usrBean.setPersonality(this.personality);
		return usrBean;
	}
	
	public String[] getUserLocations() {
		String loc[] = new String[3];
		UserDataBean usrBean = new UserDataBean();
		usrBean.setPersonality(this.personality);
		loc = usrDao.getCity(usrBean);
		return loc;
	}
	
	public void getUserPersonality(UserDataBean dataBean) {
	dataBean.setPersonality(this.personality);
	}
}

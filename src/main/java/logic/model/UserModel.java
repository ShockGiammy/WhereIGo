package logic.model;

import logic.beans.UserDataBean;

public class UserModel {
	protected String name;
	protected String surname;
	protected String dateOfBirth;
	protected String gender;
	protected String username;
	protected String personality;
	protected String userType;
	
	public void setUserDatas(UserDataBean usrBean) {
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
		this.userType = usrBean.getType();
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
	
	public void insertPersonality(String typeOfPers) {
		personality = typeOfPers;
	}
	
	public void getUserPersonality(UserDataBean dataBean) {
		dataBean.setPersonality(this.personality);
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public String getUserPersonality(){
		return this.personality;
	}
	
	public String getUserType() {
		return this.userType;
	}
}

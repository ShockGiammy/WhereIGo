package logic.controllers;
import logic.dao.TravelDao;
import logic.dao.UserDao;
import logic.model.TicketModel;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.beans.LogInBean;

import java.util.ArrayList;
import java.util.List;

import logic.LoggedUser;

public class LoginController {
	private UserDao usrDao;
	private TravelDao travDao;
	
	public LoginController() {
		this.usrDao = new UserDao();
		this.travDao = new TravelDao();
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
	
	public void getUserBoughtTickets(UserDataBean bean, List<UserTravelBean> travBeanList) {
		List<TicketModel> tickList = new ArrayList<>();
		this.travDao.getUserTickets(bean, tickList);
		int i;
		for(i = 0; i < tickList.size(); i++) {
			UserTravelBean trav = new UserTravelBean();
			trav.setDepCity(tickList.get(i).getDepCity());
			trav.setArrCity(tickList.get(i).getArrCity());
			trav.setFirstDay(tickList.get(i).getDepDay());
			trav.setLastDay(tickList.get(i).getArrDay());
			travBeanList.add(trav);
		}
	}
}

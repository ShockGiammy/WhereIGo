package logic.controllers;

import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.dao.LocationDao;
import logic.dao.UserDao;
import logic.model.GroupModel;

public class BookTravelControl {
	private LoggedUser logUser;
	private UserDataBean userBean;
	private GroupModel grpModel;
	private UserDao usrDao;
	private LocationDao locDao;
	
	public BookTravelControl() {
		this.userBean = new UserDataBean();
		this.grpModel = new GroupModel();
		this.usrDao = new UserDao();
		this.locDao = new LocationDao();
		logUser = LoggedUser.getIstance(null);
	}
	
	public String[] showLocationsControl() { /*Shall change this String[] into a location bean*/
		String suggLoc[] = new String[3];
		userBean.setPersonality(logUser.getUserPersonality());
		userBean.setUserName(logUser.getUserName());
		suggLoc = this.usrDao.getLocations(userBean);
		return suggLoc;
	}
	
	public void getGroupsControl(GroupBean grpBean[]) {
		this.logUser.getUserPersonality(this.userBean);
		grpModel.getGroups(grpBean, this.userBean);
	}
	
	public void retriveLocInfoControl(LocationBean bean) {
		this.locDao.retriveLocationInfo(bean);
	}
}

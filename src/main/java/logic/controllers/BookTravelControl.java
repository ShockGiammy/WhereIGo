package logic.controllers;

import logic.model.UserModel;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.model.GroupModel;

public class BookTravelControl {
	private UserModel usrModel;
	private UserDataBean userBean;
	private GroupModel grpModel;
	
	public BookTravelControl() {
		this.usrModel = UserModel.getUserModelIstance();
		this.userBean = new UserDataBean();
		this.grpModel = new GroupModel();
	}
	
	public String[] showLocationsControl() {
		String suggLoc[] = new String[3]; 
		suggLoc = usrModel.getUserLocations();
		return suggLoc;
	}
	
	public void getGroupsControl(GroupBean grpBean[]) {
		this.usrModel.getUserPersonality(this.userBean);
		grpModel.getGroups(grpBean, this.userBean);
	}
}

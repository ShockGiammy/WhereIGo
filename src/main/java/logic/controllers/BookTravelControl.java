package logic.controllers;

import logic.model.UserModel;
import logic.beans.UserDataBean;

public class BookTravelControl {
	UserModel usrModel;
	UserDataBean userBean;
	
	public BookTravelControl() {
		this.usrModel = UserModel.getUserModelIstance();
		this.userBean = new UserDataBean();
	}
	public String[] showLocationsControl() {
		String suggLoc[] = new String[3]; 
		suggLoc = usrModel.getUserLocations();
		return suggLoc;
	}
}

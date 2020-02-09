package logic.controllers;

import logic.dao.UserDao;

public class BookTravelControl {
	
	public String showLocationsControl() {
		UserDao usrdao = new UserDao();
		return usrdao.getCity();
	}
}

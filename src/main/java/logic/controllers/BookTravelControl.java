package logic.controllers;

import java.sql.SQLException;

import logic.UserDao;

public class BookTravelControl {
	
	public String showLocationsControl() throws SQLException {
		UserDao usrdao = new UserDao();
		String city = usrdao.getCity();
		return city;
	}
}

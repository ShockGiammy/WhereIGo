package logic.controllers;

import logic.LoggedUser;
import logic.beans.InterestsBean;
import logic.dao.UserDao;

public class InterestsController {
	private int numbOf1 = 0;
	private int numbOf2 = 0;
	private int numbOf3 = 0;
	private int numbOf4 = 0;
	private UserDao usrDao;
	
	public InterestsController() {
		this.usrDao = new UserDao();
	}
	
	public void evaluateInterests(InterestsBean interBean) {
		LoggedUser logUser = new LoggedUser();
		String pers = null;
		int[] answares = interBean.getAnswares();
		int i;
		for(i = 0; i < answares.length; i++) {
			switch(answares[i]) {
				case(1):
					numbOf1 += 1;
					break;
				case(2):
					numbOf2 += 1;
					break;
				case(3):
					numbOf3 += 1;
					break;
				case(4):
					numbOf4 += 1;
					break;
				default:
					break;
			}
		}
		if(numbOf1 >= 2) {
			this.usrDao.insertPersonality("Friendly", logUser.getUserName());
			pers = "Friendly";
		}
		if(numbOf2 >= 2) {
			this.usrDao.insertPersonality("Adventurer", logUser.getUserName());
			pers = "Adventurer";
		}
		if(numbOf3 >= 2) {
			this.usrDao.insertPersonality("Lone wolf", logUser.getUserName());
			pers = "Lone wolf";
		}
		if(numbOf4 >= 2) {
			this.usrDao.insertPersonality("Lazybone", logUser.getUserName());
			pers = "Lazybone";
		}
		LoggedUser.setPersonality(pers);
	}
}

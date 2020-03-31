package logic.controllers;

import logic.LoggedUser;
import logic.beans.InterestsBean;
import logic.dao.UserDao;
import logic.model.UserModel;

public class InterestsController {
	private int numbOf1 = 0;
	private int numbOf2 = 0;
	private int numbOf3 = 0;
	private int numbOf4 = 0;
	private UserDao usrDao;
	
	public InterestsController() {
		this.usrDao = new UserDao();
	}
	
	public void evaluateInterestsControl(InterestsBean interBean) {
		LoggedUser logUser = new LoggedUser();
		UserModel usrMod = new UserModel();
		usrMod.setUserName(logUser.getUserName());
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
			usrMod.setUserPersonality("Friendly");
			this.usrDao.insertPersonality(usrMod);
			pers = "Friendly";
		}
		if(numbOf2 >= 2) {
			usrMod.setUserPersonality("Adventurer");
			this.usrDao.insertPersonality(usrMod);
			pers = "Adventurer";
		}
		if(numbOf3 >= 2) {
			usrMod.setUserPersonality("Lone wolf");
			this.usrDao.insertPersonality(usrMod);
			pers = "Lone wolf";
		}
		if(numbOf4 >= 2) {
			usrMod.setUserPersonality("Lazybone");
			this.usrDao.insertPersonality(usrMod);
			pers = "Lazybone";
		}
		LoggedUser.setPersonality(pers);
	}
}

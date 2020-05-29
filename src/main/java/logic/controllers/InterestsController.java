package logic.controllers;

import java.util.List;
import logic.LoggedUser;
import logic.beans.InterestsBean;
import logic.beans.UserDataBean;
import logic.dao.UserDao;
import logic.exceptions.MissingAnswareException;
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
	
	public String evaluateInterestsControl(InterestsBean interBean) throws MissingAnswareException {
		List<Integer> answ = interBean.getAnswares();
		if(answ.size() <= 3) {
			throw new MissingAnswareException("Pelase answare to all questions");
		}
		UserDataBean dBean = new UserDataBean(LoggedUser.getUserName());
		UserModel usrMod = new UserModel();
		usrMod.setUsrNameByBean(dBean);
		String pers = null;
		for(int i = 0; i < answ.size(); i++) {
			switch(answ.get(i)) {
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
		if(numbOf1 >= numbOf2 && numbOf1 >= numbOf3 && numbOf1 >= numbOf4) {
			usrMod.setUserPersonality("Friendly");
			this.usrDao.insertPersonality(usrMod);
			pers = "Friendly";
		}
		else if(numbOf2 >= numbOf1 && numbOf2 >= numbOf3 && numbOf2 >= numbOf4) {
			usrMod.setUserPersonality("Adventurer");
			this.usrDao.insertPersonality(usrMod);
			pers = "Adventurer";
		}
		else if(numbOf3 >= numbOf1 && numbOf3 >= numbOf2 && numbOf3 >= numbOf4) {
			usrMod.setUserPersonality("Lone wolf");
			this.usrDao.insertPersonality(usrMod);
			pers = "Lone wolf";
		}
		else if(numbOf4 >= numbOf1 && numbOf4 >= numbOf2 && numbOf4 >= numbOf3) {
			usrMod.setUserPersonality("Lazybone");
			this.usrDao.insertPersonality(usrMod);
			pers = "Lazybone";
		}
		LoggedUser.setPersonality(pers);
		return pers;
	}
}

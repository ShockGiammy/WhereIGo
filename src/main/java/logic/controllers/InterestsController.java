package logic.controllers;

import logic.beans.InterestsBean;
import logic.model.UserModel;

public class InterestsController {
	private int numbOf1 = 0;
	private int numbOf2 = 0;
	private int numbOf3 = 0;
	private int numbOf4 = 0;
	private UserModel usrModel;
	
	public InterestsController() {
		usrModel = UserModel.getUserModelIstance();
	}
	
	public void evaluateInterests(InterestsBean interBean) {
		int answares[] = interBean.getAnswares();
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
			}
		}
		if(numbOf1 >= 2) {
			this.usrModel.saveUserPersonality("Friendly");
		}
		if(numbOf2 >= 2) {
			this.usrModel.saveUserPersonality("Adventurer");
		}
		if(numbOf3 >= 2) {
			this.usrModel.saveUserPersonality("Lone wolf");
		}
		if(numbOf4 >= 2) {
			this.usrModel.saveUserPersonality("Lazybone");
		}
	}
}

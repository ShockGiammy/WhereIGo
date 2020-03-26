package logic.graphiccontrollers;

import logic.LoggedUser;
import logic.view.MenuWindow;

public class GraphicControllerRenterHomePage extends MenuWindow{
	
	public void initialize() {
		LoggedUser logUsr = new LoggedUser();
		this.userImage.setImage(logUsr.getImage());
	}
}

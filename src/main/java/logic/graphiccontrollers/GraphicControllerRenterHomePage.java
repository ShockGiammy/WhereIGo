package logic.graphiccontrollers;

import logic.LoggedUser;
import logic.view.BasicGui;

public class GraphicControllerRenterHomePage extends BasicGui{
	
	public void initialize() {
		LoggedUser logUsr = new LoggedUser();
		this.userImage.setImage(logUsr.getImage());
	}
}

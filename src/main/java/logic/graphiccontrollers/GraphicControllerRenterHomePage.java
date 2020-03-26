package logic.graphiccontrollers;

import logic.LoggedUser;
import logic.view.RenterGui;

public class GraphicControllerRenterHomePage extends RenterGui{
	
	public void initialize() {
		LoggedUser logUsr = new LoggedUser();
		this.userImage.setImage(logUsr.getImage());
	}
}

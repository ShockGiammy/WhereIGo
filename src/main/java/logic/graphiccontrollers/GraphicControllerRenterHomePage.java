package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import logic.LoggedUser;
import logic.view.BasicGui;

public class GraphicControllerRenterHomePage extends BasicGui{
	
	@FXML private Text user;
	
	public void initialize() {
		this.userImage.setImage(setUserImage());
		this.user.setText(LoggedUser.getUserName());
	}
}

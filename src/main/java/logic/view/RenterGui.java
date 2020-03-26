package logic.view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RenterGui extends Window{
	@FXML protected ImageView home;
	@FXML protected ImageView keys;
	@FXML protected ImageView chat;
	@FXML protected ImageView settings;
	@FXML protected ImageView userImage;
	@FXML protected ImageView exit;
	
	public void goRent(MouseEvent event) {
    	setScene("RenterAccomodations.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
    
    public void goHome(MouseEvent event) {
    	setScene("HomePage.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
	
	public void goChat(MouseEvent event) {
    	setScene("ChatViewRenter.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
}

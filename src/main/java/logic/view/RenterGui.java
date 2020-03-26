package logic.view;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RenterGui extends Window implements CommonIcons{
	@FXML protected ImageView home;
	@FXML protected ImageView keys;
	@FXML protected ImageView chat;
	@FXML protected ImageView settings;
	@FXML protected ImageView userImage;
	@FXML protected ImageView exit;
	
	@Override
	public void goRent(MouseEvent event) {
    	setScene("RenterAccomodations.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
    
	@Override
    public void goHome(MouseEvent event) {
    	setScene("RenterHomePage.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
	
	@Override
	public void goChat(MouseEvent event) {
    	setScene("ChatViewRenter.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
	
	public void leaveApp(MouseEvent event) {
		setScene("Login.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	public void showMouseChat() {
		Image chatMouse = new Image(getClass().getClassLoader().getResource("images/chatMouse.png").toString());
		chat.setImage(chatMouse);
	}
	
	public void showMouseKeys() {
		Image chatMouse = new Image(getClass().getClassLoader().getResource("images/keysMouse.png").toString());
		keys.setImage(chatMouse);
	}
	
	public void showMouseHome() {
		Image chatMouse = new Image(getClass().getClassLoader().getResource("images/homeMouse.png").toString());
		home.setImage(chatMouse);
	}
	
	public void showChat() {
		Image chatMouse = new Image(getClass().getClassLoader().getResource("images/chat.png").toString());
		chat.setImage(chatMouse);
	}
	
	public void showMKeys() {
		Image chatMouse = new Image(getClass().getClassLoader().getResource("images/keys.png").toString());
		keys.setImage(chatMouse);
	}
	
	public void showHome() {
		Image chatMouse = new Image(getClass().getClassLoader().getResource("images/home.png").toString());
		home.setImage(chatMouse);
	}
}

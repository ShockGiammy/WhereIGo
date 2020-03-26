package logic.view;

import java.util.List;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.LoggedUser;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.graphiccontrollers.GraphicControllerCheckOut;
import logic.graphiccontrollers.GraphicControllerLocationInfo;
import logic.graphiccontrollers.GraphicControllerTickets;

public class MenuWindow extends Window{
	
	@FXML protected ImageView home;
	@FXML protected ImageView keys;
	@FXML protected ImageView chat;
	@FXML protected ImageView bookTravel;
	@FXML protected ImageView settings;
	@FXML protected ImageView userImage;
	@FXML protected ImageView exit;
	
	protected String userType;
	private static final String RENTER = "Renter";
	
	public MenuWindow() {
		LoggedUser logUser = new LoggedUser();
    	this.userType = logUser.getUserType();
	}
	
	public void changeImage(ImageView image, String newImageString) {
		Image newImage = new Image(getClass().getClassLoader().getResource(newImageString).toString());
		image.setImage(newImage);
	}
	
	public void showMouseChat() {
		changeImage(chat, "images/chatMouse.png");
	}
	
	public void showMouseKeys() {
		changeImage(keys, "images/keysMouse.png");
	}
	
	public void showMouseHome() {
		changeImage(home, "images/homeMouse.png");
	}
	
	public void showMouseTravel() {
		changeImage(bookTravel, "images/BookTravelMouse.png");
	}
	
	public void showTravel() {
		changeImage(bookTravel, "images/BookTravel.png");
	}
	
	public void showChat() {
		changeImage(chat, "images/chat.png");
	}
	
	public void showKeys() {
		changeImage(keys, "images/keys.png");
	}
	
	public void showHome() {
		changeImage(home, "images/home.png");
	}

	public void goHome(MouseEvent event) {
    	if (userType.equals(RENTER)) {
    		changeGUI(event, "RenterHomePage.fxml");
    	}
    	else {
    		changeGUI(event, "HomePage.fxml");
    	}
    }
    
    public void goRent(MouseEvent event) {
    	if (userType.equals(RENTER)) {
    		changeGUI(event, "RenterAccomodations.fxml");
    	}
    	else {
    		changeGUI(event, "RentAccomodation.fxml");
    	}
    }
    
    public void goBookTravel(MouseEvent event) {
    	if (userType.equals(RENTER)) {
    		Logger logger = Logger.getLogger("WIG");
    		logger.info("method not accessible");
    	}
    	else {
    		changeGUI(event, "BookTravel.fxml");
    	}
    }
    
    public void goChat(MouseEvent event) {
    	if (userType.equals(RENTER)) {
    		changeGUI(event, "ChatViewRenter.fxml");
    	}
    	else {
    		changeGUI(event, "ChatView.fxml");
    	}
    }
    
	public void leaveApp(MouseEvent event) {
		setScene("Login.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
    
    public void setLocationInfo(MouseEvent e, LocationBean bean) {
		GraphicControllerLocationInfo controller = loader.getController();
		controller.setInfo(bean);
		nextGuiOnClick(e);
	}
	
	public void setTicketsDats(List<UserTravelBean> bean, MouseEvent e) {
		GraphicControllerTickets controller = loader.getController();
		controller.setDatas(bean);
		nextGuiOnClick(e);
	}
	
	public void setCheckoutValues(UserTravelBean travBean, UserDataBean dataBean, MouseEvent e) {
		GraphicControllerCheckOut controller = loader.getController();
		controller.setInfo(travBean, dataBean);
		nextGuiOnClick(e);
	}
}

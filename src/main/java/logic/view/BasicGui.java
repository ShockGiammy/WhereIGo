package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.LoggedUser;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.graphiccontrollers.GraphicControllerCheckOut;
import logic.graphiccontrollers.GraphicControllerLocationInfo;
import logic.graphiccontrollers.GraphicControllerTickets;

public class BasicGui extends Application{
	
	@FXML protected ImageView home;
	@FXML protected ImageView keys;
	@FXML protected ImageView chat;
	@FXML protected ImageView bookTravel;
	@FXML protected ImageView settings;
	@FXML protected ImageView userImage;
	@FXML protected ImageView exit;
	
	protected static FXMLLoader loader = new FXMLLoader();
	private static final String RENTER = "Renter";
	private static String sample;
	private static Scene scene;
	protected static Logger logger = Logger.getLogger("WIG");
	protected LoggedUser logUsr;
	
	public BasicGui() {
		this.logUsr = new LoggedUser();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Cannot load GUI\n",e);
		}
	}
	
	public static void setScene(String newScene) {
		sample = newScene;
	}
	
	public void nextGuiOnClick(MouseEvent event) {
		Stage regStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		start(regStage);
	}
	
	public static void loadScene() {
		try {
			loader = new FXMLLoader();
			URL loc = BasicGui.class.getResource(sample);
			loader.setLocation(loc);
			Parent newSceneParent = loader.load();
			scene = new Scene(newSceneParent);
			scene.getStylesheets().add(BasicGui.class.getResource("application.css").toExternalForm());
		}catch(IOException e) {
			logger.log(Level.SEVERE, "Cannot load the scene\n", e);
		}
	}
	
	public void changeGUI(MouseEvent event, String newScene) {
		setScene(newScene);
    	loadScene();
    	nextGuiOnClick(event);
	}
	
	public void changeImage(ImageView image, String newImageString) {
		Image newImage = new Image(getClass().getClassLoader().getResource(newImageString).toString());
		image.setImage(newImage);
	}
	
	public void showMouseChat() {
		changeImage(chat, "images/chatMouse.png");
	}
	
	public void showMouseKeys() {
		changeImage(keys, "images/keysMouse.jpg");
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
		changeImage(keys, "images/keys.jpg");
	}
	
	public void showHome() {
		changeImage(home, "images/home.png");
	}

	public void goHome(MouseEvent event) {
    	if (logUsr.getUserType().equals(RENTER)) {
    		changeGUI(event, "RenterHomePage.fxml");
    	}
    	else {
    		changeGUI(event, "HomePage.fxml");
    	}
    }
    
    public void goRent(MouseEvent event) {
    	if (logUsr.getUserType().equals(RENTER)) {
    		changeGUI(event, "RenterAccomodations.fxml");
    	}
    	else {
    		changeGUI(event, "RentAccomodation.fxml");
    	}
    }
    
    public void goBookTravel(MouseEvent event) {
    	if (logUsr.getUserType().equals(RENTER)) {
    		logger.info("method not accessible");
    	}
    	else {
    		changeGUI(event, "BookTravel.fxml");
    	}
    }
    
    public void goChat(MouseEvent event) {
    	if (logUsr.getUserType().equals(RENTER)) {
    		changeGUI(event, "ChatViewRenter.fxml");
    	}
    	else {
    		changeGUI(event, "ChatView.fxml");
    	}
    }
    
	public void leaveApp(MouseEvent event) {
		changeGUI(event, "Login.fxml");
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
	
	public static void main(String[] args) {
		setScene("Login.fxml");
		loadScene();
		launch(args);
	}
}

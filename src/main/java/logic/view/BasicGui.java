package logic.view;

import java.io.IOException;
import java.net.URL;
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

public class BasicGui extends Application{
	
	@FXML protected ImageView home;
	@FXML protected ImageView keys;
	@FXML protected ImageView chat;
	@FXML protected ImageView bookTravel;
	@FXML protected ImageView settings;
	@FXML protected ImageView userImage;
	@FXML protected ImageView exit;
	
	protected static FXMLLoader loader = new FXMLLoader();
	private static String sample;
	private static Scene scene;
	private static Logger logger = Logger.getLogger("WIG");
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
	
	public void showMouseExit() {
		changeImage(exit, "images/exitMouse.png");
	}
	
	public void showExit() {
		changeImage(exit, "images/exit.png");
	}

	public void goHome(MouseEvent event) {
		switch (logUsr.getUserType()) {
			case RENTER:
				changeGUI(event, "RenterHomePage.fxml");
				break;
			case TRAVELER:
				changeGUI(event, "HomePage.fxml");
				break;
    	}
    }
    
    public void goRent(MouseEvent event) {
    	switch (logUsr.getUserType()) {
			case RENTER:
				changeGUI(event, "RenterAccomodations.fxml");
				break;
			case TRAVELER:
				changeGUI(event, "RentAccomodation.fxml");
				break;
    	}
    }
    
    public void goBookTravel(MouseEvent event) {
    	switch (logUsr.getUserType()) {
			case RENTER:
				logger.info("method not accessible");
				break;
			case TRAVELER:
				changeGUI(event, "BookTravel.fxml");
				break;
    	}
    }
    
    public void goChat(MouseEvent event) {
    	switch (logUsr.getUserType()) {
			case RENTER:
				changeGUI(event, "ChatViewRenter.fxml");
				break;
			case TRAVELER:
				changeGUI(event, "ChatView.fxml");
				break;
    	}
    }
    
	public void leaveApp(MouseEvent event) {
		changeGUI(event, "Login.fxml");
	}
	
	public static void main(String[] args) {
		setScene("Login.fxml");
		loadScene();
		launch(args);
	}
}

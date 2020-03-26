package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;

public class Window extends Application{
	private static String sample;
	protected static FXMLLoader loader;
	private static Scene scene;
	private static Logger logger = Logger.getLogger("WIG");
	
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
			URL loc = Window.class.getResource(sample);
			loader.setLocation(loc);
			Parent newSceneParent = loader.load();
			scene = new Scene(newSceneParent);
			scene.getStylesheets().add(Window.class.getResource("application.css").toExternalForm());
		}catch(IOException e) {
			logger.log(Level.SEVERE, "Cannot load the scene\n", e);
		}
	}
	
    public void goRent(MouseEvent event) {
    	setScene("RentAccomodation.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
    
    public void goHome(MouseEvent event) {
    	setScene("HomePage.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
	
	public void goChat(MouseEvent event) {
    	setScene("ChatView.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
}

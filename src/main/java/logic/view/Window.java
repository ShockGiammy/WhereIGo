package logic.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.beans.UserDataBean;
import logic.graphiccontrollers.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;

public class Window extends Application{
	
	private static String sample;
	private static FXMLLoader loader;
	private static Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setScene(String newScene) {
		sample = newScene;
	}
	
	public void nextGuiOnClick(MouseEvent event) {
		Stage regStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		start(regStage);
	}
	
	public void setUserDatas(UserDataBean bean, MouseEvent e) {
		GraphicControllerHomePage controller = loader.getController();
		controller.setDatas(bean);
		nextGuiOnClick(e);
	}
	
	public void setSuggestedLocations(MouseEvent e) throws SQLException {
		GraphicControllerBookTravel controller = loader.getController();
		controller.setLocation();
		nextGuiOnClick(e);
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
			System.out.println("Exception occurred\n");
		}
	}
}

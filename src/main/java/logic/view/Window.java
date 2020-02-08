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
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Window extends Application{
	
	private static String sample;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			URL loc = Window.class.getResource(sample);
			AnchorPane root = (AnchorPane)FXMLLoader.load(loc);
			Scene scene = new Scene(root,1059,751);
			scene.getStylesheets().add(Window.class.getResource("application.css").toExternalForm());
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
	
	public void guiWithValue(UserDataBean bean, MouseEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL loc = Window.class.getResource(sample);
		loader.setLocation(loc);
		Parent newSceneParent = loader.load();
		GraphicControllerHomePage controller = loader.getController();
		controller.setDatas(bean);
		Scene scene = new Scene(newSceneParent);
		scene.getStylesheets().add(Window.class.getResource("application.css").toExternalForm());
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void guiWithValue1(MouseEvent e) throws IOException, SQLException {
		FXMLLoader loader = new FXMLLoader();
		URL loc = Window.class.getResource(sample);
		loader.setLocation(loc);
		Parent newSceneParent = loader.load();
		GraphicControllerBookTravel controller = loader.getController();
		controller.setLocation();
		Scene scene = new Scene(newSceneParent);
		scene.getStylesheets().add(Window.class.getResource("application.css").toExternalForm());
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

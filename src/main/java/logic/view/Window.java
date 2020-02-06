package logic.view;

import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
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
			System.out.println(loc);
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
}

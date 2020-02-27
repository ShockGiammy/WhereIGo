/* This class should be a part of a Decorator pattern*/

package logic.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorLogin {
	public void displayLoginError(String text) {
		Stage window = new Stage();
		window.setWidth(300);
		window.setHeight(300);
		window.initModality(Modality.APPLICATION_MODAL); //this avoid user to interact with other users
		Text label = new Text();
		label.setText(text);
		Button closeButton = new Button("close");
		closeButton.setOnAction(e->window.close());
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}

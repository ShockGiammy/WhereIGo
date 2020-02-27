package logic.view;
import logic.beans.LocationBean;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LocationInfo {
	private Text locName;
	private Text locCountry;
	private Button closeButton;
	private Text description;
	private ImageView image;
	
	public void setAndShow(LocationBean locBean) {
		Stage locStage = new Stage();
		locStage.setWidth(1050);
		locStage.setHeight(700);
		locName = new Text();
		locName.setText(locBean.getCityName());
		locCountry = new Text();
		locCountry.setText(locBean.getCountryName());
		description = new Text();
		description.setText(locBean.getDescription());
		image = new ImageView(); //this will be set up
		closeButton = new Button("close");
		closeButton.setOnAction(e->locStage.close());
		Pane pane = new Pane();
		pane.setMinHeight(500);
		pane.setMinWidth(800);
		pane.getChildren().addAll(locName,locCountry, description, image);
		locName.setLayoutY(17);
		locName.setFont(Font.font(20));
		locCountry.setLayoutY(40);
		locCountry.setFont(Font.font(20));
		description.setLayoutY(180);
		description.setFont(Font.font(15));
		image.setLayoutY(19);
		Scene scene = new Scene(pane);
		locStage.setScene(scene);
		locStage.showAndWait();
	}
}

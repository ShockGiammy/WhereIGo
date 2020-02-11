package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.beans.RentAccomodationBean;
import logic.controllers.RentAccomodationController;

public class GraphicControlRentAccomodation {
	
	@FXML TextField city;
	@FXML TextField beds;
	@FXML ImageView house;
	@FXML TextField rating;
	@FXML Button detail;
	@FXML ImageView home;
	
	/*
	@FXML TextField city2;
	@FXML TextField beds2;
	@FXML ImageView house2;
	@FXML TextField rating2;
	@FXML Button detail2;
	
	@FXML TextField city3;
	@FXML TextField beds3;
	@FXML ImageView house3;
	@FXML TextField rating3;
	@FXML Button detail3;
	
	@FXML TextField city4;
	@FXML TextField beds4;
	@FXML ImageView house4;
	@FXML TextField rating4;
	@FXML Button detail4;

	@FXML TextField city5;
	@FXML TextField beds5;
	@FXML ImageView house5;
	@FXML TextField rating5;
	@FXML Button detail5;
	
	@FXML TextField city6;
	@FXML TextField beds6;
	@FXML ImageView house6;
	@FXML TextField rating6;
	@FXML Button detail6;
	*/
	
	private RentAccomodationController control;
	private RentAccomodationBean bean;
	
	@FXML
	public void initialize() {
		control = new RentAccomodationController();
		bean = control.displayAnnouncement();
		city.setText(bean.getCity());
		beds.setText(bean.getBeds());
		//Image image = new Image(bean.getHouseImage().toURI().toString());
		//house[1].setImage(image);
		rating.setText("5/5");
	}
}


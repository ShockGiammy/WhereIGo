package logic.graphiccontrollers;

import java.awt.image.BufferedImage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import logic.ImageViewer;
import logic.beans.RentAccomodationBean;
import logic.controllers.RentAccomodationController;
import logic.view.Window;

public class GraphicControlRentAccomodation extends Window{
	
	@FXML private ImageView home;
	@FXML private ImageView chiavi;
	
	@FXML TextField city;
	@FXML Text beds;
	@FXML ImageView house;
	@FXML TextField rating;
	@FXML Button detail;
	@FXML AnchorPane aPane;
	
	@FXML TextField city2;
	@FXML Text beds2;
	@FXML ImageView house2;
	@FXML TextField rating2;
	@FXML Button detail2;
	@FXML AnchorPane aPane2;
	
	@FXML TextField city3;
	@FXML Text beds3;
	@FXML ImageView house3;
	@FXML TextField rating3;
	@FXML Button detail3;
	@FXML AnchorPane aPane3;
	
	@FXML TextField city4;
	@FXML Text beds4;
	@FXML ImageView house4;
	@FXML TextField rating4;
	@FXML Button detail4;
	@FXML AnchorPane aPane4;

	@FXML TextField city5;
	@FXML Text beds5;
	@FXML ImageView house5;
	@FXML TextField rating5;
	@FXML Button detail5;
	@FXML AnchorPane aPane5;
	
	@FXML TextField city6;
	@FXML Text beds6;
	@FXML ImageView house6;
	@FXML TextField rating6;
	@FXML Button detail6;
	@FXML AnchorPane aPane6;
	
	//details
	@FXML TextArea description;
	@FXML TextField cityDetail;
	@FXML TextField address;
	@FXML TextField bedsDetail;
	@FXML TextField type;
	@FXML TextField squareMetres;
	@FXML TextField renter;
	@FXML ImageView houseDetail;
	@FXML CheckBox garden;
	@FXML CheckBox wifi;
	@FXML CheckBox bathroom;
	@FXML CheckBox kitchen;
	@FXML Button contactRenter;
	
	
	private RentAccomodationController control;
	private ImageViewer viewer;
	
	
	@FXML
	public void initialize() {
		control = new RentAccomodationController();
		RentAccomodationBean[] bean = new RentAccomodationBean[6];
		bean = control.displayAnnouncement();
		if (bean[0] != null) {
			setDisplayInfo(city, beds, house, rating, bean[0]);
		}
		else {
			aPane.setVisible(false);
		}
		if (bean[1] != null) {
			setDisplayInfo(city2, beds2, house2, rating2, bean[1]);
		}
		else {
			aPane2.setVisible(false);
		}
		if (bean[2] != null) {
			setDisplayInfo(city3, beds3, house3, rating3, bean[2]);
		}
		else {
			aPane3.setVisible(false);
		}
		if (bean[3] != null) {
			setDisplayInfo(city4, beds4, house4, rating4, bean[3]);
		}
		else {
			aPane4.setVisible(false);
		}
		if (bean[4] != null) {
			setDisplayInfo(city5, beds5, house5, rating5, bean[4]);
		}
		else {
			aPane5.setVisible(false);
		}
		if (bean[5] != null) {
			setDisplayInfo(city6, beds6, house6, rating6, bean[5]);
		}
		else {
			aPane6.setVisible(false);
		}
	}
	
	public GraphicControlRentAccomodation() {
		viewer = new ImageViewer();
	}
	
	@SuppressWarnings("exports")
	public void setDisplayInfo(TextField city, Text beds, ImageView house, TextField rating, RentAccomodationBean bean) {
		city.setText(bean.getCity());
		beds.setText(bean.getBeds());
		BufferedImage bufImage = viewer.loadImage(bean);
		house.setImage(viewer.convertToFxImage(bufImage));
		rating.setText("5/5");
}
	
	public void setAccomodationDetail1() {
		RentAccomodationBean bean = control.getDetail(0);
		this.setDetail(bean);
	}
	
	public void setAccomodationDetail2() {
		RentAccomodationBean bean = control.getDetail(1);
		this.setDetail(bean);
	}
	
	public void setAccomodationDetail3() {
		RentAccomodationBean bean = control.getDetail(2);
		this.setDetail(bean);
	}
	
	public void setAccomodationDetail4() {
		RentAccomodationBean bean = control.getDetail(3);
		this.setDetail(bean);
	}

	public void setAccomodationDetail5() {
		RentAccomodationBean bean = control.getDetail(4);
		this.setDetail(bean);
	}
	
	public void setAccomodationDetail6() {
		RentAccomodationBean bean = control.getDetail(5);
		this.setDetail(bean);
	}
	
	public void setDetail(RentAccomodationBean bean) {
		description.setVisible(true);
		description.setText(bean.getDescription());
		cityDetail.setVisible(true);
		cityDetail.setText(bean.getCity());
		address.setVisible(true);
		address.setText(bean.getAddress());
		bedsDetail.setVisible(true);
		bedsDetail.setText(bean.getBeds());
		type.setVisible(true);
		type.setText(bean.getType());
		squareMetres.setVisible(true);
		squareMetres.setText(bean.getSquareMetres());
		renter.setVisible(true);
		renter.setText(bean.getRenter());
		houseDetail.setVisible(true);
		BufferedImage bufImage = viewer.loadImage(bean);
		rating.setText("5/5");
		houseDetail.setImage(viewer.convertToFxImage(bufImage));
		garden.setVisible(true);
		wifi.setVisible(true);
		bathroom.setVisible(true);
		kitchen.setVisible(true);
		byte[] list = bean.getServices();
		for (int i = 0; i <= 3; i++) {
			if (list[0] == 1) {
				garden.setSelected(true);
			}
			if (list[1] == 1) {
				wifi.setSelected(true);
			}
			if (list[2] == 1) {
				bathroom.setSelected(true);
			}
			if (list[3] == 1) {
				kitchen.setSelected(true);
			}
		}
		contactRenter.setVisible(true);
		garden.setDisable(true);
		wifi.setDisable(true);
		bathroom.setDisable(true);
		kitchen.setDisable(true);
	}
	
	@SuppressWarnings("exports")
	public void contactRenter(MouseEvent event) {
		setScene("ChatTraveller.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
}


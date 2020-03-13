package logic.graphiccontrollers;

import java.awt.image.BufferedImage;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.ImageViewer;
import logic.beans.RentAccomodationBean;
import logic.controllers.RentAccomodationController;
import logic.view.Window;

public class GraphicControlRentAccomodation extends Window{
	
	@FXML private ImageView home;
	@FXML private ImageView keys;
	@FXML private ImageView chat;
	@FXML private ImageView bookTravel;
	@FXML private ImageView favourite;
	@FXML private ImageView settings;
	
	@FXML private ListView<HBox> accomodationList;
	@FXML private HBox hBox;
	
	//details
	@FXML private TextArea description;
	@FXML private TextField cityDetail;
	@FXML private TextField address;
	@FXML private TextField bedsDetail;
	@FXML private TextField type;
	@FXML private TextField squareMetres;
	@FXML private TextField renter;
	@FXML private ImageView houseDetail;
	@FXML private CheckBox garden;
	@FXML private CheckBox wifi;
	@FXML private CheckBox bathroom;
	@FXML private CheckBox kitchen;
	@FXML private Button contactRenter;
	@FXML private TextField rating;
	
	
	private RentAccomodationController control;
	private ImageViewer viewer;
	private int number = 0;
	
	
	@FXML
	public void initialize() {
		control = new RentAccomodationController();
		RentAccomodationBean[] bean = new RentAccomodationBean[6];
		bean = control.displayAnnouncement();
		if (bean[0] != null) {
			System.out.println("0");
			setDisplayInfo(bean[0]);
		}
		if (bean[1] != null) {
			setDisplayInfo(bean[1]);
			System.out.println("1");
		}
		if (bean[2] != null) {
			setDisplayInfo(bean[2]);
			System.out.println("2");
		}

		if (bean[3] != null) {
			setDisplayInfo(bean[3]);
			System.out.println("3");
		}

		if (bean[4] != null) {
			setDisplayInfo(bean[4]);
		}

		if (bean[5] != null) {
			setDisplayInfo(bean[5]);
		}
	}
	
	public GraphicControlRentAccomodation() {
		viewer = new ImageViewer();
	}
	
	public void setDisplayInfo(RentAccomodationBean bean) {
		BorderPane pane = new BorderPane();
		Text city = new Text();
		city.setText("City  ");
		Text cityValue = new Text();
		cityValue.setText(bean.getCity());
		HBox cityBox = new HBox();
		cityBox.getChildren().addAll(city, cityValue);
		VBox vBox = new VBox();
		HBox bedsBox = new HBox();
		vBox.getChildren().add(bedsBox);
		Text beds = new Text();
		beds.setText("Beds  ");
		Text numberBeds = new Text();
		numberBeds.setText(bean.getBeds());
		bedsBox.getChildren().addAll(beds, numberBeds);
		ImageView house = new ImageView();
		BufferedImage bufImage = viewer.loadImage(bean.getHouseImage());
		house.setFitHeight(150);
		house.setFitWidth(150);
		house.setX(25);
		house.setImage(viewer.convertToFxImage(bufImage));
		Text rating2 = new Text();
		rating2.setText("Rating  ");
		Text ratingValue = new Text();		
		ratingValue.setText("5/5");
		HBox ratingBox = new HBox();
		ratingBox.getChildren().addAll(rating2, ratingValue);
		vBox.getChildren().add(ratingBox);
		Button details = new Button();
		details.setText("View Details");
		details.setOnMouseClicked(e -> {
    		setDetail(bean);
    	});
		HBox detailBox = new HBox();
		detailBox.getChildren().add(details);
		detailBox.setAlignment(Pos.CENTER_RIGHT);
		pane.setTop(cityBox);
		pane.setCenter(house);
		pane.setRight(vBox);
		pane.setBottom(detailBox);
		pane.setBorder(new Border(new BorderStroke(Color.BLUE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		if (number == 0) {
			hBox = new HBox();
			hBox.getChildren().add(pane);
			accomodationList.getItems().addAll(hBox);
		}
		else {
			hBox.getChildren().add(pane);
		}
		number = (number+1)%2;
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
		BufferedImage bufImage = viewer.loadImage(bean.getHouseImage());
		//rating.setText("5/5");
		houseDetail.setFitHeight(180);
		houseDetail.setFitWidth(350);
		houseDetail.setImage(viewer.convertToFxImage(bufImage));
		garden.setVisible(true);
		wifi.setVisible(true);
		bathroom.setVisible(true);
		kitchen.setVisible(true);
		byte[] list = bean.getServices();
		if (bean.getServices() != null) {
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
		}
		contactRenter.setVisible(true);
		garden.setDisable(true);
		wifi.setDisable(true);
		bathroom.setDisable(true);
		kitchen.setDisable(true);
	}
	
	public void contactRenter(MouseEvent event) {
		setScene("ChatView.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
}


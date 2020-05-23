package logic.graphiccontrollers;

import java.util.List;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
import logic.beans.RentAccomodationBean;
import logic.view.BasicGui;
import logic.view.ErrorPopup;

public class GraphicControlRentAccomodation extends BasicGui{
	
	@FXML private ListView<HBox> accomodationList;
	@FXML private HBox hBox;
	
	//details
	@FXML private Text description;
	@FXML private Text descriptionText;
	@FXML private Text cityDetail;
	@FXML private Text address;
	@FXML private Text addressText;
	@FXML private Text cityText;
	@FXML private Text bedsText;
	@FXML private Text bedsDetail;
	@FXML private Text type;
	@FXML private Text typeText;
	@FXML private Text squareMetres;
	@FXML private Text squareMetresText;
	@FXML private Text renter;
	@FXML private ImageView houseDetail;
	@FXML private Text garden;
	@FXML private Text wifi;
	@FXML private Text bathroom;
	@FXML private Text kitchen;
	@FXML private Text gardenText;
	@FXML private Text wifiText;
	@FXML private Text bathroomText;
	@FXML private Text kitchenText;
	@FXML private Button contactRenter;
	
	private int number = 0;
	private double pading = 5.0;
	
	
	@FXML
	public void initialize() {
		this.userImage.setImage(setUserImage());
		
		List<RentAccomodationBean> listOfBean = facade.displayAnnouncement();
		if (listOfBean.isEmpty()) {
			ErrorPopup error = new ErrorPopup();
			error.displayLoginError("no accomodation to been shown");
		}
		else {
			for (RentAccomodationBean bean : listOfBean) {
			setDisplayBoxInfo(bean);
			}
		}
	}
	
	public void setDisplayBoxInfo(RentAccomodationBean bean) {
		
		if (number == 0) {
			hBox = new HBox();
			setDisplayInfo(bean, hBox);
			accomodationList.getItems().addAll(hBox);	
		}
		else {
			setDisplayInfo(bean, hBox);
		}
		number = (number+1)%2;
	}
	
	public void setDisplayInfo(RentAccomodationBean bean, HBox box) {
		
		Task<BorderPane> task = new Task<BorderPane>() {
            @Override
            public BorderPane call() {
            	BorderPane pane = new BorderPane();
            	Text city = new Text();
            	city.setText("City  ");
            	city.setUnderline(true);
            	Text cityValue = new Text();
            	cityValue.setText(bean.getCity());
            	HBox cityBox = new HBox();
            	cityBox.getChildren().addAll(city, cityValue);
            	cityBox.setPadding(new Insets(pading, pading, pading, pading));
            	VBox vBox = new VBox();
				HBox bedsBox = new HBox();
				vBox.getChildren().add(bedsBox);
				Text beds = new Text();
				beds.setText("Beds  ");
				beds.setUnderline(true);
				Text numberBeds = new Text();
				numberBeds.setText(bean.getBeds());
				bedsBox.getChildren().addAll(beds, numberBeds);
				bedsBox.setPadding(new Insets(pading, pading, pading, pading));
				ImageView house = new ImageView();
				house.setFitHeight(150);
				house.setFitWidth(150);
				house.setX(30);
				house.setImage(facade.loadImage(bean.getHouseImage()));
				Button details = new Button();
				details.setText("View Details");
				details.setOnMouseClicked(e -> 
    				setDetail(bean));
				HBox detailBox = new HBox();
				detailBox.getChildren().add(details);
				detailBox.setAlignment(Pos.CENTER_RIGHT);
				detailBox.setPadding(new Insets(pading, pading, pading, pading));
				pane.setTop(cityBox);
				pane.setCenter(house);
				pane.setRight(vBox);
				pane.setBottom(detailBox);
				pane.setBorder(new Border(new BorderStroke(Color.BLUE, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			return pane;
            }
        };
        
		task.setOnSucceeded(event ->
			box.getChildren().add(task.getValue()));
			
		Thread t = new Thread(task);
	    t.setDaemon(true);
	    t.start();    	
	}
	
	public void setDetail(RentAccomodationBean bean) {
		descriptionText.setVisible(true);
		description.setVisible(true);
		description.setText(bean.getDescription());
		cityDetail.setVisible(true);
		cityText.setVisible(true);
		cityDetail.setText(bean.getCity());
		address.setVisible(true);
		addressText.setVisible(true);
		address.setText(bean.getAddress());
		bedsDetail.setVisible(true);
		bedsText.setVisible(true);
		bedsDetail.setText(bean.getBeds());
		type.setVisible(true);
		typeText.setVisible(true);
		type.setText(bean.getType());
		squareMetres.setVisible(true);
		squareMetresText.setVisible(true);
		squareMetres.setText(bean.getSquareMetres());
		renter.setVisible(true);
		renter.setText(bean.getRenter());
		houseDetail.setVisible(true);
		houseDetail.setFitHeight(180);
		houseDetail.setFitWidth(350);
		houseDetail.setImage(facade.loadImage(bean.getHouseImage()));
		
		setServices(bean);
		
		contactRenter.setVisible(true);		
	}
	
	public void setServices(RentAccomodationBean bean) {
		
    	garden.setVisible(true);
    	gardenText.setVisible(true);
    	wifi.setVisible(true);
    	wifiText.setVisible(true);
    	bathroom.setVisible(true);
    	bathroomText.setVisible(true);
    	kitchen.setVisible(true);
    	kitchenText.setVisible(true);
    	garden.setText("NO");
    	wifi.setText("NO");
    	bathroom.setText("NO");
    	kitchen.setText("NO");
    	byte[] list = bean.getServices();
    	if (bean.getServices() != null) {
    		for (int i = 0; i <= 3; i++) {
    			if (list[0] == 1) {
    				garden.setText("SI");
    			}
    			if (list[1] == 1) {
    				wifi.setText("SI");
    			}
    			if (list[2] == 1) {
    				bathroom.setText("SI");
    			}
    			if (list[3] == 1) {
    				kitchen.setText("SI");
    			}
    		}
		}
	}
	
	public void contactRenter(MouseEvent event) {
		facade.createChat(renter.getText());
		goChat(event);
	}
}


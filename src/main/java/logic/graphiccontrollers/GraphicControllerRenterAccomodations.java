package logic.graphiccontrollers;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import logic.beans.RentAccomodationBean;
import logic.view.BasicGui;

public class GraphicControllerRenterAccomodations extends BasicGui{

	@FXML private ListView<HBox> accomodationsList;
	
	public GraphicControllerRenterAccomodations() {
		addCreateLabel();
		List<RentAccomodationBean> listOfBean = facade.displayMyAnnouncement();
		for (RentAccomodationBean bean : listOfBean) {
			setDisplayInfo(bean);
		}
		this.userImage.setImage(setUserImage());
	}
		
	private synchronized void setDisplayInfo(RentAccomodationBean bean) {
		
		Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
            	
            	HBox accomodationBox = new HBox();
            	ImageView house = new ImageView();
            	BufferedImage bufImage = facade.loadImage(bean.getHouseImage());
            	house.setFitHeight(50);
            	house.setFitWidth(50);
            	house.setX(25);
            	house.setImage(facade.convertToFxImage(bufImage));
            	Text type = new Text();
            	type.setText("  Type  ");
            	Text typeValue = new Text();
            	typeValue.setText(bean.getType());
            	Text city = new Text();
            	city.underlineProperty();
            	city.setText("  City  ");
            	Text cityValue = new Text();
            	cityValue.setText(bean.getCity());
            	Button modify = new Button();
            	modify.setText("Modify");
            	modify.setOnMouseClicked(e -> {
            		setScene("InfoAccomodation.fxml");
                	loadScene();
            		setAccomodationInfo(e, bean);
            	});
            	Button delete = new Button();
            	delete.setText("Delete");
            	delete.setOnMouseClicked(e -> {
            		facade.deleteMyAccomodation(bean.getID());
            		delete(accomodationBox);
            	});
			
            	accomodationBox.getChildren().addAll(house, type, typeValue, city, cityValue, modify, delete);
            	return accomodationBox;
            }
		};
	    
	    task.setOnSucceeded(event ->
			accomodationsList.getItems().add(task.getValue()));

	    Thread t = new Thread(task);
	    t.setDaemon(true);
	    t.start();
	}
	
	public void delete(HBox box) {
		accomodationsList.getItems().remove(box);
	}
	
	public void addCreateLabel() {
		Task<HBox> task = new Task<HBox>() {
			
			@Override
			public HBox call() throws Exception {
        	HBox createBox = new HBox();
        	Button create = new Button();
        	create.setText("Create a new Accomodation");
        	create.setOnMouseClicked(e -> {
        		setScene("InfoAccomodation.fxml");
            	loadScene();
            	nextGuiOnClick(e);
        	});
		
        	createBox.getChildren().add(create);
        	return createBox;
			}
		};
    
		task.setOnSucceeded(event ->
			accomodationsList.getItems().add(task.getValue()));

		Thread t = new Thread(task);
		t.setDaemon(true);
		t.start();
	}
	
	public void setAccomodationInfo(MouseEvent e, RentAccomodationBean bean) {
		GraphicControllerCreateAccomodation controllerCalled = loader.getController();
		controllerCalled.setInfo(bean);
		nextGuiOnClick(e);
	}
}

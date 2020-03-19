package logic.graphiccontrollers;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import logic.ImageViewer;
import logic.beans.RentAccomodationBean;
import logic.controllers.ManageAnnouncementController;
import logic.view.Window;

public class GraphicControllerRenterAccomodations extends Window{
	
	@FXML private ImageView home;
	@FXML private ImageView chat;
	@FXML private ImageView bookTravel;
	@FXML private ImageView favourite;
	@FXML private ImageView settings;

	@FXML private ListView<HBox> accomodationsList;
	
	private ManageAnnouncementController controller;
	private ImageViewer viewer;
	
	
	public GraphicControllerRenterAccomodations() {
		controller = new ManageAnnouncementController();
		viewer = new ImageViewer();
		List<RentAccomodationBean> listOfBean = controller.displayMyAnnouncement();
		for (RentAccomodationBean bean : listOfBean) {
			setDisplayInfo(bean);
		}
		addCreateLabel();
	}
		
	private synchronized void setDisplayInfo(RentAccomodationBean bean) {
		
		Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
            	
            	HBox accomodationBox = new HBox();
            	ImageView house = new ImageView();
            	BufferedImage bufImage = viewer.loadImage(bean.getHouseImage());
            	house.setFitHeight(50);
            	house.setFitWidth(50);
            	house.setX(25);
            	house.setImage(viewer.convertToFxImage(bufImage));
            	Text type = new Text();
            	type.setText("  Type  ");
            	Text typeValue = new Text();
            	typeValue.setText(bean.getType());
            	Text city = new Text();
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
            		controller.deleteMyAccomodation(bean.getID());
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
}

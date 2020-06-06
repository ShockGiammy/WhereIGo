package logic.graphiccontrollers;

import java.util.List;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.beans.AccommodationBean;
import logic.view.BasicGui;

public class GraphicControllerManageAccommodations extends BasicGui{

	@FXML private ListView<HBox> accommodationsList;
	
	private static final String BOLD = "-fx-font-weight: bold";
	
	@FXML
	public void initialize() {
		this.userImage.setImage(setUserImage());
		addCreateLabel();
		List<AccommodationBean> listOfBean = facade.retrieveMyAnnouncement();
		if (listOfBean.isEmpty()) {
			this.popErr.displayErrorPopup("No accommodation has to been shown");
		}
		else {
			for (AccommodationBean bean : listOfBean) {
				setDisplayInfo(bean);
			}
		}	
	}
		
	private synchronized void setDisplayInfo(AccommodationBean bean) {
		
		Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() {
            	
            	HBox accommodationBox = new HBox(10);
            	ImageView house = new ImageView();
            	house.setFitHeight(50);
            	house.setFitWidth(50);
            	house.setX(25);
            	house.setImage(facade.loadImage(bean.getHouseImage()));
            	VBox vbox1 = new VBox();
            	VBox vbox2 = new VBox();
            	Text type = new Text();
            	type.setText("Type:");
            	Text typeValue = new Text();
            	typeValue.setText(bean.getType());
            	typeValue.setStyle(BOLD);
            	Text space1 = new Text();
            	space1.setText("                  ");
            	vbox1.getChildren().addAll(typeValue, space1);
            	Text city = new Text();
            	city.setText("City:");
            	Text cityValue = new Text();
            	cityValue.setText(bean.getCity());
            	cityValue.setStyle(BOLD);
            	Text space2 = new Text();
            	space2.setText("                                     ");
            	vbox2.getChildren().addAll(cityValue, space2);
            	Button modify = new Button();
            	modify.setText("Modify");
            	modify.setOnMouseClicked(e -> {
            		setScene("InfoAccommodation.fxml");
                	loadScene();
            		setAccommodationInfo(e, bean);
            	});
            	Button delete = new Button();
            	delete.setText("Delete");
            	delete.setOnMouseClicked(e -> {
            		facade.deleteMyAccommodation(bean.getID());
            		delete(accommodationBox);
            	});
			
            	accommodationBox.getChildren().addAll(house, type, vbox1, city, vbox2, modify, delete);
            	return accommodationBox;
            }
		};
	    
	    task.setOnSucceeded(event ->
			accommodationsList.getItems().add(task.getValue()));

	    Thread t = new Thread(task);
	    t.setDaemon(true);
	    t.start();
	}
	
	public void delete(HBox box) {
		accommodationsList.getItems().remove(box);
	}
	
	public void addCreateLabel() {
		Task<HBox> task = new Task<HBox>() {
			
			@Override
			public HBox call() throws Exception {
        	HBox createBox = new HBox();
        	Button create = new Button();
        	create.setText("Create a new Accommodation");
        	create.setOnMouseClicked(e -> {
        		setScene("InfoAccommodation.fxml");
            	loadScene();
            	nextGuiOnClick(e);
        	});
		
        	createBox.getChildren().add(create);
        	return createBox;
			}
		};
    
		task.setOnSucceeded(event ->
			accommodationsList.getItems().add(task.getValue()));

		Thread t = new Thread(task);
		t.setDaemon(true);
		t.start();
	}
	
	public void setAccommodationInfo(MouseEvent e, AccommodationBean bean) {
		GraphicControllerCreateAccommodation controllerCalled = loader.getController();
		controllerCalled.setInfo(bean);
		nextGuiOnClick(e);
	}
}

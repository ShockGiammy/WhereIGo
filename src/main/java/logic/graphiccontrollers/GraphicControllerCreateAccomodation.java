package logic.graphiccontrollers;

import java.awt.image.BufferedImage;
import java.io.File;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import logic.ImageViewer;
import logic.LoggedUser;
import logic.beans.RentAccomodationBean;
import logic.controllers.ManageAnnouncementController;
import logic.view.ErrorPopup;
import logic.view.Window;

public class GraphicControllerCreateAccomodation extends Window{
	
	ObservableList<String> typeList = FXCollections.observableArrayList("appartamento", "villetta", "monolocale");
	ObservableList<String> squareList = FXCollections.observableArrayList("< 20", "20 - 39", "40 - 59", "> 60");
	ObservableList<String> beds = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", ">8");
	
	@FXML private ChoiceBox<String> numberBeds;
	@FXML private Button saveInfo;
	@FXML private TextField description;
	@FXML private TextField city;
	@FXML private TextField address;
	@FXML private CheckBox bathroom;
	@FXML private CheckBox kitchen;
	@FXML private CheckBox wifi;
	@FXML private CheckBox garden;
	@FXML private ChoiceBox<String> squareMetres;
	@FXML private ChoiceBox<String> type;
	@FXML private ImageView imageView;
	@FXML private Button openButton;
	@FXML private ImageView home;
	@FXML private ImageView chat;
	@FXML private ImageView bookTravel;
	@FXML private ImageView favourite;
	@FXML private ImageView settings;
	
	private LoggedUser logUser;
	private ManageAnnouncementController control;
	private RentAccomodationBean bean;
	private File houseImage;
	private ImageViewer viewer;
	
	@FXML
	private void initialize() {
		type.setValue("appartamento");
		type.setItems(typeList);
		squareMetres.setValue("< 20");
		squareMetres.setItems(squareList);
		numberBeds.setValue("1");	
		numberBeds.setItems(beds);
		garden.setSelected(false);
		wifi.setSelected(false);
		bathroom.setSelected(false);
		kitchen.setSelected(false);
		this.logUser = new LoggedUser();
	}
	
	public GraphicControllerCreateAccomodation() {
		control = new ManageAnnouncementController();
		bean = new RentAccomodationBean();
		viewer = new ImageViewer();
	}

	public void applyInfo(MouseEvent event) {
		byte[] listOfServices = new byte[4];
		if (garden.isSelected()) {
			listOfServices[0] = 1;
		}
		else { 
			listOfServices[0] = 0;
		}
		if (wifi.isSelected()) {
			listOfServices[1] = 1;
		}
		else { 
			listOfServices[1] = 0;
		}
		if (bathroom.isSelected()) {
			listOfServices[2] = 1;
		}
		else { 
			listOfServices[2] = 0;
		}
		if (kitchen.isSelected()) {
			listOfServices[3] = 1;
		}
		else { 
			listOfServices[3] = 0;
		}
		String numBeds = numberBeds.getValue();
		bean.setBeds(numBeds);
		bean.setServices(listOfServices);
		bean.setCity(city.getText());
		bean.setAddress(address.getText());
		bean.setDescription(description.getText());
		if (houseImage == null) {
        	ErrorPopup error = new ErrorPopup();
        	error.displayLoginError("immagine non inserita");
        }
		bean.setHouseImage(houseImage);
		bean.setSquareMetres(squareMetres.getValue());
		bean.setType(type.getValue());
		bean.setRenter(logUser.getUserName());
		control.createAccomodation(bean);
		goHome(event);
	}
    
    public void insertImage() {
    	FileChooser fileChooser = new FileChooser();
    	//Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        houseImage = fileChooser.showOpenDialog(null);

        if (houseImage != null) {
            Image imageHouse = new Image(houseImage.toURI().toString());
            imageView.setImage(imageHouse);
        }
    }
    
    public void setInfo(RentAccomodationBean beanToUpdate) {
		bean = beanToUpdate;

    	description.setVisible(true);
		description.setText(bean.getDescription());
		city.setVisible(true);
		city.setText(bean.getCity());
		address.setVisible(true);
		address.setText(bean.getAddress());
		numberBeds.setVisible(true);
		numberBeds.setValue(bean.getBeds());
		type.setVisible(true);
		type.setValue(bean.getType());
		squareMetres.setVisible(true);
		squareMetres.setValue(bean.getSquareMetres());
		imageView.setVisible(true);
		BufferedImage bufImage = viewer.loadImage(bean.getHouseImage());
		imageView.setFitHeight(180);
		imageView.setFitWidth(350);
		imageView.setImage(viewer.convertToFxImage(bufImage));
		setServices(bean);
    }
    
    public void setServices(RentAccomodationBean bean) {
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
	}
}
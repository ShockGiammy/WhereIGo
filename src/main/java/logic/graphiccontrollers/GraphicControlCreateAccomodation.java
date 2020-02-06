package logic.graphiccontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import logic.beans.RentAccomodationBean;
import logic.controllers.RentAccomodationController;
import logic.view.Window;

public class GraphicControlCreateAccomodation extends Window{
	
	private RentAccomodationController control;
	
	ObservableList<String> typeList = FXCollections.observableArrayList("appartamento", "villetta", "monolocale");
	ObservableList<String> squareList = FXCollections.observableArrayList("<20", "20 to 39", "40 to 59", ">60");
	ObservableList<String> beds = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", ">8");
	
	@FXML private ChoiceBox<String> numberBeds;
	@FXML private Button saveInfo;
	@FXML private TextField description;
	@FXML private TextField city;
	@FXML private TextField address;
	//@FXML private ChoiceBox services;
	@FXML private ChoiceBox<String> squareMetres;
	@FXML private ChoiceBox<String> type;
	
	@FXML
	private void initialize() {
		type.setValue("appartamento");
		type.setItems(typeList);
		squareMetres.setValue("<20");
		squareMetres.setItems(squareList);
		numberBeds.setValue("1");	
		numberBeds.setItems(beds);
	}
	

	
	private RentAccomodationBean bean;
	
	public static void main(String[] args) {
		setScene("InfoAccomodation.fxml");
		launch(args);
	}
	
	public GraphicControlCreateAccomodation() {
		control = new RentAccomodationController();
		bean = new RentAccomodationBean();
	}
	
	public void selectType() {
		
	}
	public void applyInfo() {
		bean.setCity(city.getText());
		bean.setAddress(address.getText());
		bean.setDescription(description.getText());
		control.createAccomodation(bean);
	}
}

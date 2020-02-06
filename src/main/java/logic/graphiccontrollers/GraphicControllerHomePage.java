package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.view.Window;

public class GraphicControllerHomePage extends Window{
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private TextField dateOfBirth;
	@FXML private TextField gender;
	@FXML private Button bookATravel;
	
	public void bookTravelControl(MouseEvent event) {
		setScene("BookTravel.fxml");
		nextGuiOnClick(event);
	}
	
	public void setName() {
		this.name.setText("Pierciro");
	}
	
	public void setSurname() {
		this.surname.setText("Caliandro");
	}
	
	public void setDate() {
		this.dateOfBirth.setText("18/02/1998");
	}
	
	public void setGender() {
		this.bookATravel.setText("Male");
	}
}


package logic.graphiccontrollers;

import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.LoggedUser;
import logic.beans.UserDataBean;
import logic.controllers.LoginController;
import logic.view.Window;

public class GraphicControllerRegistration extends Window{
	
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private DatePicker dateOfBirth;
	@FXML private ChoiceBox<String> gender;
	@FXML private TextField userName;
	@FXML private TextField password;
	@FXML private ChoiceBox<String> typeOfUser;
	@FXML private Button registerNow;
	
	ObservableList<String> gendList = FXCollections.observableArrayList("Female", "Male", "Other");
	ObservableList<String> typeUsrList = FXCollections.observableArrayList("Traveler", "Renter");
	private UserDataBean dataBean;
	private DateTimeFormatter formatter;
	private LoginController loginCtrl;
	private int ret;
	
	@FXML
	public void initialize(){
		this.dataBean = new UserDataBean();
		this.formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		this.loginCtrl = new LoginController();
		this.gender.setItems(gendList);
		this.typeOfUser.setItems(typeUsrList);
		this.gender.setValue("Female");
		this.typeOfUser.setValue("Traveler");
	}
	
	public void getName() {
		String nameOfUsr = this.name.getText();
		this.dataBean.setName(nameOfUsr);
	}
	
	public void getSurname() {
		String snameOfUsr = this.surname.getText();
		this.dataBean.setSurname(snameOfUsr);
	}
	
	public void getDateOfBirth() {
		String birth = this.dateOfBirth.getValue().format(formatter);
		this.dataBean.setDateOfBirth(birth);
	}
	
	public void getUserName() {
		String user = this.userName.getText();
		this.dataBean.setUserName(user);
	}
	
	public void getPassword() {
		String paswd = this.password.getText();
		this.dataBean.setPsw(paswd);
	}
	
	public void registerNowControl(MouseEvent event) {
		this.dataBean.setType(this.typeOfUser.getValue());
		this.dataBean.setGender(this.gender.getValue());
		this.loginCtrl.insertNewUserControl(this.dataBean);
		setScene("HomePage.fxml");
		loadScene();
		setUserNick(event, this.dataBean);
	}
}


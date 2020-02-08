package logic.graphiccontrollers;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.beans.UserDataBean;
import logic.view.Window;

public class GraphicControllerRegistration extends Window{
	
	@FXML private TextField name;
	@FXML private TextField surname;
	@FXML private DatePicker dateOfBirth;
	@FXML private TextField gender;
	@FXML private TextField userName;
	@FXML private TextField password;
	@FXML private Button registerNow;
	
	private UserDataBean dataBean;
	private DateTimeFormatter formatter;
	
	public GraphicControllerRegistration() {
		this.dataBean = new UserDataBean();
		this.formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	}
	
	public void getName() {
		String name = this.name.getText();
		this.dataBean.setName(name);
	}
	
	public void getSurname() {
		String surname = this.surname.getText();
		this.dataBean.setSurname(surname);
	}
	
	public void getDateOfBirth() {
		String dateOfBirth = this.dateOfBirth.getValue().format(formatter);
		this.dataBean.setDateOfBirth(dateOfBirth);
	}
	
	public void getUserName() {
		String user = this.userName.getText();
		this.dataBean.setUserName(user);
	}
	
	public void getGender() {
		String gender = this.gender.getText();
		this.dataBean.setGender(gender);
	}
	
	public void getPassword() {
		String paswd = this.password.getText();
		this.dataBean.setPsw(paswd);
	}
	
	public void registerNowControl(MouseEvent event) throws IOException {
		setScene("HomePage.fxml");
		guiWithValue(this.dataBean,event);
	}
}

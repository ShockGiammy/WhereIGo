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
	
	public void getGender() {
		String gen = this.gender.getText();
		this.dataBean.setGender(gen);
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

package logic.graphiccontrollers;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.LoginController;
import logic.view.Window;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import logic.view.ErrorPopup;


public class GraphicControllerLogIn extends Window{
	@FXML private PasswordField psw;
	@FXML private TextField usrname;
	@FXML private Button loginButton;
	@FXML private Button registerButton;
	private LogInBean logBean;
	private UserDataBean usrBean;
	private LoginController loginCtrl;
	private ErrorPopup errLogin;
	
	public GraphicControllerLogIn() {
		this.logBean = new LogInBean();
		this.loginCtrl = new LoginController();
		this.usrBean = new UserDataBean();
		this.errLogin = new ErrorPopup();
	}
	
	public void logInControl(MouseEvent event) {
		if(this.loginCtrl.checkLogInControl(this.usrBean, this.logBean) == 1) {
			List<UserTravelBean> travList = new ArrayList<>();
			UserDataBean dataBean = new UserDataBean();
			dataBean.setUserName(this.usrBean.getUsername());
			setScene("HomePage.fxml");
			loadScene();
			setTicketBought(travList, dataBean, event);
		}
		else {
			this.errLogin.displayLoginError("User not registered or wrong credentials");
		}
	}
	
	public void registrationControl(MouseEvent event) {
		setScene("Registration.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	public void getUserNameControl() {
		String usName = this.usrname.getText();
		this.logBean.setUserName(usName);
	}
	
	public void getPasswordControl() {
		String password = this.psw.getText();
		this.logBean.setPassword(password);
	}
	
	public static void main(String[] args) {
		setScene("Login.fxml");
		loadScene();
		launch(args);
	}
}

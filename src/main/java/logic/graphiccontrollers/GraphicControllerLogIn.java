package logic.graphiccontrollers;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.controllers.LoginController;
import logic.view.Window;
import java.io.IOException;
import javafx.fxml.FXML;
import logic.view.ErrorLogin;


public class GraphicControllerLogIn extends Window{
	@FXML private PasswordField psw;
	@FXML private TextField usrname;
	@FXML private Button loginButton;
	@FXML private Button registerButton;
	private LogInBean logBean;
	private UserDataBean usrBean;
	private LoginController loginCtrl;
	private ErrorLogin errLogin;
	
	public GraphicControllerLogIn() {
		this.logBean = new LogInBean();
		this.loginCtrl = new LoginController();
		this.usrBean = new UserDataBean();
		this.errLogin = new ErrorLogin();
	}
	
	public void logInControl(MouseEvent event) throws IOException {
		if(this.loginCtrl.checkLogInControl(this.usrBean, this.logBean) == 1) {
			usrBean.setUserName(logBean.getUserName()); //this should be done by controller
			setScene("HomePage.fxml");
			loadScene();
			setUserNick(event, usrBean);
		}
		else {
			/* this should be in a method called by the controller*/
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

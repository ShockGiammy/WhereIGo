package logic.graphiccontrollers;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import logic.beans.LogInBean;
import logic.beans.UserDataBean;
import logic.controllers.LoginController;
import javafx.fxml.FXML;
import logic.view.ErrorPopup;
import logic.view.BasicGui;


public class GraphicControllerLogIn {
	@FXML private PasswordField psw;
	@FXML private TextField usrname;
	@FXML private Button loginButton;
	@FXML private Button registerButton;
	private LogInBean logBean;
	private UserDataBean usrBean;
	private LoginController loginCtrl;
	private ErrorPopup errLogin;
	private BasicGui bgui;
	
	@FXML
	public void initialize() {
		this.logBean = new LogInBean();
		this.loginCtrl = new LoginController();
		this.usrBean = new UserDataBean();
		this.errLogin = new ErrorPopup();
		bgui = new BasicGui();
	}
	
	public void logInControl(MouseEvent event) {
		if(this.loginCtrl.checkLogInControl(this.usrBean, this.logBean) == 1) {
			bgui.goHome(event);
		}
		else {
			this.errLogin.displayLoginError("User not registered or wrong credentials");
		}
	}
	
	public void registrationControl(MouseEvent event) {
		bgui.changeGUI(event, "Registration.fxml");
	}
	
    public void sendMethod(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
        	MouseEvent clickEvent = new MouseEvent(loginButton, loginButton, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);
        	logInControl(clickEvent);
        }
    }
	
	public void getUserNameControl() {
		String usName = this.usrname.getText();
		this.logBean.setUserName(usName);
	}
	
	public void getPasswordControl() {
		String password = this.psw.getText();
		this.logBean.setPassword(password);
	}
}

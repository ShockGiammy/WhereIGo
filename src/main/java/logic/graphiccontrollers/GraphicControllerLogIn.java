package logic.graphiccontrollers;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import logic.beans.LogInBean;
import logic.view.Window;
import javafx.fxml.FXML;

public class GraphicControllerLogIn extends Window{
	@FXML private PasswordField psw;
	@FXML private TextField usrname;
	@FXML private Button loginButton;
	@FXML private Button registerButton;
	private LogInBean logBean;
	
	public GraphicControllerLogIn() {
		this.logBean = new LogInBean();
	}
	
	public void logInControl(MouseEvent event) {
		setScene("HomePage.fxml");
		nextGuiOnClick(event);
	}
	
	public void registrationControl(MouseEvent event) {
		setScene("Registration.fxml");
		nextGuiOnClick(event);
	}
	
	public void getUserNameControl() {
		String usrname = this.usrname.getText();
		this.logBean.setUserName(usrname);
	}
	
	public void getPasswordControl() {
		String password = this.psw.getText();
		this.logBean.setPassword(password);
	}
	
	public static void main(String[] args) {
		setScene("Login.fxml");
		launch(args);
	}
}

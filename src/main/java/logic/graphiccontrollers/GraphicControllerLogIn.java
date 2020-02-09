package logic.graphiccontrollers;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import logic.LogInDao;
import logic.beans.LogInBean;
import logic.view.Window;
import javafx.fxml.FXML;

public class GraphicControllerLogIn extends Window{
	@FXML private PasswordField psw;
	@FXML private TextField usrname;
	@FXML private Button loginButton;
	@FXML private Button registerButton;
	private LogInBean logBean;
	private LogInDao logDao;
	
	public GraphicControllerLogIn() {
		this.logBean = new LogInBean();
		this.logDao = new LogInDao();
	}
	
	public void logInControl(MouseEvent event) {
		if(logDao.checkLogInInfo(this.logBean) == 1) {
			setScene("HomePage.fxml");
			nextGuiOnClick(event);
		}
		else {
			System.out.println("Utente non registrato\n");
		}
	}
	
	public void registrationControl(MouseEvent event) {
		setScene("Registration.fxml");
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
		launch(args);
	}
}

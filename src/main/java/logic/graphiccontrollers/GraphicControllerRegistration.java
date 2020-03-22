package logic.graphiccontrollers;
import java.io.File;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import logic.beans.UserDataBean;
import logic.controllers.LoginController;
import logic.exceptions.TakenUsernameException;
import logic.view.ErrorPopup;
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
	@FXML private ImageView profile;
	@FXML private Button addPhoto;
	
	ObservableList<String> gendList = FXCollections.observableArrayList("Female", "Male", "Other");
	ObservableList<String> typeUsrList = FXCollections.observableArrayList("Traveler", "Renter");
	private UserDataBean dataBean;
	private DateTimeFormatter formatter;
	private LoginController loginCtrl;
	private ErrorPopup errLogin;
	private String today;
	private File profileImage;
	
	@FXML
	public void initialize(){
		this.dataBean = new UserDataBean();
		this.formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		this.loginCtrl = new LoginController();
		this.gender.setItems(gendList);
		this.typeOfUser.setItems(typeUsrList);
		this.gender.setValue("Female");
		this.typeOfUser.setValue("Traveler");
		this.errLogin = new ErrorPopup();
		today = formatter.format(java.time.LocalDate.now()); /* needed to manage the dates, change the type to Date and not to String*/
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
	
	public void getImage() {
		this.dataBean.setUsrImage(profileImage);
	}
	
	public void registerNowControl(MouseEvent event) {
		try{
			int ret;
			getImage();
			this.dataBean.setType(this.typeOfUser.getValue());
			this.dataBean.setGender(this.gender.getValue());
			ret = this.loginCtrl.insertNewUserControl(this.dataBean);
			if(ret == 0) {
				this.errLogin.displayLoginError("Inserire tutti i dati");
			}
			else {
				setScene("HomePage.fxml");
				loadScene();
				nextGuiOnClick(event);
			}
		}catch(TakenUsernameException e) {
			this.errLogin.displayLoginError("Questo username non è disponibile");
		}
	}
	
    public void insertImage() {
    	FileChooser fileChooser = new FileChooser();
    	//Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        profileImage = fileChooser.showOpenDialog(null);

        if (profileImage != null) {
            Image usrImage = new Image(profileImage.toURI().toString());
            profile.setImage(usrImage);
        }
    }
}


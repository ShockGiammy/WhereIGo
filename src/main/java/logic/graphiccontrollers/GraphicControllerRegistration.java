package logic.graphiccontrollers;

import java.io.File;
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
import logic.controllers.ControllerFacade;
import logic.exceptions.DuplicateUsernameException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;
import logic.view.ErrorPopup;
import logic.view.BasicGui;

public class GraphicControllerRegistration {
	
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
	@FXML private Button back;
	ObservableList<String> gendList = FXCollections.observableArrayList("Female", "Male", "Other");
	ObservableList<String> typeUsrList = FXCollections.observableArrayList("Traveler", "Renter");
	private UserDataBean dataBean;
	private ErrorPopup errLogin;
	private File profileImage;
	private BasicGui bgui;
	private ControllerFacade facCtrl;
	
	@FXML
	public void initialize(){
		this.dataBean = new UserDataBean();
		this.gender.setItems(gendList);
		this.typeOfUser.setItems(typeUsrList);
		this.gender.setValue("Female");
		this.typeOfUser.setValue("Traveler");
		this.errLogin = new ErrorPopup();
		this.bgui = new BasicGui();
		this.facCtrl = new ControllerFacade();
	}
	
	public void registerNowControl(MouseEvent event) {
		this.dataBean = new UserDataBean();
	    try {
	    	this.dataBean.setName(this.name.getText());
	    	this.dataBean.setSurname(this.surname.getText());
	    	this.dataBean.setDateOfBirth(this.dateOfBirth.getValue());
	    	this.dataBean.setUserName(this.userName.getText());
	    	this.dataBean.setPsw(this.password.getText());
	    	this.dataBean.setUsrImage(this.profileImage);
	    	register(event);
	    }catch(LengthFieldException e ) {
	    	this.errLogin.displayLoginError(e.getMsg());
	    }catch (NullValueException e) {
	    	this.errLogin.displayLoginError(e.getNullExcMsg());
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
    
    private void register(MouseEvent event) {
    	try {
    		this.dataBean.setType(this.typeOfUser.getValue());
    		this.dataBean.setGender(this.gender.getValue());
    		this.facCtrl.insertNewUser(this.dataBean);
    		this.errLogin.displayLoginError("Correcty Registered");
    		bgui.setUserImage();
    		bgui.goHome(event);
    	}
    	catch(DuplicateUsernameException e) {
			this.errLogin.displayLoginError("Username not available");
		}
    }
    
    public void backLogIn(MouseEvent event) {
    	bgui.changeGUI(event, "Login.fxml");
    }
}


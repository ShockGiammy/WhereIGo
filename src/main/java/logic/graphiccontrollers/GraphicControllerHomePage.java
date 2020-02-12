package logic.graphiccontrollers;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.view.Window;
import logic.beans.UserDataBean;
import logic.model.UserModel;

public class GraphicControllerHomePage extends Window{
	@FXML private Text name;
	@FXML private Text surname;
	@FXML private Text dateOfBirth;
	@FXML private Text gender;
	@FXML private Text personality;
	@FXML private ImageView bookATravel;
	@FXML private Button changeInfo;
	@FXML private Button moreInfo;
	@FXML private ImageView rentAnnPost;
	
	public void bookTravelControl(MouseEvent event) throws SQLException {
		setScene("BookTravel.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	public void getInterestControl(MouseEvent event) {
		setScene("InterestsForm.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	public void postRentAnnouncementControl(MouseEvent event) {
		setScene("InfoAccomodation.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	/*set the datas of the user before the UI is loaded*/
	public void setDatas(){
		UserDataBean usrBean = new UserDataBean();
		UserModel usrModel = UserModel.getUserModelIstance();
		usrBean = usrModel.getUserDatas();
		this.name.setText(usrBean.getName());
		this.surname.setText(usrBean.getSurname());
		this.dateOfBirth.setText(usrBean.getDateOfBirth());
		this.gender.setText(usrBean.getGender());
		if(usrBean.getPersonality() != null) {
			this.personality.setText(usrBean.getPersonality());
		}
	}
}


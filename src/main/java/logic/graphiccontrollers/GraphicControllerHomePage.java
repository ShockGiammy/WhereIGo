package logic.graphiccontrollers;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.view.Window;
import logic.beans.UserDataBean;

public class GraphicControllerHomePage extends Window{
	@FXML private Text name;
	@FXML private Text surname;
	@FXML private Text dateOfBirth;
	@FXML private Text gender;
	@FXML private Button bookATravel;
	@FXML private Button changeInfo;
	@FXML private Button moreInfo;
	
	public void bookTravelControl(MouseEvent event) throws SQLException {
		setScene("BookTravel.fxml");
		loadScene();
		setSuggestedLocations(event);
	}
	
	public void getInterestControl(MouseEvent event) {
		setScene("InterestsForm.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	/*set the datas of the user before the UI is loaded*/
	public void setDatas(UserDataBean usrBean){
		this.name.setText(usrBean.getName());
		this.surname.setText(usrBean.getSurname());
		this.gender.setText(usrBean.getGender());
		this.dateOfBirth.setText(usrBean.getDateOfBirth());
	}
}


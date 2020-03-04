package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.view.Window;
import logic.LoggedUser;
import logic.beans.UserDataBean;

public class GraphicControllerHomePage extends Window{
	@FXML private Text username;
	@FXML private ImageView bookATravel;
	@FXML private Button changeInfo;
	@FXML private Button moreInfo;
	@FXML private ImageView rentAnnPost;
	@FXML private Button takeTest;
	
	public void bookTravelControl(MouseEvent event) {
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
		LoggedUser logUser = new LoggedUser();
		if (logUser.getUserType().equals("Renter")) {
			setScene("InfoAccomodation.fxml");
			loadScene();
			nextGuiOnClick(event);
		}
		else {
			setScene("RentAccomodation.fxml");
			loadScene();
			nextGuiOnClick(event);
		}
	}
	
	/*set the datas of the user before the UI is loaded*/
	public void setNick(UserDataBean dataBean){
		this.username.setText(dataBean.getUsername());
	}
	
	public void logOut(MouseEvent e){
		setScene("Login.fxml");
		loadScene();
		nextGuiOnClick(e);
	}
}


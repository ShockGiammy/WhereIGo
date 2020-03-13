package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.view.Window;
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;

public class GraphicControllerHomePage extends Window{
	@FXML private Text username;
	@FXML private ImageView bookATravel;
	@FXML private Button changeInfo;
	@FXML private Button moreInfo;
	@FXML private ImageView rentAnnPost;
	@FXML private Button takeTest;
	@FXML private Button exitButton;
	@FXML private List<VBox> tavelBox;
	@FXML private List<VBox> groupBox;
	@FXML private ListView<VBox> lwTickets;
	@FXML private ListView<VBox> lwGroups;
	
	public void initialize() {
		this.tavelBox = new ArrayList<>();
		this.groupBox = new ArrayList<>();
	}
	
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
	
	public void setTravel(List<UserTravelBean> travBean) {
		int i;
		for(i = 0; i < travBean.size(); i++) {
			VBox vbox = new VBox(7);
			Text depCity = new Text(travBean.get(i).getCityOfDep());
			Text arrCity = new Text(travBean.get(i).getCityOfArr());
			Text depDay = new Text(travBean.get(i).getFirstDay().toString());
			Text retDay = new Text(travBean.get(i).getLastDay().toString());
			vbox.getChildren().addAll(depCity, depDay, arrCity, retDay);
			this.lwTickets.getItems().add(vbox);
			this.tavelBox.add(vbox);
		}
	}
	
	public void setGroups(GroupBean grpBean) {
		VBox vbox = new VBox(7);
		Text groupTitle = new Text(grpBean.getGroupTitle());
		Text groupDest = new Text(grpBean.getGroupDestination());
		Text groupLeader = new Text(grpBean.getGroupOwner());
		vbox.getChildren().addAll(groupTitle, groupDest, groupLeader);
		this.lwGroups.getItems().add(vbox);
		this.groupBox.add(vbox);
	}
	
	public void logOut(MouseEvent e) {
		setScene("Login.fxml");
		loadScene();
		nextGuiOnClick(e);
	}
}


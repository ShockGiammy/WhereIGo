package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.view.BasicGui;
import logic.LoggedUser;
import logic.UserType;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;

public class GraphicControllerHomePage extends BasicGui{
	@FXML private Button changeInfo;
	@FXML private Button moreInfo;
	@FXML private ListView<VBox> lwTickets;
	@FXML private ListView<VBox> lwGroups;
	@FXML private ListView<HBox> lwSuggUsers;
	private List<UserTravelBean> travBean;
	private List<GroupBean> grpBean;
	private List<UserDataBean> dataBeanList;
	
	@FXML
	public void initialize() {
		this.userImage.setImage(setUserImage());
		this.travBean = new ArrayList<>();
		this.grpBean = new ArrayList<>();
		this.dataBeanList = new ArrayList<>();
		this.facade.getBookedTicks(this.travBean);
		this.facade.getSimilarUsers(this.dataBeanList);
		this.facade.getUsersGroups(this.grpBean);
		setAllTravels();
		setAllGroups();
		setAllSuggUsers();
	}
	
	public void postRentAnnouncementControl(MouseEvent event) {
		if (LoggedUser.getUserType() == UserType.RENTER) {
			setScene("RenterAccomodations.fxml");
			loadScene();
			nextGuiOnClick(event);
		}
		else {
			setScene("RentAccomodation.fxml");
			loadScene();
			nextGuiOnClick(event);
		}
	}
	
	public void setAllTravels(){
		for(int i = 0; i < this.travBean.size(); i++) {
			setTravel(this.travBean.get(i));
		}
	}
	
	public void setAllGroups() {
		for(int i = 0; i < this.grpBean.size(); i++) {
			setGroup(this.grpBean.get(i));
		}
	}
	
	public void setAllSuggUsers() {
		for(int i = 0; i < this.dataBeanList.size(); i++) {
			setSuggUser(this.dataBeanList.get(i));
		}
	}
	
	public void setTravel(UserTravelBean travBean) {
		VBox vbox = new VBox(7);
		Text ident = new Text("Travel id : "+travBean.getId());
		Text depCity = new Text("Departure city : "+travBean.getCityOfDep());
		Text arrCity = new Text("Arrive city : "+travBean.getCityOfArr());
		Text depDay = new Text("Departure day : "+travBean.getFirstDay());
		Text retDay = new Text("Return day : "+travBean.getLastDay());
		Text money = new Text("Cost : "+travBean.getCost());
		Button delete = new Button("Cancel travel");
		delete.setOnMouseClicked(e->{
			this.facade.deleteSavedTravel(travBean);
			this.lwTickets.getItems().remove(vbox);
			this.popErr.displayErrorPopup("Travel correctly deleted");
		});
		vbox.getChildren().addAll(ident, depCity, depDay, arrCity, retDay, money, delete);
		this.lwTickets.getItems().add(vbox);
	}
	
	public void setGroup(GroupBean grpBean) {
		VBox vbox = new VBox(7);
		Text groupTitle = new Text("Group name : "+grpBean.getGroupTitle());
		Text groupDest = new Text("Group destination : "+grpBean.getGroupDestination());
		Text groupLeader = new Text("Group leader : "+grpBean.getGroupOwner());
		Button deleteGroup = new Button("Delete/Leave group");
		deleteGroup.setOnMouseClicked(e->{
			this.facade.deleteTravelGroup(grpBean);
			this.lwGroups.getItems().remove(vbox);
			this.popErr.displayErrorPopup("Group correctly deleted");
		});
		vbox.getChildren().addAll(groupTitle, groupDest, groupLeader,deleteGroup);
		this.lwGroups.getItems().add(vbox);
		Button newGroup = new Button("Create a new group");
		newGroup.setOnMouseClicked(e->
			changeGUI(e, "CreateGroup.fxml")
		);
		VBox box = new VBox();
		box.getChildren().add(newGroup);
		this.lwGroups.getItems().add(box);
	}
	
	public void setSuggUser(UserDataBean dataBean) {
		HBox hbox = new HBox(3);
		ImageView ivProf = new ImageView();
		ivProf.setImage(facade.loadImage(dataBean.getByteStream()));
		ivProf.setFitHeight(50);
		ivProf.setFitWidth(50);
		hbox.getChildren().add(ivProf);
		Text usr = new Text(dataBean.getUsername());
		usr.setStyle("-fx-font: 24 arial;");
		hbox.getChildren().add(usr);
		Button btnChat = new Button("Chat with the user");
		btnChat.setOnMouseClicked(e->{
			this.facade.createChat(dataBean.getUsername());
			goChat(e);
		});
		hbox.getChildren().add(btnChat);
		this.lwSuggUsers.getItems().add(hbox);
	}
}


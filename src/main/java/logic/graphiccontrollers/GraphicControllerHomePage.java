package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.view.ErrorPopup;
import logic.view.BasicGui;
import logic.LoggedUser;
import logic.UserType;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;

public class GraphicControllerHomePage extends BasicGui{
	@FXML private Button changeInfo;
	@FXML private Button moreInfo;
	@FXML private Button takeTest;
	@FXML private ListView<VBox> lwTickets;
	@FXML private ListView<VBox> lwGroups;
	@FXML private ListView<HBox> lwSuggUsers;
	private ErrorPopup err;
	private List<UserTravelBean> travBean;
	private List<GroupBean> grpBean;
	private List<UserDataBean> dataBeanList;
	private UserDataBean dBean;
	
	@FXML
	public void initialize() {
		this.err = new ErrorPopup();
		this.userImage.setImage(setUserImage());
		this.travBean = new ArrayList<>();
		this.grpBean = new ArrayList<>();
		this.dataBeanList = new ArrayList<>();
		this.dBean = new UserDataBean(this.logUsr.getUserName());
		this.dBean.setPersonality(this.logUsr.getPersonality());
		this.facade.getBookedTicks(this.travBean, dBean);
		this.facade.getSimilarUsers(this.dataBeanList, dBean);
		this.facade.getUsersGroups(this.grpBean, dBean);
		setTravel();
		setGroups();
		setSuggUsers();
	}
	
	public void getInterestControl(MouseEvent event) {
		setScene("InterestsForm.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	public void postRentAnnouncementControl(MouseEvent event) {
		LoggedUser logUser = new LoggedUser();
		if (logUser.getUserType() == UserType.RENTER) {
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
	
	public void setTravel() {
		int i;
		for(i = 0; i < travBean.size(); i++) {
			VBox vbox = new VBox(7);
			Text ident = new Text("Travel id : "+travBean.get(i).getId());
			Text depCity = new Text("Departure city : "+travBean.get(i).getCityOfDep());
			Text arrCity = new Text("Arrive city : "+travBean.get(i).getCityOfArr());
			Text depDay = new Text("Departure day : "+travBean.get(i).getFirstDay());
			Text retDay = new Text("Return day : "+travBean.get(i).getLastDay());
			Text money = new Text("Cost : "+travBean.get(i).getCost());
			Button delete = new Button("Cancel travel");
			delete.setOnMouseClicked(this::deleteTravel);
			vbox.getChildren().addAll(ident, depCity, depDay, arrCity, retDay, money, delete);
			this.lwTickets.getItems().add(vbox);
		}
	}
	
	public void setGroups() {
		int i;
		for(i = 0; i < grpBean.size(); i++) {
			VBox vbox = new VBox(7);
			Text groupTitle = new Text("Group name : "+grpBean.get(i).getGroupTitle());
			Text groupDest = new Text("Group destination : "+grpBean.get(i).getGroupDestination());
			Text groupLeader = new Text("Group leader : "+grpBean.get(i).getGroupOwner());
			if(grpBean.get(i).getGroupOwner().equals(logUsr.getUserName())) {
				Button deleteGroup = new Button("Delete group");
				deleteGroup.setOnMouseClicked(this::deleteGroup);
				vbox.getChildren().addAll(groupTitle, groupDest, groupLeader,deleteGroup);
			}
			else {
				Button leaveGroup = new Button("Leave group");
				leaveGroup.setOnMouseClicked(this::leaveGroup);
				vbox.getChildren().addAll(groupTitle, groupDest, groupLeader,leaveGroup);
			}
			this.lwGroups.getItems().add(vbox);
		}
		Button newGroup = new Button("Create a new group");
		newGroup.setOnMouseClicked(this::newGroup);
		VBox box = new VBox();
		box.getChildren().add(newGroup);
		this.lwGroups.getItems().add(box);
	}
	
	public void setSuggUsers() {
		for(int i = 0; i < dataBeanList.size(); i++) {
			HBox hbox = new HBox(3);
			if(dataBeanList.get(i).getByteStream() != null) {
				ImageView ivProf = new ImageView();
				ivProf.setImage(facade.loadImage(dataBeanList.get(i).getByteStream()));
				ivProf.setFitHeight(50);
				ivProf.setFitWidth(50);
				hbox.getChildren().add(ivProf);
			}
			Text usr = new Text(dataBeanList.get(i).getUsername());
			usr.setStyle("-fx-font: 24 arial;");
			hbox.getChildren().add(usr);
			Button btnChat = new Button("Chat with the user");
			btnChat.setOnMouseClicked(this::openTheChat);
			hbox.getChildren().add(btnChat);
			this.lwSuggUsers.getItems().add(hbox);
		}
	}
	
	public void deleteTravel(MouseEvent e) {
		int i;
		ObservableList<VBox> travels = FXCollections.observableArrayList(this.lwTickets.getItems());
		for(i = 0; i < travels.size(); i++) {
			if(travels.get(i).getChildren().get(6).equals(e.getTarget())) {
				UserTravelBean delBean = new UserTravelBean();
				Text id = (Text)travels.get(i).getChildren().get(0);
				delBean.setId(id.getText().substring(12, id.getText().length()));
				this.facade.deleteSavedTravel(delBean, this.dBean);
				VBox temp = travels.get(i);
				this.lwTickets.getItems().remove(temp);
				err.displayLoginError("Prenotazione correttamente cancellata");
			}
		}
	}
	
	public void deleteGroup(MouseEvent e) {
		int i;
		ObservableList<VBox> grps = FXCollections.observableArrayList(this.lwGroups.getItems());
		for(i = 0; i < grps.size(); i++) {
			if(grps.get(i).getChildren().size() == 4 && grps.get(i).getChildren().get(3).equals(e.getTarget())) {
				Text description = (Text)grps.get(i).getChildren().get(0);
				Text owner = (Text)grps.get(i).getChildren().get(2);
				GroupBean bean = new GroupBean(description.getText().substring(13, description.getText().length()));
				bean.setGroupOwner(owner.getText().substring(15,owner.getText().length()));
				this.facade.deleteTravelGroup(bean);
				VBox temp = grps.get(i);
				grps.remove(i);
				this.lwGroups.getItems().remove(temp);
				err.displayLoginError("Gruppo correttamente cancellato");
			}
		}
	}
	
	public void leaveGroup(MouseEvent e) {
		int i;
		ObservableList<VBox> grps = FXCollections.observableArrayList(this.lwGroups.getItems());
		for(i = 0; i < grps.size(); i++) {
			if(grps.get(i).getChildren().size() == 4 && grps.get(i).getChildren().get(3).equals(e.getTarget())) {
				Text description = (Text)grps.get(i).getChildren().get(0);
				GroupBean bean = new GroupBean(description.getText().substring(13, description.getText().length()));
				this.facade.leaveTravelGroup(bean, this.dBean);
				VBox temp = grps.get(i);
				grps.remove(temp);
				this.lwGroups.getItems().remove(temp);
				err.displayLoginError("Gruppo correttamente abbandonato");
			}
		}
	}
	
	public void newGroup(MouseEvent e) {
		changeGUI(e, "CreateGroup.fxml");
	}
	
	public void openTheChat(MouseEvent e) {
		ObservableList<HBox> usrs = FXCollections.observableArrayList(this.lwSuggUsers.getItems());
		for(int i = 0; i < usrs.size(); i++) {
			if(usrs.get(i).getChildren().get(2).equals(e.getTarget())){
				Text usName = (Text)usrs.get(i).getChildren().get(1);
				this.facade.createChat(usName.getText());
			}
		}
		goChat(e);
	}
}


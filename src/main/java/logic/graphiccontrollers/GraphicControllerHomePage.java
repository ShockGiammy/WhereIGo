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
	@FXML private List<VBox> travelBox;
	@FXML private List<VBox> groupBox;
	@FXML private List <VBox> suggUsersList;
	@FXML private ListView<VBox> lwTickets;
	@FXML private ListView<VBox> lwGroups;
	@FXML private ListView<VBox> lwSuggUsers;
	private ErrorPopup err;
	private List<UserTravelBean> travBean;
	private List<GroupBean> grpBean;
	private List<UserDataBean> dataBeanList;
	
	@FXML
	public void initialize() {
		this.travelBox = new ArrayList<>();
		this.groupBox = new ArrayList<>();
		this.suggUsersList = new ArrayList<>();
		this.err = new ErrorPopup();
		this.userImage.setImage(setUserImage());
		this.travBean = new ArrayList<>();
		this.grpBean = new ArrayList<>();
		this.dataBeanList = new ArrayList<>();
		this.facade.getTravHomePageDatas(this.travBean, this.grpBean, this.dataBeanList);
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
			this.travelBox.add(vbox);
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
			this.groupBox.add(vbox);
		}
		Button newGroup = new Button("Create a new group");
		newGroup.setOnMouseClicked(this::newGroup);
		VBox box = new VBox();
		box.getChildren().add(newGroup);
		this.lwGroups.getItems().add(box);
	}
	
	public void setSuggUsers() {
		for(int i = 0; i < dataBeanList.size(); i++) {
			VBox vbox= new VBox(7);
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
			vbox.getChildren().addAll(hbox, btnChat);
			this.lwSuggUsers.getItems().add(vbox);
		}
	}
	
	public void deleteTravel(MouseEvent e) {
		int i;
		for(i = 0; i < this.travelBox.size(); i++) {
			if(this.travelBox.get(i).getChildren().get(6).equals(e.getTarget())) {
				UserTravelBean delBean = new UserTravelBean();
				Text id = (Text)this.travelBox.get(i).getChildren().get(0);
				delBean.setId(Integer.parseInt(id.getText().substring(12, id.getText().length())));
				this.facade.deleteSavedTravel(delBean);
				VBox temp = this.travelBox.get(i);
				this.travelBox.remove(i);
				this.lwTickets.getItems().remove(temp);
				err.displayLoginError("Prenotazione correttamente cancellata");
			}
		}
	}
	
	public void deleteGroup(MouseEvent e) {
		int i;
		for(i = 0; i < this.groupBox.size(); i++) {
			if(this.groupBox.get(i).getChildren().size() == 4 && this.groupBox.get(i).getChildren().get(3).equals(e.getTarget())) {
				GroupBean bean = new GroupBean();
				Text description = (Text)this.groupBox.get(i).getChildren().get(0);
				Text owner = (Text)this.groupBox.get(i).getChildren().get(2);
				bean.setGroupTitle(description.getText().substring(13, description.getText().length()));
				bean.setGroupOwner(owner.getText().substring(15,owner.getText().length()));
				this.facade.deleteTravelGroup(bean);
				VBox temp = this.groupBox.get(i);
				this.groupBox.remove(i);
				this.lwGroups.getItems().remove(temp);
				err.displayLoginError("Gruppo correttamente cancellato");
			}
		}
	}
	
	public void leaveGroup(MouseEvent e) {
		int i;
		for(i = 0; i < this.groupBox.size(); i++) {
			if(this.groupBox.get(i).getChildren().size() == 4 && this.groupBox.get(i).getChildren().get(3).equals(e.getTarget())) {
				GroupBean bean = new GroupBean();
				Text description = (Text)this.groupBox.get(i).getChildren().get(0);
				bean.setGroupTitle(description.getText().substring(13, description.getText().length()));
				this.facade.leaveTravelGroup(bean);
				VBox temp = this.groupBox.get(i);
				this.groupBox.remove(i);
				this.lwGroups.getItems().remove(temp);
				err.displayLoginError("Gruppo correttamente abbandonato");
			}
		}
	}
	
	public void newGroup(MouseEvent e) {
		changeGUI(e, "CreateGroup.fxml");
	}
	
	public void openTheChat(MouseEvent e) {
		for(int i = 0; i < this.suggUsersList.size(); i++) {
			if(this.suggUsersList.get(i).getChildren().get(1).equals(e.getTarget())){
				HBox box = (HBox)this.suggUsersList.get(i).getChildren().get(0);
				Text usName = (Text)box.getChildren().get(1);
				UserDataBean usBean = new UserDataBean();
				usBean.setUserName(usName.getText());
				/* forse prende anche la foto, la passa a te e ci crea una chat*/
			}
		}
		goChat(e); //aspetto te Gian per sapere che cazzo fare
	}
}


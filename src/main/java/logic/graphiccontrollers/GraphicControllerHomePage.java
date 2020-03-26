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
import logic.view.ErrorPopup;
import logic.view.MenuWindow;
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;

public class GraphicControllerHomePage extends MenuWindow{
	@FXML private ImageView bookATravel;
	@FXML private ImageView rentAnnPost;
	@FXML private Button changeInfo;
	@FXML private Button moreInfo;
	@FXML private Button takeTest;
	@FXML private Button exitButton;
	@FXML private List<VBox> travelBox;
	@FXML private List<VBox> groupBox;
	@FXML private ListView<VBox> lwTickets;
	@FXML private ListView<VBox> lwGroups;
	private BookTravelControl bookTrav;
	private ErrorPopup err;
	private LoggedUser logUsr;
	
	public void initialize() {
		this.travelBox = new ArrayList<>();
		this.groupBox = new ArrayList<>();
		this.bookTrav = new BookTravelControl();
		this.err = new ErrorPopup();
		this.logUsr = new LoggedUser();
		this.userImage.setImage(this.logUsr.getImage());
		setTravel();
		setGroups();
	}
	
	public void getInterestControl(MouseEvent event) {
		setScene("InterestsForm.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	
	public void postRentAnnouncementControl(MouseEvent event) {
		LoggedUser logUser = new LoggedUser();
		if (logUser.getUserType().equals("Renter")) {
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
		List<UserTravelBean> travBean = new ArrayList<>();
		this.bookTrav.getBookedTickets(travBean);
		int i;
		for(i = 0; i < travBean.size(); i++) {
			VBox vbox = new VBox(7);
			Text ident = new Text("Travel id : "+travBean.get(i).getId());
			Text depCity = new Text("Departure city : "+travBean.get(i).getCityOfDep());
			Text arrCity = new Text("Arrive city : "+travBean.get(i).getCityOfArr());
			Text depDay = new Text("Departure day : "+travBean.get(i).getFirstDay().toString());
			Text retDay = new Text("Return day : "+travBean.get(i).getLastDay().toString());
			Text money = new Text("Cost : "+travBean.get(i).getCost());
			Button delete = new Button("Cancel travel");
			delete.setOnMouseClicked(this::deleteTravel);
			vbox.getChildren().addAll(ident, depCity, depDay, arrCity, retDay, money, delete);
			this.lwTickets.getItems().add(vbox);
			this.travelBox.add(vbox);
		}
	}
	
	public void setGroups() {
		List<GroupBean> grpBean = new ArrayList<>();
		this.bookTrav.getUserGroups(grpBean);
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
	}
	
	public void deleteTravel(MouseEvent e) {
		int i;
		for(i = 0; i < this.travelBox.size(); i++) {
			if(this.travelBox.get(i).getChildren().get(6).equals(e.getTarget())) {
				UserTravelBean delBean = new UserTravelBean();
				Text id = (Text)this.travelBox.get(i).getChildren().get(0);
				delBean.setId(Integer.parseInt(id.getText().substring(12, id.getText().length())));
				UserDataBean data = new UserDataBean();
				data.setUserName(logUsr.getUserName());
				this.bookTrav.deleteSavedTravel(delBean, data);
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
				this.bookTrav.deleteTravelGroup(bean);
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
				UserDataBean dBean = new UserDataBean();
				dBean.setUserName(this.logUsr.getUserName());
				this.bookTrav.leaveTravelGroup(bean, dBean);
				VBox temp = this.groupBox.get(i);
				this.groupBox.remove(i);
				this.lwGroups.getItems().remove(temp);
				err.displayLoginError("Gruppo correttamente abbandonato");
			}
		}
	}
}


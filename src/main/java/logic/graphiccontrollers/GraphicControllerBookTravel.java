package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.view.ErrorPopup;
import logic.view.BasicGui;
import javafx.scene.control.TextField;

public class GraphicControllerBookTravel extends BasicGui{
	private UserTravelBean travBean;
	private GroupBean grpBean;
	private LocationBean locBean;
	private List<UserTravelBean> travBeanArray;
	private ErrorPopup popUp;
	@FXML private DatePicker firstDay;
	@FXML private DatePicker lastDay;
	@FXML private TextField departureCity;
	@FXML private TextField arrivalCity;
	@FXML private Button bookMyTravel;
	@FXML private ListView<HBox> suggLocView;
	@FXML private ListView<VBox> groupsView;
	@FXML private List<HBox> hboxList;
	@FXML private List<VBox> vboxlist;
	
	@FXML
	public void initialize() {
		this.travBeanArray = new ArrayList<>();
		this.hboxList = new ArrayList<>();
		this.grpBean = new GroupBean();
		this.locBean = new LocationBean();
		this.travBean = new UserTravelBean();
		this.popUp = new ErrorPopup();
		this.vboxlist = new ArrayList<>();
		this.userImage.setImage(this.logUsr.getImage());
		setLocation();
		setGroups();
	}
	
	public void bookMyTravelControl(MouseEvent event) {
		if(this.travBean.getFirstDay() == null || this.travBean.getLastDay() == null || this.travBean.getCityOfDep() == null || this.travBean.getCityOfArr() == null) {
			this.popUp.displayLoginError("Please, insert all datas");
		}
		else {
			int i;
			i = this.facCtrl.retriveTravelSolutions(travBean, travBeanArray);
			if(i == -1) {
			popUp.displayLoginError("No travel available at this moment: either you have already bought the same flight or there are no solutions for these datas");
			}
			else {
				setScene("TicketSolutions.fxml");
				loadScene();
				setTicketsDatas(this.travBeanArray, event);
			}
		}
	}

	public void setGroups() {
		if(this.logUsr.getPersonality() != null) {
			List<GroupBean> grpList = new ArrayList<>();
			int j;
			this.facCtrl.getGroups(grpList);
			for(j = 0; j < grpList.size(); j++) {
				VBox vbox = new VBox(10);
				Text title = new Text(grpList.get(j).getGroupTitle());
				Text owner = new Text(grpList.get(j).getGroupOwner());
				Text location = new Text(grpList.get(j).getGroupDestination());
				Button join = new Button("join group");
				join.setOnMouseClicked(this::joinTheGroup);
				vbox.getChildren().addAll(title, owner, location, join);
				this.vboxlist.add(vbox);
				this.groupsView.getItems().add(vbox);
			}
		}
	}
	
	public void setLocation() {
		if(this.logUsr.getPersonality() == null) {
			this.popUp.displayLoginError("No suggested location/travel groups to be shown. Please,take personality test");
		}
		else {
			List<String> suggLoc = new ArrayList<>();
			suggLoc.addAll(facCtrl.showLocations());
			int i;
			for(i = 0; i < suggLoc.size(); i++) {
				HBox hbox = new HBox(20);
				Text loc = new Text(suggLoc.get(i));
				Button info = new Button("Get more info");
				Button bookNow = new Button("Book a travel");
				bookNow.setOnMouseClicked(this::bookSuggestedLoc);
				info.setOnMouseClicked(this::showMoreInfo);
				hbox.getChildren().addAll(loc,info, bookNow);
				this.suggLocView.getItems().add(hbox);
				this.hboxList.add(hbox);
			}
		}
	}
	
	public void getFirstDay() {
		travBean.setFirstDay(this.firstDay.getValue());
	}
	
	public void getLastDay() {
		travBean.setLastDay(this.lastDay.getValue());
	}
	
	public void getArrivalCity() {
		travBean.setArrCity(this.arrivalCity.getText());
	}
	
	public void getDepartureCity() {
		travBean.setDepCity(this.departureCity.getText());
	}
	
	public void showMoreInfo(MouseEvent e) {
		int i;
		for(i = 0; i < this.hboxList.size(); i++) {
			if(this.hboxList.get(i).getChildren().get(1).equals(e.getTarget())) {
				Text text = (Text)this.hboxList.get(i).getChildren().get(0);
				this.locBean.setCityName(text.getText());
				this.facCtrl.retriveLocInfo(this.locBean);
				loadLocInfo(e);
			}
		}
	}
	
	public void backHome(MouseEvent e) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(logUsr.getUserName());
		setScene("HomePage.fxml");
		loadScene();
		nextGuiOnClick(e);
	}
	
	public void loadLocInfo(MouseEvent e) {
		setScene("LocationInfo.fxml");
		loadScene();
		setLocationInfo(e, this.locBean);
	}
	
	public void bookSuggestedLoc(MouseEvent e) {
		int i;
		for(i = 0; i < this.hboxList.size(); i++) {
			if(this.hboxList.get(i).getChildren().get(2).equals(e.getTarget())) {
				Text city = (Text)this.hboxList.get(i).getChildren().get(0);
				this.travBean.setArrCity(city.getText());
				this.travBeanArray.addAll(this.facCtrl.getSuggTicketsInfo(this.travBean));
				if(this.travBeanArray.isEmpty()) {
					this.popUp.displayLoginError("Nessun viaggio disponibile o viaggio già prenotato");
				}
				else {
					setScene("TicketSolutions.fxml");
					loadScene();
					setTicketsDatas(this.travBeanArray, e);
				}
			}
		}
	}
	
	public void joinTheGroup(MouseEvent e) {
		int i;
		for(i = 0; i < this.vboxlist.size(); i++) {
			if(this.vboxlist.get(i).getChildren().get(3).equals(e.getTarget())) {
				Text title = (Text)this.vboxlist.get(i).getChildren().get(0);
				this.grpBean.setGroupTitle(title.getText());
				if(this.facCtrl.insertParticipant(this.grpBean) == 0) {
					this.popUp.displayLoginError("Gruppo correttamente joinato");
				}
				else {
					this.popUp.displayLoginError("Errore nel join del gruppo");
				}
			}
		}
	}
	
	public void setTicketsDatas(List<UserTravelBean> bean, MouseEvent e) {
		GraphicControllerTickets controller = loader.getController();
		controller.setDatas(bean);
		nextGuiOnClick(e);
	}
	
	public void setLocationInfo(MouseEvent e, LocationBean bean) {
		GraphicControllerLocationInfo controller = loader.getController();
		controller.setInfo(bean);
		nextGuiOnClick(e);
	}
}

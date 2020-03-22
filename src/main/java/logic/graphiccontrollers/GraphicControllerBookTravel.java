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
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;
import logic.view.ErrorPopup;
import logic.view.Window;
import javafx.scene.control.TextField;

public class GraphicControllerBookTravel extends Window{
	private UserTravelBean travBean;
	private BookTravelControl bookTravCtrl;
	private GroupBean grpBean;
	private LoggedUser logUsr;
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
	
	public void initialize() {
		this.travBeanArray = new ArrayList<>();
		this.hboxList = new ArrayList<>();
		this.grpBean = new GroupBean();
		logUsr = new LoggedUser();
		this.locBean = new LocationBean();
		this.travBean = new UserTravelBean();
		this.bookTravCtrl = new BookTravelControl();
		this.popUp = new ErrorPopup();
		this.vboxlist = new ArrayList<>();
		setLocation();
		setGroups();
	}
	
	public void bookMyTravelControl(MouseEvent event) {
		if(this.travBean.getFirstDay() == null || this.travBean.getLastDay() == null || this.travBean.getCityOfDep() == null || this.travBean.getCityOfArr() == null) {
			this.popUp.displayLoginError("Please, insert all datas");
			return;
		}
		int i;
		i = this.bookTravCtrl.retriveTravelSolutions(travBean, travBeanArray);
		if(i == -1) {
			popUp.displayLoginError("No travel available at this moment: either you have already bought the same flight or there are no solutions for these datas");
		}
		else {
			setScene("TicketSolutions.fxml");
			loadScene();
			setTicketsDats(this.travBeanArray, event);
		}
	}

	public void setGroups() {
		List<GroupBean> grpList = new ArrayList<>();
		int j;
		int i;
		this.bookTravCtrl.getGroupsControl(grpList);
		List<GroupBean> usrGroups = new ArrayList<>();
		this.bookTravCtrl.getParticipateGroups(usrGroups);
		for(i = 0; i < grpList.size(); i++) {
			for(j = 0; j < usrGroups.size(); j++) {
				if(grpList.get(i).getGroupOwner().equalsIgnoreCase(usrGroups.get(j).getGroupOwner()) && grpList.get(i).getGroupDestination().equalsIgnoreCase(usrGroups.get(j).getGroupDestination()) && grpList.get(i).getGroupTitle().equalsIgnoreCase(usrGroups.get(j).getGroupTitle())) {
					grpList.remove(i);
				}
			}
		}
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
	
	public void setLocation() {
		List<String> suggLoc = new ArrayList<>();
		suggLoc.addAll(bookTravCtrl.showLocationsControl());
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
				this.bookTravCtrl.retriveLocInfoControl(this.locBean);
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
				this.travBeanArray.addAll(this.bookTravCtrl.getSuggTicketsInfo(this.travBean));
				if(this.travBeanArray.isEmpty()) {
					this.popUp.displayLoginError("Nessun viaggio disponibile o viaggio già prenotato");
				}
				else {
					setScene("TicketSolutions.fxml");
					loadScene();
					setTicketsDats(this.travBeanArray, e);
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
				UserDataBean usrDBean = new UserDataBean();
				usrDBean.setUserName(this.logUsr.getUserName());
				if(this.bookTravCtrl.insertParticipant(this.grpBean, usrDBean) == 0) {
					this.popUp.displayLoginError("Gruppo correttamente joinato");
				}
				else {
					this.popUp.displayLoginError("Errore nel join del gruppo");
				}
			}
		}
	}
}

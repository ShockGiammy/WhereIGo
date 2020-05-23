package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserTravelBean;
import logic.exceptions.BigDateException;
import logic.exceptions.EmptyListException;
import logic.exceptions.NullValueException;
import logic.view.ErrorPopup;
import logic.view.BasicGui;

public class GraphicControllerBookTravel extends BasicGui{
	private UserTravelBean travBean;
	private LocationBean locBean;
	private List<UserTravelBean> travBeanArray;
	private ErrorPopup popUp;
	private List<GroupBean> grpList;
	private List<String> suggLoc;
	private List<UserTravelBean> avalTrav;  //used to load available travels
	@FXML private DatePicker firstDay;
	@FXML private DatePicker lastDay;
	@FXML private ChoiceBox<String> departureCity;
	@FXML private ChoiceBox<String> arrivalCity;
	@FXML private Button bookMyTravel;
	@FXML private ListView<HBox> suggLocView;
	@FXML private ListView<VBox> groupsView;
	
	@FXML
	public void initialize() {
		this.travBeanArray = new ArrayList<>();
		this.locBean = new LocationBean();
		this.popUp = new ErrorPopup();
		this.userImage.setImage(setUserImage());
		this.grpList = new ArrayList<>();
		this.suggLoc = new ArrayList<>();
		avalTrav = new ArrayList<>();  //used to load available travels
		this.facade.loadBookTravSuggestion(suggLoc, grpList, avalTrav); // we load all the suggestion for the user
		setDepAndArr();
		setLocation();
		setGroups();
	}
	
	public void bookMyTravelControl(MouseEvent event) {
		this.travBean = new UserTravelBean();
		try {
			travBean.setFirstDay(this.firstDay.getValue());
			travBean.setLastDay(this.lastDay.getValue());
			travBean.setArrCity(this.arrivalCity.getValue());
			travBean.setDepCity(this.departureCity.getValue());
			checkBookSol(event);
		}catch (NullValueException e) {
			this.popUp.displayLoginError(e.getNullExcMsg());
		}
	}
	
	public void setDepAndArr() {
		List<String> dep = new ArrayList<>();
		List<String> arr = new ArrayList<>();
		for(int i = 0; i < this.avalTrav.size(); i++) {
			arr.add(this.avalTrav.get(i).getCityOfArr());
			dep.add(this.avalTrav.get(i).getCityOfDep());
		}
		ObservableList<String> arrCities = FXCollections.observableArrayList(arr);
		ObservableList<String> depCities = FXCollections.observableArrayList(dep);
		this.departureCity.setItems(depCities);
		this.arrivalCity.setItems(arrCities);
	}
	
	public void setGroups() {
		if(this.logUsr.getPersonality() != null) {
			int j;
			for(j = 0; j < grpList.size(); j++) {
				VBox vbox = new VBox(10);
				Text title = new Text(grpList.get(j).getGroupTitle());
				Text owner = new Text(grpList.get(j).getGroupOwner());
				Text location = new Text(grpList.get(j).getGroupDestination());
				Button join = new Button("join group");
				join.setOnMouseClicked(this::joinTheGroup);
				vbox.getChildren().addAll(title, owner, location, join);
				this.groupsView.getItems().add(vbox);
			}
		}
	}
	
	public void setLocation() {
		if(this.logUsr.getPersonality() == null) {
			this.popUp.displayLoginError("No suggested location/travel groups to be shown. Please,take personality test");
		}
		else {
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
			}
		}
	}
	
	public void showMoreInfo(MouseEvent e) {
		int i;
		ObservableList<HBox> loc = FXCollections.observableArrayList(this.suggLocView.getItems());
		for(i = 0; i < loc.size(); i++) {
			if(loc.get(i).getChildren().get(1).equals(e.getTarget())) {
				Text text = (Text)loc.get(i).getChildren().get(0);
				this.locBean.setCityName(text.getText());
				this.facade.retriveLocInfo(this.locBean);
				loadLocInfo(e);
			}
		}
	}
	
	public void loadLocInfo(MouseEvent e) {
		setScene("LocationInfo.fxml");
		loadScene();
		setLocationInfo(e, this.locBean);
	}
	
	public void bookSuggestedLoc(MouseEvent e) {
		int i;
		ObservableList<HBox> travls = FXCollections.observableArrayList(this.suggLocView.getItems());
		for(i = 0; i < travls.size(); i++) {
			if(travls.get(i).getChildren().get(2).equals(e.getTarget())) {
				Text city = (Text)travls.get(i).getChildren().get(0);
				this.travBean = new UserTravelBean(city.getText());
				try {
					this.travBeanArray.addAll(this.facade.getSuggTicketsInfo(this.travBean));
					setScene("TicketSolutions.fxml");
					loadScene();
					setTicketsDatas(this.travBeanArray, e);
				}
				catch(EmptyListException e1) {
					this.popUp.displayLoginError("Nessun viaggio disponibile o viaggio già prenotato");
				}
			}
		}
	}
	
	public void joinTheGroup(MouseEvent e) {
		int i;
		ObservableList<VBox> groups = FXCollections.observableArrayList(this.groupsView.getItems());
		for(i = 0; i < groups.size(); i++) {
			if(groups.get(i).getChildren().get(3).equals(e.getTarget())) {
				Text title = (Text)groups.get(i).getChildren().get(0);
				GroupBean grpBean = new GroupBean(title.getText());
				if(this.facade.insertParticipant(grpBean) == 0) {
					this.popUp.displayLoginError("Gruppo correttamente joinato");
					VBox temp = groups.get(i);
					this.groupsView.getItems().remove(temp);
				}
				else {
					this.popUp.displayLoginError("Errore nel join del gruppo");
				}
			}
		}
	}
	
	private void checkBookSol(MouseEvent event) {
		try {
			this.facade.retriveTravelSolutions(travBean, travBeanArray);
			setScene("TicketSolutions.fxml");
			loadScene();
			setTicketsDatas(this.travBeanArray, event);
		}
		catch(EmptyListException e) {
			this.popUp.displayLoginError("No available travels for the requested cities/dates");
		}
		catch(BigDateException e) {
			this.popUp.displayLoginError(e.getMessage());
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

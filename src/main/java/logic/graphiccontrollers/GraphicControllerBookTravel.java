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
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserTravelBean;
import logic.exceptions.BigDateException;
import logic.exceptions.EmptyListException;
import logic.exceptions.NullValueException;
import logic.view.BasicGui;

public class GraphicControllerBookTravel extends BasicGui{
	private UserTravelBean travBean;
	private LocationBean locBean;
	private List<UserTravelBean> travBeanArray;
	private List<GroupBean> grpList;
	private List<String> suggLoc;
	private List<String> depCities;  //used to load available travels
	private List<String> arrCities;
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
		this.userImage.setImage(setUserImage());
		if(LoggedUser.getPersonality() == null) {
			this.popErr.displayErrorPopup("No suggestions for you, please take our test");
			Button btnTest = new Button("Take personality test");
			btnTest.setOnMouseClicked(e->{
				setScene("InterestsForm.fxml");
				loadScene();
				nextGuiOnClick(e);
			});
			HBox test = new HBox();
			test.getChildren().add(btnTest);
			this.suggLocView.getItems().add(test);
		}
		else {
			this.grpList = new ArrayList<>();
			this.suggLoc = new ArrayList<>();
			this.facade.findSuggGroups(grpList);
			this.facade.findTravelSugg(suggLoc);
			setAllLocations();
			setAllGroups();
		}
		this.depCities = new ArrayList<>();
		this.arrCities = new ArrayList<>();
		this.facade.getAvailableTick(this.depCities, this.arrCities);
		setDepAndArr();
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
			this.popErr.displayErrorPopup(e.getNullExcMsg());
		}
	}
	
	public void setAllLocations() {
		for(int i = 0; i < this.suggLoc.size(); i++) {
			setLocation(this.suggLoc.get(i));
		}
	}
	
	public void setAllGroups() {
		for(int i = 0; i < this.grpList.size(); i++) {
			setGroup(this.grpList.get(i));
		}
	}
	
	public void setDepAndArr() {
		ObservableList<String> arr = FXCollections.observableArrayList(this.arrCities);
		ObservableList<String> dep = FXCollections.observableArrayList(this.depCities);
		this.departureCity.setItems(dep);
		this.arrivalCity.setItems(arr);
	}
	
	public void setGroup(GroupBean grpBean) {
		VBox vbox = new VBox(10);
		Text title = new Text(grpBean.getGroupTitle());
		Text owner = new Text(grpBean.getGroupOwner());
		Text location = new Text(grpBean.getGroupDestination());
		Button join = new Button("join group");
		join.setOnMouseClicked(e->{
			this.facade.insertParticipant(grpBean);
			this.groupsView.getItems().remove(vbox);
			this.popErr.displayErrorPopup("Group joined");
		});
		vbox.getChildren().addAll(title, owner, location, join);
		this.groupsView.getItems().add(vbox);
	}
	
	public void setLocation(String locat) {
		HBox hbox = new HBox(20);
		VBox vbox = new VBox();
		Text space = new Text();
		space.setText("                           ");
		Text loc = new Text(locat);
		vbox.getChildren().addAll(loc, space);
		Button info = new Button("Get more info");
		Button bookNow = new Button("Book a travel");
		bookNow.setOnMouseClicked(e->{
			this.travBean = new UserTravelBean(locat);
			try {
				this.travBeanArray.addAll(this.facade.getSuggTicketsInfo(this.travBean));
				setScene("TicketSolutions.fxml");
				loadScene();
				setTicketsDatas(this.travBeanArray, e);
			}
			catch(EmptyListException e1) {
				this.popErr.displayErrorPopup("No available travel or travel already bought");
			}
		});
		info.setOnMouseClicked(e->{
			this.locBean.setCityName(locat);
			this.facade.retrieveLocInfo(this.locBean);
			loadLocInfo(e);
		});
		hbox.getChildren().addAll(vbox, info, bookNow);
		this.suggLocView.getItems().add(hbox);
	}
	
	public void loadLocInfo(MouseEvent e) {
		setScene("LocationInfo.fxml");
		loadScene();
		setLocationInfo(e, this.locBean);
	}
	
	private void checkBookSol(MouseEvent event) {
		try {
			this.facade.retrieveTravelSolutions(travBean, travBeanArray);
			setScene("TicketSolutions.fxml");
			loadScene();
			setTicketsDatas(this.travBeanArray, event);
		}
		catch(EmptyListException e) {
			this.popErr.displayErrorPopup("No available travels for the requested cities/dates");
		}
		catch(BigDateException e) {
			this.popErr.displayErrorPopup(e.getMessage());
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

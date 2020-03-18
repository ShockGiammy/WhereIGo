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
	
	public void initialize() {
		this.travBeanArray = new ArrayList<>();
		this.hboxList = new ArrayList<>();
		this.grpBean = new GroupBean();
		logUsr = new LoggedUser();
		this.locBean = new LocationBean();
		this.travBean = new UserTravelBean();
		this.bookTravCtrl = new BookTravelControl();
		this.popUp = new ErrorPopup();
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
		int i;
		int j;
		for(i = 0; i < hboxList.size(); i++) {
			Text text = (Text)this.hboxList.get(i).getChildren().get(0);
			this.grpBean.setGroupDestination(text.getText());
			List<GroupBean> grpBeanList = new ArrayList<>();
			this.bookTravCtrl.getGroupsControl(this.grpBean, grpBeanList);
			for(j = 0; j < grpBeanList.size(); j++) {
				if(grpBeanList.get(j).getGroupOwner() != null && grpBeanList.get(j).getGroupTitle() != null) {
					VBox vbox = new VBox(7);
					Text title = new Text(grpBeanList.get(j).getGroupTitle());
					Text owner = new Text(grpBeanList.get(j).getGroupOwner());
					Text location = new Text(grpBeanList.get(j).getGroupDestination());
					Button contact = new Button("contact owner");
					vbox.getChildren().addAll(title, owner, location, contact);
					this.groupsView.getItems().add(vbox);
				}
			}
		}
	}
	
	public void setLocation() {
		List<String> suggLoc = new ArrayList<>();
		suggLoc.addAll(bookTravCtrl.showLocationsControl());
		int i;
		for(i = 0; i < suggLoc.size(); i++) {
			HBox hbox = new HBox(12);
			Text loc = new Text(suggLoc.get(i));
			Button info = new Button("Get more info");
			info.setOnMouseClicked(this::showMoreInfo);
			hbox.getChildren().addAll(loc,info);
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
}

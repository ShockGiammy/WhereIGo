package logic.graphiccontrollers;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
	private List<GroupBean> grpBean;
	private LoggedUser logUsr;
	private LocationBean locBean;
	private String locInfo = "LocationInfo.fxml";
	private List<UserTravelBean> travBeanArray;
	private ErrorPopup popUp;
	@FXML private DatePicker firstDay;
	@FXML private DatePicker lastDay;
	@FXML private TextField departureCity;
	@FXML private TextField arrivalCity;
	@FXML private Text location1;
	@FXML private Text location2;
	@FXML private Text location3;
	@FXML private Button bookMyTravel;
	@FXML private Button moreInfo1;
	@FXML private Button moreInfo2;
	@FXML private Button moreInfo3;
	@FXML private ListView<Pane> listview;
	
	public void initialize() {
		this.travBeanArray = new ArrayList<>();
		this.grpBean = new ArrayList<>();
		this.grpBean.add(new GroupBean());
		this.grpBean.add(new GroupBean());
		this.grpBean.add(new GroupBean());
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
			popUp.displayLoginError("No travel available at this moment "
					+ "for the requested cities/dates");
		}
		else {
			setScene("TicketSolutions.fxml");
			loadScene();
			setTicketsDats(this.travBeanArray, event);
		}
	}

	public void setGroups() {
		this.grpBean.get(0).setGroupDestination(this.location1.getText());
		this.grpBean.get(1).setGroupDestination(this.location2.getText());
		this.grpBean.get(2).setGroupDestination(this.location3.getText());
		this.bookTravCtrl.getGroupsControl(this.grpBean);
		for(int i = 0; i < this.grpBean.size(); i++) {
			Pane pane = new Pane();
			Text title = new Text(grpBean.get(i).getGroupTitle());
			Text owner = new Text(grpBean.get(i).getGroupOwner());
			Text location = new Text(grpBean.get(i).getGroupDestination());
			Button contact = new Button("contact owner");
			pane.getChildren().addAll(title, owner, location, contact);
			this.listview.getItems().add(pane);
		}
	}
	
	public void setLocation() {
		List<String> suggLoc = new ArrayList<>();
		suggLoc.addAll(bookTravCtrl.showLocationsControl());
		this.location1.setText(suggLoc.get(0));
		this.location2.setText(suggLoc.get(1));
		this.location3.setText(suggLoc.get(2));
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
	
	public void showMoreInfo1(MouseEvent e) {
		this.locBean.setCityName(this.location1.getText());
		this.bookTravCtrl.retriveLocInfoControl(this.locBean);
		loadLocInfo(e);
	}
	
	public void showMoreInfo2(MouseEvent e) {
		this.locBean.setCityName(this.location2.getText());
		this.bookTravCtrl.retriveLocInfoControl(this.locBean);
		loadLocInfo(e);
	}
	
	public void showMoreInfo3(MouseEvent e) {
		this.locBean.setCityName(this.location3.getText());
		this.bookTravCtrl.retriveLocInfoControl(this.locBean);
		loadLocInfo(e);
	}
	
	public void backHome(MouseEvent e) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(logUsr.getUserName());
		setScene("HomePage.fxml");
		loadScene();
		setUserNick(e, dataBean);
	}
	
	public void loadLocInfo(MouseEvent e) {
		setScene(locInfo);
		loadScene();
		setLocationInfo(e, this.locBean);
	}
}

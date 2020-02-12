package logic.graphiccontrollers;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.beans.GroupBean;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;
import logic.view.Window;
import javafx.scene.control.TextField;

public class GraphicControllerBookTravel extends Window{
	private UserTravelBean travBean;
	private BookTravelControl bookTravCtrl;
	private GroupBean grpBean[];
	@FXML private DatePicker firstDay;
	@FXML private DatePicker lastDay;
	@FXML private TextField moneyRange;
	@FXML private Text location1;
	@FXML private Text location2;
	@FXML private Text location3;
	@FXML private Button bookMyTravel;
	@FXML private Text groupTitle1;
	@FXML private Text groupTitle2;
	@FXML private Text city1;
	@FXML private Text city2;
	@FXML private Text owner1;
	@FXML private Text owner2;
	@FXML private Button contact1;
	@FXML private Button contact2;
	@FXML private Button moreInfo1;
	@FXML private Button moreInfo2;
	@FXML private Button moreInfo3;
	
	private DateTimeFormatter formatter;
	
	public GraphicControllerBookTravel() {
		this.travBean = new UserTravelBean();
		bookTravCtrl = new BookTravelControl();
		formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		this.grpBean = new GroupBean[2];
		this.grpBean[0] = new GroupBean();
		this.grpBean[1] = new GroupBean();
	}
	
	public void initialize() {
		this.travBean = new UserTravelBean();
		this.bookTravCtrl = new BookTravelControl();
		formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
		this.grpBean = new GroupBean[2];
		this.grpBean[0] = new GroupBean();
		this.grpBean[1] = new GroupBean();
		setLocation();
		setGroups();
		
	}
	
	@FXML 
	public void bookMyTravelControl(MouseEvent event) {
		setScene("DummyBookTravel.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
	@FXML
	public void setGroups() {
		this.bookTravCtrl.getGroupsControl(this.grpBean);
		this.groupTitle1.setText(grpBean[0].getGroupTitle());
		this.owner1.setText(this.grpBean[0].getGroupOwner());
		this.city1.setText(this.grpBean[0].getGroupDestination());
		this.groupTitle2.setText(grpBean[1].getGroupTitle());
		this.owner2.setText(this.grpBean[1].getGroupOwner());
		this.city2.setText(this.grpBean[1].getGroupDestination());
	}
	
	@FXML
	public void setLocation() {
		String suggLoc[] = new String[3];
		suggLoc = bookTravCtrl.showLocationsControl();
		this.location1.setText(suggLoc[0]);
		this.location2.setText(suggLoc[1]);
		this.location3.setText(suggLoc[2]);
	}
	
	@FXML
	public void getFirstDay() {
		String fDay = this.firstDay.getValue().format(formatter);
		travBean.setFirstDay(fDay);
	}
	
	@FXML
	public void getLastDay() {
		String lDay = this.lastDay.getValue().format(formatter);
		travBean.setFirstDay(lDay);
	}
	
	@FXML
	public void getMoneyRange() {
		String mRange = this.moneyRange.getText();
		travBean.setMoneyRange(mRange);
	}
}

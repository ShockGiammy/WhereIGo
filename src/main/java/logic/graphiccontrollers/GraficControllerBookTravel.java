package logic.graphiccontrollers;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.UserDao;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;
import logic.view.Window;
import javafx.scene.control.TextField;

public class GraficControllerBookTravel extends Window{
	private UserTravelBean travBean;
	private BookTravelControl bookTravCtrl;
	@FXML private DatePicker firstDay;
	@FXML private DatePicker lastDay;
	@FXML private TextField moneyRange;
	@FXML private Text location1;
	@FXML private Text location2;
	@FXML private Text location3;
	@FXML private Button locations;
	
	private DateTimeFormatter formatter;
	
	public GraficControllerBookTravel() {
		this.travBean = new UserTravelBean();
		bookTravCtrl = new BookTravelControl();
		formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	}
	
	@FXML
	public void setLocation() throws SQLException {
		this.location1.setText(bookTravCtrl.showLocationsControl());
		this.location2.setText(bookTravCtrl.showLocationsControl());
		this.location3.setText(bookTravCtrl.showLocationsControl());
	}
	
	@FXML
	public void getFirstDay() {
		String firstDay = this.firstDay.getValue().format(formatter);
		travBean.setFirstDay(firstDay);
	}
	
	@FXML
	public void getLastDay() {
		String lastDay = this.lastDay.getValue().format(formatter);
		travBean.setFirstDay(lastDay);
	}
	
	@FXML
	public void getMoneyRange() {
		String moneyRange = this.moneyRange.getText();
		travBean.setMoneyRange(moneyRange);
	}
}

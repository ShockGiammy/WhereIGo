package logic.graphiccontrollers;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;
import javafx.scene.control.TextField;

public class GraficControllerBookTravel {
	private UserTravelBean travBean;
	private BookTravelControl travelController;
	@FXML private Button findMyTravel;
	@FXML private DatePicker firstDay;
	@FXML private DatePicker lastDay;
	@FXML private TextField moneyRange;
	
	private DateTimeFormatter formatter;
	
	public GraficControllerBookTravel() {
		this.travBean = new UserTravelBean();
		this.travelController = new BookTravelControl();
		formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	}
	
	public void getFirstDay() {
		String firstDay = this.firstDay.getValue().format(formatter);
		travBean.setFirstDay(firstDay);
	}
	
	public void getLastDay() {
		String lastDay = this.lastDay.getValue().format(formatter);
		travBean.setFirstDay(lastDay);
	}
	
	public void getMoneyRange() {
		String moneyRange = this.moneyRange.getText();
		travBean.setMoneyRange(moneyRange);
	}
	
	public void displayTravelOptions() {
		travelController.showLocationsControl(this.travBean);
	}
}

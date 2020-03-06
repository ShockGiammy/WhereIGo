package logic.graphiccontrollers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.beans.UserTravelBean;

public class GraphicControllerTickets {
	@FXML TableView<UserTravelBean> ticketsView;
	@FXML TableColumn<UserTravelBean, String> departureCity;
	@FXML TableColumn<UserTravelBean, String> arrivalCity;
	@FXML TableColumn<UserTravelBean, LocalDate> departureDay;
	@FXML TableColumn<UserTravelBean, LocalDate> arrivalDate;
	@FXML TableColumn<UserTravelBean, Float> cost;
	ObservableList<UserTravelBean> travBeanList = FXCollections.observableArrayList();
	
	public void setDatas(List<UserTravelBean> travBean) {
		travBeanList.addAll(travBean);
		departureDay.setCellValueFactory(new PropertyValueFactory<>("firstDay"));
		arrivalDate.setCellValueFactory(new PropertyValueFactory<>("lastDay"));
		departureCity.setCellValueFactory(new PropertyValueFactory<>("cityOfDep"));
		arrivalCity.setCellValueFactory(new PropertyValueFactory<>("cityOfArr"));
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		ticketsView.setItems(travBeanList);
	}
}

package logic.graphiccontrollers;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.beans.UserTravelBean;
import logic.view.Window;

public class GraphicControllerTickets extends Window{
	@FXML private TableView<UserTravelBean> ticketsView;
	@FXML private TableColumn<UserTravelBean, String> departureCity;
	@FXML private TableColumn<UserTravelBean, String> arrivalCity;
	@FXML private TableColumn<UserTravelBean, LocalDate> departureDay;
	@FXML private TableColumn<UserTravelBean, LocalDate> arrivalDate;
	@FXML private TableColumn<UserTravelBean, Float> cost;
	@FXML private List<ToggleButton> toggleList;
	@FXML private ToggleGroup bookNowGroup;
	ObservableList<UserTravelBean> travBeanList = FXCollections.observableArrayList();
	
	public void setDatas(List<UserTravelBean> travBean) {
		travBeanList.addAll(travBean);
		departureDay.setCellValueFactory(new PropertyValueFactory<>("firstDay"));
		arrivalDate.setCellValueFactory(new PropertyValueFactory<>("lastDay"));
		departureCity.setCellValueFactory(new PropertyValueFactory<>("cityOfDep"));
		arrivalCity.setCellValueFactory(new PropertyValueFactory<>("cityOfArr"));
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		ticketsView.setItems(travBeanList);
		ticketsView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
}

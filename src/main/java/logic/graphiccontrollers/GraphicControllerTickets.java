package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logic.LoggedUser;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.view.BasicGui;

public class GraphicControllerTickets extends BasicGui{
	@FXML private TableView<UserTravelBean> ticketsView;
	@FXML private TableColumn<UserTravelBean, String> ticketId;
	@FXML private TableColumn<UserTravelBean, String> departureCity;
	@FXML private TableColumn<UserTravelBean, String> arrivalCity;
	@FXML private TableColumn<UserTravelBean, String> departureDay;
	@FXML private TableColumn<UserTravelBean, String> arrivalDate;
	@FXML private TableColumn<UserTravelBean, String> cost;
	@FXML private List<RadioButton> rbList;
	@FXML private ToggleGroup bookNowGroup;
	@FXML private VBox vbox;
	@FXML private Button bookTheTravel;
	ObservableList<UserTravelBean> travBeanList = FXCollections.observableArrayList();
	
	@FXML
	public void initialize() {
		this.bookNowGroup = new ToggleGroup();
		this.rbList = new ArrayList<>();
		this.userImage.setImage(setUserImage());
	}
	
	public void setDatas(List<UserTravelBean> travBean) {
		int i = 0;
		ticketId.setCellValueFactory(new PropertyValueFactory<>("id"));
		departureDay.setCellValueFactory(new PropertyValueFactory<>("firstDay"));
		arrivalDate.setCellValueFactory(new PropertyValueFactory<>("lastDay"));
		departureCity.setCellValueFactory(new PropertyValueFactory<>("cityOfDep"));
		arrivalCity.setCellValueFactory(new PropertyValueFactory<>("cityOfArr"));
		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
		while(i < travBean.size()) {
			ticketsView.getItems().add(travBean.get(i));
			setRadioButton();
			i+=1;
		}
	}
	
	public void setRadioButton() {
		RadioButton r1 = new RadioButton();
		r1.setToggleGroup(this.bookNowGroup);
		vbox.getChildren().add(r1);
		this.rbList.add(r1);
	}
	
	public void bookTravel(MouseEvent e) {
		if(this.bookNowGroup.getSelectedToggle() == null) {
			this.popErr.displayErrorPopup("Please select a travel");
		}
		else {
			int i;
			for(i = 0; i < this.rbList.size(); i++) {
				if(this.bookNowGroup.getSelectedToggle().equals(this.rbList.get(i))) {
					UserTravelBean travBean = new UserTravelBean(this.departureDay.getCellData(i), this.arrivalDate.getCellData(i), this.departureCity.getCellData(i), this.arrivalCity.getCellData(i));
					travBean.setId(this.ticketId.getCellData(i));
					travBean.setCost(this.cost.getCellData(i));
					UserDataBean dataBean = new UserDataBean(LoggedUser.getUserName());
					setScene("TicketCheckout.fxml");
					loadScene();
					setCheckoutValues(travBean, dataBean, e);
				}
			}
		}
	}
	
	public void setCheckoutValues(UserTravelBean travBean, UserDataBean dataBean, MouseEvent e) {
		GraphicControllerCheckOut controller = loader.getController();
		controller.setInfo(travBean, dataBean);
		nextGuiOnClick(e);
	}
}

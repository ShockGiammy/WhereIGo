package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;
import logic.view.ErrorPopup;
import logic.view.Window;

public class GraphicControllerCheckOut extends Window{
	@FXML private Text departure;
	@FXML private Text arrive;
	@FXML private Text depDay;
	@FXML private Text retDay;
	@FXML private TextField groupName;
	@FXML private TextField groupAdmin;
	@FXML private TextField groupDest;
	@FXML private Button createGroup;
	@FXML private Button confirmTrav;
	private BookTravelControl bookTravCtrl;
	private ErrorPopup errPop;
	private List<UserTravelBean> travbean;
	private UserDataBean bean;
	
	public GraphicControllerCheckOut() {
		this.bookTravCtrl = new BookTravelControl();
		errPop = new ErrorPopup();
		travbean = new ArrayList<>();
		bean = new UserDataBean();
	}
	
	public void setInfo(UserTravelBean travBean, UserDataBean dataBean) {
		travbean.add(travBean);
		this.bean = dataBean;
		this.departure.setText(travBean.getCityOfDep());
		this.arrive.setText(travBean.getCityOfArr());
		this.depDay.setText(travBean.getFirstDay().toString());
		this.retDay.setText(travBean.getLastDay().toString());
		this.groupAdmin.setText(dataBean.getUsername());
		this.groupAdmin.setEditable(false);
		this.groupDest.setText(travBean.getCityOfArr());
		this.groupDest.setEditable(false);
	}
	
	public void saveNewGroup() {
		if(this.groupName.getText() == null) {
			errPop.displayLoginError("Inserisci il nome del gruppo");
			return;
		}
		GroupBean grpBean = new GroupBean();
		grpBean.setGroupTitle(this.groupName.getText());
		grpBean.setGroupOwner(this.groupAdmin.getText());
		grpBean.setGroupDestination(this.groupDest.getText());
		int ret = this.bookTravCtrl.saveGroup(grpBean);
		if(ret != -1) {
			errPop.displayLoginError("Gruppo correttamente salvato");
		}
		else {
			errPop.displayLoginError("Errore nel salvataggio del gruppo");
		}
	}
	
	public void confirmTrav(MouseEvent e) {
		this.bookTravCtrl.saveBoughtTicket(this.travbean.get(0), this.bean);
		setScene("HomePage.fxml");
		loadScene();
		setTicketBought(travbean, bean, e);
	}
}

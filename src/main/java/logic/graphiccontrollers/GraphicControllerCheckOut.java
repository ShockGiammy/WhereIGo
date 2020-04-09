package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.exceptions.GroupNameTakenException;
import logic.view.ErrorPopup;
import logic.view.BasicGui;

public class GraphicControllerCheckOut extends BasicGui{
	@FXML private Text id;
	@FXML private Text departure;
	@FXML private Text arrive;
	@FXML private Text depDay;
	@FXML private Text retDay;
	@FXML private Text cost;
	@FXML private TextField groupName;
	@FXML private TextField groupAdmin;
	@FXML private TextField groupDest;
	@FXML private Button createGroup;
	@FXML private Button confirmTrav;
	private ErrorPopup errPop;
	private UserTravelBean travbean;
	
	@FXML
	public void initialize() {
		errPop = new ErrorPopup();
		travbean = new UserTravelBean();
		this.userImage.setImage(setUserImage());
	}
	
	public void setInfo(UserTravelBean travBean, UserDataBean dataBean) {
		this.travbean = travBean;
		this.id.setText(String.valueOf(travBean.getId()));
		this.departure.setText(travBean.getCityOfDep());
		this.arrive.setText(travBean.getCityOfArr());
		this.depDay.setText(travBean.getFirstDay());
		this.retDay.setText(travBean.getLastDay());
		this.cost.setText(String.valueOf(travBean.getCost()));
		this.groupAdmin.setText(dataBean.getUsername());
		this.groupAdmin.setEditable(false);
		this.groupDest.setText(travBean.getCityOfArr());
		this.groupDest.setEditable(false);
	}
	
	public void saveNewGroup() {
		if(this.groupName.getText() == null) {
			errPop.displayLoginError("Inserisci il nome del gruppo");
		}
		else {
			try {
				GroupBean grpBean = new GroupBean();
				grpBean.setGroupTitle(this.groupName.getText());
				grpBean.setGroupOwner(this.groupAdmin.getText());
				grpBean.setGroupDestination(this.groupDest.getText());
				this.facCtrl.saveGroup(grpBean);
			}catch(GroupNameTakenException e) {
				errPop.displayLoginError("Nome del gruppo già scelto. Per favore, inserire un nome diverso");
			}
		}
	}
	
	public void confirmTrav(MouseEvent e) {
		this.facCtrl.saveBoughtTicket(this.travbean);
		goHome(e);
	}
}

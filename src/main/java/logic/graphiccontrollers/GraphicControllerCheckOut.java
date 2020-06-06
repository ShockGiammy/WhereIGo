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
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;
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
	private UserTravelBean travbean;
	
	@FXML
	public void initialize() {
		travbean = new UserTravelBean();
		this.userImage.setImage(setUserImage());
	}
	
	public void setInfo(UserTravelBean travBean, UserDataBean dataBean) {
		this.travbean = travBean;
		this.id.setText(travBean.getId());
		this.departure.setText(travBean.getCityOfDep());
		this.arrive.setText(travBean.getCityOfArr());
		this.depDay.setText(travBean.getFirstDay());
		this.retDay.setText(travBean.getLastDay());
		this.cost.setText(travBean.getCost());
		this.groupAdmin.setText(dataBean.getUsername());
		this.groupAdmin.setEditable(false);
		this.groupDest.setText(travBean.getCityOfArr());
		this.groupDest.setEditable(false);
	}
	
	public void saveNewGroup() {
		GroupBean grpBean = new GroupBean();
		try {
			grpBean.setGroupTitle(this.groupName.getText());
			grpBean.setGroupDestination(this.groupDest.getText());
			saveGroup(grpBean);
		} catch (LengthFieldException e) {
			this.popErr.displayErrorPopup(e.getMsg());
		} catch (NullValueException e) {
			this.popErr.displayErrorPopup(e.getNullExcMsg());
		}
	}
	
	private void saveGroup(GroupBean grpBean) {
		try {
			grpBean.setGroupOwner(this.groupAdmin.getText());
			this.facade.saveGroup(grpBean);
			this.popErr.displayErrorPopup("Group correctly saved");
		}
		catch(GroupNameTakenException e) {
			this.popErr.displayErrorPopup("Group name already taken");
		}
	}
	
	public void confirmTrav(MouseEvent e) {
		this.facade.saveBoughtTicket(this.travbean);
		goHome(e);
	}
}

package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;
import logic.view.BasicGui;
public class GraphicControllerCreateGroup extends BasicGui{
	@FXML private TextField groupName;
	@FXML private TextField groupAdmin;
	@FXML private TextField groupDest;
	@FXML private Button createGroup;
	private GroupBean grpBean;
	
	@FXML
	public void initialize() {
		this.groupAdmin.setText(LoggedUser.getUserName());
		this.grpBean = new GroupBean();
		grpBean.setGroupOwner(LoggedUser.getUserName());
		this.userImage.setImage(setUserImage());
	}
	
	public void saveUserGroup(MouseEvent event) {
		try {
			this.grpBean.setGroupTitle(this.groupName.getText());
			this.grpBean.setGroupDestination(this.groupDest.getText());
			checkValues(event);
		} catch (LengthFieldException e1) {
			this.popErr.displayErrorPopup(e1.getMsg());
		} catch (NullValueException e) {
			this.popErr.displayErrorPopup(e.getNullExcMsg());
		}
	}
	
	private void checkValues(MouseEvent e) {
		try {
			this.facade.saveGroup(this.grpBean);
			goHome(e);
		} 
		catch (GroupNameTakenException e1) {
			this.popErr.displayErrorPopup("Nome gruppo non disponibile");
		}
	}
}

package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.beans.GroupBean;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;
import logic.view.BasicGui;
import logic.view.ErrorPopup;

public class GraphicControllerCreateGroup extends BasicGui{
	@FXML private TextField groupName;
	@FXML private TextField groupAdmin;
	@FXML private TextField groupDest;
	@FXML private Button createGroup;
	private GroupBean grpBean;
	private ErrorPopup err;
	
	@FXML
	public void initialize() {
		this.groupAdmin.setText(this.logUsr.getUserName());
		this.grpBean = new GroupBean();
		grpBean.setGroupOwner(this.logUsr.getUserName());
		this.err = new ErrorPopup();
		this.userImage.setImage(setUserImage());
	}
	
	public void saveUserGroup(MouseEvent e) {
		try {
			checkValues();
			this.facade.saveGroup(this.grpBean);
			goHome(e);
		} 
		catch (GroupNameTakenException e1) {
			err.displayLoginError("Nome gruppo non disponibile");
		}
		catch(NullValueException e2) {
			err.displayLoginError(e2.getErrorMessage());
		}
	}
	
	private void checkValues() {
		try {
			this.grpBean.setGroupTitle(this.groupName.getText());
		} catch (LengthFieldException e1) {
			this.err.displayLoginError(e1.getMsg());
		}
		try {
			this.grpBean.setGroupDestination(this.groupDest.getText());
		} catch (LengthFieldException e1) {
			this.err.displayLoginError(e1.getMsg());
		}
	}
}

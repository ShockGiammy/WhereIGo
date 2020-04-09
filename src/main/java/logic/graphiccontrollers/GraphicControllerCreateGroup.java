package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.beans.GroupBean;
import logic.exceptions.GroupNameTakenException;
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
	
	public void getName() {
		this.grpBean.setGroupTitle(this.groupName.getText());
	}
	
	public void getDest() {
		this.grpBean.setGroupDestination(this.groupDest.getText());
	}
	
	public void saveUserGroup(MouseEvent e) {
		try {
			this.facade.saveGroup(this.grpBean);
		} catch (GroupNameTakenException e1) {
			err.displayLoginError("Nome gruppo non disponibile");
		}
		goHome(e);
	}
}

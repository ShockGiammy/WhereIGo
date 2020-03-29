package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.beans.GroupBean;
import logic.controllers.BookTravelControl;
import logic.view.BasicGui;

public class GraphicControllerCreateGroup extends BasicGui{
	@FXML private TextField groupName;
	@FXML private TextField groupAdmin;
	@FXML private TextField groupDest;
	@FXML private Button createGroup;
	private GroupBean grpBean;
	private BookTravelControl travCtr;
	
	@FXML
	public void initialize() {
		this.userImage.setImage(this.logUsr.getImage());
		this.groupAdmin.setText(this.logUsr.getUserName());
		this.grpBean = new GroupBean();
		grpBean.setGroupOwner(this.logUsr.getUserName());
		this.travCtr = new BookTravelControl();
	}
	
	public void getName() {
		this.grpBean.setGroupTitle(this.groupName.getText());
	}
	
	public void getDest() {
		this.grpBean.setGroupDestination(this.groupDest.getText());
	}
	
	public void saveUserGroup(MouseEvent e) {
		this.travCtr.saveGroup(this.grpBean);
		goHome(e);
	}
}

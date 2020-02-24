package logic.graphiccontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import logic.LoggedUser;
import logic.beans.InterestsBean;
import logic.beans.UserDataBean;
import logic.controllers.InterestsController;
import logic.view.Window;

public class GraphicControllerInterestForm extends Window{
	@FXML private RadioButton quest1a;
	@FXML private RadioButton quest1b;
	@FXML private RadioButton quest1c;
	@FXML private RadioButton quest1d;
	@FXML private RadioButton quest2a;
	@FXML private RadioButton quest2b;
	@FXML private RadioButton quest2c;
	@FXML private RadioButton quest2d;
	@FXML private RadioButton quest3a;
	@FXML private RadioButton quest3b;
	@FXML private RadioButton quest3c;
	@FXML private RadioButton quest3d;
	@FXML private RadioButton quest4a;
	@FXML private RadioButton quest4b;
	@FXML private RadioButton quest4c;
	@FXML private RadioButton quest4d;
	@FXML private ToggleGroup question1;
	@FXML private ToggleGroup question2;
	@FXML private ToggleGroup question3;
	@FXML private ToggleGroup question4;
	@FXML private Button submitForm;
	private int[] questAnsw;
	private InterestsBean intBean;
	private InterestsController interCtrl;
	private LoggedUser logUser;
	private UserDataBean dataBean;
	
	public void initialize() {
		this.questAnsw = new int[4];
		this.intBean = new InterestsBean();
		this.interCtrl = new InterestsController();
		this.logUser = LoggedUser.getIstance(null);
		this.dataBean = new UserDataBean();
		question1 = new ToggleGroup();
		question2 = new ToggleGroup();
		question3 = new ToggleGroup();
		question4 = new ToggleGroup();
		quest1a.setToggleGroup(this.question1);
		quest1b.setToggleGroup(this.question1);
		quest1c.setToggleGroup(this.question1);
		quest1d.setToggleGroup(this.question1);
		quest2a.setToggleGroup(this.question2);
		quest2b.setToggleGroup(this.question2);
		quest2c.setToggleGroup(this.question2);
		quest2d.setToggleGroup(this.question2);
		quest3a.setToggleGroup(this.question3);
		quest3b.setToggleGroup(this.question3);
		quest3c.setToggleGroup(this.question3);
		quest3d.setToggleGroup(this.question3);
		quest4a.setToggleGroup(this.question4);
		quest4b.setToggleGroup(this.question4);
		quest4c.setToggleGroup(this.question4);
		quest4d.setToggleGroup(this.question4);
	}
	
	public void manageGroup1(ActionEvent e) {
		if(this.question1.getSelectedToggle().equals(this.quest1a)) {
			this.questAnsw[0] = 2;
		}
		if(this.question1.getSelectedToggle().equals(this.quest1b)) {
			this.questAnsw[0] = 1;
		}
		if(this.question1.getSelectedToggle().equals(this.quest1c)) {
			this.questAnsw[0] = 4;
		}
		if(this.question1.getSelectedToggle().equals(this.quest1d)) {
			this.questAnsw[0] = 3;
		}
	}
		
	public void manageGroup2(ActionEvent e) {
		if(this.question2.getSelectedToggle().equals(this.quest2a)) {
			this.questAnsw[1] = 4;
		}
		if(this.question2.getSelectedToggle().equals(this.quest2b)) {
			this.questAnsw[1] = 1;
		}
		if(this.question2.getSelectedToggle().equals(this.quest2c)) {
			this.questAnsw[1] = 2;
		}
		if(this.question2.getSelectedToggle().equals(this.quest2d)) {
			this.questAnsw[1] = 3;
		}
	}
	
	public void manageGroup3(ActionEvent e) {
		if(this.question3.getSelectedToggle().equals(this.quest3a)) {
			this.questAnsw[2] = 1;
		}
		if(this.question3.getSelectedToggle().equals(this.quest3b)) {
			this.questAnsw[2] = 3;
		}
		if(this.question3.getSelectedToggle().equals(this.quest3c)) {
			this.questAnsw[2] = 2;
		}
		if(this.question3.getSelectedToggle().equals(this.quest3d)) {
			this.questAnsw[2] = 4;
		}
	}
	
	public void manageGroup4(ActionEvent e) {
		if(this.question4.getSelectedToggle().equals(this.quest4a)) {
			this.questAnsw[3] = 2;
		}
		if(this.question4.getSelectedToggle().equals(this.quest4b)) {
			this.questAnsw[3] = 3;
		}
		if(this.question4.getSelectedToggle().equals(this.quest4c)) {
			this.questAnsw[3] = 1;
		}
		if(this.question4.getSelectedToggle().equals(this.quest4d)) {
			this.questAnsw[3] = 4;
		}
	}
	
	public void evaluatePersonality(MouseEvent e){
		this.dataBean.setUserName(this.logUser.getUserName());
		this.intBean.setAnswares(this.questAnsw);
		this.interCtrl.evaluateInterests(this.intBean);
		setScene("HomePage.fxml");
		loadScene();
		setUserNick(e, dataBean);
	}
}

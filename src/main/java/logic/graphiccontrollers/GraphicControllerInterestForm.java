package logic.graphiccontrollers;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import logic.beans.InterestsBean;
import logic.exceptions.MissingAnswareException;
import logic.view.ErrorPopup;
import logic.view.BasicGui;

public class GraphicControllerInterestForm extends BasicGui{
	@FXML private List<RadioButton> rbList1;
	@FXML private List<RadioButton> rbList2;
	@FXML private List<RadioButton> rbList3;
	@FXML private List<RadioButton> rbList4;
	@FXML private VBox box1;
	@FXML private VBox box2;
	@FXML private VBox box3;
	@FXML private VBox box4;
	@FXML private List<ToggleGroup> tgList;
	@FXML private Button submitForm;
	private List<Integer> questAnsw;
	private ErrorPopup errPop;
	
	@FXML
	public void initialize() {
		this.questAnsw = new ArrayList<>();
		this.rbList1 = new ArrayList<>();
		this.rbList2 = new ArrayList<>();
		this.rbList3 = new ArrayList<>();
		this.rbList4 = new ArrayList<>();
		this.errPop = new ErrorPopup();
		initToggle(4);
		setGroup(this.box1, this.rbList1, this.tgList.get(0), 4, 1);
		setGroup(this.box2, this.rbList2, this.tgList.get(1), 4, 2);
		setGroup(this.box3, this.rbList3, this.tgList.get(2), 4, 3);
		setGroup(this.box4, this.rbList4, this.tgList.get(3), 4, 4);
		this.userImage.setImage(setUserImage());
	}
	
	public void manageGroup1() {
		if(this.tgList.get(0).getSelectedToggle().equals(this.rbList1.get(0))) {
			this.questAnsw.add(2);
		}
		if(this.tgList.get(0).getSelectedToggle().equals(this.rbList1.get(1))) {
			this.questAnsw.add(1);
		}
		if(this.tgList.get(0).getSelectedToggle().equals(this.rbList1.get(2))) {
			this.questAnsw.add(4);
		}
		if(this.tgList.get(0).getSelectedToggle().equals(this.rbList1.get(3))) {
			this.questAnsw.add(3);
		}
	}
		
	public void manageGroup2() {
		if(this.tgList.get(1).getSelectedToggle().equals(this.rbList2.get(0))) {
			this.questAnsw.add(4);
		}
		if(this.tgList.get(1).getSelectedToggle().equals(this.rbList2.get(1))) {
			this.questAnsw.add(1);
		}
		if(this.tgList.get(1).getSelectedToggle().equals(this.rbList2.get(2))) {
			this.questAnsw.add(2);
		}
		if(this.tgList.get(1).getSelectedToggle().equals(this.rbList2.get(3))) {
			this.questAnsw.add(3);
		}
	}
	
	public void manageGroup3() {
		if(this.tgList.get(2).getSelectedToggle().equals(this.rbList3.get(0))) {
			this.questAnsw.add(1);
		}
		if(this.tgList.get(2).getSelectedToggle().equals(this.rbList3.get(1))) {
			this.questAnsw.add(3);
		}
		if(this.tgList.get(2).getSelectedToggle().equals(this.rbList3.get(2))) {
			this.questAnsw.add(2);
		}
		if(this.tgList.get(2).getSelectedToggle().equals(this.rbList3.get(3))) {
			this.questAnsw.add(4);
		}
	}
	
	public void manageGroup4() {
		if(this.tgList.get(3).getSelectedToggle().equals(this.rbList4.get(0))) {
			this.questAnsw.add(2);
		}
		if(this.tgList.get(3).getSelectedToggle().equals(this.rbList4.get(1))) {
			this.questAnsw.add(3);
		}
		if(this.tgList.get(3).getSelectedToggle().equals(this.rbList4.get(2))) {
			this.questAnsw.add(1);
		}
		if(this.tgList.get(3).getSelectedToggle().equals(this.rbList4.get(3))) {
			this.questAnsw.add(4);
		}
	}
	
	public void initToggle(int n) {
		int j;
		this.tgList = new ArrayList<>();
		for(j = 0; j < n; j++) {
			ToggleGroup tg = new ToggleGroup();
			this.tgList.add(tg);
		}
	}

	@SuppressWarnings("exports")
	public void setGroup(VBox vbox, List<RadioButton> list, ToggleGroup toggle, int n, int who) {
		int i;
		for(i = 0; i < n; i++) {
			RadioButton rb = new RadioButton();
			list.add(rb);
			list.get(i).setToggleGroup(toggle);
			vbox.getChildren().add(list.get(i));
		}
		switch(who) {
			case(1):
				for(i = 0; i < n; i++) {
					list.get(i).setOnAction(e->manageGroup1());
				}
				break;
			case(2):
				for(i = 0; i < n; i++) {
					list.get(i).setOnAction(e->manageGroup2());
				}
				break;
			case(3):
				for(i = 0; i < n; i++) {
				list.get(i).setOnAction(e->manageGroup3());
				}
				break;
			case(4):
				for(i = 0; i < n; i++) {
					list.get(i).setOnAction(e->manageGroup4());
				}
				break;
			default:
				break;
			}
		}
	
	public void evaluatePersonality(MouseEvent e){
		try{
			InterestsBean intBean = new InterestsBean(this.questAnsw);
			this.errPop.displayLoginError("This is your personality : "+this.facade.evaluateInterests(intBean));
			goHome(e);
		}
		catch(MissingAnswareException e1) {
			this.errPop.displayLoginError(e1.getMsg());
		}
	}
}

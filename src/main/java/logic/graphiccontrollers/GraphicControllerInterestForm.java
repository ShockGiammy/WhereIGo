package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class GraphicControllerInterestForm {
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
	private char quest1Answ;
	
	public GraphicControllerInterestForm() {
		this.question1 = new ToggleGroup();
		this.question2 = new ToggleGroup();
		this.question3 = new ToggleGroup();
		this.question4 = new ToggleGroup();
		this.quest1a = new RadioButton();
		this.quest1b = new RadioButton();
		this.quest1c = new RadioButton();
		this.quest1d = new RadioButton();
		setGroup1();
	}
	
	public void setGroup1(){
		this.quest1a.setToggleGroup(question1);
		this.quest1b.setToggleGroup(question1);
		this.quest1c.setToggleGroup(question1);
		this.quest1d.setToggleGroup(question1);
	}
	
	public void manageGroup1() {
		if(this.question1.getSelectedToggle().equals(this.quest1a)) {
			this.quest1Answ = 'a';
		}
		if(this.question1.getSelectedToggle().equals(this.quest1b)) {
			this.quest1Answ = 'b';
		}
		if(this.question1.getSelectedToggle().equals(this.quest1c)) {
			this.quest1Answ = 'c';
		}
		if(this.question1.getSelectedToggle().equals(this.quest1d)) {
			this.quest1Answ = 'd';
		}
	}
	
	public void showAnswares() {
		System.out.println(this.quest1Answ);
	}
}

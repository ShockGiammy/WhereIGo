package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.model.Chat;
import logic.model.SingleChat;
import logic.view.Window;

public class GraphicControllerChat extends Window{
	
	private Chat actualChat;
	@FXML Button sendMessageButton;
	@FXML TextArea message;
	@FXML TextField rMessage;
	
	public GraphicControllerChat() {
		actualChat = new SingleChat("localhost", 2404);
		actualChat.execute();
		actualChat.saveGraphic(this);
	}

	public void sendMessage() {
		actualChat.sendMessage(message.getText());
	}

	public void display(String text) {
		if (text != null) {
			rMessage.setText(text);
		}
	}
	
}

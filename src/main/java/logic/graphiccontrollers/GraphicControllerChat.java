package logic.graphiccontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.controllers.ChatController;
import logic.controllers.DBChatController;
import logic.model.Chat;
import logic.model.SingleChat;
import logic.view.Window;

public class GraphicControllerChat extends Window{
	
	private ChatController chatController;
	private Chat chatBean;
	
	@FXML Button sendMessageButton;
	@FXML TextArea message;
	@FXML TextField rMessage;
	
	@FXML
	public void initialize() {
		chatController = new DBChatController();
		chatBean = chatController.getChat();
		display();		
	}

	public void sendMessage() {
		chatController.sendMessage(message.getText());
	}

	public void display() {
		rMessage.setText(chatBean.getMessages());
	}
	
}

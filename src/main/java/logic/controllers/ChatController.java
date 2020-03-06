package logic.controllers;

import java.util.ArrayList;

import com.messages.Message;

import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Chat;

public interface ChatController {

	public void createChat(String type);
	public void openChat();
	public void execute();
	public void notificateMessage();
	public void acceptChat();
	public void sendMessage(String message);
	public ArrayList<Message> getChat();
}

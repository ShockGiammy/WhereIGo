package logic.controllers;

import java.util.List;
import logic.graphiccontrollers.Message;
import logic.graphiccontrollers.User;

public interface ChatController {

	public void createChat(String type);
	public List<Message> openChat(String receiver);
	public void execute();
	public void acceptChat();
	public void sendMessage(String message, String receiver);
	public List<User> getUsers();
	public void notificateMessage(Message msg);
}

package logic.controllers;

import java.util.ArrayList;
import java.util.List;

import com.messages.Message;
import com.messages.User;

import javafx.collections.ObservableList;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Chat;

public interface ChatController {

	public void createChat(String type);
	public List<Message> openChat(String receiver);
	public void execute();
	public void notificateMessage();
	public void acceptChat();
	public Message sendMessage(String message);
	public List<User> getUsers();
}

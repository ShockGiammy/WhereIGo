package logic.controllers;

import java.util.List;
import java.util.logging.Logger;

import logic.dao.ChatDao;
import logic.model.GroupMessage;
import logic.model.Message;
import logic.model.MessageType;
import logic.model.PrivateMessage;

public class MessageFactory {
	
	private ChatDao chatDao;
	protected Logger logger = Logger.getLogger("WIG");
	
	public MessageFactory() {
		chatDao = new ChatDao();
	}
	
	public List<Message> openChat(String username, String receiver, ChatType type) {
		if (type == ChatType.PRIVATE) {
			return chatDao.getSavedMsg(username, receiver);
		}
		else {
			return chatDao.getGroupMsg(receiver);
		}
	}
	
	public void createChat(ChatType type, String sender, String renter) {
		Message newMessage;
		if (type == ChatType.PRIVATE) {
			newMessage = new PrivateMessage();
		}
		else {
			newMessage = new GroupMessage();
		}
		newMessage.setName(sender);
		newMessage.setGroupOrReceiver(renter);
		chatDao.createNewChat(newMessage);
	}
	
	public Message createMessage(String sender, ChatType chatType, String msg, String receiver, MessageType messageType) {
		Message createMessage;
		if (chatType == ChatType.PRIVATE) {
			createMessage = new PrivateMessage();
			
		}
		else {
			createMessage = new GroupMessage();
		}
		createMessage.setName(sender);
		createMessage.setMsg(msg);
		createMessage.setGroupOrReceiver(receiver);
		createMessage.setType(messageType);
		return createMessage;
	}

	public void saveMessage(String sender, ChatType chatType, String msg, String receiver) {
		Message createMessage;
		if (chatType == ChatType.PRIVATE) {
			createMessage = new PrivateMessage();
		}
		else {
			createMessage = new GroupMessage();
		}
		createMessage.setName(sender);
		createMessage.setMsg(msg);
		createMessage.setGroupOrReceiver(receiver);
		createMessage.save();		
	}
}

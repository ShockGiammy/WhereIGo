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
		if (type == ChatType.PRIVATE) {
			PrivateMessage newMessage = new PrivateMessage();
			newMessage.setName(sender);
			newMessage.setGroupOrReceiver(renter);
			chatDao.createNewChat(newMessage);
		}
	}
	
	public Message createMessage(String sender, ChatType chatType, String msg, String receiver, MessageType messageType) {
		Message createMessage;
		if (chatType == ChatType.PRIVATE) {
			createMessage = new PrivateMessage();
			createMessage.setName(sender);
			createMessage.setMsg(msg);
			createMessage.setGroupOrReceiver(receiver);
			createMessage.setType(messageType);
		}
		else {
			createMessage = new GroupMessage();
			createMessage.setName(sender);
			createMessage.setMsg(msg);
			createMessage.setGroupOrReceiver(receiver);
			createMessage.setType(messageType);
		}
		return createMessage;
	}

	public void saveMessage(String sender, ChatType chatType, String msg, String receiver) {
		if (chatType == ChatType.PRIVATE) {
			PrivateMessage createMessage = new PrivateMessage();
			createMessage.setName(sender);
			createMessage.setMsg(msg);
			createMessage.setGroupOrReceiver(receiver);
			createMessage.save();		}
	}
}

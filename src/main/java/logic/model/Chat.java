package logic.model;

import java.util.ArrayList;

public class Chat {

	private ArrayList<String> messages;
	
	public void setMessages(ArrayList<String> messageDao) {
		this.messages = messageDao;
	}
	
	public String getMessages() {
		return messages.get(0);
	}
}

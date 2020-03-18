package logic.model;

import java.util.List;

public class Chat {

	private List<String> messages;
	
	public void setMessages(List<String> messageDao) {
		this.messages = messageDao;
	}
	
	public String getMessages() {
		return messages.get(0);
	}
}

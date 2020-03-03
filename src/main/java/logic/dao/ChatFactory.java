package logic.dao;

import logic.model.Chat;
import logic.model.MultipleChat;
import logic.model.SingleChat;

public class ChatFactory {
	
	public ChatFactory() {
	}
	
	public Chat createChat(int type) {
		Chat retval = null;
		switch(type) {
			case 1:
				retval = new SingleChat(null, type);
				break;
			case 2:
				retval = new MultipleChat();
				break;
		}
		return retval;
	}

}

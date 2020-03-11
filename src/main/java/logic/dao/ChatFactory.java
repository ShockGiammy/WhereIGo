package logic.dao;

import logic.controllers.ChatController;
import logic.controllers.DBChatController;
//import logic.model.SingleChat;

public class ChatFactory {
	
	public ChatFactory() {
	}
	
	public ChatController createChat(int type) {
		ChatController retval = null;
		switch(type) {
			case 1:
				//retval = new SingleChat(null, type);
				break;
			case 2:
				retval = new DBChatController(null);
				break;
		}
		return retval;
	}

}

package controllertests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import logic.LoggedUser;
import logic.beans.MessageBean;
import logic.controllers.ChatType;
import logic.controllers.ControllerFacade;
import logic.exceptions.LengthFieldException;

public class TestChatController {
	
	ControllerFacade facade;
	LoggedUser logUser;
	private static final String MESSAGE = "Hi! How are you?";
	
	public TestChatController() {
		facade = new ControllerFacade();
	}

	@Test
	public void createChat() {
		
		LoggedUser.setUserName("Traveler");
		facade.createChat("Renter");
		MessageBean msg = null;
		try {
			msg = new MessageBean(MESSAGE, "Renter");
		} catch (LengthFieldException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, ()-> e.getMessage());
		}
		facade.sendMessage(msg);
		
		facade.closeLastChat();
    	List<MessageBean> chat = facade.openChat("Renter", ChatType.PRIVATE);
    	MessageBean returnedMessage = chat.get(chat.size()-1);
    	assertEquals(msg.getMsg(), returnedMessage.getMsg());
	}		
}

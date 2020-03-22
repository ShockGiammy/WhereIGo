package logic.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import logic.LoggedUser;
import logic.dao.ChatDao;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Message;
import logic.model.User;

public class DBChatController { //implements ChatController{
	private String username;
	private ChatDao chatDao;
	private List<Message> chat;
	private GraphicControllerChat graphic;
	private ObservableList<User> users;
	private String status;
	private String hostname;
	private Listener listener;
	private LoggedUser logUser;
	protected Logger logger = Logger.getLogger("WIG");
	private static final String ONLINE = "online";
	private boolean alreadyActive = false;
	
	public DBChatController(GraphicControllerChat reference) {
		chatDao = new ChatDao();
		this.graphic = reference;
		this.logUser = new LoggedUser();
		this.username = logUser.getUserName();
		chatDao.setStatus(username, ONLINE);
	}
	
	public DBChatController() {
		chatDao = new ChatDao();
	}

	public List<Message> openChat(String receiver) {
		chat = chatDao.getSavedMsg(username, receiver);
		status = chatDao.getStatus(receiver);
		return chat;
	}
	
	public List<User> getUsers() {
		users = chatDao.getUsersQuery(username);
		return users;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void closeLastChat() {
		if (alreadyActive) {
			try {
				listener.closeConnection();
			} catch (IOException e) {
				logger.log(Level.SEVERE, ()-> "error closeLastChat - oos.writeObject");
				logger.log(Level.SEVERE, e.getMessage());
			}
			alreadyActive = false;
		}
	}
	public void execute() {
		if (status.equals(ONLINE)) {
			alreadyActive = true;
			this.hostname = "localhost";
			int port = 2400;
			logger.info("socket attivo");
			listener = new Listener(hostname, port, username, this);
	        Thread x = new Thread(listener);
	        x.start();
		}
	}

	//@Override
	public void createChat(String renter) {
		logUser = new LoggedUser();
		chatDao.createNewChat(logUser.getUserName(), renter);
	}


	//@Override
	public void notificateMessage(Message msg) {
		//graphic.newUserNotification(msg);		
	}

	//@Override
	public void acceptChat() {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void sendMessage(String msg, String receiver) {
	        Message createMessage = new Message();
	        createMessage.setName(username);
	        createMessage.setMsg(msg);
	        chatDao.saveMessage(createMessage, receiver);
	        if (status.equals(ONLINE)) {
		    	try {
		    		listener.send(msg);
		        } catch (IOException ex) {
		        	logger.log(Level.SEVERE, ()-> "Error getting output stream: " + ex.getMessage());
		        }
	        }
	        else {
	        	addMessage(createMessage);
	        }
	}
	
	public void addMessage(Message message) {
		graphic.addToChat(message);
	}
	
	public void addServerMessage(Message message) {
		graphic.addAsServer(message);
	}
	
	/*public void closeWindowEvent() {
		
		listener.closeSocket();
	}*/
}

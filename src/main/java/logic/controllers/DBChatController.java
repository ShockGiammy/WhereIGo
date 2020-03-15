package logic.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import logic.LoggedUser;
import logic.dao.ChatDao;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Message;
import logic.model.User;

public class DBChatController implements ChatController{
	private String username;
	private PrintWriter writer;
	private ChatDao chatDao;
	private List<Message> chat;
	private static String picture;
	private GraphicControllerChat graphic;
	private ObservableList<User> users;
	private String status;
	private String hostname;
	private int port;
	private Listener listener;
	private LoggedUser logUser;
	protected Logger logger = Logger.getLogger("WIG");
	
	public DBChatController(GraphicControllerChat reference) {
		chatDao = new ChatDao();
        //Listener.picture = picture;
		this.graphic = reference;
		this.logUser = new LoggedUser();
		this.username = logUser.getUserName();
		chatDao.setStatus(username, "online");
	}
	
	public DBChatController() {
		chatDao = new ChatDao();
	}

	public List<Message> openChat(String receiver) {
		chat = chatDao.getSavedMsg(username, receiver);
		status = chatDao.getStatus(receiver);
		if (status.equals("online")) {
			this.hostname = "localhost";
			this.port = 2400;
			execute();
			logger.info("socket attivo");
		}
		return chat;
	}
	
	public List<User> getUsers() {
		users = chatDao.getUsersQuery(username);
		return users;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void execute() {
		listener = new Listener(hostname, port, username, picture, this);
        Thread x = new Thread(listener);
        x.start();
	}

	@Override
	public void createChat(String renter) {
		LoggedUser logUser = new LoggedUser();
		chatDao.createNewChat(logUser.getUserName(), renter);
	}


	@Override
	public void notificateMessage(Message msg) {
		//graphic.newUserNotification(msg);		
	}

	@Override
	public void acceptChat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(String msg, String receiver) {
	        Message createMessage = new Message();
	        createMessage.setName(username);
	        createMessage.setMsg(msg);
	        //createMessage.setPicture(picture);
	        chatDao.saveMessage(createMessage, receiver);
	        if (status.equals("online")) {
		    	try {
		    		Listener.send(msg);
		        } catch (IOException ex) {
		        	logger.log(Level.SEVERE, "Error getting output stream: " + ex.getMessage());
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
}

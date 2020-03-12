package logic.controllers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.messages.Message;
import com.messages.User;

import javafx.collections.ObservableList;
import logic.LoggedUser;
import logic.dao.ChatDao;
import logic.dao.UserDao;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.graphiccontrollers.Listener;

public class DBChatController implements ChatController{
	private String username;
	private PrintWriter writer;
	protected Logger logger = Logger.getLogger(UserDao.class.getName());
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
	
	public DBChatController(GraphicControllerChat reference) {
		chatDao = new ChatDao();
        //Listener.picture = picture;
		this.graphic = reference;
		chatDao.setStatus("online");
		this.logUser = new LoggedUser();
		this.username = logUser.getUserName();
	}

	public List<Message> openChat(String receiver) {
		chat = chatDao.getSavedMsg("prova", receiver);
		status = chatDao.getStatus(receiver);
		if (status.equals("online")) {
			this.hostname = "localhost";
			this.port = 2400;
			execute();
			System.out.println("socket attivo");
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
	public void createChat(String type) {
		
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
	public void sendMessage(String msg) {
	        Message createMessage = new Message();
	        createMessage.setName(username);
	        createMessage.setMsg(msg);
	        //createMessage.setPicture(picture);
	        chatDao.saveMessage(createMessage);
	        if (status.equals("online")) {
		    	try {
		    		Listener.send(msg);
		        } catch (IOException ex) {
		        	logger.log(Level.SEVERE, "Error getting output stream: " + ex.getMessage());
		            ex.printStackTrace();
		        }
	        }
	        else {
	        	graphic.addToChat(createMessage);
	        }
	}
	
	public void addMessage(Message message) {
		graphic.addToChat(message);
	}
	
	public void addServerMessage(Message message) {
		graphic.addAsServer(message);
	}
}

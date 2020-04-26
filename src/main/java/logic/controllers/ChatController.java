package logic.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.LoggedUser;
import logic.dao.ChatDao;
import logic.dao.GroupDao;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.ServerDownException;
import logic.model.GroupModel;
import logic.model.Message;
import logic.model.User;
import logic.model.UserModel;

public class ChatController {
	private String username;
	private ChatDao chatDao;
	private GroupDao groupDao;
	private ControllerFacade facade;
	private List<User> users;
	private Listener listener;
	private LoggedUser logUser;
	protected Logger logger = Logger.getLogger("WIG");
	private static final String ONLINE = "online";
	private boolean alreadyActive = false;
	private List<GroupModel> grpModel;
	private User myUserModel;
	private MessageFactory factory;
	
	public ChatController(ControllerFacade reference) {
		chatDao = new ChatDao();
		groupDao = new GroupDao();
		this.facade = reference;
		this.logUser = new LoggedUser();
		this.username = logUser.getUserName();
		this.factory = new MessageFactory();
		myUserModel = new User();
		myUserModel.setName(username);
		myUserModel.setStatus(ONLINE);
		chatDao.setStatus(myUserModel);
		grpModel = new ArrayList<>();
	}
	
	public ChatController() {
		chatDao = new ChatDao();
		factory = new MessageFactory();
	}
	
	public void modificateMyStatus(String status) {
		myUserModel.setStatus(status);
		chatDao.setStatus(myUserModel);
	}

	public List<Message> openChat(String receiver, ChatType type) {
		return factory.openChat(username, receiver, type);
	}
	
	public List<User> getUsers() {
		users = chatDao.getUsersQuery(username);
		return users;
	}
	
	public void updateUsersStatus() {
		users = chatDao.getUsersQuery(username);
		facade.updateUserList(users);
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
	
	public void notConnected() {
		alreadyActive = false;
	}
	
	public void connected() {
		alreadyActive = true;
	}
	
	public void execute(String groupNameOrReceiver, ChatType type) throws ServerDownException {
		connected();
		String hostname = "localhost";
		int port = 2400;
		logger.info("socket attivo");
		Semaphore semaphore = new Semaphore(1);
		listener = new Listener(hostname, port, username, this, groupNameOrReceiver, type, semaphore);
	    Thread x = new Thread(listener);
	    x.start();
	    try {
			semaphore.acquire(2);
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE, ()-> "Semaphore(2) not acquirable: " + e.getMessage());
			Thread.currentThread().interrupt();
		}
	    if (!alreadyActive) {
			throw new ServerDownException(username);
		}	   
	}

	public void createChat(String renter) {
		logUser = new LoggedUser();
		factory.createChat(ChatType.PRIVATE, logUser.getUserName(), renter);
		
	}

	public void sendMessage(String msg, String receiver) {
		factory.saveMessage(username, ChatType.PRIVATE, msg, receiver);
		if (alreadyActive) {
			try {
				listener.send(msg);
			} catch (IOException ex) {
				logger.log(Level.SEVERE, ()-> "Error getting output stream: " + ex.getMessage());
			}
		}	        
	}
	
	public void addMessage(Message message) {
		facade.addToChat(message);
	}
	
	public void addServerMessage(Message message) {
		facade.addAsServer(message);
	}
	
	public void createGroup(String groupName, List<String> groupList) throws GroupNameTakenException {	
		GroupModel grpMod = new GroupModel();
		grpMod.setAll(this.logUser.getUserName(), groupName, null);
		groupDao.saveUserGroup(grpMod);
		
		UserModel userData = new UserModel();
		for (String user : groupList) {	
			userData.setUserName(user);
			groupDao.insertParticipant(grpMod, userData);
		}
	}
	
	public List<String> getGroups() {
		UserModel usrMod = new UserModel();
		usrMod.setUserName(username);
		groupDao.getUserGroups(grpModel, usrMod);
		groupDao.getPartGroups(grpModel, usrMod);
		List<String> groupNames = new ArrayList<>();
		for (GroupModel group : grpModel) {
			groupNames.add(group.getDescription());
		}
		return groupNames;
	}
	
	public User getUser(String user) {
		return chatDao.getUser(user);
	}
}

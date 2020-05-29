package logic.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.LoggedUser;
import logic.beans.MessageBean;
import logic.beans.UserChatBean;
import logic.beans.UserDataBean;
import logic.dao.ChatDao;
import logic.dao.GroupDao;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;
import logic.exceptions.ServerDownException;
import logic.model.GroupModel;
import logic.model.Message;
import logic.model.UserChatModel;
import logic.model.UserModel;

public class ChatController {
	private String username;
	private ChatDao chatDao;
	private GroupDao groupDao;
	private ControllerFacade facade;
	private List<UserChatModel> users;
	private Listener listener;
	protected Logger logger = Logger.getLogger("WIG");
	private static final String ONLINE = "online";
	private boolean alreadyActive = false;
	private List<GroupModel> grpModel;
	private UserChatModel myUserModel;
	private MessageFactory factory;
	
	public ChatController(ControllerFacade reference) {
		chatDao = new ChatDao();
		groupDao = new GroupDao();
		this.facade = reference;
		this.username = LoggedUser.getUserName();
		this.factory = new MessageFactory();
		myUserModel = new UserChatModel();
		myUserModel.setName(username);
		myUserModel.setStatus(ONLINE);
		myUserModel.saveStatus();
		grpModel = new ArrayList<>();
	}
	
	public ChatController() {
		chatDao = new ChatDao();
		this.username = LoggedUser.getUserName();
		factory = new MessageFactory();
	}
	
	public void modificateMyStatus(String status) {
		myUserModel.setStatus(status);
		myUserModel.saveStatus();
	}

	public List<MessageBean> openChat(String receiver, ChatType type) {		
		List<Message> messages = factory.openChat(username, receiver, type);
		List<MessageBean> beanMessages = new ArrayList<>();
		for (Message message : messages) {
			MessageBean newMessage = new MessageBean(message);
			beanMessages.add(newMessage);
		}
		return beanMessages;
	}
	
	public List<UserChatBean> getUsers() {
		users = chatDao.getUsersQuery(username);
		List<UserChatBean> usersBean = new ArrayList<>();
		for (UserChatModel user : users) {
			UserChatBean userBean = new UserChatBean(user);			
			usersBean.add(userBean);
		}
		return usersBean;
	}
	
	public void updateUsersStatus() {
		users = chatDao.getUsersQuery(username);
		List<UserChatBean> usersBean = new ArrayList<>();
		for (UserChatModel user : users) {
			UserChatBean userBean = new UserChatBean(user);			
			usersBean.add(userBean);
		}
		facade.updateUserList(usersBean);
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
		factory.createChat(ChatType.PRIVATE, LoggedUser.getUserName(), renter);
	}

	public void sendMessage(MessageBean message) {
		factory.saveMessage(username, ChatType.PRIVATE, message.getMsg(), message.getName());
		if (alreadyActive) {
			try {
				listener.send(message.getMsg());
			} catch (IOException ex) {
				logger.log(Level.SEVERE, ()-> "Error getting output stream: " + ex.getMessage());
			}
		}	        
	}
	
	public void addMessage(Message message) {
		MessageBean msg = new MessageBean(message);
		facade.addToChat(msg);
	}
	
	public void addServerMessage(Message message) {
		MessageBean msg = new MessageBean(message);
		facade.addAsServer(msg);
	}
	
	public void createGroup(String groupName, List<String> groupList) throws GroupNameTakenException {	
		GroupModel grpMod = new GroupModel();
		grpMod.setAll(LoggedUser.getUserName(), groupName, null);
		groupDao.saveUserGroup(grpMod);
		
		UserModel userData = new UserModel();
		UserDataBean dBean = new UserDataBean();
		for (String user : groupList) {
			try {
				dBean.setUserName(user);
			} catch (LengthFieldException | NullValueException e) {
				logger.log(Level.SEVERE, ()-> "Error getting output stream: " + e.getMessage());
			}
			userData.setUsrNameByBean(dBean);
			groupDao.insertParticipant(grpMod, userData);
		}
	}
	
	public List<String> getGroups() {
		UserModel usrMod = new UserModel();
		UserDataBean dBean = new UserDataBean(username);
		usrMod.setUsrNameByBean(dBean);
		groupDao.getUserGroups(grpModel, usrMod);
		groupDao.getPartGroups(grpModel, usrMod);
		List<String> groupNames = new ArrayList<>();
		for (GroupModel group : grpModel) {
			groupNames.add(group.getDescription());
		}
		return groupNames;
	}
	
	public UserChatBean getUser(String user) {
		UserChatModel selectedUser = chatDao.getUser(user);
		return new UserChatBean(selectedUser);
	}
}

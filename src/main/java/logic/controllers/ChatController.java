package logic.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.dao.ChatDao;
import logic.dao.GroupDao;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.GroupModel;
import logic.model.Message;
import logic.model.User;

public class ChatController {
	private String username;
	private ChatDao chatDao;
	private GroupDao groupDao;
	private GraphicControllerChat graphic;
	private ObservableList<User> users;
	private Listener listener;
	private LoggedUser logUser;
	protected Logger logger = Logger.getLogger("WIG");
	private static final String ONLINE = "online";
	private boolean alreadyActive = false;
	private List<GroupModel> grpModel;
	
	public ChatController(GraphicControllerChat reference) {
		chatDao = new ChatDao();
		groupDao = new GroupDao();
		this.graphic = reference;
		this.logUser = new LoggedUser();
		this.username = logUser.getUserName();
		chatDao.setStatus(username, ONLINE);
		grpModel = new ArrayList<>();
	}
	
	public ChatController() {
		chatDao = new ChatDao();
	}
	
	public void modificateStatus(String status) {
		chatDao.setStatus(username, status);
	}

	public List<Message> openChat(String receiver, ChatType type) {
		if (type == ChatType.PRIVATE) {
			return chatDao.getSavedMsg(username, receiver);
		}
		else {
			return chatDao.getGroupMsg(receiver);
		}
	}
	
	public List<User> getUsers() {
		users = chatDao.getUsersQuery(username);
		return users;
	}
	
	public void updateUsersStatus() {
		users = chatDao.getUsersQuery(username);
		graphic.updateUserList(users);
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
	
	public void execute(String groupNameOrReceiver, ChatType type) {
		alreadyActive = true;
		String hostname = "localhost";
		int port = 2400;
		logger.info("socket attivo");
		listener = new Listener(hostname, port, username, this, groupNameOrReceiver, type);
	    Thread x = new Thread(listener);
	    x.start();
	}

	public void createChat(String renter) {
		logUser = new LoggedUser();
		chatDao.createNewChat(logUser.getUserName(), renter);
	}

	public void sendMessage(String msg, String receiver) {
		Message createMessage = new Message();
	    createMessage.setName(username);
	    createMessage.setMsg(msg);
	    chatDao.saveMessage(createMessage, receiver);
	    try {
	    	listener.send(msg);
	    } catch (IOException ex) {
		    logger.log(Level.SEVERE, ()-> "Error getting output stream: " + ex.getMessage());
	    }
	        
	}
	
	public void addMessage(Message message) {
		graphic.addToChat(message);
	}
	
	public void addServerMessage(Message message) {
		graphic.addAsServer(message);
	}
	
	public void createGroup(String groupName, List<String> groupList) {
	
		GroupBean grpBean = new GroupBean();
		grpBean.setGroupOwner(username);
		grpBean.setGroupTitle(groupName);
		groupDao.saveUserGroup(grpBean);
		
		UserDataBean dataBean = new UserDataBean();
		for (String user : groupList) {	
			dataBean.setUserName(user);
			groupDao.insertParticipant(grpBean, dataBean);
		}
	}
	
	public List<String> getGroups() {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(username);
		groupDao.getUserGroups(grpModel, dataBean);
		groupDao.getPartGroups(grpModel, dataBean);
		List<String> groupNames = new ArrayList<>();
		for (GroupModel group : grpModel) {
			groupNames.add(group.getDescription());
		}
		return groupNames;
	}
}

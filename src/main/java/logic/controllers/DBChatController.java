package logic.controllers;

import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.messages.Message;
import com.messages.MessageType;
import com.messages.Status;

import logic.LoggedUser;
import logic.ReadThread;
import logic.dao.ChatDao;
import logic.dao.UserDao;
import logic.graphiccontrollers.ChatControllerCopy;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Chat;

public class DBChatController implements ChatController{
	private String username;
	private PrintWriter writer;
	protected Logger logger = Logger.getLogger(UserDao.class.getName());
	private ChatDao chatDao;
	private ArrayList<Message> chat;
	private static String picture;
	private ChatControllerCopy graphic;
	
	public DBChatController(ChatControllerCopy reference, LoggedUser loggedUser) {
		chatDao = new ChatDao();
		this.username = "ciao"; //loggedUser.getUserName();;
        //Listener.picture = picture;
		this.graphic = reference;
	}

	public ArrayList<Message> getChat() {
		chat = chatDao.queryDB("prova", "pippo");
		return chat;
	}

	public String getUsername() {
		return this.username;
	}
		

	@Override
	public void createChat(String type) {
		
	}


	@Override
	public void notificateMessage() {
		// TODO Auto-generated method stub
		
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
	        graphic.addToChat(createMessage);
	        chatDao.saveMessage(createMessage);
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void openChat() {
		// TODO Auto-generated method stub
		
	}

}

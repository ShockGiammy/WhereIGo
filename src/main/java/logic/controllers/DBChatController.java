package logic.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.ReadThread;
import logic.dao.ChatDao;
import logic.dao.UserDao;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Chat;

public class DBChatController implements ChatController{
	private String sender;
	private String receiver;
	private PrintWriter writer;
	protected Logger logger = Logger.getLogger(UserDao.class.getName());
	private ChatDao chatDao;
	private Chat chat;

	public DBChatController() {
		chatDao = new ChatDao();
	}

	public Chat getChat() {
		chat = chatDao.queryDB("ciao", "ciao2");
		return chat;
	}

	public String getSender() {
		return this.sender;
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
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
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

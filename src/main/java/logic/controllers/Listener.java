package logic.controllers;

import logic.model.Message;
import logic.model.MessageType;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements Runnable{

    private static final String HASCONNECTED = "has connected";

    private Socket socket;
    private String hostname;
    private int port;
    private String username;
    private String usersGroup;
    private ObjectOutputStream oos;
    private ObjectInputStream input;
    protected Logger logger = Logger.getLogger("WIG");
	private ChatController controller;
	private int myConnection = 0;
	private boolean close = false;
	private ChatType chatType;

    public Listener(String hostname, int port, String username, ChatController controller, String usersGroup, ChatType type) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.controller = controller;
        this.usersGroup = usersGroup;
        this.chatType = type;
    }

    public void run() {
        try {
        	socket = new Socket(hostname, port);
            OutputStream outputStream = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            input = new ObjectInputStream(is);
            oos = new ObjectOutputStream(outputStream);
        		
        }
	    catch (UnknownHostException ex) {
	    	logger.log(Level.FINE, ()-> "Server not found: " + ex.getMessage());
	    }
        catch (IOException e) {
        	logger.info("Could not Connect");
        	controller.notConnected();
        	close = true;
        }
        if (!close) {
        	logger.info("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        	try {
        		connect();
        		logger.info("Sockets in and out ready!");
        		while (socket.isConnected()) {
        			Message message = null;
        			message = (Message) input.readObject();

        			if (message != null) {
        				manageMessage(message);
        			}
        		}
        	}
        	catch (IOException | ClassNotFoundException e) {
        		logger.info("Connection closed!");       
        	}
        }
    }
    
    public void manageMessage(Message message) {
    	logger.info("Message recieved:" + message.getMsg() + " MessageType:" + message.getType() + " Name:" + message.getName());
        switch (message.getType()) {
            case USER:
                controller.addMessage(message);
                break;
            case SERVER:
            	if (!message.getName().equals(username)) {
            		controller.addServerMessage(message);
            		controller.updateUsersStatus();
            	}
                break;
            case CONNECTED:
            	if (myConnection == 0) {
            		this.usersGroup = message.getUsersGroup();
            		controller.addServerMessage(message);
            		myConnection++;
            	}
            	break;                      		
            case DISCONNECTED:
            	controller.addServerMessage(message);
            	controller.updateUsersStatus();
            	break;
        }
    }

    public void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.USER);
        createMessage.setMsg(msg);
        createMessage.setUsersGroup(usersGroup);
        oos.writeObject(createMessage);
        oos.flush();
    }
    
    /* This method is used to send a connecting message */
    public void connect() throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.CONNECTED);
        createMessage.setChatType(chatType);
        createMessage.setMsg(HASCONNECTED);
        createMessage.setUsersGroup(usersGroup);
        oos.writeObject(createMessage);
    }
    
    public void closeConnection() throws IOException{
    	logger.info("closeConnection method entered");  
    	Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.DISCONNECTED);
        oos.writeObject(createMessage);
    }
}

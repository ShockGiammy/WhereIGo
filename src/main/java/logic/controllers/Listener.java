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

    //private static String picture;
    private Socket socket;
    public String hostname;
    public int port;
    public String username;
    private static ObjectOutputStream oos;
    private ObjectInputStream input;
    protected Logger logger = Logger.getLogger("WIG");
	private DBChatController controller;
	private int myConnection = 0;

    public Listener(String hostname, int port, String username, String picture, DBChatController controller) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        //this.picture = picture;
        this.controller = controller;
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
        }
        logger.info("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            connect();
            logger.info("Sockets in and out ready!");
            while (socket.isConnected()) {
            	Message message = null;
                message = (Message) input.readObject();

                if (message != null) {
                    logger.info("Message recieved:" + message.getMsg() + " MessageType:" + message.getType() + " Name:" + message.getName());
                    switch (message.getType()) {
                        case USER:
                            controller.addMessage(message);
                            break;
                        case SERVER:
                        	if (!message.getName().equals(username)) {
                        		controller.addServerMessage(message);
                        	}
                            break;
                        case CONNECTED:
                        	if (myConnection == 0) {
                        		controller.addServerMessage(message);
                        		myConnection++;
                        	}
                        	break;                      		
                        case DISCONNECTED:
                        	break;
                    }
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
        	logger.info("Connection closed!");         
        }
    }

    public void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.USER);
        createMessage.setMsg(msg);
        //createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }
    
    /* This method is used to send a connecting message */
    public void connect() throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.CONNECTED);
        createMessage.setMsg(HASCONNECTED);
        //createMessage.setPicture(picture);
        oos.writeObject(createMessage);
    }
}

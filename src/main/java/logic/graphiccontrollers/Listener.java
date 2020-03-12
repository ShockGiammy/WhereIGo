package logic.graphiccontrollers;

import logic.controllers.DBChatController;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.messages.Message;
import com.messages.MessageType;

public class Listener implements Runnable{

    private static final String HASCONNECTED = "has connected";

    private static String picture;
    private Socket socket;
    public String hostname;
    public int port;
    public static String username;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;
    protected Logger logger = Logger.getLogger("WIG");
	private DBChatController controller;

    public Listener(String hostname, int port, String username, String picture, DBChatController controller) {
        this.hostname = hostname;
        this.port = port;
        Listener.username = username;
        Listener.picture = picture;
        this.controller = controller;
    }

    public void run() {
        try {
            socket = new Socket(hostname, port);
            
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            is = socket.getInputStream();
            input = new ObjectInputStream(is);
        }
	    catch (UnknownHostException ex) {
	    	logger.log(Level.FINE, "Server not found: " + ex.getMessage());
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
                    logger.info("Message recieved:" + message.getMsg() + " MessageType:" + message.getType() + "Name:" + message.getName());
                    switch (message.getType()) {
                        case USER:
                            controller.addMessage(message);
                            break;
                        case NOTIFICATION:
                            //controller.notificateMessage(message);
                            break;
                        case SERVER:
                            controller.addServerMessage(message);
                            break;
                        case CONNECTED:
                            controller.addServerMessage(message);
                            break;
                        /*case DISCONNECTED:
                            controller.setUserList(message);
                            break;
                            */
                    }
                }
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
         
        }
    }

    public static void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.USER);
        createMessage.setMsg(msg);
        //createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }
    
    /* This method is used to send a connecting message */
    public static void connect() throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.CONNECTED);
        createMessage.setMsg(HASCONNECTED);
        //createMessage.setPicture(picture);
        oos.writeObject(createMessage);
    }

}

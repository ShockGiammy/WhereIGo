package logic.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.LoggedUser;
import logic.ReadThread;
import logic.dao.UserDao;
import logic.graphiccontrollers.GraphicControllerChat;

public class SingleChat implements Chat{
	private String hostname;
	private int port;
	private String userName;
	private Socket socket;
	private PrintWriter writer;
	protected Logger logger = Logger.getLogger(UserDao.class.getName());
	private GraphicControllerChat reference;
	 
	public SingleChat(String hostname, int port) {
	   this.hostname = hostname;
	   this.port = port;
   }
	 
	public void execute() {
    try {
    	Socket socket = new Socket(hostname, port);
    	try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
        	logger.log(Level.SEVERE, "Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
	 
	    System.out.println("Connected to the chat server");

	    new ReadThread(socket, this).start();
	    this.setUserName(userName);
	 
	    } catch (UnknownHostException ex) {
	    	logger.log(Level.FINE, "Server not found: " + ex.getMessage());
	    } catch (IOException ex) {
	        logger.log(Level.FINE, "I/O Error: " + ex.getMessage());
	    }
	 
	}
	 
	public void setUserName(String userName) {
	    this.userName = userName;
	}
	 
	public String getUserName() {
	    return this.userName;
	}
	
	    
	@Override
	public void createChat(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(String message) {

		writer.println(message);
	}

	@Override
	public void saveGraphic(GraphicControllerChat chat) {
		this.reference = chat;
	}
	@Override
	public void openChat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificateMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptChat() {
		// TODO Auto-generated method stub
		
	}

	public void display(String text) {
		reference.display(text);
		
	}

}

package logic;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.dao.UserDao;
import logic.model.SingleChat;

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private SingleChat client;
    protected Logger logger = Logger.getLogger(UserDao.class.getName());
 
    public ReadThread(Socket socket, SingleChat client) {
        this.socket = socket;
        this.client = client;
 
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
        	logger.log(Level.SEVERE, "Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println(response);
            } catch (IOException ex) {
            	logger.log(Level.SEVERE, "Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
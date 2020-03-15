package logic.chatserver;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import logic.DuplicateUsernameException;
import logic.model.Message;
import logic.model.MessageType;
import logic.model.User;

public class Server {

    /* Setting up variables */
    private static final int PORT = 2400;
    private static final HashMap<String, User> names = new HashMap<>();
    private static HashSet<ObjectOutputStream> writers = new HashSet<>();
    private static ArrayList<User> users = new ArrayList<>();
    protected static Logger logger = Logger.getLogger("WIG");
    private static final String EXCEPTION = "Got an exception!";
    private static final String REMOVED = " has been removed!";
    private static int connections = 0;
    private static final int MAX_CONNECTONS = 1000;

    public static void main(String[] args) throws Exception {
        logger.info("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);

        try {
            while (true) {
                new Handler(listener.accept()).start();
                connections++;
                if (connections == MAX_CONNECTONS) {
                	break;
                }
            }
        } catch (Exception e) {
        	logger.log(Level.SEVERE, EXCEPTION);
			logger.log(Level.SEVERE, e.getMessage());
        } finally {
            listener.close();
        }
    }


    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private Logger logger = Logger.getLogger("WIG");
        private User user;
        private ObjectInputStream input;
        private OutputStream os;
        private ObjectOutputStream output;
        private InputStream is;

        public Handler(Socket socket){
            this.socket = socket;
        }
        
        @Override
        public void run() {
            logger.info("Attempting to connect a user...");
            
            try {
                is = socket.getInputStream();
                os = socket.getOutputStream();
                output = new ObjectOutputStream(os);
            	input = new ObjectInputStream(is);

                Message firstMessage = (Message) input.readObject();
                checkDuplicateUsername(firstMessage);
                writers.add(output);
                sendNotification(firstMessage);
                addToList();

                while (socket.isConnected()) {
                    Message inputmsg = (Message) input.readObject();
                    if (inputmsg != null) {
                        logger.info(inputmsg.getType() + " - " + inputmsg.getName() + ": " + inputmsg.getMsg());
                        switch (inputmsg.getType()) {
                            case USER:
                                write(inputmsg);
                                break;
                            case CONNECTED:
                                addToList();
                                break;
                            case SERVER:
                            	break;
                            case DISCONNECTED:
                            	break;
                        }
                    }
                }
            } catch (SocketException socketException) {
                logger.log(Level.SEVERE, "Socket Exception for user " + name);
            } catch (DuplicateUsernameException duplicateException){
                logger.log(Level.SEVERE, "Duplicate Username : " + name);
            } catch (Exception e){
                logger.log(Level.SEVERE, "Exception in run() method for user: " + name, e);
            } finally {
                closeConnections();
            }
        }

        private synchronized void checkDuplicateUsername(Message firstMessage) throws DuplicateUsernameException {
            logger.info(firstMessage.getName() + " is trying to connect");
            if (!names.containsKey(firstMessage.getName())) {
                this.name = firstMessage.getName();
                user = new User();
                user.setName(firstMessage.getName());
                //user.setPicture(firstMessage.getPicture());

                users.add(user);
                names.put(name, user);

                logger.info(name + " has been added to the list");
            } else {
                logger.log(Level.SEVERE, firstMessage.getName() + " is already connected");
                throw new DuplicateUsernameException(firstMessage.getName() + " is already connected");
            }
        }

        private Message sendNotification(Message firstMessage) throws IOException {
            Message msg = new Message();
            msg.setMsg(firstMessage.getName()+" has joined the chat.");
            msg.setType(MessageType.SERVER);
            msg.setName(firstMessage.getName());
            //msg.setPicture(firstMessage.getPicture());
            write(msg);
            return msg;
        }


        private Message removeFromList() throws IOException {
            logger.info("removeFromList() method Enter");
            Message msg = new Message();
            msg.setMsg("has left the chat.");
            //msg.setType(MessageType.DISCONNECTED);
            msg.setName("SERVER");
            msg.setUserlist(names);
            write(msg);
            logger.info("removeFromList() method Exit");
            return msg;
        }

        /*
         * For displaying that a user has joined the server
         */
        private Message addToList() throws IOException {
            Message msg = new Message();
            msg.setMsg("Welcome, You have now joined the server! Enjoy chatting!");
            msg.setType(MessageType.CONNECTED);
            msg.setName("SERVER");
            write(msg);
            return msg;
        }

        /*
         * Creates and sends a Message type to the listeners.
         */
        private static void write(Message msg) throws IOException {
            for (ObjectOutputStream writer : writers) {
                msg.setUserlist(names);
                msg.setUsers(users);
                msg.setOnlineCount(names.size());
                writer.writeObject(msg);
                writer.reset();
            }
        }

        /*
         * Once a user has been disconnected, we close the open connections and remove the writers
         */
        private synchronized void closeConnections()  {
            logger.info("closeConnections() method Enter");
            connections--;
            logger.info("HashMap names:" + names.size() + " writers:" + writers.size() + " usersList size:" + users.size());
            if (name != null) {
                names.remove(name);
                logger.info("User: " + name + REMOVED);
            }
            if (user != null){
                users.remove(user);
                logger.info("User object: " + user + REMOVED);
            }
            if (output != null){
                writers.remove(output);
                logger.info("Writer object: " + user + REMOVED);
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                	logger.log(Level.SEVERE, EXCEPTION);
        			logger.log(Level.SEVERE, e.getMessage());
                }
            }
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                	logger.log(Level.SEVERE, EXCEPTION);
        			logger.log(Level.SEVERE, e.getMessage());
                }
            }
            if (input != null){
                try {
                    input.close();
                } catch (IOException e) {
                	logger.log(Level.SEVERE, EXCEPTION);
        			logger.log(Level.SEVERE, e.getMessage());
                }
            }
            try {
                removeFromList();
            } catch (Exception e) {
            	logger.log(Level.SEVERE, EXCEPTION);
    			logger.log(Level.SEVERE, e.getMessage());
            }
            logger.info("HashMap names:" + names.size() + " writers:" + writers.size() + " usersList size:" + users.size());
            logger.info("closeConnections() method Exit");
        }
    }
}

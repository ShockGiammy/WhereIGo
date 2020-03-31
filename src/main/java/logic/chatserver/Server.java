package logic.chatserver;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import logic.controllers.ChatType;
import logic.exceptions.DuplicateUsernameException;
import logic.model.Message;
import logic.model.MessageType;
import logic.model.SecureObjectInputStream;
import logic.model.User;

public class Server{

    /* Setting up variables */
	private static final int PORT = 2400;
    private final HashMap<String, User> names = new HashMap<>();
    private HashMap<String, HashSet<ObjectOutputStream>> listOfLists = new HashMap<>();
    protected static Logger logger = Logger.getLogger("WIG");
    private static final String EXCEPTION = "Got an exception!";
    private static final String REMOVED = " has been removed!";
    private int connections = 0;
    private static final int MAX_CONNECTONS = 1000;

    public static void main(String[] args){
        logger.info("The chat server is running.");
        Server server = new Server();
        server.startServer();
    }
        
     public void startServer() {
    	 try ( 
    			 ServerSocket listener = new ServerSocket(PORT);
    			 ) {   
    		 while (true) {
        		Socket socket = listener.accept();
        		Handler handler = new Handler(socket);
        		handler.start();
        		connections++;
        		if (connections == MAX_CONNECTONS) {
        			break;
                }
            }     		 
    	}catch (Exception e) {
            logger.log(Level.SEVERE, EXCEPTION);
     		logger.log(Level.SEVERE, e.getMessage());
    	}
    }


    private class Handler extends Thread {
        private String name;
        private Socket socket;
        private Logger logger = Logger.getLogger("WIG");
        private User user;
        private ObjectOutputStream output;
        private SecureObjectInputStream input;
        private String usersGroup;

        public Handler(Socket socket){
            this.socket = socket;
        }
        
        @Override
        public void run() {
            logger.info("Attempting to connect a user...");
            try {
				output = new ObjectOutputStream(socket.getOutputStream());
        		input = new SecureObjectInputStream(socket.getInputStream());
			} catch (Exception e) {
                logger.log(Level.SEVERE, e, () -> "Exception in output/input");
			}
                      
            try {
                Message firstMessage = (Message) input.readObject();
                
                checkGroupName(firstMessage);
                checkDuplicateUsername(firstMessage);
                               
                addToList();
                sendNotification(firstMessage);

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
                            case DISCONNECTED:
                            	closeConnections(inputmsg.getName());
                            	break;
                            default:
                            	break;
                        }
                    }
                }
            } catch (SocketException socketException) {
                logger.log(Level.SEVERE, () -> "Socket Exception for user " + name);
                logger.log(Level.SEVERE, socketException.getMessage());
            } catch (DuplicateUsernameException duplicateException) {
                logger.log(Level.SEVERE, () -> "Duplicate Username : " + name);
            } catch (IOException e) {
            	logger.log(Level.SEVERE, () -> "Problem with IO deserialization" + e.getMessage());
            } catch (Exception e) {
                logger.log(Level.SEVERE, e, () -> "Exception in run() method for user: " + name);
                
            } finally {
                closeConnections(null);
            }
        }

        private synchronized void checkDuplicateUsername(Message firstMessage) throws DuplicateUsernameException {
            logger.info(firstMessage.getName() + " is trying to connect");
            if (!names.containsKey(firstMessage.getName())) {
                this.name = firstMessage.getName();
                user = new User();
                user.setName(firstMessage.getName());

                names.put(name, user);

                logger.info(() -> name + " has been added to the list");
            } else {
                logger.log(Level.SEVERE, () -> firstMessage.getName() + " is already connected");
                throw new DuplicateUsernameException(firstMessage.getName() + " is already connected");
            }
        }
        
        public void checkGroupName(Message msg) {
        	if (msg.getChatType() == ChatType.PRIVATE) {
        		if (listOfLists.containsKey(msg.getGroupOrReceiver()+msg.getName())) {
        			this.usersGroup = msg.getGroupOrReceiver()+msg.getName();
        			listOfLists.get(usersGroup).add(output);
        			logger.info(() -> usersGroup + " chat already existed");
        		} 
        		else if (listOfLists.containsKey(msg.getName()+msg.getGroupOrReceiver())) {
        			this.usersGroup = msg.getName()+msg.getGroupOrReceiver();
        			listOfLists.get(usersGroup).add(output);
        			logger.info(() -> usersGroup + " chat already existed");
        		}
        		else {
        			HashSet<ObjectOutputStream> writers = new HashSet<>();
        			writers.add(output);
        			this.usersGroup = msg.getName()+msg.getGroupOrReceiver();
        			listOfLists.put(usersGroup, writers);
        			logger.info(() -> usersGroup + " chat created");
        		}
        	}
        	else {
        		this.usersGroup = msg.getGroupOrReceiver();
        		if (listOfLists.containsKey(usersGroup)) {            		
            		listOfLists.get(usersGroup).add(output);         
                    logger.info(() -> usersGroup + " group already existed");
        		}
        		else {
        			HashSet<ObjectOutputStream> writers = new HashSet<>();
        			writers.add(output);
        			listOfLists.put(usersGroup, writers);
        			logger.info(() -> usersGroup + " group created");
        		}
        	}
        }

        private Message sendNotification(Message firstMessage) throws IOException {
        	logger.info("sendNotification() method Enter");
            Message msg = new Message();
            msg.setMsg(firstMessage.getName()+" has joined the chat.");
            msg.setType(MessageType.SERVER);
            msg.setName(firstMessage.getName());
            msg.setGroupOrReceiver(usersGroup);
            write(msg);
            logger.info("sendNotification() method Exit");
            return msg;
        }


        private Message removeFromList(String userToRemove) throws IOException {
            logger.info("removeFromList() method Enter");
            Message msg = new Message();
            msg.setMsg(userToRemove + "has left the chat.");
            msg.setType(MessageType.DISCONNECTED);
            msg.setName("SERVER");
            msg.setGroupOrReceiver(usersGroup);
            write(msg);
            logger.info("removeFromList() method Exit");
            return msg;
        }

        /*
         * For displaying that a user has joined the server
         */
        private Message addToList() throws IOException {
        	logger.info("addToList() method Enter");
            Message msg = new Message();
            msg.setMsg("Welcome, You have now joined the server! Enjoy chatting!");
            msg.setType(MessageType.CONNECTED);
            msg.setName("SERVER");
            msg.setGroupOrReceiver(usersGroup);
            write(msg);
            logger.info("addToList() method Exit");
            return msg;
        }

        /*
         * Creates and sends a Message type to the listeners.
         */
        private void write(Message msg) throws IOException {
        	for(Entry<String, HashSet<ObjectOutputStream>> writers : listOfLists.entrySet()) {
        		if (writers.getKey().equals(msg.getGroupOrReceiver())) {
        			for (ObjectOutputStream writer : writers.getValue()) {
        			writer.writeObject(msg);
        			writer.reset();
        			}
        		}
            }
        }

        /*
         * Once a user has been disconnected, we close the open connections and remove the writers
         */
		private void closeConnections(String userToRemove)  {
            logger.info("closeConnections() method Enter");
            connections--;
            if (name != null) {
                names.remove(name);
                logger.info(() -> "User: " + name + REMOVED);
            }
            if ((output != null) && (listOfLists.containsKey(usersGroup))) {
            	listOfLists.get(usersGroup).remove(output);
            	logger.info(() -> "Writer object: " + user + REMOVED);
            }
            if (input != null){
                try {
                    input.close();
                } catch (IOException e) {
                	logger.log(Level.SEVERE, EXCEPTION);
        			logger.log(Level.SEVERE, e.getMessage());
                }
            }
            if (userToRemove != null) {
            	try {
            		removeFromList(userToRemove);
            	} catch (IOException e) {
            		logger.log(Level.SEVERE, EXCEPTION);
        			logger.log(Level.SEVERE, e.getMessage());
				}
			}
            logger.info("closeConnections() method Exit");
        }
    }
}

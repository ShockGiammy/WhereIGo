package logic.graphiccontrollers;
/*
import com.messages.Message;
import com.messages.MessageType;
import com.messages.Status;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

import static com.messages.MessageType.CONNECTED;

public class Listener implements Runnable{

    private static final String HASCONNECTED = "has connected";

    private static String picture;
    private Socket socket;
    public String hostname;
    public int port;
    public static String username;
    public ChatControllerCopy controller;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;
    protected Logger logger = Logger.getLogger("WIG");

    public Listener(String hostname, int port, String username, String picture, ChatControllerCopy controller) {
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
        } catch (IOException e) {
      
        }
        logger.info("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            connect();
            logger.info("Sockets in and out ready!");
            while (socket.isConnected()) {
                Message message = null;
                message = (Message) input.readObject();

                if (message != null) {
              
                    switch (message.getType()) {
                        case USER:
                            controller.addToChat(message);
                            break;
                        /*case NOTIFICATION:
                            controller.newUserNotification(message);
                            break;*/
                /*        case SERVER:
                            controller.addAsServer(message);
                            break;
                            /*
                        case CONNECTED:
                            controller.setUserList(message);
                            break;
                        case DISCONNECTED:
                            controller.setUserList(message);
                            break;
                            */
       /*         }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
         
        }
    }

    /* This method is used for sending a normal Message
     * @param msg - The message which the user generates
     */
/*    public static void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.USER);
        createMessage.setStatus(Status.AWAY);
        createMessage.setMsg(msg);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }

    /* This method is used for sending a normal Message
 * @param msg - The message which the user generates
 */
/*    public static void sendStatusUpdate(Status status) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MessageType.STATUS);
        createMessage.setStatus(status);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
        oos.flush();
    }

    /* This method is used to send a connecting message */
 /*   public static void connect() throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(CONNECTED);
        createMessage.setMsg(HASCONNECTED);
        createMessage.setPicture(picture);
        oos.writeObject(createMessage);
    }

}*/

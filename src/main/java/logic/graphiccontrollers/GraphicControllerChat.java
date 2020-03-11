package logic.graphiccontrollers;


import com.messages.Message;
import com.messages.User;
import com.messages.bubble.BubbleSpec;
import com.messages.bubble.BubbledLabel;
//import com.traynotifications.animations.AnimationType;
//import com.traynotifications.notification.TrayNotification;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.LoggedUser;
import logic.controllers.ChatController;
import logic.controllers.DBChatController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GraphicControllerChat implements Initializable {

    @FXML private TextArea messageBox;
    @FXML private Label usernameLabel;
    @FXML private ListView<HBox> userList;
    @FXML private ImageView userImageView;
    @FXML private ListView<HBox> chatPane;
    @FXML BorderPane borderPane;
    @FXML private ListView groupMember;
    @FXML private Text activeChat;

    private double xOffset;
    private double yOffset;
    protected Logger logger = Logger.getLogger("WIG");
    private ChatController chatController;

    public GraphicControllerChat() {
    	chatController = new DBChatController(this);
    }
    
    public void sendButtonAction() throws IOException {
        String msg = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
        	chatController.sendMessage(msg);
            messageBox.clear();
        }
    }
    
    public void setActiveChat(String name) {
    	activeChat.setText(name);
    }

    public synchronized void addToChat(Message msg) {
        if (msg.getName().equals("ciao")) {
        	HBox yourMessage = new HBox();
        	/*
            Image image = userImageView.getImage();
            ImageView profileImage = new ImageView(image);
            profileImage.setFitHeight(32);
            profileImage.setFitWidth(32);
            */
            BubbledLabel bl6 = new BubbledLabel();
            bl6.setText(msg.getMsg());
            bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            yourMessage.setMaxWidth(chatPane.getWidth() - 20);
            yourMessage.setAlignment(Pos.TOP_RIGHT);
            bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
            yourMessage.getChildren().addAll(bl6); //, profileImage);
            Platform.runLater(new Runnable() {
                @Override public void run() {
                	chatPane.getItems().add(yourMessage);
                	//chatPane.scrollTo(yourMessage);
                }
            });
        }
        else {
        	HBox othersMessage = new HBox();
            /*
            Image image = new Image(getClass().getClassLoader().getResource("images/" + msg.getPicture().toLowerCase() + ".png").toString());
            ImageView profileImage = new ImageView(image);
            profileImage.setFitHeight(32);
            profileImage.setFitWidth(32);
            */
            BubbledLabel bl6 = new BubbledLabel();
            bl6.setText(msg.getName() + ": " + msg.getMsg());
            bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null, null)));
            bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
            othersMessage.getChildren().addAll(bl6);   //profileImage, bl6);
            Platform.runLater(new Runnable() {
                @Override public void run() {
                	chatPane.getItems().add(othersMessage);
                	//chatPane.scrollTo(yourMessage);
                }
            });
        }
    }
    /*
    public void setUsernameLabel(String username) {
        this.usernameLabel.setText(username);
    }

    public void setImageLabel() throws IOException {
        this.userImageView.setImage(new Image(getClass().getClassLoader().getResource("images/dominic.png").toString()));
    }
*/

    public void setUserList() {
    	List<User> users = chatController.getUsers();
    	for (User user : users) {
    		addToUserList(user);
    	}
    }
    
    public void selectUser() {
    	Node node = userList.getSelectionModel().getSelectedItem().getChildren().get(1);
    	String receiver = ((Text)node).getText();
    	if (!activeChat.getText().equals(receiver)) {
    		displayChat(receiver);
    		setActiveChat(receiver);
    	}
    }

    /* Displays Notification when a user joins *//*
    public void newUserNotification(Message msg) {
        Platform.runLater(() -> {
            Image profileImg = new Image(getClass().getClassLoader().getResource("images/" + msg.getPicture().toLowerCase() +".png").toString(),50,50,false,false);
            TrayNotification tray = new TrayNotification();
            tray.setTitle("A new user has joined!");
            tray.setMessage(msg.getName() + " has joined the JavaFX Chatroom!");
            tray.setRectangleFill(Paint.valueOf("#2C3E50"));
            tray.setAnimationType(AnimationType.POPUP);
            tray.setImage(profileImg);
            tray.showAndDismiss(Duration.seconds(5));
            try {
                Media hit = new Media(getClass().getClassLoader().getResource("sounds/notification.wav").toString());
                MediaPlayer mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
*/
    public void sendMethod(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    @FXML
    public void closeApplication() {
        Platform.exit();
        System.exit(0);
    }

    /* Method to display server messages */
    public synchronized void addAsServer(Message msg) {
        Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText(msg.getMsg());
                bl6.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,
                        null, null)));
                HBox x = new HBox();
                bl6.setBubbleSpec(BubbleSpec.FACE_BOTTOM);
                x.setAlignment(Pos.CENTER);
                x.getChildren().addAll(bl6);
                return x;
            }
        };
        task.setOnSucceeded(event -> {
            chatPane.getItems().add(task.getValue());
        });

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
    
    public void displayChat(String receiver) {
    	chatPane.getItems().clear();
    	List<Message> chat = chatController.openChat(receiver);
    	for (Message message : chat) {
    		addToChat(message);
    	}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUserList();
        
        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });

        /* Added to prevent the enter from adding a new line to inputMessageBox */
        messageBox.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    sendButtonAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ke.consume();
            }
        });
    }

    public synchronized void addToUserList(User user) {
    	
    	Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {

            	HBox hBox = new HBox();

            	ImageView statusImageView = new ImageView();
            	Image statusImage = new Image(getClass().getClassLoader().getResource("images/" + user.getStatus().toString().toLowerCase() + ".png").toString(), 16, 16,true,true);
            	statusImageView.setImage(statusImage);
            	
            	Text name = new Text(user.getName());
/*
            	ImageView pictureImageView = new ImageView();
            	Image image = new Image(getClass().getClassLoader().getResource("images/" + user.getPicture().toLowerCase() + ".png").toString(),50,50,true,true);
            	pictureImageView.setImage(image);
*/
            	hBox.getChildren().addAll(statusImageView, name);  //, pictureImageView, 
            	hBox.setAlignment(Pos.CENTER_LEFT);
            	hBox.setOnMouseClicked(e -> {
            		selectUser();
            	});
            	return hBox;
            }
    	};
        task.setOnSucceeded(event -> {
            	 userList.getItems().add(task.getValue());
        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
}
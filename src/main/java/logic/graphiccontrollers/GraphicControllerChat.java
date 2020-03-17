package logic.graphiccontrollers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import logic.LoggedUser;
import logic.controllers.ChatController;
import logic.controllers.DBChatController;
import logic.model.Message;
import logic.model.User;
import logic.view.Window;

import java.io.IOException;
import java.util.List;

import java.util.logging.Logger;

public class GraphicControllerChat extends Window {

	@FXML private ImageView home;
	@FXML private ImageView keys;
	@FXML private ImageView bookTravel;
	@FXML private ImageView favourite;
	@FXML private ImageView settings;
	
    @FXML private TextArea messageBox;
    @FXML private Label usernameLabel;
    @FXML private ListView<HBox> userList;
    @FXML private ImageView userImageView;
    @FXML private ListView<HBox> chatPane;
    @FXML private BorderPane borderPane;
    @FXML private ListView<HBox> groupMember;
    @FXML private Text activeChat;

    protected Logger logger = Logger.getLogger("WIG");
    private ChatController chatController;
    private String username;
    private double pading = 5.0;

    public GraphicControllerChat() {
    	chatController = new DBChatController(this);
    	LoggedUser logUser = new LoggedUser();
    	this.username = logUser.getUserName();
    }
    
    public void sendButtonAction() {
        String msg = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
        	chatController.sendMessage(msg, activeChat.getText());
            messageBox.clear();
        }
    }
    
    public void setActiveChat(String name) {
    	activeChat.setText(name);
    }

    public synchronized void addToChat(Message msg) {
        if (msg.getName().equals(username)) {
        	HBox yourMessage = new HBox();
        	
            Image image = new Image(getClass().getClassLoader().getResource("images/sarah.png").toString()); //userImageView.getImage();
            ImageView profileImage = new ImageView(image);
            profileImage.setFitHeight(32);
            profileImage.setFitWidth(32);
            
            Label bl6 = new Label();
            bl6.setText(msg.getMsg());
            bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            yourMessage.setAlignment(Pos.TOP_RIGHT);
            bl6.setPadding(new Insets(pading, pading,pading, pading));
            yourMessage.getChildren().addAll(bl6, profileImage);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	chatPane.getItems().add(yourMessage);
                	chatPane.scrollTo(yourMessage);
                }
            });
        }
        else {
        	HBox othersMessage = new HBox();
            
            Image image = new Image(getClass().getClassLoader().getResource("images/dominic.png").toString());
            ImageView profileImage = new ImageView(image);
            profileImage.setFitHeight(32);
            profileImage.setFitWidth(32);
            
            Label bl6 = new Label();
            bl6.setText(msg.getName() + ": " + msg.getMsg());
            bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null, null)));
    		bl6.setPadding(new Insets(pading, pading, pading, pading));
            othersMessage.getChildren().addAll(profileImage, bl6);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	chatPane.getItems().add(othersMessage);
                	chatPane.scrollTo(othersMessage);
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
    	Node node = userList.getSelectionModel().getSelectedItem().getChildren().get(2);
    	String receiver = ((Text)node).getText();
    	if (!activeChat.getText().equals(receiver)) {
    		displayChat(receiver);
    		setActiveChat(receiver);
    	}
    }

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
                Label bl6 = new Label();
                bl6.setText(msg.getMsg());
                bl6.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,
                        null, null)));
                HBox x = new HBox();
                x.setAlignment(Pos.CENTER);
                x.getChildren().addAll(bl6);
                return x;
            }
        };
        task.setOnSucceeded(event -> 
            chatPane.getItems().add(task.getValue()));

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
    
    public void displayChat(String receiver) {
    	chatPane.getItems().clear();
    	List<Message> chat = chatController.openChat(receiver);
    	for (Message message : chat) {
    		if (message.getMsg() != null) {
    			addToChat(message);
    		}
    	}
    }

    public void initialize() {

        setUserList();
        
        borderPane.setOnMouseReleased(event ->
            borderPane.setCursor(Cursor.DEFAULT));
    }

    public synchronized void addToUserList(User user) {
    	
    	Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {

            	HBox hBox = new HBox();

            	ImageView statusImageView = new ImageView();
            	Image statusImage = new Image(getClass().getClassLoader().getResource("images/" + user.getStatus().toLowerCase() + ".png").toString(), 16, 16,true,true);
            	statusImageView.setImage(statusImage);
            	
            	Text name = new Text(user.getName());
            	
            	ImageView pictureImageView = new ImageView();
            	Image image = new Image(getClass().getClassLoader().getResource("images/dominic.png").toString(),50,50,true,true);
            	pictureImageView.setImage(image);

            	hBox.getChildren().addAll(statusImageView, pictureImageView, name);
            	hBox.setAlignment(Pos.CENTER_LEFT);
            	hBox.setOnMouseClicked(e ->
            		selectUser());
            	return hBox;
            }
    	};
        task.setOnSucceeded(event ->
            	 userList.getItems().add(task.getValue()));
        
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
}
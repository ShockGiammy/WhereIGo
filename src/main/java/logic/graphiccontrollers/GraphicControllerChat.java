package logic.graphiccontrollers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.beans.MessageBean;
import logic.beans.UserChatBean;
import logic.controllers.ChatType;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.ServerDownException;
import logic.view.BasicGui;
import logic.view.ErrorPopup;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Logger;

public class GraphicControllerChat extends BasicGui {
	
    @FXML private TextArea messageBox;
    @FXML private Label usernameLabel;
    @FXML private ListView<HBox> userList;
    @FXML private ImageView userImageView;
    @FXML private ListView<HBox> chatPane;
    @FXML private BorderPane borderPane;
    @FXML private ListView<Text> groupMember;
    @FXML private Text activeChat;
    @FXML private Button createGroup;

    protected Logger logger = Logger.getLogger("WIG");
    private String username;
    private double pading = 5.0;
    private Image pictureImage;
    private List<UserChatBean> users;
    private List<String> namesList;
    private Image myImage;

    public GraphicControllerChat() {
    	facade.callChatController(this);
    	this.username = logUsr.getUserName();
    }
    
    public void sendButtonAction() {
        String msg = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
        	facade.sendMessage(msg, activeChat.getText());
            messageBox.clear();
        }
    }
    
    public void setActiveChat(String name) {
    	activeChat.setText(name);
    	Stage primaryStage = (Stage) borderPane.getScene().getWindow();
    	primaryStage.setOnHiding( e ->
			exitChat());
    }

    public synchronized void addToChat(MessageBean message) {
        if (message.getName().equals(username)) {
        	HBox yourMessage = new HBox();
        	
        	ImageView profileImage = new ImageView();
            profileImage.setFitHeight(32);
            profileImage.setFitWidth(32);
            profileImage.setImage(myImage);
            
            Label bl6 = new Label();
            bl6.setText(message.getMsg());
            bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            yourMessage.setAlignment(Pos.TOP_RIGHT);
            bl6.setPadding(new Insets(pading, pading,pading, pading));
            yourMessage.getChildren().addAll(bl6, profileImage);
            Platform.runLater(() -> {
                chatPane.getItems().add(yourMessage);
                chatPane.scrollTo(yourMessage);
            });
        }
        else {
        	HBox othersMessage = new HBox();
            
        	ImageView profileImage = new ImageView();
        	profileImage.setFitHeight(32);
        	profileImage.setFitWidth(32);
        	profileImage.setImage(pictureImage);
        	
            Label bl6 = new Label();
            bl6.setText(message.getName() + ": " + message.getMsg());
            bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null, null)));
    		bl6.setPadding(new Insets(pading, pading, pading, pading));
            othersMessage.getChildren().addAll(profileImage, bl6);
            Platform.runLater(() -> {
                chatPane.getItems().add(othersMessage);
                chatPane.scrollTo(othersMessage);
            });
        }
    }

    public void setUserList() {
    	users = facade.getUsers();
    	for (UserChatBean user : users) {
    		addToUserList(user);
    	}
    }
    
    public void setGroupList() {
    	List<String> groupNames = facade.getGroups();
    	for (String groupName : groupNames) {
    		addToGroupList(groupName);
    	}
    }
    
    public void addToGroupList(String groupName) {
    	Task<Text> task = new Task<Text>() {
            @Override
            public Text call() {

            	Text group = new Text(groupName);
            	group.setOnMouseClicked(e ->
        			selectGroup());

            	return group;
            }
    	};
        task.setOnSucceeded(event ->
        	groupMember.getItems().add(task.getValue()));
        
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
    
    public void selectGroup() {
    	Node node = groupMember.getSelectionModel().getSelectedItem();
    	String name = ((Text)node).getText();
    	if (!activeChat.getText().equals(name)) {
    		setActiveChat(name);
    		displayChat(name, ChatType.GROUP);
    		messageBox.setEditable(true);
    	}
    }
    
    public void updateUserList(List<UserChatBean> users) {
    	Platform.runLater(() ->
    		userList.getItems().clear());
    	for (UserChatBean user : users) {
    		addToUserList(user);
    	}
    }
    
    
    public void selectUser() {
    	Node node = userList.getSelectionModel().getSelectedItem().getChildren().get(2);
    	String receiver = ((Text)node).getText();
    	if (!activeChat.getText().equals(receiver)) {
    		Node node2 = userList.getSelectionModel().getSelectedItem().getChildren().get(1);
    		pictureImage = ((ImageView)node2).getImage();
    		setActiveChat(receiver);
    		displayChat(receiver, ChatType.PRIVATE);   		
    		messageBox.setEditable(true);
    	}
    }

    public void sendMethod(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER) {
            sendButtonAction();
        }
    }

    /* Method to display server messages */
    public synchronized void addAsServer(MessageBean msg) {
        Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() {
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
    
    public void displayChat(String receiver, ChatType type) {
    	chatPane.getItems().clear();
    	facade.closeLastChat();
    	List<MessageBean> chat = facade.openChat(receiver, type);
    	for (MessageBean message : chat) {
    		if (message.getMsg() != null) {
    			addToChat(message);
    		}
    	}
    	try {
			facade.execute(receiver, type);
		} catch (ServerDownException e) {
			ErrorPopup error = new ErrorPopup();
        	error.displayLoginError("I'm sorry " + e.getMessage() + "\nServer is down\nYou are going to be redirected to the Home Page");
        	MouseEvent clickEvent = new MouseEvent(home, home, MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);
        	super.goHome(clickEvent);
		}
    }

    public void initialize() {
    	
    	this.userImage.setImage(setUserImage());
    	this.myImage = setUserImage();
        setUserList();
        setGroupList();
        
        borderPane.setOnMouseReleased(event ->
            borderPane.setCursor(Cursor.DEFAULT));
    }

    public synchronized void addToUserList(UserChatBean user) {
    	
    	Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() {

            	HBox hBox = new HBox();

            	ImageView statusImageView = new ImageView();
            	Image statusImage = new Image(getClass().getClassLoader().getResource("images/" + user.getStatus().toLowerCase() + ".png").toString(), 16, 16,true,true);
            	statusImageView.setImage(statusImage);
            	
            	Text name = new Text(user.getName());
            	
            	ImageView pictureImageView = new ImageView();
            	pictureImageView.setFitHeight(45);
            	pictureImageView.setFitWidth(45);
            	pictureImageView.setImage(facade.loadImage(user.getPicture()));

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
    
    public synchronized void addToGroupList(UserChatBean user, ListView<HBox> list, ListView<Text> groupList) {
    	Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() {

            	HBox hBox = new HBox();
            	
            	Text name = new Text(user.getName());
            	
            	ImageView pictureImageView = new ImageView();
            	pictureImageView.setFitHeight(25);
            	pictureImageView.setFitWidth(25);
            	pictureImageView.setImage(facade.loadImage(user.getPicture()));
            	
            	Button add = new Button("Add user");
            	add.setOnAction(e ->
            		addUser(user, groupList));

            	hBox.getChildren().addAll(pictureImageView, name, add);
            	hBox.setAlignment(Pos.CENTER_LEFT);
            	return hBox;
            }
    	};
        task.setOnSucceeded(event ->
        	list.getItems().add(task.getValue()));
        
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
    
    public void addUser(UserChatBean user, ListView<Text> groupList) {
    	Task<Text> task = new Task<Text>() {
            @Override
            public Text call() {

            	return new Text(user.getName());
            }
    	};
        task.setOnSucceeded(event -> {
        	groupList.getItems().add(task.getValue());
        	namesList.add(user.getName());
        });
        
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }
    
    public void createAGroup() {
    	Stage window = new Stage();
		window.setWidth(700);
		window.setHeight(400);
		window.initModality(Modality.APPLICATION_MODAL); //this avoid user to interact with other users
		ListView<HBox> list = new ListView<>();
		ListView<Text> groupList = new ListView<>();
		VBox groupBox = new VBox();
		TextField groupName = new TextField("Insert group name");
		groupName.setOnMouseClicked( event ->
			groupName.clear());
		groupBox.getChildren().addAll(groupName, groupList);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(list, groupBox);
		hBox.setAlignment(Pos.CENTER);
		Button backButton = new Button("Back");
		backButton.setOnAction(e->window.close());
		Button confirmButton = new Button("Confirm");
		confirmButton.setOnAction(event -> {
			try {
				facade.createGroup(groupName.getText(), namesList);
			} catch (GroupNameTakenException e1) {
				ErrorPopup err = new ErrorPopup();
				err.displayLoginError("Nome gruppo non disponibile");
			}
		window.close();
		});
		HBox buttonBox = new HBox();
		buttonBox.getChildren().addAll(backButton, confirmButton);
		VBox layout = new VBox();
		layout.getChildren().addAll(hBox, buttonBox);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		namesList = new ArrayList<>();
		for (UserChatBean user : users) {
    		addToGroupList(user, list, groupList);
    	}
		window.showAndWait();		
    }
    
    public void exitChat() {
    	logger.info("exitChat() method Enter");
    	facade.closeLastChatAndExit();
    	logger.info("exitChat() method Exit");
    }
    
    @Override
    public void goHome(MouseEvent event) {
    	exitChat();
    	super.goHome(event);
    }
    
    @Override
    public void goRent(MouseEvent event) {
    	exitChat();
    	super.goRent(event);
    }
    
    @Override
    public void goBookTravel(MouseEvent event) {
    	exitChat();
    	super.goBookTravel(event);
    }
    
    @Override
    public void leaveApp(MouseEvent event) {
    	exitChat();
    	super.leaveApp(event);
    }
}
package logic.controllers;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.image.Image;
import logic.ImageViewer;
import logic.beans.RentAccomodationBean;
import logic.controllers.RentAccomodationController;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Message;
import logic.model.User;
import logic.controllers.ChatController;

public class ControllerFacade {

	private ManageAnnouncementController controllerManage;
	private RentAccomodationController controllerRent;
	private ChatController chatController;
	private ImageViewer viewer;
	private GraphicControllerChat graphicChat;
	
	public ControllerFacade() {
		viewer = new ImageViewer();
	}
	
	public ControllerFacade(GraphicControllerChat reference) {
		viewer = new ImageViewer();
		this.graphicChat = reference;
		chatController = new ChatController(this);
	}
	
	public List<RentAccomodationBean> displayMyAnnouncement() {
		
		controllerManage = new ManageAnnouncementController();
		List<RentAccomodationBean> listOfBean = controllerManage.displayMyAnnouncement();
		return listOfBean;		
	}
	
	public void deleteMyAccomodation(long id) {
		controllerManage.deleteMyAccomodation(id);
	}
	
	public List<RentAccomodationBean> displayAnnouncement() {
		controllerRent = new RentAccomodationController();
		return controllerRent.displayAnnouncement();
	}
	
	public void createChat(String renter) {
		controllerRent.createChat(renter);
	}
	
	public List<User> getUsers() {
		return chatController.getUsers();
	}
	
	public List<String> getGroups() {
		return chatController.getGroups();
	}
	
	public void sendMessage(String msg, String receiver) {
		chatController.sendMessage(msg, receiver);
	}
	
	public void closeLastChat() {
		chatController.closeLastChat();
	}
	
	public List<Message> openChat(String receiver, ChatType type) {
		return chatController.openChat(receiver, type);
	}
	public void execute(String receiver, ChatType type) {
		chatController.execute(receiver, type);
	}
	
	public void createGroup(String groupName, List<String> groupList) {
		chatController.createGroup(groupName, groupList);
	}
	
	public void modificateStatus(String status) {
		chatController.modificateStatus(status);
	}
	
	public void updateUserList(List<User> users) {
		graphicChat.updateUserList(users);
	}
	
	public void addToChat(Message message) {
		graphicChat.addToChat(message);
	}
	
	public void addAsServer(Message message) {
		graphicChat.addAsServer(message);
	}
	
	@SuppressWarnings("exports")
	public BufferedImage loadImage(byte[] bs) {
		return viewer.loadImage(bs);
	}
	
	@SuppressWarnings("exports")
	public Image convertToFxImage(BufferedImage image) {
		return viewer.convertToFxImage(image);
	}
}

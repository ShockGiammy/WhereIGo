package logic.controllers;

import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.image.Image;
import logic.ImageViewer;
import logic.beans.GroupBean;
import logic.beans.InterestsBean;
import logic.beans.LocationBean;
import logic.beans.RentAccomodationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.exceptions.DuplicateUsernameException;
import logic.exceptions.GroupNameTakenException;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.model.Message;
import logic.model.User;

public class ControllerFacade {

	private ManageAnnouncementController controllerManage;
	private RentAccomodationController controllerRent;
	private ChatController chatController;
	private GraphicControllerChat graphicChat;
	private BookTravelControl bookTravCtrl;
	private InterestsController intCtrl;
	private LoginController loginCtrl;
	
	public ControllerFacade() {
		this.bookTravCtrl = new BookTravelControl();
		this.intCtrl = new InterestsController();
		this.loginCtrl = new LoginController();
	}
	
	public ControllerFacade(GraphicControllerChat reference) {
		this.graphicChat = reference;
		chatController = new ChatController(this);
	}
	
	/*ManageAnnouncementController references methods*/
	
	public List<RentAccomodationBean> displayMyAnnouncement() {
		
		controllerManage = new ManageAnnouncementController();
		return controllerManage.displayMyAnnouncement();	
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
	
	/*ChatController references methods*/
	
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
	
	public void createGroup(String groupName, List<String> groupList) throws GroupNameTakenException {
		chatController.createGroup(groupName, groupList);
	}
	
	public void modificateStatus(String status) {
		chatController.modificateMyStatus(status);
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
	
	/*CreateAccomodation references methods*/
	
	public void createAccomodation(RentAccomodationBean bean) {
		controllerManage = new ManageAnnouncementController();
		controllerManage.createAccomodation(bean);
	}
	
	@SuppressWarnings("exports")
	public BufferedImage loadImage(byte[] bs) {
		ImageViewer viewer = new ImageViewer();
		return viewer.loadImage(bs);
	}
	
	@SuppressWarnings("exports")
	public Image convertToFxImage(BufferedImage image) {
		ImageViewer viewer = new ImageViewer();
		return viewer.convertToFxImage(image);
	}
	
	/*BookTravelControl references methods*/
	
	public List<String> showLocations() {
		return this.bookTravCtrl.showLocationsControl();
	}
	
	public void getGroups(List<GroupBean> beanList) {
		this.bookTravCtrl.getGroupsControl(beanList);
	}
	
	public void getParticipateGroups(List<GroupBean> beanList) {
		this.bookTravCtrl.getParticipateGroupsControl(beanList);
	}
	
	public void retriveLocInfo(LocationBean bean) {
		this.bookTravCtrl.retriveLocInfoControl(bean);
	}
	
	public int retriveTravelSolutions(UserTravelBean travBean, List<UserTravelBean> travList) {
		return this.bookTravCtrl.retriveTravelSolutionsControl(travBean, travList);
	}
	
	public void saveBoughtTicket(UserTravelBean travBean , UserDataBean dataBean) {
		this.bookTravCtrl.saveBoughtTicketControl(travBean , dataBean);
	}
	
	public void getBookedTickets(List<UserTravelBean> travBeanList) {
		this.bookTravCtrl.getBookedTicketsControl(travBeanList);
	}
	
	public void saveGroup(GroupBean grpBean) throws GroupNameTakenException {
		this.bookTravCtrl.saveGroupControl(grpBean);
	}
	
	public void getUserGroups(List<GroupBean> grpBean) {
		this.bookTravCtrl.getUserGroupsControl(grpBean);
	}
	
	public List<UserTravelBean> getSuggTicketsInfo(UserTravelBean travBean) {
		return this.bookTravCtrl.getSuggTicketsInfoControl(travBean);
	}
	
	public int insertParticipant(GroupBean bean) {
		return this.bookTravCtrl.insertParticipantControl(bean);
	}
	
	public void deleteSavedTravel(UserTravelBean travBean) {
		this.bookTravCtrl.deleteSavedTravelControl(travBean);
	}
	
	public void deleteTravelGroup(GroupBean grpBean) {
		this.bookTravCtrl.deleteTravelGroupControl(grpBean);
	}
	
	public void leaveTravelGroup(GroupBean grpBean) {
		this.bookTravCtrl.leaveTravelGroupControl(grpBean);
	}
	
	public void getSamePersUsers(List<UserDataBean> usrList) {
		this.bookTravCtrl.getSamePersUsersControl(usrList);
	}
	
	/* methods calls of the Interest Controller*/
	
	public void evaluateInterests(InterestsBean interBean) {
		this.intCtrl.evaluateInterestsControl(interBean);
	}
	
	/* methods calls of the Login Controller*/
	
	public int checkLogIn(UserDataBean logBean) {
		return this.loginCtrl.checkLogInControl(logBean);
	}
	
	public int insertNewUser(UserDataBean usrBean) throws DuplicateUsernameException {
		return this.loginCtrl.insertNewUserControl(usrBean);
	}
}

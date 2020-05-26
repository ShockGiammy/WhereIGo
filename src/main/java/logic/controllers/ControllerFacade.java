package logic.controllers;

import java.util.List;

import javafx.scene.image.Image;
import logic.ImageViewer;
import logic.beans.GroupBean;
import logic.beans.InterestsBean;
import logic.beans.LocationBean;
import logic.beans.MessageBean;
import logic.beans.RentAccomodationBean;
import logic.beans.UserChatBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.exceptions.BigDateException;
import logic.exceptions.DuplicateUsernameException;
import logic.exceptions.EmptyListException;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.MissingAnswareException;
import logic.exceptions.ServerDownException;
import logic.graphiccontrollers.GraphicControllerChat;
import logic.servlets.ChatRenterServlet;
import logic.servlets.ChatTravellerServlet;

public class ControllerFacade {

	private ManageAnnouncementController controllerManage;
	private ChatController chatController;
	private GraphicControllerChat graphicChat;
	private BookTravelControl bookTravCtrl;
	private LoginController loginCtrl;
	private ImageViewer viewer;
	
	public ControllerFacade() {
	}
	
	/*ChatController references methods*/
	
	public void callChatController(GraphicControllerChat reference) {
		this.graphicChat = reference;
		chatController = new ChatController(this);
	}
	
	@SuppressWarnings("exports")
	public ControllerFacade(ChatTravellerServlet chatTravellerServlet) {
		this.chatController = new ChatController(this);
	}
	
	@SuppressWarnings("exports")
	public ControllerFacade(ChatRenterServlet chatRenterServlet) {
		this.chatController = new ChatController(this);
	}

	public void createChat(String renter) {
		if (chatController == null) {
			chatController = new ChatController();
		}
		chatController.createChat(renter);
	}
	
	public List<UserChatBean> getUsers() {
		return chatController.getUsers();
	}
	
	public List<String> getGroups() {
		return chatController.getGroups();
	}
	
	public void sendMessage(MessageBean msg) {
		chatController.sendMessage(msg);
	}
	
	public void closeLastChat() {
		chatController.closeLastChat();
	}
	
	public void closeLastChatAndExit() {
		chatController.modificateMyStatus("offline");
		chatController.closeLastChat();
	}
	
	public void setOfflineStatus() {
		chatController.modificateMyStatus("offline");
	}
	
	public List<MessageBean> openChat(String receiver, ChatType type) {
		return chatController.openChat(receiver, type);
	}
	public void execute(String receiver, ChatType type) throws ServerDownException {
		chatController.execute(receiver, type);
	}
	
	public void createGroup(String groupName, List<String> groupList) throws GroupNameTakenException {
		chatController.createGroup(groupName, groupList);
	}
	
	public void updateUserList(List<UserChatBean> users) {
		if (graphicChat!=null) {
			graphicChat.updateUserList(users);
		}
	}
	
	public void addToChat(MessageBean message) {
		if (graphicChat!=null) {
			graphicChat.addToChat(message);
		}
	}
	
	public void addAsServer(MessageBean message) {
		if (graphicChat!=null) {
			graphicChat.addAsServer(message);
		}
	}
	
	public UserChatBean getUser(String user) {
		return chatController.getUser(user);
	}
	
	/*ManageAnnouncementController references methods*/

	public List<RentAccomodationBean> displayMyAnnouncement() {
		if (controllerManage == null) {
			controllerManage = new ManageAnnouncementController();
		}
		return controllerManage.displayMyAnnouncement();	
	}
	
	public void deleteMyAccomodation(long id) {
		if (controllerManage == null) {
			controllerManage = new ManageAnnouncementController();
		}
		controllerManage.deleteMyAccomodation(id);
	}
	
	public List<RentAccomodationBean> displayAnnouncement() {
		RentAccomodationController controllerRent = new RentAccomodationController();
		return controllerRent.displayAnnouncement();
	}
	
	/*CreateAccomodation references methods*/
	
	public void createAccomodation(RentAccomodationBean bean) {
		if (controllerManage == null) {
			controllerManage = new ManageAnnouncementController();
		}
		controllerManage.createAccomodation(bean);
	}
	
	public Image loadImage(byte[] bs) {
		if (viewer == null) {
			this.viewer = new ImageViewer();
		}
		return viewer.loadImage(bs);
	}
	
	/*BookTravelControl references methods*/
	
	public void findTravelSugg(List<String> suggLoc,UserDataBean dBean) {
		this.bookTravCtrl = new BookTravelControl();
		suggLoc.addAll(this.bookTravCtrl.showLocationsControl(dBean));
	}
	
	public void findSuggGroups(List<GroupBean> gBeanList, UserDataBean dBean) {
		if(this.bookTravCtrl == null) {
			this.bookTravCtrl = new BookTravelControl();
		}
		this.bookTravCtrl.getSuggestedGroupsControl(gBeanList, dBean);
	}
	
	public void getAvailableTick(List<String> depCities, List<String> arrCities) {
		if(this.bookTravCtrl == null) {
			this.bookTravCtrl = new BookTravelControl();
		}
		this.bookTravCtrl.setAvailableTick(depCities, arrCities);
	}
	
	public void retriveLocInfo(LocationBean bean) {
		this.bookTravCtrl = new BookTravelControl();
		this.bookTravCtrl.retriveLocInfoControl(bean);
	}
	
	public void retriveTravelSolutions(UserTravelBean travBean, List<UserTravelBean> travList, UserDataBean dBean) throws BigDateException, EmptyListException {
		this.bookTravCtrl = new BookTravelControl();
		this.bookTravCtrl.retriveTravelSolutionsControl(dBean, travBean, travList);
	}
	
	public void saveBoughtTicket(UserTravelBean travBean, UserDataBean dBean) {
		this.bookTravCtrl = new BookTravelControl();
		this.bookTravCtrl.saveBoughtTicketControl(travBean, dBean);
	}
	
	public void getSimilarUsers(List<UserDataBean> usrList, UserDataBean dBean) {
		if(this.bookTravCtrl == null) {
			this.bookTravCtrl = new BookTravelControl();
		}
		this.bookTravCtrl.getSamePersUsersControl(usrList, dBean);
	}
	
	public void getUsersGroups(List<GroupBean> grpBean, UserDataBean dBean) {
		if(this.bookTravCtrl == null) {
			this.bookTravCtrl = new BookTravelControl();
		}
		this.bookTravCtrl.getUserGroupsControl(grpBean, dBean);
	}
	
	public void getBookedTicks(List<UserTravelBean> travBeanList, UserDataBean dBean) {
		if(this.bookTravCtrl == null) {
			this.bookTravCtrl = new BookTravelControl();
		}
		this.bookTravCtrl.getBookedTicketsControl(travBeanList, dBean);
	}
	
	public void saveGroup(GroupBean grpBean) throws GroupNameTakenException {
		this.bookTravCtrl = new BookTravelControl();
		this.bookTravCtrl.saveGroupControl(grpBean);
	}
	
	public List<UserTravelBean> getSuggTicketsInfo(UserTravelBean travBean, UserDataBean dBean) throws EmptyListException {
		this.bookTravCtrl = new BookTravelControl();
		return this.bookTravCtrl.getSuggTicketsInfoControl(travBean, dBean);
	}
	
	public int insertParticipant(GroupBean bean, UserDataBean dBean) {
		this.bookTravCtrl = new BookTravelControl();
		return this.bookTravCtrl.insertParticipantControl(bean, dBean);
	}
	
	public void deleteSavedTravel(UserTravelBean travBean, UserDataBean dBean) {
		this.bookTravCtrl = new BookTravelControl();
		this.bookTravCtrl.deleteSavedTravelControl(travBean, dBean);
	}
	
	public void deleteTravelGroup(GroupBean grpBean) {
		this.bookTravCtrl = new BookTravelControl();
		this.bookTravCtrl.deleteTravelGroupControl(grpBean);
	}
	
	public void leaveTravelGroup(GroupBean grpBean, UserDataBean dBean) {
		this.bookTravCtrl = new BookTravelControl();
		this.bookTravCtrl.leaveTravelGroupControl(grpBean, dBean);
	}
	
	/* methods calls of the Interest Controller*/
	
	public String evaluateInterests(InterestsBean interBean) throws MissingAnswareException {
		InterestsController intCtrl = new InterestsController();
		return intCtrl.evaluateInterestsControl(interBean);
	}
	
	/* methods calls of the Login Controller*/
	
	public int checkLogIn(UserDataBean logBean) {
		this.loginCtrl = new LoginController();
		return this.loginCtrl.checkLogInControl(logBean);
	}
	
	public void insertNewUser(UserDataBean usrBean) throws DuplicateUsernameException {
		this.loginCtrl = new LoginController();
		this.loginCtrl.insertNewUserControl(usrBean);
	}
}

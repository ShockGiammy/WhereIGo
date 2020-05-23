package logic.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.dao.GroupDao;
import logic.dao.LocationDao;
import logic.dao.TravelDao;
import logic.dao.UserDao;
import logic.exceptions.BigDateException;
import logic.exceptions.EmptyListException;
import logic.exceptions.GroupNameTakenException;
import logic.model.GroupModel;
import logic.model.LocationModel;
import logic.model.TicketModel;
import logic.model.UserModel;

public class BookTravelControl {
	private LoggedUser logUser;
	private UserModel usrMod;
	private GroupModel grpMod;
	private LocationDao locDao;
	private TravelDao travDao;
	private GroupDao grpDao;
	private UserDao usrDao;
	
	public BookTravelControl() {
		this.usrMod = new UserModel();
		this.locDao = new LocationDao();
		this.logUser = new LoggedUser();
		this.travDao = new TravelDao();
		this.grpDao = new GroupDao();
		this.usrDao = new UserDao();
		this.grpMod = new GroupModel();
	}
	
	public void setAvailableTick(List<UserTravelBean> avTrav) {
		List<TicketModel> avModTickList = new ArrayList<>();
		this.travDao.findCurrTravels(avModTickList);
		for(int i = 0; i < avModTickList.size(); i++) {
			UserTravelBean travB = new UserTravelBean(avModTickList.get(i).getArrCity());
			travB.setDepCity(avModTickList.get(i).getDepCity());
			avTrav.add(travB);
		}
	}
	
	public List<String> showLocationsControl() {
		List<String> suggLoc = new ArrayList<>();
		UserModel usrModel = new UserModel();
		usrModel.setUserName(this.logUser.getUserName());
		usrModel.setUserPersonality(this.logUser.getPersonality());
		suggLoc.addAll(this.locDao.getSuggestedLocations(usrModel));
		return suggLoc;
	}
	
	public void getSuggestedGroupsControl(List<GroupBean> beanList) {
		this.usrMod.setUserPersonality(this.logUser.getPersonality());
		this.usrMod.setUserName(this.logUser.getUserName());
		List<GroupModel> grpModelList = new ArrayList<>();
		this.grpDao.retriveSuggestedGroups(this.usrMod, grpModelList);
		extractGroupsControl(grpModelList, beanList);
	}
	
	public void getParticipateGroupsControl(List<GroupBean> beanList) {
		this.usrMod.setUserPersonality(this.logUser.getPersonality());
		List<GroupModel> grpList = new ArrayList<>();
		this.grpDao.getPartGroups(grpList, usrMod);
		extractGroupsControl(grpList, beanList);
	}
	
	public void extractGroupsControl(List<GroupModel> grpList, List<GroupBean> beanList) {
		int i;
		for(i = 0; i < grpList.size(); i++) {
			GroupBean grpbean = new GroupBean(grpList.get(i).getDescription(), grpList.get(i).getOwner(), grpList.get(i).getDestination());
			beanList.add(grpbean);
		}
	}
	
	public void retriveLocInfoControl(LocationBean bean) {
		LocationModel locModel = new LocationModel();
		locModel.setCity(bean.getCityName());
		this.locDao.retriveLocationInfo(locModel);
		bean.setCountryName(locModel.getCountry());
		bean.setDescription(locModel.getDescription());
		bean.setStream(locModel.getPhoto());
	}
	
	public void retriveTravelSolutionsControl(UserTravelBean travBean, List<UserTravelBean> travList) throws BigDateException, EmptyListException {
		List<TicketModel> tickList = new ArrayList<>();
		if(LocalDate.parse(travBean.getFirstDay()).compareTo(LocalDate.parse(travBean.getLastDay())) >= 0) {
			throw new BigDateException("Departure date is before Return date");
		}
		else {
			this.usrMod.setUserName(this.logUser.getUserName());
			TicketModel tick = new TicketModel(travBean.getCityOfDep(), travBean.getCityOfArr(), LocalDate.parse(travBean.getFirstDay()), LocalDate.parse(travBean.getLastDay()));
			tickList.addAll(travDao.retriveAvailableTickets(tick, usrMod));
			if(tickList.isEmpty()) {
				throw new EmptyListException();
			}
			else {
				setTickInfo(tickList, travList);
			}
		}
	}
	
	public void saveBoughtTicketControl(UserTravelBean travBean) {
		TicketModel tick = new TicketModel(travBean.getCityOfDep(), travBean.getCityOfArr(), travBean.getFirstDayPars(), travBean.getLastDayPars());
		tick.setId(travBean.getId());
		tick.setCost(travBean.getCost());
		this.usrMod.setUserName(this.logUser.getUserName());
		this.travDao.saveBoughtTickets(tick, this.usrMod);
	}
	
	public void getBookedTicketsControl(List<UserTravelBean> travBeanList) {
		this.usrMod.setUserName(logUser.getUserName());
		List<TicketModel> tickList = new ArrayList<>();
		this.travDao.getUserTickets(usrMod, tickList);
		setTickInfo(tickList, travBeanList);
	}
	
	public void saveGroupControl(GroupBean grpBean) throws GroupNameTakenException {
		this.grpMod.setAll(grpBean.getGroupOwner(),grpBean.getGroupTitle(), grpBean.getGroupDestination());
		this.grpDao.saveUserGroup(this.grpMod);
	}
	
	public void getUserGroupsControl(List<GroupBean> grpBean) {
		this.usrMod.setUserName(this.logUser.getUserName());
		List<GroupModel> grpList = new ArrayList<>();
		grpDao.getUserGroups(grpList,this.usrMod);
		extractGroupsControl(grpList, grpBean);
		getParticipateGroupsControl(grpBean);
	}
	
	public List<UserTravelBean> getSuggTicketsInfoControl(UserTravelBean travBean) throws EmptyListException{
		List<TicketModel> tickList = new ArrayList<>();
		List<UserTravelBean> travList = new ArrayList<>();
		this.usrMod.setUserName(this.logUser.getUserName());
		TicketModel tickMod = new TicketModel(travBean.getCityOfArr());
		this.travDao.getSuggestedTickets(tickMod,this.usrMod, tickList);
		if(tickList.isEmpty()) {
			throw new EmptyListException();
		}
		setTickInfo(tickList, travList);
		return travList;
	}
	
	public int insertParticipantControl(GroupBean bean) {
		this.grpMod.setAll(bean.getGroupOwner(), bean.getGroupTitle(), bean.getGroupDestination());
		usrMod.setUserName(this.logUser.getUserName());
		return this.grpDao.insertParticipant(grpMod, usrMod);
	}
	
	public void deleteSavedTravelControl(UserTravelBean travBean) {
		this.usrMod.setUserName(this.logUser.getUserName());
		TicketModel tickModel = new TicketModel(travBean.getId());
		this.travDao.deleteTick(tickModel, usrMod);
	}
	
	public void deleteTravelGroupControl(GroupBean grpBean) {
		this.grpMod.setAll(grpBean.getGroupOwner(), grpBean.getGroupTitle(), grpBean.getGroupDestination());
		this.grpDao.deleteGroup(grpMod);
	}
	
	public void leaveTravelGroupControl(GroupBean grpBean) {
		this.grpMod.setAll(grpBean.getGroupOwner(), grpBean.getGroupTitle(), grpBean.getGroupDestination());
		this.usrMod.setUserName(this.logUser.getUserName());
		this.grpDao.leaveJoinedGroup(grpMod, usrMod);
	}
	
	public void getSamePersUsersControl(List<UserDataBean> usrList) {
		List<UserModel> usrModelList = new ArrayList<>();
		UserModel loggedUsr = new UserModel();
		loggedUsr.setUserName(this.logUser.getUserName());
		loggedUsr.setUserPersonality(this.logUser.getPersonality());
		this.usrDao.findSimilarUsers(usrModelList, loggedUsr);
		setSimUsersBeanControl(usrList, usrModelList);
	}
	
	public void setSimUsersBeanControl(List<UserDataBean> usrList, List<UserModel> usrModList) {
		for(int i = 0; i < usrModList.size(); i++) {
			UserDataBean databean = new UserDataBean(usrModList.get(i).getUserName());
			databean.setByteSteam(usrModList.get(i).getProfilePic());
			usrList.add(databean);
		}
	}
	
	private void setTickInfo(List<TicketModel> tickList, List<UserTravelBean> travBeanList) {
		for(int i = 0; i < tickList.size(); i++) {
			UserTravelBean trav = new UserTravelBean(tickList.get(i).getDepDay(), tickList.get(i).getArrDay(), tickList.get(i).getDepCity(), tickList.get(i).getArrCity());
			trav.setId(tickList.get(i).getId());
			trav.setCost(tickList.get(i).getMoney());
			travBeanList.add(trav);
		}
	}
}

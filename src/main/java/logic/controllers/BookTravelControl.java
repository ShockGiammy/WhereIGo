package logic.controllers;

import java.sql.SQLException;
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
import logic.exceptions.GroupNameTakenException;
import logic.model.GroupModel;
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
	
	public List<String> showLocationsControl() {
		List<String> suggLoc = new ArrayList<>();
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(this.logUser.getUserName());
		dataBean.setPersonality(this.logUser.getPersonality());
		suggLoc.addAll(this.locDao.getSuggestedLocations(dataBean));
		return suggLoc;
	}
	
	public void getSuggestedGroupsControl(List<GroupBean> beanList) {
		this.usrMod.setUserPersonality(this.logUser.getPersonality());
		this.usrMod.setUserName(this.logUser.getUserName());
		List<GroupModel> grpModelList = new ArrayList<>();
		this.grpDao.retriveSuggestedGroups(this.usrMod, grpModelList);
		extractGroupsControl(grpModelList, beanList);
	}
	
	public void getParticipateGroupsControl(List<GroupBean> beanList){
		this.usrMod.setUserPersonality(this.logUser.getPersonality());
		List<GroupModel> grpList = new ArrayList<>();
		this.grpDao.getPartGroups(grpList, usrMod);
		extractGroupsControl(grpList, beanList);
	}
	
	public void extractGroupsControl(List<GroupModel> grpList, List<GroupBean> beanList) {
		int i;
		for(i = 0; i < grpList.size(); i++) {
			GroupBean grpbean = new GroupBean();
			grpbean.setGroupOwner(grpList.get(i).getOwner());
			grpbean.setGroupTitle(grpList.get(i).getDescription());
			grpbean.setGroupDestination(grpList.get(i).getDestination());
			beanList.add(grpbean);
		}
	}
	
	public void retriveLocInfoControl(LocationBean bean) {
		this.locDao.retriveLocationInfo(bean);
	}
	
	public int retriveTravelSolutionsControl(UserTravelBean travBean, List<UserTravelBean> travList) {
		List<TicketModel> tickList = new ArrayList<>();
		int i = 0;
		try {
			if(LocalDate.parse(travBean.getFirstDay()).compareTo(LocalDate.parse(travBean.getLastDay())) > 0) {
				return -1;
			}
			else {
				UserDataBean dataBean = new UserDataBean();
				dataBean.setUserName(this.logUser.getUserName());
				tickList.addAll(travDao.retriveAvailableTickets(travBean, dataBean));
				if(tickList.isEmpty()) {
					return -1;
				}
				else {
					while(i < tickList.size()) {
						UserTravelBean bean = new UserTravelBean();
						bean.setId(tickList.get(i).getId());
						bean.setFirstDay(tickList.get(i).getDepDay());
						bean.setLastDay(tickList.get(i).getArrDay());
						bean.setArrCity(tickList.get(i).getArrCity());
						bean.setDepCity(tickList.get(i).getDepCity());
						bean.setCost(tickList.get(i).getMoney());
						travList.add(bean);
						i+=1;
					}
				}
			}
		}catch(SQLException e) {
			return -1;
		}
		return 0;
	}
	
	public void saveBoughtTicketControl(UserTravelBean travBean) {
		TicketModel tick = new TicketModel();
		tick.setAll(travBean.getId(), travBean.getCityOfDep(), travBean.getCityOfArr(), LocalDate.parse(travBean.getFirstDay()), LocalDate.parse(travBean.getLastDay()), travBean.getCost());
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(this.logUser.getUserName());
		this.travDao.saveBoughtTickets(tick, dataBean);
	}
	
	public void getBookedTicketsControl(List<UserTravelBean> travBeanList) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(logUser.getUserName());
		List<TicketModel> tickList = new ArrayList<>();
		this.travDao.getUserTickets(dataBean, tickList);
		int i;
		for(i = 0; i < tickList.size(); i++) {
			UserTravelBean bean = new UserTravelBean();
			bean.setId(tickList.get(i).getId());
			bean.setDepCity(tickList.get(i).getDepCity());
			bean.setArrCity(tickList.get(i).getArrCity());
			bean.setFirstDay(tickList.get(i).getDepDay());
			bean.setLastDay(tickList.get(i).getArrDay());
			bean.setCost(tickList.get(i).getMoney());
			travBeanList.add(bean);
		}
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
	
	public List<UserTravelBean> getSuggTicketsInfoControl(UserTravelBean travBean) {
		List<TicketModel> tickList = new ArrayList<>();
		List<UserTravelBean> travList = new ArrayList<>();
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(this.logUser.getUserName());
		this.travDao.getSuggestedTickets(travBean,dataBean, tickList);
		int i;
		for(i = 0; i < tickList.size(); i++) {
			UserTravelBean trav = new UserTravelBean();
			trav.setId(tickList.get(i).getId());
			trav.setDepCity(tickList.get(i).getDepCity());
			trav.setArrCity(tickList.get(i).getArrCity());
			trav.setFirstDay(tickList.get(i).getDepDay());
			trav.setLastDay(tickList.get(i).getArrDay());
			trav.setCost(tickList.get(i).getMoney());
			travList.add(trav);
		}
		return travList;
	}
	
	public int insertParticipantControl(GroupBean bean) {
		this.grpMod.setAll(bean.getGroupOwner(), bean.getGroupTitle(), bean.getGroupDestination());
		usrMod.setUserName(this.logUser.getUserName());
		return this.grpDao.insertParticipant(grpMod, usrMod);
	}
	
	public void deleteSavedTravelControl(UserTravelBean travBean) {
		UserDataBean dataBean = new UserDataBean();
		dataBean.setUserName(this.logUser.getUserName());
		TicketModel tickModel = new TicketModel();
		tickModel.setId(travBean.getId());
		this.travDao.deleteTick(tickModel, dataBean);
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
			UserDataBean databean = new UserDataBean();
			databean.setByteSteam(usrModList.get(i).getProfilePic());
			databean.setUserName(usrModList.get(i).getUserName());
			usrList.add(databean);
		}
	}
}

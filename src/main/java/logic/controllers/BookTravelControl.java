package logic.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
	private UserModel usrMod;
	private GroupModel grpMod;
	private LocationDao locDao;
	private TravelDao travDao;
	private GroupDao grpDao;
	private UserDao usrDao;
	
	public BookTravelControl() {
		if(this.usrMod == null)
			this.usrMod = new UserModel();
		this.locDao = new LocationDao();
		this.travDao = new TravelDao();
		this.grpDao = new GroupDao();
		this.usrDao = new UserDao();
		this.grpMod = new GroupModel();
	}
	
	public void setAvailableTick(List<String> depCities, List<String> arrCities) {
		this.travDao.findCurrTravels(depCities, arrCities);
	}
	
	public List<String> showLocationsControl(UserDataBean dBean) {
		List<String> suggLoc = new ArrayList<>();
		this.usrMod.setUsrAndPersByBean(dBean);
		suggLoc.addAll(this.locDao.getSuggestedLocations(this.usrMod));
		return suggLoc;
	}
	
	public void getSuggestedGroupsControl(List<GroupBean> beanList, UserDataBean dataBean) {
		this.usrMod.setUsrAndPersByBean(dataBean);
		List<GroupModel> grpModelList = new ArrayList<>();
		this.grpDao.retriveSuggestedGroups(this.usrMod, grpModelList);
		extractGroupsControl(grpModelList, beanList);
	}
	
	public void getParticipateGroupsControl(List<GroupBean> beanList, UserDataBean dBean) {
		this.usrMod.setPersByBean(dBean);
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
		locModel.setCityByBean(bean);
		this.locDao.retriveLocationInfo(locModel);
		bean.setCountryName(locModel.getCountry());
		bean.setDescription(locModel.getDescription());
		bean.setStream(locModel.getPhoto());
	}
	
	public void retriveTravelSolutionsControl(UserDataBean dataBean, UserTravelBean travBean, List<UserTravelBean> travList) throws BigDateException, EmptyListException {
		List<TicketModel> tickList = new ArrayList<>();
		if(LocalDate.parse(travBean.getFirstDay()).compareTo(LocalDate.parse(travBean.getLastDay())) >= 0) {
			throw new BigDateException("Departure date is before Return date");
		}
		else {
			this.usrMod.setUsrNameByBean(dataBean);
			TicketModel tick = new TicketModel();
			tick.setTravByBean(travBean);
			tickList.addAll(travDao.retriveAvailableTickets(tick, usrMod));
			if(tickList.isEmpty()) {
				throw new EmptyListException();
			}
			else {
				setTickInfo(tickList, travList);
			}
		}
	}
	
	public void saveBoughtTicketControl(UserTravelBean travBean, UserDataBean dBean) {
		TicketModel tick = new TicketModel();
		tick.setAllByBean(travBean);
		this.usrMod.setUsrNameByBean(dBean);
		this.travDao.saveBoughtTickets(tick, this.usrMod);
	}
	
	public void getBookedTicketsControl(List<UserTravelBean> travBeanList, UserDataBean dBean) {
		this.usrMod.setUsrNameByBean(dBean);
		List<TicketModel> tickList = new ArrayList<>();
		this.travDao.getUserTickets(usrMod, tickList);
		setTickInfo(tickList, travBeanList);
	}
	
	public void saveGroupControl(GroupBean grpBean) throws GroupNameTakenException {
		this.grpMod.setAllByBean(grpBean);
		this.grpDao.saveUserGroup(this.grpMod);
	}
	
	public void getUserGroupsControl(List<GroupBean> grpBean, UserDataBean dBean) {
		this.usrMod.setUsrNameByBean(dBean);
		List<GroupModel> grpList = new ArrayList<>();
		grpDao.getUserGroups(grpList,this.usrMod);
		extractGroupsControl(grpList, grpBean);
		getParticipateGroupsControl(grpBean, dBean);
	}
	
	public List<UserTravelBean> getSuggTicketsInfoControl(UserTravelBean travBean, UserDataBean dataBean) throws EmptyListException{
		List<TicketModel> tickList = new ArrayList<>();
		List<UserTravelBean> travList = new ArrayList<>();
		this.usrMod.setUsrNameByBean(dataBean);
		TicketModel tickMod = new TicketModel();
		tickMod.setArrCityByBean(travBean);
		this.travDao.getSuggestedTickets(tickMod,this.usrMod, tickList);
		if(tickList.isEmpty()) {
			throw new EmptyListException();
		}
		setTickInfo(tickList, travList);
		return travList;
	}
	
	public int insertParticipantControl(GroupBean bean, UserDataBean dBean) {
		this.grpMod.setAllByBean(bean);
		usrMod.setUsrNameByBean(dBean);
		return this.grpDao.insertParticipant(grpMod, usrMod);
	}
	
	public void deleteSavedTravelControl(UserTravelBean travBean, UserDataBean dataBean) {
		this.usrMod.setUsrNameByBean(dataBean);
		TicketModel tickModel = new TicketModel();
		tickModel.setIdByBean(travBean);
		this.travDao.deleteTick(tickModel, usrMod);
	}
	
	public void deleteTravelGroupControl(GroupBean grpBean) {
		this.grpMod.setAllByBean(grpBean);
		this.grpDao.deleteGroup(grpMod);
	}
	
	public void leaveTravelGroupControl(GroupBean grpBean, UserDataBean dBean) {
		this.grpMod.setAllByBean(grpBean);
		this.usrMod.setUsrNameByBean(dBean);
		this.grpDao.leaveJoinedGroup(grpMod, usrMod);
	}
	
	public void getSamePersUsersControl(List<UserDataBean> usrList, UserDataBean dBean) {
		List<UserModel> usrModelList = new ArrayList<>();
		this.usrMod.setUsrAndPersByBean(dBean);
		this.usrDao.findSimilarUsers(usrModelList, usrMod);
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

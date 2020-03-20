/* IMPORTANT : IS BETTER TO CHANGE THE RETURN VALUE OF THE FUNCTION WITH THE THORWS OF AN EXCEPTION, WHICH WILL BE
 *  HANDLED IN THE GRAPHIC CONTROLLER AND WILL DISPLAY AN ERROR MESSAGE*/

package logic.controllers;

import java.sql.SQLException;
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
import logic.model.GroupModel;
import logic.model.TicketModel;

public class BookTravelControl {
	private LoggedUser logUser;
	private UserDataBean userBean;
	private GroupModel grpModel;
	private UserDao usrDao;
	private LocationDao locDao;
	private TravelDao travDao;
	private GroupDao grpDao;
	
	public BookTravelControl() {
		this.userBean = new UserDataBean();
		this.grpModel = new GroupModel();
		this.usrDao = new UserDao();
		this.locDao = new LocationDao();
		this.logUser = new LoggedUser();
		this.travDao = new TravelDao();
		this.grpDao = new GroupDao();
	}
	
	public List<String> showLocationsControl() { /*Shall change this String[] into a location bean*/
		List<String> suggLoc = new ArrayList<>();
		userBean.setPersonality(logUser.getPersonality());
		userBean.setUserName(logUser.getUserName());
		suggLoc.addAll(this.usrDao.getLocations(userBean));
		return suggLoc;
	}
	
	public void getGroupsControl(GroupBean grpBean , List<GroupBean> beanList) {
		this.logUser.getPersonality();
		grpModel.getGroups(grpBean, beanList);
	}
	
	public void retriveLocInfoControl(LocationBean bean) {
		this.locDao.retriveLocationInfo(bean);
	}
	
	public int retriveTravelSolutions(UserTravelBean travBean, List<UserTravelBean> travList) {
		List<TicketModel> tickList = new ArrayList<>();
		int i = 0;
		try {
			if(travBean.getFirstDay().compareTo(travBean.getLastDay()) > 0) {
				return -1;
			}
			else {
				LoggedUser logUsr = new LoggedUser();
				UserDataBean usrDataBean = new UserDataBean();
				usrDataBean.setUserName(logUsr.getUserName());
				tickList.addAll(travDao.retriveAvailableTickets(travBean, usrDataBean));
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
	
	public void saveBoughtTicket(UserTravelBean travBean , UserDataBean dataBean) {
		TicketModel tick = new TicketModel();
		tick.setAll(travBean.getId(), travBean.getCityOfDep(), travBean.getCityOfArr(), travBean.getFirstDay(), travBean.getLastDay(), travBean.getCost());
		this.travDao.saveBoughtTickets(tick, dataBean);
	}
	
	public void getBookedTickets(List<UserTravelBean> travBeanList, UserDataBean dataBean) {
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
	
	public int saveGroup(GroupBean grpBean) {
		return this.grpDao.saveUserGroup(grpBean);
	}
	
	public void getUserGroups(List<GroupBean> grpBean, UserDataBean dataBean) {
		List<GroupModel> grpList = new ArrayList<>();
		grpDao.getUserGroups(grpList, dataBean);
		int i;
		for(i = 0; i < grpList.size(); i++) {
			GroupBean grpbean = new GroupBean();
			grpbean.setGroupTitle(grpList.get(i).getDescription());
			grpbean.setGroupDestination(grpList.get(i).getDestination());
			grpbean.setGroupOwner(grpList.get(i).getOwner());
			grpBean.add(grpbean);
		}
	}
	
	public List<UserTravelBean> getSuggTicketsInfo(UserTravelBean travBean) {
		List<TicketModel> tickList = new ArrayList<>();
		List<UserTravelBean> travList = new ArrayList<>();
		this.travDao.getSuggestedTickets(travBean, tickList);
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
	
	public int insertParticipant(GroupBean bean, UserDataBean dataBean) {
		return this.grpDao.insertParticipant(bean, dataBean);
	}
	
	public void deleteSavedTravel(UserTravelBean travBean, UserDataBean dataBean) {
		TicketModel tickModel = new TicketModel();
		tickModel.setId(travBean.getId());
		this.travDao.deleteTick(tickModel, dataBean);
	}
	
	public void deleteTravelGroup(GroupBean grpBean) {
		this.grpDao.deleteGroup(grpBean);
	}
	
	public void leaveTravelGroup(GroupBean grpBean, UserDataBean dataBean) {
		this.grpDao.leaveJoinedGroup(grpBean, dataBean);
	}
}

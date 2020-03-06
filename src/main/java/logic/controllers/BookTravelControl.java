/* IMPORTANT : IS BETTER TO CHANGE THE RETURN VALUE OF THE FUNCTION WITH THE THORWS OF AN EXCEPTION, WHICH WILL BE
 * HANDLED IN THE GRAPHIC CONTROLLER AND WILL DISPLAY AN ERROR MESSAGE*/

package logic.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
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
	
	public BookTravelControl() {
		this.userBean = new UserDataBean();
		this.grpModel = new GroupModel();
		this.usrDao = new UserDao();
		this.locDao = new LocationDao();
		this.logUser = new LoggedUser();
		this.travDao = new TravelDao();
	}
	
	public List<String> showLocationsControl() { /*Shall change this String[] into a location bean*/
		List<String> suggLoc = new ArrayList<>();
		userBean.setPersonality(logUser.getPersonality());
		userBean.setUserName(logUser.getUserName());
		suggLoc.addAll(this.usrDao.getLocations(userBean));
		return suggLoc;
	}
	
	public void getGroupsControl(GroupBean[] grpBean) {
		this.logUser.getPersonality();
		grpModel.getGroups(grpBean, this.userBean);
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
				tickList.addAll(travDao.retriveAvailableTickets(travBean));
				if(tickList.isEmpty()) {
					return -1;
				}
				else {
					TicketModel tickIndex = new TicketModel(); // temporary variable to get the list's tickets
					UserTravelBean bean = new UserTravelBean();
					while(i < tickList.size()) {
						tickIndex = tickList.get(i);
						bean.setFirstDay(tickIndex.getDepDay());
						bean.setLastDay(tickIndex.getArrDay());
						bean.setArrCity(tickIndex.getArrCity());
						bean.setDepCity(tickIndex.getDepCity());
						bean.setCost(tickIndex.getMoney());
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
}

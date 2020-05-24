package logic.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.LocationBean;
import logic.beans.UserTravelBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.BigDateException;
import logic.exceptions.EmptyListException;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;

@WebServlet("/BookTravelServlet")
public class BookTravelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String msg = "bookmessage";
	private static String hp = "HomePage.jsp";
	private static String booktrav = "BookTravelStart.jsp";
	private final ControllerFacade facCtrl;
	private final JspChangePage changeP;
	
	public BookTravelServlet() {
		facCtrl = new ControllerFacade();
		changeP = new JspChangePage();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserTravelBean travBean = new UserTravelBean();
		if(request.getParameter("checkSol") != null) {
			setTick(request, travBean);
			if(request.getAttribute(msg) != null) {
				setInfo(request, 1);
				changeP.forwardPage(booktrav, request, response);
			}
			else {
				List<UserTravelBean> travBeanList = new ArrayList<>();
				findTravels(travBean, travBeanList, request);
				if(request.getAttribute(msg) != null) {
					setInfo(request, 1);
					changeP.forwardPage(booktrav, request, response);
				}
				else {
					changeP.forwardPage("TicketsSolutions.jsp", request, response);
				}
			}
		}
		else if(request.getParameter("savetick") != null) {
			saveMyTicket(request, response, travBean);
		}
		else if(request.getParameter("savegroup") != null) {
			saveMyGroup(request, response);
		}
		else if(request.getParameter("savebookgroup") != null) {
			saveBookedGroup(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String dest = booktrav;
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("goconf")) {
			UserTravelBean travBean = new UserTravelBean();
			setTick(request, travBean);
			request.setAttribute("tick", travBean);
			dest = "BuyTicket.jsp";
		}
		else if(action.equalsIgnoreCase("delTick")) {
			deleteTicket(request);
			setInfo(request, 0);
			dest=hp;
		}
		else if(action.equalsIgnoreCase("delGroup")) {
			deleteGroup(request);
			setInfo(request, 0);
			dest=hp;
		}
		else if(action.equalsIgnoreCase("joinGroup")) {
			joinGroup(request);
			setInfo(request, 1);
		}
		else if(action.equalsIgnoreCase("moreInfo")) {
			loadLocInfo(request);
			dest="LocationInfo.jsp";
		}
		else if(action.equalsIgnoreCase("bookshort")) {
			UserTravelBean travBean = new UserTravelBean(request.getParameter("city"));
			findSuggTravels(request, travBean);
			dest="TicketsSolutions.jsp";
			if(request.getAttribute(msg) != null) {
				setInfo(request, 1);
				dest=booktrav;
			}
		}
		else if(action.equalsIgnoreCase("creategroup")) {
			dest = "CreateGroup.jsp";
		}
		changeP.forwardPage(dest, request, response);
	}
	
	private void setTick(HttpServletRequest request, UserTravelBean travBean) {
		try {
			travBean.setDepCity(request.getParameter("depCity"));
			travBean.setArrCity(request.getParameter("arrCity"));
			travBean.setFirstDay(request.getParameter("depDate"));
			travBean.setLastDay(request.getParameter("retDate"));
			if(request.getParameter("id") != null && request.getParameter("cost") != null) {
				travBean.setId(request.getParameter("id"));
				travBean.setCost(request.getParameter("cost"));
			}
		} catch (NullValueException e) {
			request.setAttribute(msg, e.getNullExcMsg());
		}
	}
	
	private void deleteTicket(HttpServletRequest request) {
		UserTravelBean travBean = new UserTravelBean();
		try {
			travBean.setId(Integer.valueOf(request.getParameter("id")));
			this.facCtrl.deleteSavedTravel(travBean);
		}catch(NumberFormatException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
	
	private void deleteGroup(HttpServletRequest request) {
		GroupBean bean = new GroupBean(request.getParameter("groupName"), request.getParameter("groupOwner"));
		LoggedUser logUsr = new LoggedUser();
		if(request.getParameter("groupOwner").equalsIgnoreCase(logUsr.getUserName())){ //can subistute with session
			this.facCtrl.deleteTravelGroup(bean);
		}
		else {
			this.facCtrl.leaveTravelGroup(bean);
		}
	}
	
	private void joinGroup(HttpServletRequest request) {
		GroupBean gBean = new GroupBean(request.getParameter("descr"));
		this.facCtrl.insertParticipant(gBean);
	}
	
	private void loadLocInfo(HttpServletRequest request) {
		LocationBean locBean = new LocationBean();
		locBean.setCityName(request.getParameter("city"));
		this.facCtrl.retriveLocInfo(locBean);
		request.setAttribute("location", locBean);
	}
	
	private void findSuggTravels(HttpServletRequest request, UserTravelBean travBean) {
		List<UserTravelBean> travBeanList = new ArrayList<>();
		try {
			travBeanList.addAll(this.facCtrl.getSuggTicketsInfo(travBean));
			request.setAttribute("tickets", travBeanList);
		}
		catch(EmptyListException e) {
			request.setAttribute(msg, "Sorry! No travels available for the request city");
		}
	}
	
	private void findTravels(UserTravelBean travBean, List<UserTravelBean> travBeanList, HttpServletRequest request) {
		try {
			this.facCtrl.retriveTravelSolutions(travBean, travBeanList);
			request.setAttribute("tickets", travBeanList);
		}
		catch(EmptyListException e) {
			request.setAttribute(msg, "Sorry, no tickets to be shown for the selcted destination/dates");
		}
		catch(BigDateException e) {
			request.setAttribute(msg, e.getMessage());
		}
	}
	
	private boolean saveGroup(HttpServletRequest request) {
		try {
			ControllerFacade facCtr = new ControllerFacade();
			GroupBean gBean = new GroupBean();
			gBean.setGroupOwner(request.getParameter("owner"));
			setGroupDatas(request, gBean);
			if(request.getAttribute(msg) == null) {
				request.setAttribute(msg, "Group correctly saved");
				facCtr.saveGroup(gBean);
				return true;
			}
		}
		catch(GroupNameTakenException e) {
			request.setAttribute(msg, "Group name already taken, please choose another");
		} 
		return false;
	}
	
	private void setGroupDatas(HttpServletRequest request, GroupBean gBean) {
		try {
			gBean.setGroupTitle(request.getParameter("groupname"));
			gBean.setGroupDestination(request.getParameter("dest"));
		}catch(NullValueException e) {
			request.setAttribute(msg, e.getNullExcMsg());
		} catch (LengthFieldException e) {
			request.setAttribute(msg, e.getMsg());
		}
	}
	
	private void saveMyTicket(HttpServletRequest request, HttpServletResponse response, UserTravelBean travBean) {
		setTick(request, travBean);
		this.facCtrl.saveBoughtTicket(travBean);
		setInfo(request, 0);
		changeP.forwardPage("HomePage.jsp", request, response);
	}
	
	private void saveMyGroup(HttpServletRequest request, HttpServletResponse response) {
		if(!saveGroup(request)) {
			changeP.forwardPage("CreateGroup.jsp", request, response);
		}
		else {
			setInfo(request, 0);
			changeP.forwardPage(hp, request, response);
		}
	}
	
	private void saveBookedGroup(HttpServletRequest request, HttpServletResponse response) {
		saveGroup(request);
		UserTravelBean travBean = new UserTravelBean();
		setTick(request, travBean);
		request.setAttribute("tick", travBean);
		changeP.forwardPage("BuyTicket.jsp", request, response);
	}
	
	private void setInfo(HttpServletRequest request, int code) {
		HomePageServlet hpServ = new HomePageServlet();
		if(code == 0) {
			hpServ.loadHomePageUserInfo(request);
		}
		else if (code == 1) {
			hpServ.loadBookTravelSugg(request);
		}
	}
}

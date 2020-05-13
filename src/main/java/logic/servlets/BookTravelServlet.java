package logic.servlets;

import java.io.IOException;
import java.time.LocalDate;
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

@WebServlet("/BookTravelServlet")
public class BookTravelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String defPage = "BookTravelStart.jsp";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserTravelBean travBean = new UserTravelBean();
		ChangePageServlet changeP = new ChangePageServlet();
		ControllerFacade facCtr = new ControllerFacade();
		travBean.setDepCity(request.getParameter("depCity"));
		travBean.setArrCity(request.getParameter("arrCity"));
		travBean.setFirstDay(LocalDate.parse(request.getParameter("depDate")));
		travBean.setLastDay(LocalDate.parse(request.getParameter("retDate")));
		if(request.getParameter("checkSol") != null) {
			List<UserTravelBean> travBeanList = new ArrayList<>();
			facCtr.retriveTravelSolutions(travBean, travBeanList);
			request.setAttribute("tickets", travBeanList);
			if(travBeanList.isEmpty()) {
				request.setAttribute("alreadybought", "Sorry, no tickets to be shown for the selcted destination/dates");
				HomePageServlet hpServ = new HomePageServlet();
				hpServ.loadBookTravelSugg(request);
				changeP.forwardPage(defPage, request, response);
			}
			else {
				changeP.forwardPage("TicketsSolutions.jsp", request, response);
			}
		}
		else if(request.getParameter("savetick") != null) {
			HomePageServlet hpServ = new HomePageServlet();
			setTick(request, travBean);
			facCtr.saveBoughtTicket(travBean);
			hpServ.loadHomePageUserInfo(request);
			changeP.forwardPage("HomePage.jsp", request, response);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String dest = "HomePage.jsp";
		UserTravelBean travBean = new UserTravelBean();
		String action = request.getParameter("action");
		ChangePageServlet changeP = new ChangePageServlet();
		if(action.equalsIgnoreCase("goconf")) {	
			setTick(request, travBean);
			request.setAttribute("tick", travBean);
			dest = "BuyTicket.jsp";
		}
		else if(action.equalsIgnoreCase("delTick")) {
			deleteTicket(request);
			HomePageServlet hpServ = new HomePageServlet();
			hpServ.loadHomePageUserInfo(request);
		}
		else if(action.equalsIgnoreCase("delGroup")) {
			deleteGroup(request);
			HomePageServlet hpServ = new HomePageServlet();
			hpServ.loadHomePageUserInfo(request);
		}
		else if(action.equalsIgnoreCase("joinGroup")) {
			joinGroup(request);
			dest = defPage;
			HomePageServlet hpServ = new HomePageServlet();
			hpServ.loadBookTravelSugg(request);
		}
		else if(action.equalsIgnoreCase("moreInfo")) {
			loadLocInfo(request);
			dest="LocationInfo.jsp";
		}
		else if(action.equalsIgnoreCase("bookshort")) {
			travBean.setArrCity(request.getParameter("city"));
			findSuggTravels(request, travBean);
			dest="TicketsSolutions.jsp";
			if(request.getAttribute("notravels") != null) {
				HomePageServlet hpServ = new HomePageServlet();
				hpServ.loadBookTravelSugg(request);
				dest = defPage;
			}
		}
		changeP.forwardPage(dest, request, response);
	}
	
	private void setTick(HttpServletRequest request, UserTravelBean travBean) {
		try {
			travBean.setId(Integer.parseInt(request.getParameter("id")));
			travBean.setDepCity(request.getParameter("depCity"));
			travBean.setArrCity(request.getParameter("arrCity"));
			travBean.setFirstDay(LocalDate.parse(request.getParameter("depDate")));
			travBean.setLastDay(LocalDate.parse(request.getParameter("retDate")));
			travBean.setCost(Float.parseFloat(request.getParameter("cost")));
		}catch(NumberFormatException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
	
	private void deleteTicket(HttpServletRequest request) {
		ControllerFacade fac = new ControllerFacade();
		UserTravelBean travBean = new UserTravelBean();
		try {
			travBean.setId(Integer.valueOf(request.getParameter("id")));
			fac.deleteSavedTravel(travBean);
		}catch(NumberFormatException e) {
			Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
		}
	}
	
	private void deleteGroup(HttpServletRequest request) {
		ControllerFacade fac = new ControllerFacade();
		GroupBean bean = new GroupBean();
		bean.setGroupTitle(request.getParameter("groupName"));
		bean.setGroupOwner(request.getParameter("groupOwner"));
		LoggedUser logUsr = new LoggedUser();
		if(request.getParameter("groupOwner").equalsIgnoreCase(logUsr.getUserName())){
			fac.deleteTravelGroup(bean);
		}
		else {
			fac.leaveTravelGroup(bean);
		}
	}
	
	private void joinGroup(HttpServletRequest request) {
		GroupBean gBean = new GroupBean();
		gBean.setGroupTitle(request.getParameter("descr"));
		ControllerFacade fac = new ControllerFacade();
		fac.insertParticipant(gBean);
	}
	
	private void loadLocInfo(HttpServletRequest request) {
		LocationBean locBean = new LocationBean();
		locBean.setCityName(request.getParameter("city"));
		ControllerFacade fac = new ControllerFacade();
		fac.retriveLocInfo(locBean);
		request.setAttribute("location", locBean);
	}
	
	private void findSuggTravels(HttpServletRequest request, UserTravelBean travBean) {
		List<UserTravelBean> travBeanList = new ArrayList<>();
		ControllerFacade fac = new ControllerFacade();
		travBeanList.addAll(fac.getSuggTicketsInfo(travBean));
		if(travBeanList.isEmpty()) {
			request.setAttribute("notravels", "Sorry! No travels available for the request city");
		}
		request.setAttribute("tickets", travBeanList);
	}
}

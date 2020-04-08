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

import logic.beans.UserTravelBean;
import logic.controllers.ControllerFacade;

@WebServlet("/BookTravelServlet")
public class BookTravelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
			changeP.forwardPage("TicketsSolutions.jsp", request, response);
		}
		else if(request.getParameter("savetick") != null) {
			setTick(request, travBean);
			facCtr.saveBoughtTicket(travBean);
			changeP.loadHomePageUserInfo(request);
			changeP.forwardPage("HomePage.jsp", request, response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		UserTravelBean travBean = new UserTravelBean();
		ChangePageServlet changeP = new ChangePageServlet();
		if(request.getParameter("action").equalsIgnoreCase("goconf")) {	
			setTick(request, travBean);
			request.setAttribute("tick", travBean);
			changeP.forwardPage("BuyTicket.jsp", request, response);
		}
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
}

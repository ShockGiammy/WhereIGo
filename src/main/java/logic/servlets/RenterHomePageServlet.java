package logic.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.UserTravelBean;
import logic.controllers.ControllerFacade;

@WebServlet("/RenterHomePageServlet")
public class RenterHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChangePageServlet changeP = new ChangePageServlet();
		String act = request.getParameter("action");
		String page = null;
		if(act.equalsIgnoreCase("gohome")) {
			//changeP.loadHomePageUserInfo(request);
			page = "HomePage.jsp";	
		}
		else if(act.equalsIgnoreCase("RenterAccomodation")) {
			page = "rent";
		}
		else if(act.equalsIgnoreCase("ChatRenter")) {
			page =	"ChatRenter";
		}
		else if(act.equalsIgnoreCase("delTick")) {
			ControllerFacade fac = new ControllerFacade();
			UserTravelBean travBean = new UserTravelBean();
			try {
				travBean.setId(Integer.valueOf(request.getParameter("id")));
				fac.deleteSavedTravel(travBean);
				page = "HomePage.jsp";
				changeP.loadHomePageUserInfo(request);
			}catch(NumberFormatException e) {
				Logger.getLogger("WIG").log(Level.SEVERE, e.getMessage());
			}
		}
		changeP.forwardPage(page, request, response);
	}
}

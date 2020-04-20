package logic.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.LoggedUser;
import logic.UserType;
import logic.beans.GroupBean;
import logic.controllers.ControllerFacade;

@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoggedUser logusr = new LoggedUser();
		ChangePageServlet changeP = new ChangePageServlet();
		String act = request.getParameter("action");
		String page = null;
		if(act.equalsIgnoreCase("gohome")) {
			if(logusr.getUserType() == UserType.RENTER) {
				//carica la home page del renter
			}
			else {
				changeP.loadHomePageUserInfo(request);
				page = "HomePage.jsp";
			}
		}
		else if(act.equalsIgnoreCase("gobooktravel")) {
			page = "BookTravelStart.jsp";
			 loadBookTravelSugg(request);
		}
		else if(act.equalsIgnoreCase("Rent")) {
			if(logusr.getUserType() == UserType.RENTER) {
				page = "RentRenter";
			}
			else {
				page = "RentTraveller";
			}
		}
		else if(act.equalsIgnoreCase("Chat")) {
			if(logusr.getUserType() == UserType.RENTER) {
				page =	"ChatRenter";
			}
			else {
				page =	"ChatTraveller";
			}
		}
		else if(act.equalsIgnoreCase("personality")) {
			page="PersonalityTest.jsp";
		}
		else if(act.equalsIgnoreCase("exit")) {
			HttpSession session = request.getSession();
			session.removeAttribute("image");
			page = "Login.jsp";
		}
		changeP.forwardPage(page, request, response);
	}
	
	public void loadBookTravelSugg(HttpServletRequest request) {
		ControllerFacade fac = new ControllerFacade();
		List<String> cities = new ArrayList<>();
		List<GroupBean> beanList = new ArrayList<>();
		fac.loadBookTravSuggestion(cities, beanList);
		request.setAttribute("grouplist", beanList);
		request.setAttribute("cities", cities);
		if(beanList.isEmpty() && cities.isEmpty()) {
			request.setAttribute("fineMsg", "No suggestions for you, please take our personality test!");
		}
	}
}

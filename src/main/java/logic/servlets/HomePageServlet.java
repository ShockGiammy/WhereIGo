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
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.ControllerFacade;

@WebServlet("/HomePageServlet")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JspChangePage changeP = new JspChangePage();
		String act = request.getParameter("action");
		String page = null;
		if(act.equalsIgnoreCase("gohome")) {
			if(LoggedUser.getUserType() == UserType.RENTER) {
				page = "RenterHomePage.jsp";
			}
			else {
				loadHomePageUserInfo(request);
				page = "HomePage.jsp";
			}
		}
		else if(act.equalsIgnoreCase("gobooktravel")) {
			page = "BookTravelStart.jsp";
			loadBookTravelSugg(request);
		}
		else if(act.equalsIgnoreCase("Rent")) {
			if(LoggedUser.getUserType() == UserType.RENTER) {
				page = "RentRenter";
			}
			else {
				page = "RentTraveller";
			}
		}
		else if(act.equalsIgnoreCase("Chat")) {
			if(LoggedUser.getUserType() == UserType.RENTER) {
				page =	"ChatRenter";
			}
			else {
				page =	"ChatTraveller";
			}
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
		List<String> depCities = new ArrayList<>(); 
		List<String> arrCities = new ArrayList<>();
		List<String> cities = new ArrayList<>();
		List<GroupBean> beanList = new ArrayList<>();
		fac.getAvailableTick(depCities, arrCities);
		setDepAndArr(request, depCities, arrCities);
		if(LoggedUser.getPersonality() != null) {
			fac.findTravelSugg(cities);
			fac.findSuggGroups(beanList);
		}
		else {
			request.setAttribute("bookmessage", "No suggestions for you, please take our test");
		}
		request.setAttribute("grouplist", beanList);
		request.setAttribute("cities", cities);
	}
	
	public void loadHomePageUserInfo(HttpServletRequest request) {
		ControllerFacade facCtrl = new ControllerFacade();
		List<UserTravelBean> travBeanList = new ArrayList<>();
		List<GroupBean> gBeanList = new ArrayList<>();
		List<UserDataBean> dBeanList = new ArrayList<>();
		facCtrl.getSimilarUsers(dBeanList);
		facCtrl.getBookedTicks(travBeanList);
		facCtrl.getUsersGroups(gBeanList);
		request.setAttribute("travels", travBeanList);
		request.setAttribute("groups", gBeanList);
		request.setAttribute("users", dBeanList);
	}
	
	private void setDepAndArr(HttpServletRequest request, List<String> depCities, List<String> arrCities) {
		request.setAttribute("arrcit", arrCities);
		request.setAttribute("depcit", depCities);
	}
}

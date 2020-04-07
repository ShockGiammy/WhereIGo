package logic.servlets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.RentAccomodationBean;
import logic.controllers.ControllerFacade;

@WebServlet("/rent")
public class RentAccomodationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		ControllerFacade facade = new ControllerFacade();
		List<RentAccomodationBean> listOfBean = facade.displayAnnouncement();
		req.setAttribute("list", listOfBean);
		
		ChangePageServlet changeP = new ChangePageServlet();
		String page =	"RentAnAccomodation.jsp";
		changeP.forwardPage(page, req, resp);
		
		
		String act = req.getParameter("action");
		if(act.equalsIgnoreCase("ContactRenter")) {
			String pagejsp =	"chatRenter";
			changeP.forwardPage(pagejsp, req, resp);
		}
	}
}

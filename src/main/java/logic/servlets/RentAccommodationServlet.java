package logic.servlets;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.beans.AccommodationBean;
import logic.controllers.ControllerFacade;

@WebServlet("/RentTraveller")
public class RentAccommodationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		ControllerFacade facade = new ControllerFacade();
		
		JspChangePage changeP = new JspChangePage();
		String page = null;
		
		String act = req.getParameter("action");
		if(act.equalsIgnoreCase("ContactRenter")) {
			facade.createChat(req.getParameter("renter"));
			page = "ChatTraveller";
		}
		else if (act.equalsIgnoreCase("Rent")) {
			List<AccommodationBean> listOfBean = facade.retrieveAnnouncement();
			req.setAttribute("list", listOfBean);
			page = "RentAnAccommodation.jsp";
		}
		changeP.forwardPage(page, req, resp);	
	}
}
